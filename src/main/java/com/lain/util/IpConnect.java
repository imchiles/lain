package com.lain.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lain.analysis.Analysis;
import com.lain.dao.Co2Mapper;
import com.lain.dao.DeviceIpMapper;
import com.lain.dao.ElectricmeterMapper;
import com.lain.dao.HumitureMapper;
import com.lain.dao.Ktr8052Mapper;
import com.lain.dao.Ktr8060Mapper;
import com.lain.dao.LocationMapper;
import com.lain.dao.PoisonousMapper;
import com.lain.entity.DeviceIp;
import com.lain.master.Co2OrderRead;
import com.lain.master.ElectricmeterOrderRead;
import com.lain.master.HumitureOrderRead;
import com.lain.master.KTR8052OrderRead;
import com.lain.master.LocationOrderRead;
import com.lain.master.PoisonousOrderRead;
import com.lain.master.SocketSendOrder;
import com.lain.util.ComUtil;
import com.lain.util.SocketUtil;

@Controller
@RequestMapping("/")
public class IpConnect {

	public static DeviceIpMapper deviceIpMapper;
	public static HumitureMapper humitureMapper;
	public static Ktr8052Mapper ktr8052Mapper;
	public static Ktr8060Mapper ktr8060Mapper;
	public static ElectricmeterMapper electricmeterMapper;
	public static LocationMapper LocationMapper;
	public static PoisonousMapper poisonousMapper;
	public static Co2Mapper Co2Mapper;
	public static Map<String,ExecutorService> threadMap = new HashMap<String,ExecutorService>();
	public static Map<String,Boolean> ipPortMap = new HashMap<String,Boolean>();
	public static ExecutorService singleThreadExecutor = null;

	private static ComUtil com = null;
	private static SocketUtil socket = null;
	@RequestMapping("start")
	public String startConnect(@RequestBody DeviceIp deviceIp){
		List<byte[]> orders = new ArrayList<byte[]>();
		boolean result = false;
		int size = 0;
		List<Integer> items;
		switch (deviceIp.getdId()) {
		case 1:
			items = humitureMapper.findHumitureAddress(deviceIp.getDiId());
			System.out.println(items.toString());
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
			break;
		case 4:// 定量仪
			items = electricmeterMapper.findElectricmeterById(deviceIp.getDiId());
			for (Integer address : items) {
				byte[] back = Analysis.getElectricMeterOrder(address);
				orders.add(back);
			}
			size = items.size();
			break;
		case 9:// 定位漏水
				// items = 定位漏水寻找DiId
			items = LocationMapper.findLocationAddress(deviceIp.getDiId());
			for (Integer address : items) {
				byte[] back = Analysis.getLocationOrder(address);
				orders.add(back);
			}
			size = items.size();
			break;
		case 10:// 二氧化碳
			items = Co2Mapper.findCo2Address(deviceIp.getDiId());
			for (Integer address : items) {
				byte[] back = Analysis.getCo2Order(address);
				orders.add(back);
			}
			size = items.size();
			break;
		case 12:// 有毒气体
			// items = 定位漏水寻找DiId
			items = poisonousMapper.findPoisonousAddress(deviceIp.getDiId());
			for (Integer address : items) {
				byte[] back = Analysis.getPoisonousOrder(address);
				orders.add(back);
			}
			size = items.size();
			break;
		default:
			break;
		}
		result = connectIp(deviceIp.getDiAddress(), deviceIp.getDiPort(),orders, deviceIp.getDiId(), deviceIp.getdId(),deviceIp.getgId());
		return size==0?"没有设备可以开启通讯":(result?"开启成功":"开启失败");
	}
	
	public static boolean connectIp(String Ip, int Port,List<byte[]> orders,int diId,int number,long sn){
		boolean result = false;
		if("COM".equals(Ip.substring(0, 3))){
			com = new ComUtil();
			result = com.openCom(Ip+":"+Port);
		} else {
			socket = new SocketUtil();
			result = socket.openSocket(Ip, Port);
		}
		if(result){
			singleThreadExecutor = Executors.newCachedThreadPool();	//创建线程对象
			threadMap.put(Ip+":"+Port, singleThreadExecutor);
			ipPortMap.put(Ip+":"+Port, true);
			deviceIpMapper.updIpConnectStatus(1,1,Ip,Port);
			switch (number) {
			case 1:	//温湿度设备
				SocketSendOrder.humiture(Ip,Port, orders, diId);
				break;
			case 2://8052
				SocketSendOrder.ktr8052(Ip,Port, orders, diId);
				break;
			case 4://电量仪
				SocketSendOrder.electricmenter(Ip, Port, orders, diId);
				break;
			case 9://定位漏水
				SocketSendOrder.location(Ip, Port, orders, diId);
				break;
			case 10://二氧化碳
				SocketSendOrder.co2(Ip, Port, orders, diId);
			case 12://有毒气体
				SocketSendOrder.poisonous(Ip, Port, orders, diId);
				break;
			default:
				break;
			}
		} else {
			/** 更新通信及操作状态*/
		}
		return result;
	}
	@RequestMapping("end")
	public void stopConnect(@RequestBody DeviceIp deviceIp){
		String ipPort = deviceIp.getDiAddress()+":"+deviceIp.getDiPort();
		/** 使线程执行方法停止运行*/
		ipPortMap.put(ipPort, false);
		/** 关闭线程*/
		threadMap.get(ipPort).shutdown();
		if("COM".equals(ipPort.substring(0, 3)))								/** 关闭com口通讯 */
			com.closeCom(deviceIp.getDiAddress()+deviceIp.getDiPort());
		else																	/** 关闭socket*/
			socket.closeSocket(deviceIp.getDiAddress(),deviceIp.getDiPort());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/** 更新通信及操作状态*/
		deviceIpMapper.updIpConnectStatus(0, 0,deviceIp.getDiAddress(),deviceIp.getDiPort());
	}
	
	/**
	 * 临时这么解决，后面用改框架配置解决
	 * @param deviceIpMapper
	 */
	public static void setDeviceIpMapper(DeviceIpMapper deviceIpMapper) {
		IpConnect.deviceIpMapper = deviceIpMapper;
	}
	public static void setHumitureMapper(HumitureMapper humitureMapper) {
		IpConnect.humitureMapper = humitureMapper;
	}

	public static void setKtr8052Mapper(Ktr8052Mapper ktr8052Mapper) {
		IpConnect.ktr8052Mapper = ktr8052Mapper;
	}

	public static void setKtr8060Mapper(Ktr8060Mapper ktr8060Mapper) {
		IpConnect.ktr8060Mapper = ktr8060Mapper;
	}

	public static void setElectricmeterMapper(ElectricmeterMapper electricmeterMapper) {
		IpConnect.electricmeterMapper = electricmeterMapper;
	}

	public static void setLocationMapper(LocationMapper locationMapper) {
		LocationMapper = locationMapper;
	}

	public static void setPoisonousMapper(PoisonousMapper poisonousMapper) {
		IpConnect.poisonousMapper = poisonousMapper;
	}

	public static void setCo2Mapper(Co2Mapper co2Mapper) {
		Co2Mapper = co2Mapper;
	}
	
	
	

}	
