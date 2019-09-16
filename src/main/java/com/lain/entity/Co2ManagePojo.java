package com.lain.entity;

public class Co2ManagePojo {
	/** @id Co2管理地址*/
	private int id;
	/** @address Co2设备地址*/
	private int address;
	/** @name Co2设备名称*/
	private String name;
	/** @alarmData 报警值*/
	private double alarmData;
	/** @currentData 当前值*/
	private double currentData;
	/** @locationPojo 设备所在位置*/
	private DeviceLocationPojo deviceLocationPojo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCurrentData() {
		return currentData;
	}
	public void setCurrentData(double currentData) {
		this.currentData = currentData;
	}
	public DeviceLocationPojo getDeviceLocationPojo() {
		return deviceLocationPojo;
	}
	public void setDeviceLocationPojo(DeviceLocationPojo deviceLocationPojo) {
		this.deviceLocationPojo = deviceLocationPojo;
	}
	public double getAlarmData() {
		return alarmData;
	}
	public void setAlarmData(double alarmData) {
		this.alarmData = alarmData;
	}
}
