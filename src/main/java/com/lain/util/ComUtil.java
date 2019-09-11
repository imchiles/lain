package com.lain.util;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lain.dao.DeviceIpMapper;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

/**
 * COM ��ͨѶ��ʵ���� 
 */
public class ComUtil {
	/** �� MAP ���� SerialPort �����Ϣ */
	public static Map<String, SerialPort> map = new HashMap<String, SerialPort>();
	public static ComUtil comUtil;
	/**�ı�ͨѶ״̬ */

	private static DeviceIpMapper devcieMapper;
	
	public static CommPortIdentifier commPortIdentifier;
	/** ��COM���߳� */
	public boolean openCom(String ipPort){
		boolean flag = false;
		//ipPort���в��
		String[] str = ipPort.split(":");
		String st = str[0].substring(0,4);
		try {
			//COM�Ķ˿ں�
			commPortIdentifier = CommPortIdentifier.getPortIdentifier(st);
			SerialPort serialPort = (SerialPort) commPortIdentifier.open(st, 5000);
			serialPort.setSerialPortParams(Integer.parseInt(str[1]), SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			flag = true;
			//�������飬����ipPortȡ��
			map.put(ipPort, serialPort);
			devcieMapper.updIpConnectStatus(1,1,st,Integer.parseInt(str[1]));
		} catch (Exception e) {
			devcieMapper.updIpConnectStatus(0,0,st,Integer.parseInt(str[1]));
			e.printStackTrace();
		}
		return flag;
	}
	
	/** �ر�COM���߳� */
	public boolean closeCom(String ipPort){
		boolean flag = true;
		try {
			if(map.get(ipPort).getOutputStream() != null)
				map.get(ipPort).getOutputStream().close();
			if(map.get(ipPort).getInputStream() != null)
				map.get(ipPort).getInputStream().close();
			if(map.get(ipPort) != null)
				map.get(ipPort).close();
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/** ����COM���߳� */
	public void restart(String ipPort){
		if(comUtil == null){
			comUtil = new ComUtil();
			closeCom(ipPort);
			comUtil.openCom(ipPort);
		} else {
			closeCom(ipPort);
			comUtil.openCom(ipPort);
		}
	}
	
	/** �������ݵ��� */
	public synchronized byte[] sendOrder(byte[] byt,String ipPort){
		byte[] byteBuffer = null;
		try {
			map.get(ipPort).getOutputStream().write(byt, 0, byt.length);
			map.get(ipPort).getOutputStream().flush();
			Thread.sleep(1000);
			int count = map.get(ipPort).getInputStream().available();
			if(count > 0){
				byteBuffer = new byte[count];
				map.get(ipPort).getInputStream().read(byteBuffer);
			}
		} catch (IOException e) {
			restart(ipPort);
			System.out.println(ipPort+"-------socketд���쳣--------");
		} catch(NullPointerException e){
			restart(ipPort);
			System.out.println(ipPort+"-------socket��ָ���쳣--------");
		} catch (Exception e) {
			restart(ipPort);
			System.out.println(ipPort+"-------�����쳣--------");
		}
		return byteBuffer;
	}
	
	/** ȡ������ */
	public byte[] getOrder(String ipPort){
		
		byte[] byteBuffer = null;
		try {
			Thread.sleep(100);
			int count = map.get(ipPort).getInputStream().available();
			if(count != 0){
				byteBuffer = new byte[count];
				map.get(ipPort).getInputStream().read(byteBuffer);
			}
		} catch (InterruptedException e) {
			restart(ipPort);
			System.out.println(ipPort+"-------�ж��쳣--------");
		} catch (IOException e) {
			restart(ipPort);
			System.out.println(ipPort+"-------socket��ȡ�쳣--------");
		}
		return byteBuffer;
	}

	public static DeviceIpMapper getDevcieMapper() {
		return devcieMapper;
	}

	public static void setDevcieMapper(DeviceIpMapper devcieMapper) {
		ComUtil.devcieMapper = devcieMapper;
	}

}
