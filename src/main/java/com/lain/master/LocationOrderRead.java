package com.lain.master;

import java.io.IOException;
import java.math.BigInteger;

import com.lain.analysis.TypeConvert;
import com.lain.dao.LocationMapper;
import com.lain.entity.LocationPojo;


public class LocationOrderRead {
	public static byte FUNCTION_CODE = 0X03;	//功能码
	public static byte BITS_LENGTH = 0X04;		//字节长度
	public static LocationMapper locationMapper;
	//public static LocationDao location = new LocationImpl();
	/**
	 * BGO 后台操作 （background operation）
	 * @param bytes
	 * @throws IOException 
	 */
	public static void getLocationBGO(byte[] bytes,int ipId){
//		System.out.println("定位漏水order:"+LocationTypeConvert.byteArrayToString(bytes));
		if(bytes[1] == FUNCTION_CODE && bytes[2] == BITS_LENGTH){
			int address = getDeviceId(bytes[0]);
			if(bytes[4] == 0x00){		//设备正常,更新状态
				System.out.println("good");
				try {
					locationMapper.updateLocationStatusAndLen(0, 0, address, ipId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//location.updLocationStatusAndLen(0, 0, address,ipId);
				
			} else if(bytes[4] == 0x01){	//设备检测到有漏水
				double length = getDate(bytes[5],bytes[6]);
				length = length * 10;
				System.out.println(length);
				System.out.println("bad");
				try {
					locationMapper.updateLocationStatusAndLen(1, length, address, ipId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//location.updLocationStatusAndLen(1, lenght, address,ipId);
				//selectAlarmWay(length,address,ipId);		//保存温湿度报警记录
			}
		}
	}
	
	/**
	 * 选择定位漏水报警方式,保存温湿度报警记录
	 */
	public static void selectAlarmWay(double length,int address,int ipId){
		String number = Integer.toString(ipId)+Integer.toString(address);
		//System.out.println(number+"----number");
		//System.out.println(address + "  " +ipId);
		LocationPojo lmp =null;
		try {
			lmp = locationMapper.getLocation(address,ipId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int elm_id = lmp.getElm_id();
		System.out.println(elm_id+"---id");
		String name = lmp.getElm_name();
		//String info = "检测到"-"+name+"-"+"报警距离为："+length;
		//System.out.println(info);
		//下面为其他操作，先注释掉。
		/*boolean tempFlag = IntervalTime.CheckTime(number,AlarmStatus.ALARMINTERVAL);
		if(tempFlag){
			location.saveLocationAlarm(id,ipId,info);		//保存温湿度报警记录
			AlarmMonitor.startAlarmMonitor(info);	//启动报警判断
		}*/
	}
	
	//定位漏水设备地址
	public static int getDeviceId(byte by1){
		byte[] by = new byte[1];
		by[0] = by1;
		BigInteger bi = new BigInteger(1, by);
		String str = bi.toString(16);
		int deviceId = Integer.parseInt(str.toUpperCase(),16);
		return deviceId;
	}
	
	//定位漏水长度解析
	public static double getDate(byte by1,byte by2){
		double hum = 0.0;
		byte[] by = new byte[2];
		by[0] = (byte)by1;
		by[1] = (byte)by2;
		String str = TypeConvert.byteArrayToString(by);
		hum = Integer.parseInt(str, 16);
		return hum/100;
	}
	
	public static void setLocationMapper(LocationMapper locationMapper) {
		LocationOrderRead.locationMapper = locationMapper;
	}
}
