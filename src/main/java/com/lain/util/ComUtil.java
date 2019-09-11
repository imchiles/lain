package com.lain.util;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lain.dao.DeviceIpMapper;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

/**
 * COM 口通讯的实体类 
 */
public class ComUtil {
	/** 用 MAP 储存 SerialPort 类的信息 */
	public static Map<String, SerialPort> map = new HashMap<String, SerialPort>();
	public static ComUtil comUtil;
	/**改变通讯状态 */

	private static DeviceIpMapper devcieMapper;
	
	public static CommPortIdentifier commPortIdentifier;
	/** 打开COM口线程 */
	public boolean openCom(String ipPort){
		boolean flag = false;
		//ipPort进行拆分
		String[] str = ipPort.split(":");
		String st = str[0].substring(0,4);
		try {
			//COM的端口号
			commPortIdentifier = CommPortIdentifier.getPortIdentifier(st);
			SerialPort serialPort = (SerialPort) commPortIdentifier.open(st, 5000);
			serialPort.setSerialPortParams(Integer.parseInt(str[1]), SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			flag = true;
			//放入数组，根据ipPort取出
			map.put(ipPort, serialPort);
			devcieMapper.updIpConnectStatus(1,1,st,Integer.parseInt(str[1]));
		} catch (Exception e) {
			devcieMapper.updIpConnectStatus(0,0,st,Integer.parseInt(str[1]));
			e.printStackTrace();
		}
		return flag;
	}
	
	/** 关闭COM口线程 */
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
	
	/** 重启COM口线程 */
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
	
	/** 传输数据的类 */
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
			System.out.println(ipPort+"-------socket写入异常--------");
		} catch(NullPointerException e){
			restart(ipPort);
			System.out.println(ipPort+"-------socket空指针异常--------");
		} catch (Exception e) {
			restart(ipPort);
			System.out.println(ipPort+"-------其他异常--------");
		}
		return byteBuffer;
	}
	
	/** 取出数据 */
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
			System.out.println(ipPort+"-------中断异常--------");
		} catch (IOException e) {
			restart(ipPort);
			System.out.println(ipPort+"-------socket读取异常--------");
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
