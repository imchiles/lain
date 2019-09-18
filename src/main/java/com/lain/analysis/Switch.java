package com.lain.analysis;

import com.lain.util.SocketUtil;

public class Switch {

	public static int soundLightAlarm = 0;
	private static String str = null;
	private static byte[] phoneOrder = null;
	/**
	 * 获取KTR8060打开和关闭报警
	 */
	public static void soundSwitch(String ipPort,SocketUtil socket,byte[] open,byte[] close){
		try {
			str = "START";
			StartAndClose(ipPort, socket, open, str);
			Thread.sleep(10000);
			str = "CLOSE";
			StartAndClose(ipPort, socket, close, str);
		} catch (InterruptedException e) {
			System.out.println("Switch.soundSwitch"+e.getMessage());
			e.printStackTrace();
		}
	}

	private synchronized static void StartAndClose(String ipPort,SocketUtil socket,byte[] by, String str){
		System.out.println(str+"...ON..."+new String(by));
		try {
			phoneOrder = socket.sendOrder(by,ipPort.split(":")[0],Integer.parseInt(ipPort.split(":")[1]));
			//发送失败重新发送5次
			System.out.println(phoneOrder.length);
			if (phoneOrder.length > 0) {
				for (int j=0; j<1; j++) {
					phoneOrder = socket.sendOrder(by,ipPort.split(":")[0],Integer.parseInt(ipPort.split(":")[1]));
					//发送成功跳出方法
					if(phoneOrder!=null && by.equals(phoneOrder)){
						System.out.println("SUCCESS..."+by.length);
						break;
					}
					Thread.sleep(1000);
					System.out.println(str+"...AGAIN...");
				}
			} else System.out.println(str+"...SUCCESS..."+by.length);

		} catch (NumberFormatException e) {
			System.out.println("Swith.StartAndClose"+e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Swith.StartAndClose"+e.getMessage());
			e.printStackTrace();
		}
	}
}
