package com.lain.master;

import java.io.IOException;
import java.math.BigInteger;

import com.lain.analysis.TypeConvert;
import com.lain.dao.LocationMapper;
import com.lain.entity.LocationManagePojo;


public class LocationOrderRead {
	public static byte FUNCTION_CODE = 0X03;	//������
	public static byte BITS_LENGTH = 0X04;		//�ֽڳ���
	public static LocationMapper locationMapper;
	//public static LocationDao location = new LocationImpl();
	/**
	 * BGO ��̨���� ��background operation��
	 * @param bytes
	 * @throws IOException 
	 */
	public static void getLocationBGO(byte[] bytes,int ipId){
//		System.out.println("��λ©ˮorder:"+LocationTypeConvert.byteArrayToString(bytes));
		if(bytes[1] == FUNCTION_CODE && bytes[2] == BITS_LENGTH){
			int address = getDeviceId(bytes[0]);
			if(bytes[4] == 0x00){		//�豸����,����״̬
				locationMapper.updLocationStatusAndLen(0, 0, address, ipId);
				//location.updLocationStatusAndLen(0, 0, address,ipId);
				
			} else if(bytes[4] == 0x01){	//�豸��⵽��©ˮ
				double length = getDate(bytes[5],bytes[6]);
				locationMapper.updLocationStatusAndLen(1, length, address, ipId);
				//location.updLocationStatusAndLen(1, lenght, address,ipId);
				selectAlarmWay(length,address,ipId);		//������ʪ�ȱ�����¼
			}
		}
	}
	
	/**
	 * ѡ��λ©ˮ������ʽ,������ʪ�ȱ�����¼
	 */
	public static void selectAlarmWay(double lenght,int address,int ipId){
		String number = Integer.toString(ipId)+Integer.toString(address);
		LocationManagePojo lmp = locationMapper.getLocation(address,ipId);
		int id = lmp.getId();
		String locationName = lmp.getDeviceLocationPojo().getDlName();
		String name = lmp.getName();
		String info = "��⵽"+locationName+"-"+name+"-"+"��������Ϊ��"+lenght;
		System.out.println(info);
		//����Ϊ������������ע�͵���
		/*boolean tempFlag = IntervalTime.CheckTime(number,AlarmStatus.ALARMINTERVAL);
		if(tempFlag){
			location.saveLocationAlarm(id,ipId,info);		//������ʪ�ȱ�����¼
			AlarmMonitor.startAlarmMonitor(info);	//���������ж�
		}*/
	}
	
	//��λ©ˮ�豸��ַ
	public static int getDeviceId(byte by1){
		byte[] by = new byte[1];
		by[0] = by1;
		BigInteger bi = new BigInteger(1, by);
		String str = bi.toString(16);
		int deviceId = Integer.parseInt(str.toUpperCase(),16);
		return deviceId;
	}
	
	//��λ©ˮ���Ƚ���
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
