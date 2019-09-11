package com.lain.master;

import java.math.BigInteger;

import com.lain.analysis.TypeConvert;
import com.lain.dao.PoisonousMapper;
import com.lain.entity.PoisonousManagePojo;

public class PoisonousOrderRead {
	public static byte FUNCTION_CODE = 0X03;	//功能码
	public static byte BITS_LENGTH = 0X04;		//字节长度
	public static PoisonousMapper poisonousMapper;
	/**
	 * BGO 后台操作 （background operation）
	 * @param bytes
	 * @throws IOException 
	 */
	
	public static void getPoisonousBGO(byte[] bytes,int ipId){
//		System.out.println("有毒气体order:"+poisonousTypeConvert.byteArrayToString(bytes));
		if(bytes[1] == FUNCTION_CODE && bytes[2] == BITS_LENGTH){
			int address = getDeviceId(bytes[0]);
			double data = getDate(bytes[5],bytes[6]);
			poisonousMapper.updPoisonousRealTimeData(data, address, ipId);	//更新数据
			selectAlarmWay(data, address, ipId);	//判断是否报警
		}
	}
	
	/**
	 * 选择有毒气体报警方式
	 */
	public static void selectAlarmWay(double data,int address,int ipId){
		String number = Integer.toString(ipId)+Integer.toString(address);
		PoisonousManagePojo item = poisonousMapper.getPoisonous(address,ipId);
		double alarmData = item.getAlarmData();
		String location = item.getDeviceLocationPojo().getDlName();
		String name = item.getName();
		int id = item.getId();
		String info = null;
		if(alarmData<data){
			info = "检测到"+location+"-"+name+"-"+"当前有毒气体浓度"+data+"PM超过报警浓度";
			System.out.println(info);
			/*boolean tempFlag = IntervalTime.CheckTime(number,AlarmStatus.ALARMINTERVAL);
			if(tempFlag){
				poisonous.savePoisonousAlarm(id, ipId, info);	//保存报警记录
				AlarmMonitor.startAlarmMonitor(info);	//启动报警判断
			}*/
		}
	}
	
	//有毒气体设备地址
	public static int getDeviceId(byte by1){
		byte[] by = new byte[1];
		by[0] = by1;
		BigInteger bi = new BigInteger(1, by);
		String str = bi.toString(16);
		int deviceId = Integer.parseInt(str.toUpperCase(),16);
		return deviceId;
	}
	
	//有毒气体长度解析
	public static double getDate(byte by1,byte by2){
		double co = 0;
		byte[] by = new byte[2];
		by[0] = (byte)by1;
		by[1] = (byte)by2;
		String str = TypeConvert.byteArrayToString(by);
		co = Integer.parseInt(str, 16);
	//	return co/100;
		
		return co;
	}

}
