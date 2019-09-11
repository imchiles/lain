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
import com.lain.dao.DeviceIpMapper;
import com.lain.dao.HumitureMapper;
import com.lain.entity.DeviceIp;
import com.lain.master.SocketSendOrder;
import com.lain.util.ComUtil;
import com.lain.util.SocketUtil;

@Controller
@RequestMapping("/")
public class IpConnect {

	public static DeviceIpMapper deviceIpMapper;
	public static HumitureMapper humitureMapper;
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
		switch (deviceIp.getdId()) {
		case 1:
			List<Integer> items = humitureMapper.findHumitureAddress(deviceIp.getDiId());
			System.out.println(items.toString());
			for(Integer address : items){
				byte[] back = Analysis.getHumitureOrder(address);
				orders.add(back);
			}
			size = items.size();
			break;
		default:
			break;
		}
		result = connectIp(deviceIp.getDiAddress(), deviceIp.getDiPort(),orders, deviceIp.getDiId(), deviceIp.getdId(),deviceIp.getgId());

		return size==0?"û���豸���Կ���ͨѶ":(result?"�����ɹ�":"����ʧ��");
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
			singleThreadExecutor = Executors.newCachedThreadPool();	//�����̶߳���
			threadMap.put(Ip+":"+Port, singleThreadExecutor);
			ipPortMap.put(Ip+":"+Port, true);
			deviceIpMapper.updIpConnectStatus(1,1,Ip,Port);
			switch (number) {
			case 1:	//��ʪ���豸
				SocketSendOrder.humiture(Ip,Port, orders, diId);
				break;
			case 2:
				SocketSendOrder.ktr8052(Ip,Port, orders, diId);
				break;
			case 4:
				SocketSendOrder.electricmenter(Ip, Port, orders, diId);
				break;
			case 9://��λ©ˮ
				//�����������ͼ����ش���
				SocketSendOrder.location(Ip, Port, socket, orders, diId);
				break;
			default:
				break;
			}
		} else {
			/** ����ͨ�ż�����״̬*/
		}
		return result;
	}
	@RequestMapping("end")
	public void stopConnect(@RequestBody DeviceIp deviceIp){
		String ipPort = deviceIp.getDiAddress()+":"+deviceIp.getDiPort();
		/** ʹ�߳�ִ�з���ֹͣ����*/
		ipPortMap.put(ipPort, false);
		/** �ر��߳�*/
		threadMap.get(ipPort).shutdown();
		if("COM".equals(ipPort.substring(0, 3)))								/** �ر�com��ͨѶ */
			com.closeCom(deviceIp.getDiAddress()+deviceIp.getDiPort());
		else																	/** �ر�socket*/
			socket.closeSocket(deviceIp.getDiAddress(),deviceIp.getDiPort());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/** ����ͨ�ż�����״̬*/
		deviceIpMapper.updIpConnectStatus(0, 0,deviceIp.getDiAddress(),deviceIp.getDiPort());
	}
	public static void setDeviceIpMapper(DeviceIpMapper deviceIpMapper) {
		IpConnect.deviceIpMapper = deviceIpMapper;
	}
	public static void setHumitureMapper(HumitureMapper humitureMapper) {
		IpConnect.humitureMapper = humitureMapper;
	}

}	
