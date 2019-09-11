package com.lain.entity;

public class LocationManagePojo {
	/** @id 定位漏水管理地址*/
	private int id;
	/** @name 定位漏水设备名称*/
	private String name;
	/** @address 定位漏水报警位置*/
	private int address;
	/** @length 定位漏水通道*/
	private double length;
	/** @status 定位漏水状态*/
	private int status;
	/** @locationPojo 设备所在位置*/
	private DeviceLocationPojo deviceLocationPojo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public DeviceLocationPojo getDeviceLocationPojo() {
		return deviceLocationPojo;
	}
	public void setDeviceLocationPojo(DeviceLocationPojo deviceLocationPojo) {
		this.deviceLocationPojo = deviceLocationPojo;
	}

}
