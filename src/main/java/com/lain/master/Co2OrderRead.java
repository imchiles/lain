package com.lain.master;

import java.math.BigInteger;

import com.lain.analysis.TypeConvert;
import com.lain.dao.Co2Mapper;
import com.lain.entity.Co2ManagePojo;

public class Co2OrderRead {
	public static byte FUNCTION_CODE = 0X03;	//������
	public static byte BITS_LENGTH = 0X02;		//�ֽڳ���
	public static Co2Mapper co2Mapper;
	/**
	 * BGO ��̨���� ��background operation��
	 * @param bytes
	 * @throws IOException 
	 */
	public static void getCo2BGO(byte[] bytes,int ipId){
//		System.out.println("������̼order:"+Co2TypeConvert.byteArrayToString(bytes));
		if(bytes[1] == FUNCTION_CODE && bytes[2] == BITS_LENGTH){
			int address = getDeviceId(bytes[0]);
			int data = getDate(bytes[3],bytes[4]);
			co2Mapper.updCo2RealTimeData(data, address, ipId);	//��������
			selectAlarmWay(data, address, ipId);	//�ж��Ƿ񱨾�
		}
	}
	
	/**
	 * ѡ�������̼������ʽ
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
			info = "��⵽"+location+"-"+name+"-"+"��ǰ������̼Ũ��"+data+"PM��������Ũ��";
			System.out.println(info);
			/*boolean tempFlag = IntervalTime.CheckTime(number,AlarmStatus.ALARMINTERVAL);
			if(tempFlag){
				co2.saveCo2Alarm(id, ipId, info);	//���汨����¼
				AlarmMonitor.startAlarmMonitor(info);	//���������ж�
			}*/
		}
	}
	
	//������̼�豸��ַ
	public static int getDeviceId(byte by1){
		byte[] by = new byte[1];
		by[0] = by1;
		BigInteger bi = new BigInteger(1, by);
		String str = bi.toString(16);
		int deviceId = Integer.parseInt(str.toUpperCase(),16);
		return deviceId;
	}
	
	//������̼���Ƚ���
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
