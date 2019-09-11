package com.lain.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lain.entity.DeviceAlarm;

public class AlarmTime {

	private static Map<String, String[]> weekMap = new HashMap<String, String[]>();
	public static Map<String, Integer> timeMap = new HashMap<>();
	public static Map<String, List<Integer>> alarmMap = new HashMap<>();

	private SimpleDateFormat sdf = null;

	private int frontHour, frontMinute, backtHour, backMinute;

	public boolean isAlarmtTime(){
		boolean flag = false;
		sdf = new SimpleDateFormat("EEEE");
		String week = sdf.format(new Date());
		sdf = new SimpleDateFormat("HH:mm");
		int nowHour = Integer.parseInt(sdf.format(new Date()).split(":")[0]);
		int nowMinute = Integer.parseInt(sdf.format(new Date()).split(":")[1]);
		for(String s : weekMap.get(week)){
			String[] st0 = s.split("-")[0].split(":");
			String[] st1 = s.split("-")[1].split(":");
			frontHour = Integer.parseInt(st0[0]);
			frontMinute = Integer.parseInt(st0[1]);
			backtHour = Integer.parseInt(st1[0]);
			backMinute = Integer.parseInt(st1[1]);
			flag = (frontHour==nowHour||nowHour==backtHour)?((frontMinute<nowMinute&&nowMinute<backMinute)?true:false):((frontHour<nowHour&&nowHour<backtHour)?true:false);
		}
		return flag;
	}

	public void setAlarmInformation(List<DeviceAlarm> listda){
		for(DeviceAlarm da : listda){
			String[] s = new String[3];
			s[0] = da.getTimeQuantumOne();
			s[1] = da.getTimeQuantumTwo();
			s[2] = da.getTimeQuantumThree();
			weekMap.put(da.getWeek(), s);
			timeMap.put(da.getWeek(), da.getIntervalTime());
			List<Integer> list = new ArrayList<Integer>();
			list.add(da.getEmailStatus());
			list.add(da.getPhoneStatus());
			list.add(da.getSmsStatus());
			alarmMap.put(da.getWeek(), list);
		}
	}
}
