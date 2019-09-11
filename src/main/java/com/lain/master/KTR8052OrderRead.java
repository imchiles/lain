package com.lain.master;

import java.math.BigInteger;

import com.lain.dao.Ktr8052Mapper;
import com.lain.util.SocketUtil;


/**
 * @author 王鹏康
 * @describe Ktr8052主方法
 * @time 2014-12-00
 */
public class KTR8052OrderRead {

	public static byte FUNCTION_CODE = 0X02;	//功能码
	public static byte BITS_LENGTH = 0X01;		//字节长度
	
	private static Ktr8052Mapper ktr8052Mapper;
	
	private static SocketUtil socket = new SocketUtil();
	
	public static void getKTR8052BGO(byte[] bytes, int diId){
//		System.out.println("ktr8052order:"+TypeConvert.byteArrayToString(bytes));
		int address = getDeviceId(bytes[0]);
		if((bytes[1]&0x02) == FUNCTION_CODE && (bytes[2]&0x01) == BITS_LENGTH){
			for(int i=0; i<6; i++){
				if(((bytes[3] >> i) & 0x01) == 0x01)
					ktr8052Mapper.updataKtr8052Galery(1,"DI"+i, address, diId); 	//报警
				else
					ktr8052Mapper.updataKtr8052Galery(0,"DI"+i, address, diId);		//正常
			}
		}
	}

	//Ktr8052设备地址
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
