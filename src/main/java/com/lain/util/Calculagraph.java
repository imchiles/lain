package com.lain.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Calculagraph {
	
	public static Map<String, Integer> map = new HashMap<String, Integer>();
	
	public static Timer time = new Timer();
	
	private String key;
	
	private int value;
	
	public void StatrTime(){
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(!map.isEmpty()){
					for(Map.Entry<String, Integer> entry : map.entrySet()){
						key = entry.getKey();
						value = entry.getValue();
						map.put(key, value-1>=0?value-1:value);
					}
				}
			}
		}, 60*1000, 60*1000);
	}
	public void SetMap(String s, int i){
		map.put(s, i);
	}
	public void RemoveMap(String s){
		map.remove(s);
	}
	public int GetMap(String s){
		return map.get(s);
	}
	public boolean isMap(String s){
		return map.containsKey(s);
	}
}
