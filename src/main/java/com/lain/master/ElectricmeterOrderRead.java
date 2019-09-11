package com.lain.master;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.lain.analysis.TypeConvert;
import com.lain.dao.ElectricmeterMapper;
import com.lain.entity.ElectricmeterData;
import com.lain.entity.ElectricmeterHistory;
import com.lain.util.Calculagraph;
import com.lain.util.SocketUtil;

public class ElectricmeterOrderRead {
	
	private static Calculagraph calculagraph = null;
	
	public static byte FUNCTION_CODE = 0X03;	//������
	/** ��ȡ�����ǵ������Сֵ */
	public static Map<String, Map<String, String>> LIMIT = null;
	/** ������ʷ����ʱ�����*/
	public static Map<String,Map<String, Integer>> COUNT = null;

	private static DecimalFormat DF = new DecimalFormat("0.00");

	private static ElectricmeterMapper electricmeterMapper;
	
	private static SocketUtil scoket = new SocketUtil();
	
	/**
	 * ��ʼ��������
	 */
	
	public static void getElectricmeterBGO(byte[] bytes, int diId, String ip, int Port) throws InterruptedException{

		if((bytes[1]&0x03) == FUNCTION_CODE){
			int address = getDeviceId(bytes[0]);
			ElectricmeterData ed = getData(bytes);
			ed.setDiId(diId);
			electricmeterMapper.updateElectricmeterRealTimeData(ed);	//���µ�����ʵʱ����
			saveElectricmeterHistory(ed, address, diId);
			//isAlarm(map,address,ipId,socket);		//��������Ǳ�����¼
		}

	}

	public static void saveElectricmeterHistory(ElectricmeterData ed, int address, int diId){
		String key = "2:"+address+":"+diId;
		if(Calculagraph.map.containsKey(key)){
			Calculagraph.map.put(key, 0);
			calculagraph = new Calculagraph();
		}
		/** ������ʷ��¼*/
		if(Calculagraph.map.get(key)<=0){
			calculagraph.StatrTime();
			ElectricmeterHistory eh = new ElectricmeterHistory();
			eh.setDiId(ed.getDiId());
			eh.setPehAvol(ed.getPedAvol());
			eh.setPehAcur(ed.getPedAcur());
			eh.setPehBvol(ed.getPedBvol());
			eh.setPehBcur(ed.getPedBcur());
			eh.setPehCvol(ed.getPedCvol());
			eh.setPehCcur(ed.getPedCcur());
			eh.setPehTpap((ed.getPedAvol()*ed.getPedAcur()+ed.getPedBvol()*ed.getPedBcur()+ed.getPedCvol()*ed.getPedCcur())/1000);
			eh.setPehUserdTime(electricmeterMapper.findIntervalTime(address, diId)/60);
			eh.setPehElequantity(eh.getPehTpap() * eh.getPehUserdTime());
			eh.setPehElecharge(Double.valueOf(new DecimalFormat("######0.00").format(eh.getPehElequantity() * 0.85)));
			electricmeterMapper.saveElectricmeterHistory(eh);
		}
	}



	/**
	 * ��������Ǳ�����¼
	 * @param map
	 * @param deviceId
	 */
	public static void isAlarm(Map<String,Double> map,int address,int ipId,SocketUtil socket){
		String number = String.valueOf(ipId)+String.valueOf(address);
		StringBuffer sb = new StringBuffer();
		if(map.get("Avol")>Double.parseDouble(LIMIT.get(number).get("maxAvol"))){	//A���ѹ����
			String info = map.get("Avol")+"V����A���ѹ���ޡ�";
			sb.append(info);
		} else if(map.get("Avol")<Double.parseDouble(LIMIT.get(number).get("minAvol"))){	//A���ѹ����
			String info = map.get("Avol")+"V����A���ѹ���ޡ�";
			sb.append(info);
		}
		if(map.get("Bvol")>Double.parseDouble(LIMIT.get(number).get("maxBvol"))){	//B���ѹ����
			String info = map.get("Bvol")+"V����B���ѹ���ޡ�";
			sb.append(info);
		} else if(map.get("Bvol")<Double.parseDouble(LIMIT.get(number).get("minBvol"))){	//B���ѹ����
			String info = map.get("Bvol")+"V����B���ѹ���ޡ�";
			sb.append(info);
		}
		if(map.get("Cvol")>Double.parseDouble(LIMIT.get(number).get("maxCvol"))){	//C���ѹ����
			String info = map.get("Cvol")+"V����C���ѹ���ޡ�";
			sb.append(info);
		} else if(map.get("Cvol")<Double.parseDouble(LIMIT.get(number).get("minCvol"))){	//C���ѹ����
			String info = map.get("Cvol")+"V����C���ѹ���ޡ�";
			sb.append(info);
		}
		if(map.get("Acur")>Double.parseDouble(LIMIT.get(number).get("maxAcur"))){	//A���������
			String info = map.get("Acur")+"A����A��������ޡ�";
			sb.append(info);
		} else if(map.get("Acur")<Double.parseDouble(LIMIT.get(number).get("minAcur"))){	//A���������
			String info = map.get("Acur")+"A����A��������ޡ�";
			sb.append(info);
		}
		if(map.get("Bcur")>Double.parseDouble(LIMIT.get(number).get("maxBcur"))){	//B���������
			String info = map.get("Bcur")+"A����B��������ޡ�";
			sb.append(info);
		} else if(map.get("Bcur")<Double.parseDouble(LIMIT.get(number).get("minBcur"))){	//B���������
			String info = map.get("Bcur")+"A����B��������ޡ�";
			sb.append(info);
		}
		if(map.get("Ccur")>Double.parseDouble(LIMIT.get(number).get("maxCcur"))){	//C���������
			String info = map.get("Ccur")+"A����C��������ޡ�";
			sb.append(info);
		} else if(map.get("Ccur")<Double.parseDouble(LIMIT.get(number).get("minCcur"))){	//C���������
			String info = map.get("Ccur")+"A����C��������ޡ�";
			sb.append(info);
		}
		if(map.get("ABvol")>Double.parseDouble(LIMIT.get(number).get("maxABvol"))){	//AB���ѹ����
			String info = map.get("ABvol")+"V����AB���ѹ���ޡ�";
			sb.append(info);
		} else if(map.get("ABvol")<Double.parseDouble(LIMIT.get(number).get("minABvol"))){	//AB���ѹ����
			String info = map.get("ABvol")+"V����AB���ѹ���ޡ�";
			sb.append(info);
		}
		if(map.get("BCvol")>Double.parseDouble(LIMIT.get(number).get("maxBCvol"))){	//BC���ѹ����
			String info = map.get("BCvol")+"V����BC���ѹ���ޡ�";
			sb.append(info);
		} else if(map.get("BCvol")<Double.parseDouble(LIMIT.get(number).get("minBCvol"))){	//BC���ѹ����
			String info = map.get("BCvol")+"V����BC���ѹ���ޡ�";
			sb.append(info);
		}
		if(map.get("CAvol")>Double.parseDouble(LIMIT.get(number).get("maxCAvol"))){	//CA���ѹ����
			String info = map.get("CAvol")+"V����CA���ѹ���ޡ�";
			sb.append(info);
		} else if(map.get("CAvol")<Double.parseDouble(LIMIT.get(number).get("minCAvol"))){	//CA���ѹ����
			String info = map.get("CAvol")+"V����CA���ѹ���ޡ�";
			sb.append(info);
		}
		if(map.get("Apap")>Double.parseDouble(LIMIT.get(number).get("maxApap"))){	//A���й���������
			String info = map.get("Apap")+"KW����A���й��������ޡ�";
			sb.append(info);
		} else if(map.get("Apap")<Double.parseDouble(LIMIT.get(number).get("minApap"))){	//A���й���������
			String info = map.get("Apap")+"KW����A���й��������ޡ�";
			sb.append(info);
		}
		if(map.get("Bpap")>Double.parseDouble(LIMIT.get(number).get("maxBpap"))){	//B���й���������
			String info = map.get("Bpap")+"KW����B���й��������ޡ�";
			sb.append(info);
		} else if(map.get("Bpap")<Double.parseDouble(LIMIT.get(number).get("minBpap"))){	//B���й���������
			String info = map.get("Bpap")+"KW����B���й��������ޡ�";
			sb.append(info);
		}
		if(map.get("Cpap")>Double.parseDouble(LIMIT.get(number).get("maxCpap"))){	//C���й���������
			String info = map.get("Cpap")+"KW����C���й��������ޡ�";
			sb.append(info);
		} else if(map.get("Cpap")<Double.parseDouble(LIMIT.get(number).get("minCpap"))){	//C���й���������
			String info = map.get("Cpap")+"KW����C���й��������ޡ�";
			sb.append(info);
		}
		if(map.get("Aprp")>Double.parseDouble(LIMIT.get(number).get("maxAprp"))){	//A���޹���������
			String info = map.get("Aprp")+"Kvar����A���޹��������ޡ�";
			sb.append(info);
		} else if(map.get("Aprp")<Double.parseDouble(LIMIT.get(number).get("minAprp"))){	//A���޹���������
			String info = map.get("Aprp")+"Kvar����A���޹��������ޡ�";
			sb.append(info);
		}
		if(map.get("Bprp")>Double.parseDouble(LIMIT.get(number).get("maxBprp"))){	//B���޹���������
			String info = map.get("Bprp")+"Kvar����B���޹��������ޡ�";
			sb.append(info);
		} else if(map.get("Bprp")<Double.parseDouble(LIMIT.get(number).get("minBprp"))){	//B���޹���������
			String info = map.get("Bprp")+"Kvar����B���޹��������ޡ�";
			sb.append(info);
		}
		if(map.get("Cprp")>Double.parseDouble(LIMIT.get(number).get("maxCprp"))){	//C���޹���������
			String info = map.get("Cprp")+"Kvar����C���޹��������ޡ�";
			sb.append(info);
		} else if(map.get("Cprp")<Double.parseDouble(LIMIT.get(number).get("minCprp"))){	//C���޹���������
			String info = map.get("Cprp")+"Kvar����C���޹��������ޡ�";
			sb.append(info);
		}
		if(map.get("Appf")>Double.parseDouble(LIMIT.get(number).get("maxAppf"))){	//A�๦����������
			String info = map.get("Appf")+"����A�๦���������ޡ�";
			sb.append(info);
		} else if(map.get("Appf")<Double.parseDouble(LIMIT.get(number).get("minAppf"))){	//A�๦����������
			String info = map.get("Appf")+"����A�๦���������ޡ�";
			sb.append(info);
		}
		if(map.get("Bppf")>Double.parseDouble(LIMIT.get(number).get("maxBppf"))){	//B�๦����������
			String info = map.get("Bppf")+"����B�๦���������ޡ�";
			sb.append(info);
		} else if(map.get("Bppf")<Double.parseDouble(LIMIT.get(number).get("minBppf"))){	//B�๦����������
			String info = map.get("Bppf")+"����B�๦���������ޡ�";
			sb.append(info);
		}
		if(map.get("Cppf")>Double.parseDouble(LIMIT.get(number).get("maxCppf"))){	//C�๦����������
			String info = map.get("Cppf")+"����C�๦���������ޡ�";
			sb.append(info);
		} else if(map.get("Cppf")<Double.parseDouble(LIMIT.get(number).get("minCppf"))){	//C�๦����������
			String info = map.get("Cppf")+"����C�๦���������ޡ�";
			sb.append(info);
		}
		if(map.get("Tpap")>Double.parseDouble(LIMIT.get(number).get("maxTpap"))){	//���й���������
			String info = map.get("Tpap")+"KW�������й��������ޡ�";
			sb.append(info);
		} else if(map.get("Tpap")<Double.parseDouble(LIMIT.get(number).get("minTpap"))){	//���й���������
			String info = map.get("Tpap")+"KW�������й��������ޡ�";
			sb.append(info);
		}
		if(map.get("Tprp")>Double.parseDouble(LIMIT.get(number).get("maxTprp"))){	//���޹���������
			String info = map.get("Tprp")+"Kvar�������޹��������ޡ�";
			sb.append(info);
		} else if(map.get("Tprp")<Double.parseDouble(LIMIT.get(number).get("minTprp"))){	//���޹���������
			String info = map.get("Tprp")+"Kvar�������޹��������ޡ�";
			sb.append(info);
		}
		if(map.get("Tppf")>Double.parseDouble(LIMIT.get(number).get("maxTppf"))){	//�ܹ�����������
			String info = map.get("Tppf")+"�����ܹ����������ޡ�";
			sb.append(info);
		} else if(map.get("Tppf")<Double.parseDouble(LIMIT.get(number).get("minTppf"))){	//�ܹ�����������
			String info = map.get("Tppf")+"�����ܹ����������ޡ�";
			sb.append(info);
		}
		//		if(sb.length() > 0){
		//			boolean tempFlag = IntervalTime.CheckTime(number,AlarmStatus.ALARMINTERVAL);
		//			if(tempFlag){
		//				int id = Integer.parseInt(LIMIT.get(number).get("id"));
		//				String name = LIMIT.get(number).get("name");
		//				String location = LIMIT.get(number).get("location");
		//				String msg = location+"-"+name+":��⵽"+sb;
		//				electricMeter.saveElectricMeterAlarm(id,ipId,msg);		//��������Ǳ�����¼
		//				AlarmMonitor.startAlarmMonitor(msg,com);	//���������ж�
		//			}
		//		} else{
		//			boolean flag = IntervalTime.intervalTime.containsKey(number);
		//			if(flag){
		//				IntervalTime.intervalTime.remove(number);
		//			}
		//		}
	}

	//�������豸��ַ
	public static int getDeviceId(byte by1){
		byte[] by = new byte[1];
		by[0] = by1;
		BigInteger bi = new BigInteger(1, by);
		String str = bi.toString(16);
		int deviceId = Integer.parseInt(str.toUpperCase(),16);
		return deviceId;
	}

	//��ȡ����������
	public static Map<String,Double> getData(byte[] by,byte[] by1){

		//�������
		double Abi=getDate(by1[3],by1[4]);
		//		System.out.println(HumitureTypeConvert.byteArrayToString(by1)+ " "+TypeConvert.byteArrayToString(by1).substring(0,2)+"���="+Abi);
		Map<String,Double> map = new HashMap<String,Double>();
		double Avol = getDate(by[3],by[4]);	//A���ѹ
		map.put("Avol", Double.parseDouble(DF.format(Avol/100)));
		double Bvol = getDate(by[5],by[6]);	//B���ѹ
		map.put("Bvol", Double.parseDouble(DF.format(Bvol/100)));
		double Cvol = getDate(by[7],by[8]);	//C���ѹ
		map.put("Cvol", Double.parseDouble(DF.format(Cvol/100)));
		double Acur = getDate(by[9],by[10])/1000*Abi;;	//A�����
		map.put("Acur", Double.parseDouble(DF.format(Acur)));
		double Bcur = getDate(by[11],by[12])/1000*Abi;;	//B�����
		map.put("Bcur", Double.parseDouble(DF.format(Bcur)));
		double Ccur = getDate(by[13],by[14])/1000*Abi;;	//C�����
		map.put("Ccur", Double.parseDouble(DF.format(Ccur)));

		double Tpap = getDate(by[15],by[16],by[17],by[18])*1;	//���й�����
		map.put("Tpap", Double.parseDouble(DF.format(Tpap/50)));
		double Tprp = getDate(by[19],by[20],by[21],by[22])*1;	//���޹�����
		map.put("Tprp", Double.parseDouble(DF.format(Tprp/50)));
		double Tppf = getDate(by[23],by[24]);	//�ܹ�������
		map.put("Tppf", Double.parseDouble(DF.format(Tppf/100)));
		double Apap = getDate(by[25],by[26])*1;	//A���й����� 			40����CT��
		map.put("Apap", Double.parseDouble(DF.format(Apap/50)));
		double Bpap = getDate(by[27],by[28])*1;	//B���й�����
		map.put("Bpap", Double.parseDouble(DF.format(Bpap/50)));
		double Cpap = getDate(by[29],by[30])*1;	//C���й�����
		map.put("Cpap", Double.parseDouble(DF.format(Cpap/50)));
		double Aprp = getDate(by[31],by[32])*1;	//A���޹�����
		map.put("Aprp", Double.parseDouble(DF.format(Aprp/50)));
		double Bprp = getDate(by[33],by[34])*1;	//B���޹�����
		map.put("Bprp", Double.parseDouble(DF.format(Bprp/50)));
		double Cprp = getDate(by[35],by[36])*1;	//C���޹�����
		map.put("Cprp", Double.parseDouble(DF.format(Cprp/50)));
		double Appf = getDate(by[37],by[38]);	//A�๦������
		map.put("Appf", Double.parseDouble(DF.format(Appf/100)));
		double Bppf = getDate(by[39],by[40]);	//B�๦������
		map.put("Bppf", Double.parseDouble(DF.format(Bppf/100)));
		double Cppf = getDate(by[41],by[42]);	//C�๦������
		map.put("Cppf", Double.parseDouble(DF.format(Cppf/100)));
		double ABvol = getDate(by[75],by[76]);	//AB���ѹ
		map.put("ABvol", Double.parseDouble(DF.format(ABvol/100)));
		double BCvol = getDate(by[77],by[78]);	//BC���ѹ
		map.put("BCvol", Double.parseDouble(DF.format(BCvol/100)));
		double CAvol = getDate(by[79],by[80]);	//CA���ѹ
		map.put("CAvol", Double.parseDouble(DF.format(CAvol/100)));
		return map;
	}



	//����
	//��ȡ����������
	public static ElectricmeterData getData(byte[] by){
		ElectricmeterData ed = new ElectricmeterData();
		//A���ѹ				Avol
		ed.setPedAvol(Double.parseDouble(DF.format(getDate(by[3],by[4])/100)));
		//B���ѹ				Bvol
		ed.setPedBvol(Double.parseDouble(DF.format(getDate(by[5],by[6])/100)));
		//C���ѹ				Cvol
		ed.setPedCvol(Double.parseDouble(DF.format(getDate(by[7],by[8])/100)));
		//A����� 				Acur
		ed.setPedAcur(Double.parseDouble(DF.format(getDate(by[9],by[10])/1000*1)));
		//B�����				Bcur
		ed.setPedBcur(Double.parseDouble(DF.format(getDate(by[11],by[12])/1000*1)));
		//C�����				Ccur
		ed.setPedCcur(Double.parseDouble(DF.format(getDate(by[13],by[14])/1000*1)));
		//���й�����			Tpap
		ed.setPedTpap(Double.parseDouble(DF.format(getDate(by[15],by[16],by[17],by[18])*1)));
		//���޹�����			Tprp
		ed.setPedTprp(Double.parseDouble(DF.format(getDate(by[19],by[20],by[21],by[22])*1)));
		//�ܹ�������			Tppf
		ed.setPedTppf(Double.parseDouble(DF.format(getDate(by[23],by[24])/100)));
		//A���й����� 			Apap			40����CT��
		ed.setPedApap(Double.parseDouble(DF.format(getDate(by[25],by[26])*1)));
		//B���й�����			Bpap
		ed.setPedBpap(Double.parseDouble(DF.format(getDate(by[27],by[28])*1)));
		//C���й�����			Cpap
		ed.setPedCpap(Double.parseDouble(DF.format(getDate(by[29],by[30])*1)));
		//A���޹�����			Aprp
		ed.setPedAprp(Double.parseDouble(DF.format(getDate(by[31],by[32])*1)));
		//B���޹�����			Bprp
		ed.setPedBprp(Double.parseDouble(DF.format(getDate(by[33],by[34])*1)));
		//C���޹�����			Cprp
		ed.setPedCprp(Double.parseDouble(DF.format(getDate(by[35],by[36])*1)));
		//A�๦������			Appf
		ed.setPedAppf(Double.parseDouble(DF.format(getDate(by[37],by[38])/100)));
		//B�๦������			Bppf
		ed.setPedBppf(Double.parseDouble(DF.format(getDate(by[39],by[40])/100)));
		//C�๦������			Cppf
		ed.setPedCppf(Double.parseDouble(DF.format(getDate(by[41],by[42])/100)));
		//AB���ѹ				ABvol
		ed.setPedABvol(Double.parseDouble(DF.format(getDate(by[75],by[76])/100)));
		//BC���ѹ				BCvol
		ed.setPedBCvol(Double.parseDouble(DF.format(getDate(by[77],by[78])/100)));
		//CA���ѹ				CAvol
		ed.setPedCAvol(Double.parseDouble(DF.format(getDate(by[79],by[80])/100)));
		return ed;
	}


	//����������
	public static double getDate(byte by1,byte by2){
		double num = 0.0;
		byte[] byt = new byte[2];
		byt[0] = (byte)by1;
		byt[1] = (byte)by2;
		String str = TypeConvert.byteArrayToString(byt);
		num = Integer.parseInt(str, 16);
		return num;
	}

	//����������
	public static double getDate(byte by1,byte by2,byte by3,byte by4){
		double num = 0.0;
		int first = 0;
		int second = 0;
		StringBuffer sb = new StringBuffer();
		byte[] byt1 = new byte[2];
		byt1[0] = (byte)by1;
		byt1[1] = (byte)by2;
		byte[] byt2 = new byte[2];
		byt2[0] = (byte)by3;
		byt2[1] = (byte)by4;
		String str1 = TypeConvert.byteArrayToString(byt1);
		String str2 = TypeConvert.byteArrayToString(byt2);
		first = Integer.parseInt(str1, 16);
		second = Integer.parseInt(str2, 16);
		sb.append(first);
		sb.append(".");
		sb.append(second);
		num = Double.parseDouble(sb.toString());
		return num;
	}

	public static ElectricmeterMapper getElectricmeterMapper() {
		return electricmeterMapper;
	}

	public static void setElectricmeterMapper(ElectricmeterMapper electricmeterMapper) {
		ElectricmeterOrderRead.electricmeterMapper = electricmeterMapper;
	}


	
}
