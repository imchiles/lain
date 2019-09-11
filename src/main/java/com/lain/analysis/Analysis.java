package com.lain.analysis;

public class Analysis {

	/** 计算单路温湿度命令*/
	public static byte[] getHumitureOrder(int i){
		/** 例： 01 04 00 00 00 02 71 CB   CRC低位在前*/
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char)by[0];	//地址位
		send[1] = 0x04;		//功能码
		send[2] = 0x00;   	
		send[3] = 0x00;		
		send[4] = 0x00;		
		send[5] = 0x02;	
		
		/* 计算CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
//		System.out.println(TypeConvert.byteArrayToString(order));
		return order;
	}
	
	/** 计算8052命令*/
	public static byte[] getKtr8052Order(int address){
		/** 例如读一号设备： 01 02 00 00 00 08 79 CC   CRC低位在前*/
		char[] send = new char[8];
		String i = Integer.toHexString(address).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(i);
		send[0] = (char) by[0];	//地址位
		send[1] = 0x02;				//功能码
		send[2] = 0x00;   			
		send[3] = 0x00;				
		send[4] = 0x00;				
		send[5] = 0x08;				
		/* 计算CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** 计算8060开命令*/
	public static byte[] getKtr8060OpenOrder(int address,int gallery){
		/** 例如读一号设备的IN0： 01 05 00 00 FF 00 8C 3A    CRC低位在前*/
		char[] send = new char[8];
		String i = Integer.toHexString(address).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(i);
		send[0] = (char) by[0];	//地址位
		send[1] = 0x05;				//功能码
		send[2] = 0x00;   			
		send[3] = (char)gallery;	
		send[4] = 0xFF;				
		send[5] = 0x00;				
		/* 计算CRC */
		send = getCRC(send);
		for(int j=0;j<send.length;j++){
			System.out.print(send[j]);
		}
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** 计算8060关命令*/
	public static byte[] getKtr8060CloseOrder(int address,int gallery){
		/** 例如读一号设备的IN0： 01 05 00 00 00 00 CD CA    CRC低位在前*/
		char[] send = new char[8];
		String i = Integer.toHexString(address).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(i);
		send[0] = (char) by[0];	//地址位
		send[1] = 0x05;				//功能码
		send[2] = 0x00;   			
		send[3] = (char)gallery;	
		send[4] = 0x00;				
		send[5] = 0x00;				
		/* 计算CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** 计算电量仪命令*/
	public static byte[] getElectricMeterOrder(int i){
		/** 例： 01 03 9C 41 00 27 7B 94   CRC低位在前*/
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char) by[0];	//地址位
		send[1] = 0x03;		//功能码
		send[2] = 0x9C;   	//温度高位
		send[3] = 0x41;		//温度底位
		send[4] = 0x00;		//湿度高位
		send[5] = 0x27;		//湿度低位
		/* 计算CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** 计算电量仪电流变比命令*/
	public static byte[] getElectricMeterAOrder(int i){
		/** 例： 01 03 9d 0a 00 01 8b a4   CRC低位在前*/
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char) by[0];	//地址位
		send[1] = 0x03;		//功能码
		send[2] = 0x9d;   	//温度高位
		send[3] = 0x0a;		//温度底位
		send[4] = 0x00;		//湿度高位
		send[5] = 0x01;		//湿度低位
		/* 计算CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** 计算分体空调学习命令*/
	public static String getFissionAirStudyOrder(int i,int status){
		/** 例学习开机： S 01 001 ；例学习关机 S 01 002*/
		StringBuffer sb = new StringBuffer();
		sb.append("S");
		String address = Integer.toHexString(i).toUpperCase();
		StringBuffer stb = new StringBuffer();
		if(address.length() < 2){
			stb.append("0");
			stb.append(address);
			sb.append(stb.toString());
			if(status==1){
				sb.append("001");
			} else if(status==2){
				sb.append("002");
			}
		}else{
			sb.append(address);
			if(status==1){
				sb.append("001");
			} else if(status==2){
				sb.append("002");
			}
		}
		return sb.toString();
	}
	
	/** 计算分体空调控制命令*/
	public static String getFissionAirControlOrder(int i,int status){
		/** 例控制开机： F 01 001 ；例控制关机：F 01 002*/
		StringBuffer sb = new StringBuffer();
		sb.append("F");
		String address = Integer.toHexString(i).toUpperCase();
		StringBuffer stb = new StringBuffer();
		if(address.length() < 2){
			stb.append("0");
			stb.append(address);
			sb.append(stb.toString());
			if(status==1){
				sb.append("001");
			} else if(status==2){
				sb.append("002");
			}
		}else{
			sb.append(address);
			if(status==1){
				sb.append("001");
			} else if(status==2){
				sb.append("002");
			}
		}
		return sb.toString();
	}
	
	/** 计算定位漏水命令*/
	public static byte[] getLocationOrder(int i){
		/** 例： 01 03 00 02 00 02 65 CB   CRC低位在前*/
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char) by[0];	//地址位
		send[1] = 0x03;		//功能码
		send[2] = 0x00;   	//温度高位
		send[3] = 0x02;		//温度底位
		send[4] = 0x00;		//湿度高位
		send[5] = 0x02;		//湿度低位
		/* 计算CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** 计算定位二氧化碳命令*/
	public static byte[] getCo2Order(int i){
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char) by[0];	//地址位
		send[1] = 0x03;		//功能码
		send[2] = 0x00;   	//温度高位
		send[3] = 0x04;		//温度底位
		send[4] = 0x00;		//湿度高位
		send[5] = 0x01;		//湿度低位
		/* 计算CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	

	/** 计算定位PM10命令*/
	public static byte[] getPoisonousOrder(int i){
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char) by[0];	//地址位
		send[1] = 0x03;		//功能码
		send[2] = 0x00;   	//温度高位
		send[3] = 0x00;		//温度底位
		send[4] = 0x00;		//湿度高位
		send[5] = 0x02;		//湿度低位
		/* 计算CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	
	/** 计算噪声命令*/
	public static byte[] getNoiseOrder(int i){
		/** 例： 01 03 00 00 00 01 84 0A   CRC低位在前*/
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char)by[0];	//地址位
		send[1] = 0x03;		//功能码
		send[2] = 0x00;   	
		send[3] = 0x00;		
		send[4] = 0x00;		
		send[5] = 0x01;		
		/* 计算CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	
	
	
	/**
	 * 计算CRC
	 * @param send
	 * @return
	 */
	public static char[] getCRC(char[] send) {
		/* 得到CRC */
		String checkCode = Integer.toHexString(CrcEfficacy.getCrcCheck(send, send.length - 2)).toUpperCase();
		/* 检查CRC位数 */
		if (checkCode.length() < 4) {
			StringBuffer sb = new StringBuffer();
			sb.append("0");
			sb.append(checkCode);
			checkCode = sb.toString();
		}
		/* 取高低位 */
		send[6] = (char) Integer.parseInt(checkCode.substring(2, 4), 16);
		send[7] = (char) Integer.parseInt(checkCode.substring(0, 2), 16);
		/* 返回结果 */
		return send;
	}
}
