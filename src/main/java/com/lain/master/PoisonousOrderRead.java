package com.lain.master;

import java.math.BigInteger;

import com.lain.analysis.TypeConvert;
import com.lain.dao.PoisonousMapper;
import com.lain.entity.PoisonousManagePojo;

public class PoisonousOrderRead {
	public static byte FUNCTION_CODE = 0X03;	//������
	public static byte BITS_LENGTH = 0X04;		//�ֽڳ���
	public static PoisonousMapper poisonousMapper;
	/**
	 * BGO ��̨���� ��background operation��
	 * @param bytes
	 * @throws IOException 
	 */
	
	public static void getPoisonousBGO(byte[] bytes,int ipId){
//		System.out.println("�ж�����order:"+poisonousTypeConvert.byteArrayToString(bytes));
		if(bytes[1] == FUNCTION_CODE && bytes[2] == BITS_LENGTH){
			int address = getDeviceId(bytes[0]);
			double data = getDate(bytes[5],bytes[6]);
			poisonousMapper.updPoisonousRealTimeData(data, address, ipId);	//��������
			selectAlarmWay(data, address, ipId);	//�ж��Ƿ񱨾�
		}
	}
	
	/**
	 * ѡ���ж����屨����ʽ
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
			info = "��⵽"+location+"-"+name+"-"+"��ǰ�ж�����Ũ��"+data+"PM��������Ũ��";
			System.out.println(info);
			/*boolean tempFlag = IntervalTime.CheckTime(number,AlarmStatus.ALARMINTERVAL);
			if(tempFlag){
				poisonous.savePoisonousAlarm(id, ipId, info);	//���汨����¼
				AlarmMonitor.startAlarmMonitor(info);	//���������ж�
			}*/
		}
	}
	
	//�ж������豸��ַ
	public static int getDeviceId(byte by1){
		byte[] by = new byte[1];
		by[0] = by1;
		BigInteger bi = new BigInteger(1, by);
		String str = bi.toString(16);
		int deviceId = Integer.parseInt(str.toUpperCase(),16);
		return deviceId;
	}
	
	//�ж����峤�Ƚ���
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
