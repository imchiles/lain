package com.lain.entity;

import java.util.Date;

public class HumitureHistory {
	private int ehhId;
	private double ehhTem;
	private double ehhHum;
	private Date ehhTime;
	private int ehmDeviceAddress;
	private int diId;
	public int getEhhId() {
		return ehhId;
	}
	public void setEhhId(int ehhId) {
		this.ehhId = ehhId;
	}
	public double getEhhTem() {
		return ehhTem;
	}
	public void setEhhTem(double ehhTem) {
		this.ehhTem = ehhTem;
	}
	public double getEhhHum() {
		return ehhHum;
	}
	public void setEhhHum(double ehhHum) {
		this.ehhHum = ehhHum;
	}
	public Date getEhhTime() {
		return ehhTime;
	}
	public void setEhhTime(Date ehhTime) {
		this.ehhTime = ehhTime;
	}
	public int getEhmDeviceAddress() {
		return ehmDeviceAddress;
	}
	public void setEhmDeviceAddress(int ehmDeviceAddress) {
		this.ehmDeviceAddress = ehmDeviceAddress;
	}
	public int getDiId() {
		return diId;
	}
	public void setDiId(int diId) {
		this.diId = diId;
	}
	@Override
	public String toString() {
		return "HumitureHistory [ehhId=" + ehhId + ", ehhTem=" + ehhTem + ", ehhHum=" + ehhHum + ", ehhTime=" + ehhTime
				+ ", ehmDeviceAddress=" + ehmDeviceAddress + ", diId=" + diId + "]";
	}
}
