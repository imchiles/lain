package com.lain.master;

import java.util.List;

import com.lain.util.IpConnect;
import com.lain.util.SocketUtil;

public class SocketSendOrder {
	private static SocketUtil socket = new SocketUtil();
	/** ��ʪ�ȸ�Ӧ������ͼ����ش���*/
	public static void humiture(final String Ip, final int Port, final List<byte[]> orders, final int diId) {
		IpConnect.threadMap.get(Ip+Port).execute(new Runnable() {
			public void run() {
				while (IpConnect.ipPortMap.get(Ip+Port)) {
					try {
						for(byte[] order : orders){
							byte[] back = socket.sendOrder(order, Ip, Port);
							//����9�Ǹ�����ʪ�ȷ��ص����ݳ�����ȷ���ģ������ȷ�����ݷ��صĳ��ȣ���������̶�ֵ
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
	/** 8052����ͼ����ش���*/
	public static void ktr8052(final String Ip, final int Port, final List<byte[]> orders, final int diId) {
		IpConnect.threadMap.get(Ip+Port).execute(new Runnable() {
			public void run() {
				while (IpConnect.ipPortMap.get(Ip+Port)) {
					try {
						for(byte[] order : orders){
							byte[] back = socket.sendOrder(order, Ip, Port);
							
							//����6�Ǹ���8052ģ�鷵�ص����ݳ�����ȷ���ģ������ȷ�����ݷ��صĳ��ȣ���������̶�ֵ
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
	/** ����������ͼ����ش���*/
	public static void electricmenter(final String Ip, final int Port,final List<byte[]> orders, final int diId) {
		IpConnect.threadMap.get(Ip+Port).execute(new Runnable() {
			public void run() {
				while (IpConnect.ipPortMap.get(Ip+Port)) {
					try {
						for(byte[] order : orders){
							byte[] back = socket.sendOrder(order, Ip, Port);
							
							if(back!=null && back.length==83)	//����83�Ǹ��ݵ����Ƿ��ص����ݳ�����ȷ���ģ������ȷ�����ݷ��صĳ��ȣ���������̶�ֵ
								ElectricmeterOrderRead.getElectricmeterBGO(back,diId,Ip,Port);	//�����ص�����
							
						}
						Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	/** ��λ©ˮ����ͼ����ش���*/
	public static void location(final String Ip,final int Port,final SocketUtil socket,final List<byte[]> orders,final int diId) {
		IpConnect.threadMap.get(Ip+Port).execute(new Runnable() {
			public void run() {
				while (IpConnect.ipPortMap.get(Ip+Port)) {
					try {
						for(int i=0;i<orders.size();i++){
							byte[] back = socket.sendOrder(orders.get(i), Ip, Port);
							if(back!=null /* && back.length==9*/){	//��λ©ˮģ��
								LocationOrderRead.getLocationBGO(back, diId);	//�����ص�����
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
