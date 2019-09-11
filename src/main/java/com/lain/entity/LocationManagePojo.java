package com.lain.entity;

public class LocationManagePojo {
	/** @id ��λ©ˮ�����ַ*/
	private int id;
	/** @name ��λ©ˮ�豸����*/
	private String name;
	/** @address ��λ©ˮ����λ��*/
	private int address;
	/** @length ��λ©ˮͨ��*/
	private double length;
	/** @status ��λ©ˮ״̬*/
	private int status;
	/** @locationPojo �豸����λ��*/
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
