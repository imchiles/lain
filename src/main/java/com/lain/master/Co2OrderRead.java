package com.lain.master;

import java.math.BigInteger;

import com.lain.analysis.TypeConvert;
import com.lain.dao.Co2Mapper;
import com.lain.entity.Co2ManagePojo;

public class Co2OrderRead {
	public static byte FUNCTION_CODE = 0X03;	//功能码
	public static byte BITS_LENGTH = 0X02;		//字节长度
	public static Co2Mapper co2Mapper;
	/**
	 * BGO 后台操作 （background operation）
	 * @param bytes
	 * @throws IOException 
	 */
	public static void getCo2BGO(byte[] bytes,int ipId){
//		System.out.println("二氧化碳order:"+Co2TypeConvert.byteArrayToString(bytes));
		if(bytes[1] == FUNCTION_CODE && bytes[2] == BITS_LENGTH){
			int address = getDeviceId(bytes[0]);
			int data = getDate(bytes[3],bytes[4]);
			co2Mapper.updCo2RealTimeData(data, address, ipId);	//更新数据
			selectAlarmWay(data, address, ipId);	//判断是否报警
		}
	}
	
	/**
	 * 选择二氧化碳报警方式
	 */
	public static void selectAlarmWay(int data,int address,int ipId){
		String number = Integer.toString(ipId)+Integer.toString(address);
		Co2ManagePojo item = co2Mapper.getCo2(address,ipId);
		double alarmData = item.getAlarmData();
		String location = item.getDeviceLocationPojo().getDlName();
		String name = item.getName();
		int id = item.getId();
		String info = null;
		if(alarmData<data){
			info = "检测到"+location+"-"+name+"-"+"当前二氧化碳浓度"+data+"PM超过报警浓度";
			System.out.println(info);
			/*boolean tempFlag = IntervalTime.CheckTime(number,AlarmStatus.ALARMINTERVAL);
			if(tempFlag){
				co2.saveCo2Alarm(id, ipId, info);	//保存报警记录
				AlarmMonitor.startAlarmMonitor(info);	//启动报警判断
			}*/
		}
	}
	
	//二氧化碳设备地址
	public static int getDeviceId(byte by1){
		byte[] by = new byte[1];
		by[0] = by1;
		BigInteger bi = new BigInteger(1, by);
		String str = bi.toString(16);
		int deviceId = Integer.parseInt(str.toUpperCase(),16);
		return deviceId;
	}
	
	//二氧化碳长度解析
	public static int getDate(byte by1,byte by2){
		int co = 0;
		byte[] by = new byte[2];
		by[0] = (byte)by1;
		by[1] = (byte)by2;
		String str = TypeConvert.byteArrayToString(by);
		co = Integer.parseInt(str, 16);
		return co;
	}
	
	public static void setCo2Mapper(Co2Mapper co2Mapper) {
		Co2OrderRead.co2Mapper = co2Mapper;
	}

}
