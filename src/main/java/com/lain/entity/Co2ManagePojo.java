package com.lain.entity;

public class Co2ManagePojo {
	/** @id Co2�����ַ*/
	private int id;
	/** @address Co2�豸��ַ*/
	private int address;
	/** @name Co2�豸����*/
	private String name;
	/** @alarmData ����ֵ*/
	private double alarmData;
	/** @currentData ��ǰֵ*/
	private double currentData;
	/** @locationPojo �豸����λ��*/
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
