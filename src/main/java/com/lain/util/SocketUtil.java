package com.lain.util;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


import com.lain.dao.DeviceIpMapper;

public class SocketUtil {
	
	public static Map<String,Socket> SOCKET = new HashMap<String, Socket>();
	public static SocketUtil socketUtil;
	
	private static DeviceIpMapper deviceIpMapper;

	/** ��SocketͨѶ*/
	public boolean openSocket(String Ip,int Port){
		boolean flag = false;
		String ipPort = Ip + ":"+ Port;
		try {
			Socket sock = new Socket();
			SocketAddress socAddress = new InetSocketAddress(Ip,Port);
			sock.connect(socAddress,8000);
			SOCKET.put(ipPort, sock);
			flag = true;
			deviceIpMapper.updIpConnectStatus(1,1, Ip,Port);
		} catch(SocketTimeoutException e1){
			deviceIpMapper.updIpConnectStatus(0,0, Ip,Port);
			System.out.println(ipPort+"---���ӳ�ʱ");
		} catch (UnknownHostException e1) {
			deviceIpMapper.updIpConnectStatus(0,0, Ip,Port);
			System.out.println(ipPort+"---�޷�ʶ��������ַ");
		} catch (ConnectException e1) {
			deviceIpMapper.updIpConnectStatus(0,0, Ip,Port);
			System.out.println(ipPort+"---�޷�����ָ��IP��˿ں�");
		} catch (SocketException e1) {
			deviceIpMapper.updIpConnectStatus(0,0, Ip,Port);
			System.out.println(ipPort+"---socket�쳣");
		} catch (IOException e1) {
			deviceIpMapper.updIpConnectStatus(0,0, Ip,Port);
			System.out.println(ipPort+"---IO�������쳣");
		} catch (Exception e) {
			deviceIpMapper.updIpConnectStatus(0,0, Ip,Port);
			System.out.println(ipPort+"---�����쳣");
		}
		return flag;
	}
	
	/** �ر�Socketͨ��*/
	public boolean closeSocket(String Ip,int Port){
		String ipPort = Ip+":"+ Port;
		boolean flag = true;
		try {
			if(SOCKET.get(ipPort).getOutputStream() != null){
				SOCKET.get(ipPort).getOutputStream().close();
			}
			if(SOCKET.get(ipPort).getInputStream() != null){
				SOCKET.get(ipPort).getInputStream().close();
			}
			if(SOCKET.get(ipPort) != null){
				SOCKET.get(ipPort).close();
			}
		} catch (IOException e) {
			flag = false;
		}
		return flag;
	}
	
	/** ����Socket*/
	public void resetSocket(String Ip, int Port){
		if(socketUtil == null){
			socketUtil = new SocketUtil();
			closeSocket(Ip,Port);
			socketUtil.openSocket(Ip,Port);
		}else{
			closeSocket(Ip,Port);
			socketUtil.openSocket(Ip,Port);
		}
	}

	/** �򴮿ڷ���char[]����
	 * @throws InterruptedException */
	public synchronized byte[] sendOrder(byte[] byt,String Ip, int port) throws InterruptedException{
		String ipPort = Ip +":"+ port;
		byte[] byteBuffer = null;
		try {
			SOCKET.get(ipPort).getOutputStream().write(byt, 0, byt.length);
			SOCKET.get(ipPort).getOutputStream().flush();
			Thread.sleep(1000);
			int count = SOCKET.get(ipPort).getInputStream().available();
			if(count > 0){
				byteBuffer = new byte[count];
				SOCKET.get(ipPort).getInputStream().read(byteBuffer);
			}
		} catch (IOException e) {
			resetSocket(Ip,port);
			System.out.println(ipPort+"-------socketд���쳣--------");
		} catch(NullPointerException e){
			resetSocket(Ip,port);
			System.out.println(ipPort+"-------socket��ָ���쳣--------");
		} catch (Exception e) {
			resetSocket(Ip,port);
			System.out.println(ipPort+"-------�����쳣--------");
		}
		return byteBuffer;
	}

	public synchronized byte[] sendOrder1(byte[] byt,String Ip , int Port) throws InterruptedException{
		String ipPort = Ip + Port;
		byte[] byteBuffer = null;
		try {
			SOCKET.get(ipPort).getOutputStream().write(byt, 0, byt.length);
			SOCKET.get(ipPort).getOutputStream().flush();
			Thread.sleep(1500);
			int count = SOCKET.get(ipPort).getInputStream().available();
			if(count > 0){
				byteBuffer = new byte[count];
				SOCKET.get(ipPort).getInputStream().read(byteBuffer);
			}
		} catch (IOException e) {
			resetSocket(Ip,Port);
			System.out.println(ipPort+"-------socketд���쳣--------");
		} catch(NullPointerException e){
			resetSocket(Ip,Port);
			System.out.println(ipPort+"-------socket��ָ���쳣--------");
		} catch (Exception e) {
			resetSocket(Ip,Port);
			System.out.println(ipPort+"-------�����쳣--------");
		}
		return byteBuffer;
	}

	/** ��ȡ���ڷ�������byte[]����*/
	public byte[] getOrder(String Ip, int Port){
		String ipPort = Ip + Port;
		byte[] byteBuffer = null;
		try {
			Thread.sleep(100);
			int count = SOCKET.get(ipPort).getInputStream().available();
			if(count != 0){
				byteBuffer = new byte[count];
				SOCKET.get(ipPort).getInputStream().read(byteBuffer);
			}
		} catch (InterruptedException e) {
			resetSocket(Ip,Port);
			System.out.println(ipPort+"-------�ж��쳣--------");
		} catch (IOException e) {
			resetSocket(Ip,Port);
			System.out.println(ipPort+"-------socket��ȡ�쳣--------");
		}
		return byteBuffer;
	}

	public static DeviceIpMapper getDeviceIpMapper() {
		return deviceIpMapper;
	}

	public static void setDeviceIpMapper(DeviceIpMapper deviceIpMapper) {
		SocketUtil.deviceIpMapper = deviceIpMapper;
	}
}
