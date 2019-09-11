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
	
	public static byte FUNCTION_CODE = 0X03;	//功能码
	/** 获取电量仪的最大最小值 */
	public static Map<String, Map<String, String>> LIMIT = null;
	/** 噪声历史保存时间计算*/
	public static Map<String,Map<String, Integer>> COUNT = null;

	private static DecimalFormat DF = new DecimalFormat("0.00");

	private static ElectricmeterMapper electricmeterMapper;
	
	private static SocketUtil scoket = new SocketUtil();
	
	/**
	 * 初始化电量仪
	 */
	
	public static void getElectricmeterBGO(byte[] bytes, int diId, String ip, int Port) throws InterruptedException{

		if((bytes[1]&0x03) == FUNCTION_CODE){
			int address = getDeviceId(bytes[0]);
			ElectricmeterData ed = getData(bytes);
			ed.setDiId(diId);
			electricmeterMapper.updateElectricmeterRealTimeData(ed);	//更新电量仪实时数据
			saveElectricmeterHistory(ed, address, diId);
			//isAlarm(map,address,ipId,socket);		//保存电量仪报警记录
		}

	}

	public static void saveElectricmeterHistory(ElectricmeterData ed, int address, int diId){
		String key = "2:"+address+":"+diId;
		if(Calculagraph.map.containsKey(key)){
			Calculagraph.map.put(key, 0);
			calculagraph = new Calculagraph();
		}
		/** 保存历史记录*/
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
	 * 保存电量仪报警记录
	 * @param map
	 * @param deviceId
	 */
	public static void isAlarm(Map<String,Double> map,int address,int ipId,SocketUtil socket){
		String number = String.valueOf(ipId)+String.valueOf(address);
		StringBuffer sb = new StringBuffer();
		if(map.get("Avol")>Double.parseDouble(LIMIT.get(number).get("maxAvol"))){	//A相电压上限
			String info = map.get("Avol")+"V超过A相电压上限。";
			sb.append(info);
		} else if(map.get("Avol")<Double.parseDouble(LIMIT.get(number).get("minAvol"))){	//A相电压下限
			String info = map.get("Avol")+"V低于A相电压下限。";
			sb.append(info);
		}
		if(map.get("Bvol")>Double.parseDouble(LIMIT.get(number).get("maxBvol"))){	//B相电压上限
			String info = map.get("Bvol")+"V超过B相电压上限。";
			sb.append(info);
		} else if(map.get("Bvol")<Double.parseDouble(LIMIT.get(number).get("minBvol"))){	//B相电压下限
			String info = map.get("Bvol")+"V低于B相电压下限。";
			sb.append(info);
		}
		if(map.get("Cvol")>Double.parseDouble(LIMIT.get(number).get("maxCvol"))){	//C相电压上限
			String info = map.get("Cvol")+"V超过C相电压上限。";
			sb.append(info);
		} else if(map.get("Cvol")<Double.parseDouble(LIMIT.get(number).get("minCvol"))){	//C相电压下限
			String info = map.get("Cvol")+"V低于C相电压下限。";
			sb.append(info);
		}
		if(map.get("Acur")>Double.parseDouble(LIMIT.get(number).get("maxAcur"))){	//A相电流上限
			String info = map.get("Acur")+"A超过A相电流上限。";
			sb.append(info);
		} else if(map.get("Acur")<Double.parseDouble(LIMIT.get(number).get("minAcur"))){	//A相电流下限
			String info = map.get("Acur")+"A低于A相电流下限。";
			sb.append(info);
		}
		if(map.get("Bcur")>Double.parseDouble(LIMIT.get(number).get("maxBcur"))){	//B相电流上限
			String info = map.get("Bcur")+"A超过B相电流上限。";
			sb.append(info);
		} else if(map.get("Bcur")<Double.parseDouble(LIMIT.get(number).get("minBcur"))){	//B相电流下限
			String info = map.get("Bcur")+"A低于B相电流下限。";
			sb.append(info);
		}
		if(map.get("Ccur")>Double.parseDouble(LIMIT.get(number).get("maxCcur"))){	//C相电流上限
			String info = map.get("Ccur")+"A超过C相电流上限。";
			sb.append(info);
		} else if(map.get("Ccur")<Double.parseDouble(LIMIT.get(number).get("minCcur"))){	//C相电流下限
			String info = map.get("Ccur")+"A低于C相电流下限。";
			sb.append(info);
		}
		if(map.get("ABvol")>Double.parseDouble(LIMIT.get(number).get("maxABvol"))){	//AB相电压上限
			String info = map.get("ABvol")+"V超过AB相电压上限。";
			sb.append(info);
		} else if(map.get("ABvol")<Double.parseDouble(LIMIT.get(number).get("minABvol"))){	//AB相电压下限
			String info = map.get("ABvol")+"V低于AB相电压下限。";
			sb.append(info);
		}
		if(map.get("BCvol")>Double.parseDouble(LIMIT.get(number).get("maxBCvol"))){	//BC相电压上限
			String info = map.get("BCvol")+"V超过BC相电压上限。";
			sb.append(info);
		} else if(map.get("BCvol")<Double.parseDouble(LIMIT.get(number).get("minBCvol"))){	//BC相电压下限
			String info = map.get("BCvol")+"V低于BC相电压下限。";
			sb.append(info);
		}
		if(map.get("CAvol")>Double.parseDouble(LIMIT.get(number).get("maxCAvol"))){	//CA相电压上限
			String info = map.get("CAvol")+"V超过CA相电压上限。";
			sb.append(info);
		} else if(map.get("CAvol")<Double.parseDouble(LIMIT.get(number).get("minCAvol"))){	//CA相电压下限
			String info = map.get("CAvol")+"V低于CA相电压下限。";
			sb.append(info);
		}
		if(map.get("Apap")>Double.parseDouble(LIMIT.get(number).get("maxApap"))){	//A相有功功率上限
			String info = map.get("Apap")+"KW超过A相有功功率上限。";
			sb.append(info);
		} else if(map.get("Apap")<Double.parseDouble(LIMIT.get(number).get("minApap"))){	//A相有功功率下限
			String info = map.get("Apap")+"KW低于A相有功功率下限。";
			sb.append(info);
		}
		if(map.get("Bpap")>Double.parseDouble(LIMIT.get(number).get("maxBpap"))){	//B相有功功率上限
			String info = map.get("Bpap")+"KW超过B相有功功率上限。";
			sb.append(info);
		} else if(map.get("Bpap")<Double.parseDouble(LIMIT.get(number).get("minBpap"))){	//B相有功功率下限
			String info = map.get("Bpap")+"KW低于B相有功功率下限。";
			sb.append(info);
		}
		if(map.get("Cpap")>Double.parseDouble(LIMIT.get(number).get("maxCpap"))){	//C相有功功率上限
			String info = map.get("Cpap")+"KW超过C相有功功率上限。";
			sb.append(info);
		} else if(map.get("Cpap")<Double.parseDouble(LIMIT.get(number).get("minCpap"))){	//C相有功功率下限
			String info = map.get("Cpap")+"KW低于C相有功功率下限。";
			sb.append(info);
		}
		if(map.get("Aprp")>Double.parseDouble(LIMIT.get(number).get("maxAprp"))){	//A相无功功率上限
			String info = map.get("Aprp")+"Kvar超过A相无功功率上限。";
			sb.append(info);
		} else if(map.get("Aprp")<Double.parseDouble(LIMIT.get(number).get("minAprp"))){	//A相无功功率下限
			String info = map.get("Aprp")+"Kvar低于A相无功功率下限。";
			sb.append(info);
		}
		if(map.get("Bprp")>Double.parseDouble(LIMIT.get(number).get("maxBprp"))){	//B相无功功率上限
			String info = map.get("Bprp")+"Kvar超过B相无功功率上限。";
			sb.append(info);
		} else if(map.get("Bprp")<Double.parseDouble(LIMIT.get(number).get("minBprp"))){	//B相无功功率下限
			String info = map.get("Bprp")+"Kvar低于B相无功功率下限。";
			sb.append(info);
		}
		if(map.get("Cprp")>Double.parseDouble(LIMIT.get(number).get("maxCprp"))){	//C相无功功率上限
			String info = map.get("Cprp")+"Kvar超过C相无功功率上限。";
			sb.append(info);
		} else if(map.get("Cprp")<Double.parseDouble(LIMIT.get(number).get("minCprp"))){	//C相无功功率下限
			String info = map.get("Cprp")+"Kvar低于C相无功功率下限。";
			sb.append(info);
		}
		if(map.get("Appf")>Double.parseDouble(LIMIT.get(number).get("maxAppf"))){	//A相功率因素上限
			String info = map.get("Appf")+"超过A相功率因素上限。";
			sb.append(info);
		} else if(map.get("Appf")<Double.parseDouble(LIMIT.get(number).get("minAppf"))){	//A相功率因素下限
			String info = map.get("Appf")+"低于A相功率因素下限。";
			sb.append(info);
		}
		if(map.get("Bppf")>Double.parseDouble(LIMIT.get(number).get("maxBppf"))){	//B相功率因素上限
			String info = map.get("Bppf")+"超过B相功率因素上限。";
			sb.append(info);
		} else if(map.get("Bppf")<Double.parseDouble(LIMIT.get(number).get("minBppf"))){	//B相功率因素下限
			String info = map.get("Bppf")+"低于B相功率因素下限。";
			sb.append(info);
		}
		if(map.get("Cppf")>Double.parseDouble(LIMIT.get(number).get("maxCppf"))){	//C相功率因素上限
			String info = map.get("Cppf")+"超过C相功率因素上限。";
			sb.append(info);
		} else if(map.get("Cppf")<Double.parseDouble(LIMIT.get(number).get("minCppf"))){	//C相功率因素下限
			String info = map.get("Cppf")+"低于C相功率因素下限。";
			sb.append(info);
		}
		if(map.get("Tpap")>Double.parseDouble(LIMIT.get(number).get("maxTpap"))){	//总有功功率上限
			String info = map.get("Tpap")+"KW超过总有功功率上限。";
			sb.append(info);
		} else if(map.get("Tpap")<Double.parseDouble(LIMIT.get(number).get("minTpap"))){	//总有功功率下限
			String info = map.get("Tpap")+"KW低于总有功功率下限。";
			sb.append(info);
		}
		if(map.get("Tprp")>Double.parseDouble(LIMIT.get(number).get("maxTprp"))){	//总无功功率上限
			String info = map.get("Tprp")+"Kvar超过总无功功率上限。";
			sb.append(info);
		} else if(map.get("Tprp")<Double.parseDouble(LIMIT.get(number).get("minTprp"))){	//总无功功率下限
			String info = map.get("Tprp")+"Kvar低于总无功功率上限。";
			sb.append(info);
		}
		if(map.get("Tppf")>Double.parseDouble(LIMIT.get(number).get("maxTppf"))){	//总功率因素上限
			String info = map.get("Tppf")+"超过总功率因素上限。";
			sb.append(info);
		} else if(map.get("Tppf")<Double.parseDouble(LIMIT.get(number).get("minTppf"))){	//总功率因素下限
			String info = map.get("Tppf")+"低于总功率因素上限。";
			sb.append(info);
		}
		//		if(sb.length() > 0){
		//			boolean tempFlag = IntervalTime.CheckTime(number,AlarmStatus.ALARMINTERVAL);
		//			if(tempFlag){
		//				int id = Integer.parseInt(LIMIT.get(number).get("id"));
		//				String name = LIMIT.get(number).get("name");
		//				String location = LIMIT.get(number).get("location");
		//				String msg = location+"-"+name+":检测到"+sb;
		//				electricMeter.saveElectricMeterAlarm(id,ipId,msg);		//保存电量仪报警记录
		//				AlarmMonitor.startAlarmMonitor(msg,com);	//启动报警判断
		//			}
		//		} else{
		//			boolean flag = IntervalTime.intervalTime.containsKey(number);
		//			if(flag){
		//				IntervalTime.intervalTime.remove(number);
		//			}
		//		}
	}

	//电量仪设备地址
	public static int getDeviceId(byte by1){
		byte[] by = new byte[1];
		by[0] = by1;
		BigInteger bi = new BigInteger(1, by);
		String str = bi.toString(16);
		int deviceId = Integer.parseInt(str.toUpperCase(),16);
		return deviceId;
	}

	//获取电量仪数据
	public static Map<String,Double> getData(byte[] by,byte[] by1){

		//电流变比
		double Abi=getDate(by1[3],by1[4]);
		//		System.out.println(HumitureTypeConvert.byteArrayToString(by1)+ " "+TypeConvert.byteArrayToString(by1).substring(0,2)+"变比="+Abi);
		Map<String,Double> map = new HashMap<String,Double>();
		double Avol = getDate(by[3],by[4]);	//A相电压
		map.put("Avol", Double.parseDouble(DF.format(Avol/100)));
		double Bvol = getDate(by[5],by[6]);	//B相电压
		map.put("Bvol", Double.parseDouble(DF.format(Bvol/100)));
		double Cvol = getDate(by[7],by[8]);	//C相电压
		map.put("Cvol", Double.parseDouble(DF.format(Cvol/100)));
		double Acur = getDate(by[9],by[10])/1000*Abi;;	//A相电流
		map.put("Acur", Double.parseDouble(DF.format(Acur)));
		double Bcur = getDate(by[11],by[12])/1000*Abi;;	//B相电流
		map.put("Bcur", Double.parseDouble(DF.format(Bcur)));
		double Ccur = getDate(by[13],by[14])/1000*Abi;;	//C相电流
		map.put("Ccur", Double.parseDouble(DF.format(Ccur)));

		double Tpap = getDate(by[15],by[16],by[17],by[18])*1;	//总有功功率
		map.put("Tpap", Double.parseDouble(DF.format(Tpap/50)));
		double Tprp = getDate(by[19],by[20],by[21],by[22])*1;	//总无功功率
		map.put("Tprp", Double.parseDouble(DF.format(Tprp/50)));
		double Tppf = getDate(by[23],by[24]);	//总功率因素
		map.put("Tppf", Double.parseDouble(DF.format(Tppf/100)));
		double Apap = getDate(by[25],by[26])*1;	//A相有功功率 			40代表CT比
		map.put("Apap", Double.parseDouble(DF.format(Apap/50)));
		double Bpap = getDate(by[27],by[28])*1;	//B相有功功率
		map.put("Bpap", Double.parseDouble(DF.format(Bpap/50)));
		double Cpap = getDate(by[29],by[30])*1;	//C相有功功率
		map.put("Cpap", Double.parseDouble(DF.format(Cpap/50)));
		double Aprp = getDate(by[31],by[32])*1;	//A相无功功率
		map.put("Aprp", Double.parseDouble(DF.format(Aprp/50)));
		double Bprp = getDate(by[33],by[34])*1;	//B相无功功率
		map.put("Bprp", Double.parseDouble(DF.format(Bprp/50)));
		double Cprp = getDate(by[35],by[36])*1;	//C相无功功率
		map.put("Cprp", Double.parseDouble(DF.format(Cprp/50)));
		double Appf = getDate(by[37],by[38]);	//A相功率因素
		map.put("Appf", Double.parseDouble(DF.format(Appf/100)));
		double Bppf = getDate(by[39],by[40]);	//B相功率因素
		map.put("Bppf", Double.parseDouble(DF.format(Bppf/100)));
		double Cppf = getDate(by[41],by[42]);	//C相功率因素
		map.put("Cppf", Double.parseDouble(DF.format(Cppf/100)));
		double ABvol = getDate(by[75],by[76]);	//AB相电压
		map.put("ABvol", Double.parseDouble(DF.format(ABvol/100)));
		double BCvol = getDate(by[77],by[78]);	//BC相电压
		map.put("BCvol", Double.parseDouble(DF.format(BCvol/100)));
		double CAvol = getDate(by[79],by[80]);	//CA相电压
		map.put("CAvol", Double.parseDouble(DF.format(CAvol/100)));
		return map;
	}



	//增加
	//获取电量仪数据
	public static ElectricmeterData getData(byte[] by){
		ElectricmeterData ed = new ElectricmeterData();
		//A相电压				Avol
		ed.setPedAvol(Double.parseDouble(DF.format(getDate(by[3],by[4])/100)));
		//B相电压				Bvol
		ed.setPedBvol(Double.parseDouble(DF.format(getDate(by[5],by[6])/100)));
		//C相电压				Cvol
		ed.setPedCvol(Double.parseDouble(DF.format(getDate(by[7],by[8])/100)));
		//A相电流 				Acur
		ed.setPedAcur(Double.parseDouble(DF.format(getDate(by[9],by[10])/1000*1)));
		//B相电流				Bcur
		ed.setPedBcur(Double.parseDouble(DF.format(getDate(by[11],by[12])/1000*1)));
		//C相电流				Ccur
		ed.setPedCcur(Double.parseDouble(DF.format(getDate(by[13],by[14])/1000*1)));
		//总有功功率			Tpap
		ed.setPedTpap(Double.parseDouble(DF.format(getDate(by[15],by[16],by[17],by[18])*1)));
		//总无功功率			Tprp
		ed.setPedTprp(Double.parseDouble(DF.format(getDate(by[19],by[20],by[21],by[22])*1)));
		//总功率因素			Tppf
		ed.setPedTppf(Double.parseDouble(DF.format(getDate(by[23],by[24])/100)));
		//A相有功功率 			Apap			40代表CT比
		ed.setPedApap(Double.parseDouble(DF.format(getDate(by[25],by[26])*1)));
		//B相有功功率			Bpap
		ed.setPedBpap(Double.parseDouble(DF.format(getDate(by[27],by[28])*1)));
		//C相有功功率			Cpap
		ed.setPedCpap(Double.parseDouble(DF.format(getDate(by[29],by[30])*1)));
		//A相无功功率			Aprp
		ed.setPedAprp(Double.parseDouble(DF.format(getDate(by[31],by[32])*1)));
		//B相无功功率			Bprp
		ed.setPedBprp(Double.parseDouble(DF.format(getDate(by[33],by[34])*1)));
		//C相无功功率			Cprp
		ed.setPedCprp(Double.parseDouble(DF.format(getDate(by[35],by[36])*1)));
		//A相功率因素			Appf
		ed.setPedAppf(Double.parseDouble(DF.format(getDate(by[37],by[38])/100)));
		//B相功率因素			Bppf
		ed.setPedBppf(Double.parseDouble(DF.format(getDate(by[39],by[40])/100)));
		//C相功率因素			Cppf
		ed.setPedCppf(Double.parseDouble(DF.format(getDate(by[41],by[42])/100)));
		//AB相电压				ABvol
		ed.setPedABvol(Double.parseDouble(DF.format(getDate(by[75],by[76])/100)));
		//BC相电压				BCvol
		ed.setPedBCvol(Double.parseDouble(DF.format(getDate(by[77],by[78])/100)));
		//CA相电压				CAvol
		ed.setPedCAvol(Double.parseDouble(DF.format(getDate(by[79],by[80])/100)));
		return ed;
	}


	//解析电量仪
	public static double getDate(byte by1,byte by2){
		double num = 0.0;
		byte[] byt = new byte[2];
		byt[0] = (byte)by1;
		byt[1] = (byte)by2;
		String str = TypeConvert.byteArrayToString(byt);
		num = Integer.parseInt(str, 16);
		return num;
	}

	//解析电量仪
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
