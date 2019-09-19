package com.lain.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.lain.analysis.Analysis;
import com.lain.dao.Co2Mapper;
import com.lain.dao.DeviceIpMapper;
import com.lain.dao.ElectricmeterMapper;
import com.lain.dao.HumitureMapper;
import com.lain.dao.Ktr8052Mapper;
import com.lain.dao.Ktr8060Mapper;
import com.lain.dao.LocationMapper;
import com.lain.dao.PoisonousMapper;
import com.lain.entity.DeviceAlarm;
import com.lain.entity.DeviceIp;
import com.lain.entity.HumitureManage;
import com.lain.master.Co2OrderRead;
import com.lain.master.ElectricmeterOrderRead;
import com.lain.master.HumitureOrderRead;
import com.lain.master.KTR8052OrderRead;
import com.lain.master.LocationOrderRead;
import com.lain.master.PoisonousOrderRead;

public class MainSerialPort implements InitializingBean, ServletContextAware{


	public static Map<String,ExecutorService> threadMap = new HashMap<String,ExecutorService>();
	public static Map<String,Boolean> ipPortMap = new HashMap<String,Boolean>();
	public static ExecutorService singleThreadExecutor = null;
	public static Calculagraph time = new Calculagraph();
	
	@Autowired
	private DeviceIpMapper deviceIpMapper;

	@Autowired
	private HumitureMapper humitureMapper;

	@Autowired
	private Ktr8052Mapper ktr8052Mapper;
	
	@Autowired
	private Ktr8060Mapper ktr8060Mapper;
	
	@Autowired
	private ElectricmeterMapper electricmeterMapper;
	
	@Autowired
	private LocationMapper locationMapper;
	
	@Autowired
	private PoisonousMapper poisonousMapper;
	
	@Autowired
	private Co2Mapper co2Mapper;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		/** 注入bean */
		SetBean();
		/**    IP初始化      **/
		deviceIpMapper.updIpConnectStatus(0,0,null,0);
		/** 温湿度上下限 */
		HumTempValue();
		List<DeviceAlarm> listda = deviceIpMapper.findDeviceAlarmAll();
		AlarmTime at = new AlarmTime();
		at.setAlarmInformation(listda);
		List<DeviceIp> ipPort = deviceIpMapper.findDeviceIpAll();
		for (DeviceIp deviceIp : ipPort) {
			List<byte[]> orders = new ArrayList<byte[]>();
			int size = 0;
			List<Integer> items = new ArrayList<>();
			switch (deviceIp.getdId()) {
			case 1: //温湿度
				items = humitureMapper.findHumitureAddress(deviceIp.getDiId());
				for(Integer address : items){
					byte[] back = Analysis.getHumitureOrder(address);
					orders.add(back);
				}
				size = items.size();
				break;
			case 2://8052
				items = ktr8052Mapper.findKtr8052Address(deviceIp.getDiId());
				for(Integer address : items){
					byte[] back = Analysis.getKtr8052Order(address);
					orders.add(back);
				}
				size = items.size();
				System.out.println(size);
				break;
			case 3://8060
				Alarm.setKtr8060Mapper(ktr8060Mapper);
				break;
			case 4://电量仪
				items = electricmeterMapper.findElectricmeterById(deviceIp.getDiId());
				for(Integer address : items){
					byte[] back = Analysis.getElectricMeterOrder(address);
					orders.add(back);
				}
				size = items.size();
				break;
			case 9://定位漏水
				//items = 定位漏水寻找DiId
				try {
					items = locationMapper.findLocationAddress(deviceIp.getDiId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(Integer address : items){
					byte[] back = Analysis.getLocationOrder(address);
					orders.add(back);
				}
				size = items.size();
				break;
			case 10://二氧化碳
				items = co2Mapper.findCo2Address(deviceIp.getDiId());
				for(Integer address : items){
					byte[] back = Analysis.getCo2Order(address);
					orders.add(back);
				}
				size = items.size();
				break;
			case 12://有毒气体
				items = poisonousMapper.findPoisonousAddress(deviceIp.getDiId());
				for(Integer address : items){
					byte[] back = Analysis.getPoisonousOrder(address);
					orders.add(back);
				}
				size = items.size();
				break;
			default: 
				System.out.println("程序出错");
				break;
			}
			if(size!=0) {
				IpConnect.connectIp(deviceIp.getDiAddress(), deviceIp.getDiPort(),orders, deviceIp.getDiId(), deviceIp.getdId(),deviceIp.getgId());
				time.StatrTime();
				System.out.println("进入线程");
			} else {
				System.out.println("更新状态");
			}
		}
	}
	@Override
	public void afterPropertiesSet() throws Exception {

	}
	
	/** 温湿度上下限 */
	public void HumTempValue(){
		List<HumitureManage> lhm = humitureMapper.findHumitureManageAll();
		for(HumitureManage hm : lhm){
			List<Double> list = new ArrayList<>();
			list.add(hm.getEhmMaxTem());
			list.add(hm.getEhmMinTem());
			list.add(hm.getEhmMaxHum());
			list.add(hm.getEhmMinHum());
			HumitureOrderRead.VALUE.put(hm.getEhmDeviceAddress()+":"+hm.getDiId(), list);
		}
	}
	
	/**
	 * 在下一层开始，bean无法注入，所以临时解决方案就是自己注入
	 */
	public void SetBean(){
		IpConnect.setDeviceIpMapper(deviceIpMapper);
		IpConnect.setHumitureMapper(humitureMapper);
		IpConnect.setCo2Mapper(co2Mapper);
		IpConnect.setElectricmeterMapper(electricmeterMapper);
		IpConnect.setKtr8052Mapper(ktr8052Mapper);
		IpConnect.setPoisonousMapper(poisonousMapper);
		IpConnect.setLocationMapper(locationMapper);
		IpConnect.setKtr8060Mapper(ktr8060Mapper);
		
		HumitureOrderRead.setHumitureMapper(humitureMapper);
		ElectricmeterOrderRead.setElectricmeterMapper(electricmeterMapper);
		KTR8052OrderRead.setKtr8052Mapper(ktr8052Mapper);
		ComUtil.setDevcieMapper(deviceIpMapper);
		SocketUtil.setDeviceIpMapper(deviceIpMapper);
		LocationOrderRead.setLocationMapper(locationMapper);
		PoisonousOrderRead.setPoisonousMapper(poisonousMapper);
		Co2OrderRead.setCo2Mapper(co2Mapper);
	}
	public static void main(String[] args) {
	}
}	