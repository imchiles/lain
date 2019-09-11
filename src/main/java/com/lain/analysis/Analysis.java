package com.lain.analysis;

public class Analysis {

	/** ���㵥·��ʪ������*/
	public static byte[] getHumitureOrder(int i){
		/** ���� 01 04 00 00 00 02 71 CB   CRC��λ��ǰ*/
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char)by[0];	//��ַλ
		send[1] = 0x04;		//������
		send[2] = 0x00;   	
		send[3] = 0x00;		
		send[4] = 0x00;		
		send[5] = 0x02;	
		
		/* ����CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
//		System.out.println(TypeConvert.byteArrayToString(order));
		return order;
	}
	
	/** ����8052����*/
	public static byte[] getKtr8052Order(int address){
		/** �����һ���豸�� 01 02 00 00 00 08 79 CC   CRC��λ��ǰ*/
		char[] send = new char[8];
		String i = Integer.toHexString(address).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(i);
		send[0] = (char) by[0];	//��ַλ
		send[1] = 0x02;				//������
		send[2] = 0x00;   			
		send[3] = 0x00;				
		send[4] = 0x00;				
		send[5] = 0x08;				
		/* ����CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** ����8060������*/
	public static byte[] getKtr8060OpenOrder(int address,int gallery){
		/** �����һ���豸��IN0�� 01 05 00 00 FF 00 8C 3A    CRC��λ��ǰ*/
		char[] send = new char[8];
		String i = Integer.toHexString(address).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(i);
		send[0] = (char) by[0];	//��ַλ
		send[1] = 0x05;				//������
		send[2] = 0x00;   			
		send[3] = (char)gallery;	
		send[4] = 0xFF;				
		send[5] = 0x00;				
		/* ����CRC */
		send = getCRC(send);
		for(int j=0;j<send.length;j++){
			System.out.print(send[j]);
		}
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** ����8060������*/
	public static byte[] getKtr8060CloseOrder(int address,int gallery){
		/** �����һ���豸��IN0�� 01 05 00 00 00 00 CD CA    CRC��λ��ǰ*/
		char[] send = new char[8];
		String i = Integer.toHexString(address).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(i);
		send[0] = (char) by[0];	//��ַλ
		send[1] = 0x05;				//������
		send[2] = 0x00;   			
		send[3] = (char)gallery;	
		send[4] = 0x00;				
		send[5] = 0x00;				
		/* ����CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** �������������*/
	public static byte[] getElectricMeterOrder(int i){
		/** ���� 01 03 9C 41 00 27 7B 94   CRC��λ��ǰ*/
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char) by[0];	//��ַλ
		send[1] = 0x03;		//������
		send[2] = 0x9C;   	//�¶ȸ�λ
		send[3] = 0x41;		//�¶ȵ�λ
		send[4] = 0x00;		//ʪ�ȸ�λ
		send[5] = 0x27;		//ʪ�ȵ�λ
		/* ����CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** ��������ǵ����������*/
	public static byte[] getElectricMeterAOrder(int i){
		/** ���� 01 03 9d 0a 00 01 8b a4   CRC��λ��ǰ*/
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char) by[0];	//��ַλ
		send[1] = 0x03;		//������
		send[2] = 0x9d;   	//�¶ȸ�λ
		send[3] = 0x0a;		//�¶ȵ�λ
		send[4] = 0x00;		//ʪ�ȸ�λ
		send[5] = 0x01;		//ʪ�ȵ�λ
		/* ����CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** �������յ�ѧϰ����*/
	public static String getFissionAirStudyOrder(int i,int status){
		/** ��ѧϰ������ S 01 001 ����ѧϰ�ػ� S 01 002*/
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
	
	/** �������յ���������*/
	public static String getFissionAirControlOrder(int i,int status){
		/** �����ƿ����� F 01 001 �������ƹػ���F 01 002*/
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
	
	/** ���㶨λ©ˮ����*/
	public static byte[] getLocationOrder(int i){
		/** ���� 01 03 00 02 00 02 65 CB   CRC��λ��ǰ*/
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char) by[0];	//��ַλ
		send[1] = 0x03;		//������
		send[2] = 0x00;   	//�¶ȸ�λ
		send[3] = 0x02;		//�¶ȵ�λ
		send[4] = 0x00;		//ʪ�ȸ�λ
		send[5] = 0x02;		//ʪ�ȵ�λ
		/* ����CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	/** ���㶨λ������̼����*/
	public static byte[] getCo2Order(int i){
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char) by[0];	//��ַλ
		send[1] = 0x03;		//������
		send[2] = 0x00;   	//�¶ȸ�λ
		send[3] = 0x04;		//�¶ȵ�λ
		send[4] = 0x00;		//ʪ�ȸ�λ
		send[5] = 0x01;		//ʪ�ȵ�λ
		/* ����CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	

	/** ���㶨λPM10����*/
	public static byte[] getPoisonousOrder(int i){
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char) by[0];	//��ַλ
		send[1] = 0x03;		//������
		send[2] = 0x00;   	//�¶ȸ�λ
		send[3] = 0x00;		//�¶ȵ�λ
		send[4] = 0x00;		//ʪ�ȸ�λ
		send[5] = 0x02;		//ʪ�ȵ�λ
		/* ����CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	
	/** ������������*/
	public static byte[] getNoiseOrder(int i){
		/** ���� 01 03 00 00 00 01 84 0A   CRC��λ��ǰ*/
		char[] send = new char[8];
		String address = Integer.toHexString(i).toUpperCase();
		byte[] by = TypeConvert.stringToByteArray(address);
		send[0] = (char)by[0];	//��ַλ
		send[1] = 0x03;		//������
		send[2] = 0x00;   	
		send[3] = 0x00;		
		send[4] = 0x00;		
		send[5] = 0x01;		
		/* ����CRC */
		send = getCRC(send);
		byte[] order = TypeConvert.charArrayToByteArray(send);
		return order;
	}
	
	
	
	
	/**
	 * ����CRC
	 * @param send
	 * @return
	 */
	public static char[] getCRC(char[] send) {
		/* �õ�CRC */
		String checkCode = Integer.toHexString(CrcEfficacy.getCrcCheck(send, send.length - 2)).toUpperCase();
		/* ���CRCλ�� */
		if (checkCode.length() < 4) {
			StringBuffer sb = new StringBuffer();
			sb.append("0");
			sb.append(checkCode);
			checkCode = sb.toString();
		}
		/* ȡ�ߵ�λ */
		send[6] = (char) Integer.parseInt(checkCode.substring(2, 4), 16);
		send[7] = (char) Integer.parseInt(checkCode.substring(0, 2), 16);
		/* ���ؽ�� */
		return send;
	}
}
