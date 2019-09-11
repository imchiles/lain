package com.lain.master;

import java.util.List;

import com.lain.util.IpConnect;
import com.lain.util.SocketUtil;

public class SocketSendOrder {
	private static SocketUtil socket = new SocketUtil();
	/** 温湿度感应器命令发送及返回处理*/
	public static void humiture(final String Ip, final int Port, final List<byte[]> orders, final int diId) {
		IpConnect.threadMap.get(Ip+Port).execute(new Runnable() {
			public void run() {
				while (IpConnect.ipPortMap.get(Ip+Port)) {
					try {
						for(byte[] order : orders){
							byte[] back = socket.sendOrder(order, Ip, Port);
							//返回9是根据温湿度返回的数据长度来确定的，如果不确定数据返回的长度，不可填入固定值
							if(back!=null && back.length==9) HumitureOrderRead.getHumitureBGO(back, diId, Ip, Port);
						}
					} catch (Exception e) {
						System.out.println("SocketSendOrder.humiture"+e.getMessage());
						e.printStackTrace();
					}
				}
			}
		});
	}
	/** 8052命令发送及返回处理*/
	public static void ktr8052(final String Ip, final int Port, final List<byte[]> orders, final int diId) {
		IpConnect.threadMap.get(Ip+Port).execute(new Runnable() {
			public void run() {
				while (IpConnect.ipPortMap.get(Ip+Port)) {
					try {
						for(byte[] order : orders){
							byte[] back = socket.sendOrder(order, Ip, Port);
							
							//返回6是根据8052模块返回的数据长度来确定的，如果不确定数据返回的长度，不可填入固定值
							if(back!=null)
								KTR8052OrderRead.getKTR8052BGO(back, diId);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	/** 电量仪命令发送及返回处理*/
	public static void electricmenter(final String Ip, final int Port,final List<byte[]> orders, final int diId) {
		IpConnect.threadMap.get(Ip+Port).execute(new Runnable() {
			public void run() {
				while (IpConnect.ipPortMap.get(Ip+Port)) {
					try {
						for(byte[] order : orders){
							byte[] back = socket.sendOrder(order, Ip, Port);
							
							if(back!=null && back.length==83)	//返回83是根据电量仪返回的数据长度来确定的，如果不确定数据返回的长度，不可填入固定值
								ElectricmeterOrderRead.getElectricmeterBGO(back,diId,Ip,Port);	//处理返回的数据
							
						}
						Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	/** 定位漏水命令发送及返回处理*/
	public static void location(final String Ip,final int Port,final SocketUtil socket,final List<byte[]> orders,final int diId) {
		IpConnect.threadMap.get(Ip+Port).execute(new Runnable() {
			public void run() {
				while (IpConnect.ipPortMap.get(Ip+Port)) {
					try {
						for(int i=0;i<orders.size();i++){
							byte[] back = socket.sendOrder(orders.get(i), Ip, Port);
							if(back!=null /* && back.length==9*/){	//定位漏水模块
								LocationOrderRead.getLocationBGO(back, diId);	//处理返回的数据
							}
						}
						Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
