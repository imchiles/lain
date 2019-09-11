package com.lain.entity;

public class HumitureManage extends DeviceIp{
	private int ehmId;
	private int ehmDeviceAddress;
	private String ehmDeviceName;
	private double ehmTem;
	private double ehmHum;
	private double ehmMaxTem;
	private double ehmMinTem;
	private double ehmMaxHum;
	private double ehmMinHum;
	private int ehmStatus;
	private int intervalTime;
	private int diId;
	public int getEhmId() {
		return ehmId;
	}
	public void setEhmId(int ehmId) {
		this.ehmId = ehmId;
	}
	public int getEhmDeviceAddress() {
		return ehmDeviceAddress;
	}
	public void setEhmDeviceAddress(int ehmDeviceAddress) {
		this.ehmDeviceAddress = ehmDeviceAddress;
	}
	public String getEhmDeviceName() {
		return ehmDeviceName;
	}
	public void setEhmDeviceName(String ehmDeviceName) {
		this.ehmDeviceName = ehmDeviceName;
	}
	public double getEhmTem() {
		return ehmTem;
	}
	public void setEhmTem(double ehmTem) {
		this.ehmTem = ehmTem;
	}
	public double getEhmHum() {
		return ehmHum;
	}
	public void setEhmHum(double ehmHum) {
		this.ehmHum = ehmHum;
	}
	public double getEhmMaxTem() {
		return ehmMaxTem;
	}
	public void setEhmMaxTem(double ehmMaxTem) {
		this.ehmMaxTem = ehmMaxTem;
	}
	public double getEhmMinTem() {
		return ehmMinTem;
	}
	public void setEhmMinTem(double ehmMinTem) {
		this.ehmMinTem = ehmMinTem;
	}
	public double getEhmMaxHum() {
		return ehmMaxHum;
	}
	public void setEhmMaxHum(double ehmMaxHum) {
		this.ehmMaxHum = ehmMaxHum;
	}
	public double getEhmMinHum() {
		return ehmMinHum;
	}
	public void setEhmMinHum(double ehmMinHum) {
		this.ehmMinHum = ehmMinHum;
	}
	public int getEhmStatus() {
		return ehmStatus;
	}
	public void setEhmStatus(int ehmStatus) {
		this.ehmStatus = ehmStatus;
	}
	public int getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}
	public int getDiId() {
		return diId;
	}
	public void setDiId(int diId) {
		this.diId = diId;
	}
	@Override
	public String toString() {
		return "HumitureManage [ehmId=" + ehmId + ", ehmDeviceAddress=" + ehmDeviceAddress + ", ehmDeviceName="
				+ ehmDeviceName + ", ehmTem=" + ehmTem + ", ehmHum=" + ehmHum + ", ehmMaxTem=" + ehmMaxTem
				+ ", ehmMinTem=" + ehmMinTem + ", ehmMaxHum=" + ehmMaxHum + ", ehmMinHum=" + ehmMinHum + ", ehmStatus="
				+ ehmStatus + ", intervalTime=" + intervalTime + ", diId=" + diId + "]";
	}
}
