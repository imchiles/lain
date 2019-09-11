package com.lain.entity;

public class PoisonousAlarmPojo {
	/** @id 有毒气体报警地址*/
	private int id;
	/** @location 有毒气体报警位置*/
	private String location;
	/** @name 有毒气体报警设备名称*/
	private String name;
	/** @alarmData 有毒气体报警值*/
	private double alarmData;
	/** @info 有毒气体报警信息*/
	private String info;
	/** @time 有毒气体报警时间*/
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
