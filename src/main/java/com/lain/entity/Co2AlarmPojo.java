package com.lain.entity;

public class Co2AlarmPojo {
	/** @id Co2报警地址*/
	private int id;
	/** @location Co2报警位置*/
	private String location;
	/** @name Co2报警设备名称*/
	private String name;
	/** @alarmData Co2报警值*/
	private double alarmData;
	/** @info Co2报警信息*/
	private String info;
	/** @time Co2报警时间*/
	private String time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAlarmData() {
		return alarmData;
	}
	public void setAlarmData(double alarmData) {
		this.alarmData = alarmData;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
