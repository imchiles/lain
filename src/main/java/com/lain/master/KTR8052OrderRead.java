package com.lain.master;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.lain.dao.Ktr8052Mapper;
import com.lain.entity.TimePojo;
import com.lain.util.SocketUtil;
import com.lain.util.Time;


/**
 * @author ������
 * @describe Ktr8052������
 * @time 2014-12-00
 */
public class KTR8052OrderRead {

	public static byte FUNCTION_CODE = 0X02;	//������
	public static byte BITS_LENGTH = 0X01;		//�ֽڳ���
	private final static Map<String, TimePojo> map = new HashMap<String,TimePojo>();
    private final static long codeOutTime = 15*60*1000;  //������Ϣ�洢���
	
	private static Ktr8052Mapper ktr8052Mapper;
	
	private static SocketUtil socket = new SocketUtil();
	
	public static void getKTR8052BGO(byte[] bytes, int diId){
//		System.out.println("ktr8052order:"+TypeConvert.byteArrayToString(bytes));
		int address = getDeviceId(bytes[0]);
		if((bytes[1]&0x02) == FUNCTION_CODE && (bytes[2]&0x01) == BITS_LENGTH){
			for(int i=0; i<2; i++){
				if(((bytes[3] >> i) & 0x01) == 0x01) {
					ktr8052Mapper.updataKtr8052Galery(1,"DI"+i, address, diId); 	//����
					System.out.println("����");
					long alarmTime = System.currentTimeMillis();
					String time = Time.getTimeymd();//����ʱ��
					TimePojo timePojo = new TimePojo();
					
					if(map.get("alarmTime") == null) {
						timePojo.setAlarmTime(alarmTime);
						map.put("alarmTime", timePojo);//˵��û�����ִ�е�һ�δ��ȥʱ��
						String ktr8052Name = ktr8052Mapper.selectKtr8052Name("DI"+i);
						ktr8052Mapper.insertAlarm(ktr8052Name, time, "DI"+i);//���汨����Ϣ
					}
					TimePojo newTimePojo = map.get("alarmTime");
					if(System.currentTimeMillis() - newTimePojo.getAlarmTime() > codeOutTime) {
						String ktr8052Name = ktr8052Mapper.selectKtr8052Name("DI"+i);
						ktr8052Mapper.insertAlarm(ktr8052Name, time, "DI"+i);//���汨����Ϣ
						timePojo.setAlarmTime(System.currentTimeMillis());
						map.put("alarmTime", timePojo);//���ȥ
					}
				}
				else {
					//System.out.println("DI"+i);
					ktr8052Mapper.updataKtr8052Galery(0,"DI"+i, address, diId);		//����
					//System.out.println("����");
				}
					
			}
		}
	}

	//Ktr8052�豸��ַ
	public static int getDeviceId(byte by1){
		byte[] by = new byte[1];
		by[0] = by1;
		BigInteger bi = new BigInteger(1, by);
		String str = bi.toString(16);
		int deviceId = Integer.parseInt(str.toUpperCase(),16);
		return deviceId;
	}
	public static Ktr8052Mapper getKtr8052Mapper() {
		return ktr8052Mapper;
	}
	public static void setKtr8052Mapper(Ktr8052Mapper ktr8052Mapper) {
		KTR8052OrderRead.ktr8052Mapper = ktr8052Mapper;
	}
	
}
