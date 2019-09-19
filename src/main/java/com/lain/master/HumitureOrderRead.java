package com.lain.master;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lain.analysis.TypeConvert;
import com.lain.dao.HumitureMapper;
import com.lain.util.AlarmMonitor;
import com.lain.util.AlarmTime;
import com.lain.util.Calculagraph;

/**
 * @author 王鹏康
 * @describe 温湿度主方法
 * @time 2014-12-00
 */
public class HumitureOrderRead {
	public static byte FUNCTION_CODE = 0X04;	//功能码
	public static byte BITS_LENGTH = 0X04;		//字节长度

	public static HumitureMapper humitureMapper;

	/** 获取温湿度的最大最小值 */
	public static Map<String, Map<String, String>> LIMIT = null;

	/** 温湿度上下限 */
	public static Map<String, List<Double>> VALUE = new HashMap<String, List<Double>>();
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
	
	private static Calculagraph CALCULAGRAPH = new Calculagraph();
	
	private static String KEY;

	public static void getHumitureBGO(byte[] bytes,int diId, String Ip, int Port) throws InterruptedException, NumberFormatException, ParseException{
		//System.out.println("温湿度order:"+HumitureTypeConvert.byteArrayToString(bytes));
		//CRC校验
		if (CrcCheck.CrcEqualsResult(bytes)&&((bytes[1]&FUNCTION_CODE)==FUNCTION_CODE && (bytes[2]&BITS_LENGTH)==BITS_LENGTH)) {
			int address = getDeviceId(bytes[0]);
			if((bytes[3]&0xFF) == 0xFF && (bytes[4]&0x01) == 0x01 && (bytes[5]&0xFF) == 0xFF && (bytes[6]&0x01) == 0x01)
				System.out.println("--------------设备已损坏---------------");
			else if((bytes[3]&0xFF) == 0xFF && (bytes[4]&0x02) == 0x02 && (bytes[5]&0xFF) == 0xFF && (bytes[6]&0x02) == 0x02)
				System.out.println("--------------设备异常---------------");
			else{
				double temp = getHum(bytes[3],bytes[4]);									//温度
				double hum = getHum(bytes[5],bytes[6]);										//湿度
				humitureMapper.updateHumitureRealTimeData(temp, hum, address, diId);		//更新温湿度实时数据
				saveHumitureHistory(temp,hum,address,diId);									//保存温湿度历史记录
				saveHumitureAlarm(temp,hum,address,diId,Ip,Port);							//保存温湿度报警记录
			}
		}
	}

	/**保存温室度历史数据*/
	/**具备定时器功能，根据数据库en_humiture_manage的intervalTime获取时间间隔*/

	public static void saveHumitureHistory(double temp,double hum,int address,int diId){

		String key = address+":"+diId;

		if(!Calculagraph.map.containsKey(key))
			CALCULAGRAPH.SetMap(key, 0);

		/** 保存历史记录*/
		if(CALCULAGRAPH.GetMap(key)==0){
			humitureMapper.saveHumitureHistory(temp, hum, address, diId);
			CALCULAGRAPH.SetMap(key, humitureMapper.findIntervalTime(address,diId));
		}
	}
	public static void saveHumitureAlarm(double temp,double hum,int address,int diId, String Ip, int Port){
		KEY = address+":"+diId;
		double maxTemp = VALUE.get(KEY).get(0);
		double minTemp = VALUE.get(KEY).get(1);
		double maxHum = VALUE.get(KEY).get(2);
		double minHum = VALUE.get(KEY).get(3);
		
		String info = null;
		
		if((temp>maxTemp)||(temp<minTemp))
			info = "检测温度"+temp+"°C超过报警阀值"+maxTemp+"°C。";
		
		if((hum>maxHum)||(hum<minHum))
			info = "检测湿度"+hum+"%超过报警阀值"+maxHum+"%。";
		
		String s = sdf.format(new Date());
		
		if(info != null){
			String msg = info;
			selectAlarmWay(msg,address,diId,Ip,Port);
		} else {
			if(CALCULAGRAPH.isMap(s))
				CALCULAGRAPH.RemoveMap(s);
		}
	}

	/** 选择温湿度报警方式*/
	public static void selectAlarmWay(String info, int address, int diId, String Ip, int Port){
		if(!CALCULAGRAPH.isMap(KEY)){
			String s = sdf.format(new Date());
			CALCULAGRAPH.SetMap(KEY, AlarmTime.timeMap.get(s));
		}
		if(CALCULAGRAPH.GetMap(KEY)==0){
			humitureMapper.saveHumitureAlarm(address, diId, info);
			AlarmMonitor.startAlarmMonitor(info, Ip, Port);	//启动报警判断
		}
	}

	//温湿度设备地址
	public static int getDeviceId(byte by1){
		byte[] by = new byte[1];
		by[0] = by1;
		BigInteger bi = new BigInteger(1, by);
		String str = bi.toString(16);
		int deviceId = Integer.parseInt(str.toUpperCase(),16);
		return deviceId;
	}

	//温度解析
	public static double getTemp(byte by1,byte by2){
		double temp = 0.0;
		byte[] by = new byte[2];
		by[0] = (byte)by1;
		by[1] = (byte)by2;
		String strH = TypeConvert.byteArrayToString(by);
		BigInteger bit = new BigInteger(1, by);
		String str2 = bit.toString(2);
		if(str2.length() != 16){			//判断二进制长度如果不为16，则说明温度为正数
			temp = Integer.parseInt(strH, 16);
		} else if(str2.length() == 16){		//判断二进制长度如果为16，则说明温度为负数
			temp = Integer.parseInt(strH, 16)*(-1);
			int in = Integer.parseInt(str2, 2);
			String sd = Integer.toBinaryString(~in+1);
			temp = Integer.parseInt(sd.substring(16), 2)*(-1);
		}
		return temp/10;
	}

	//湿度解析
	public static double getHum(byte by1,byte by2){
		double hum = 0.0;
		byte[] by = new byte[2];
		by[0] = (byte)by1;
		by[1] = (byte)by2;
		String str = TypeConvert.byteArrayToString(by);
		hum = Integer.parseInt(str, 16);
		return hum/10;
	}


	public static void setHumitureMapper(HumitureMapper humitureMapper) {
		HumitureOrderRead.humitureMapper = humitureMapper;
	}
}
