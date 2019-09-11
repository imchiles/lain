package com.lain.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HumitureAlarm {
	private int ehaId;
	private int dId;
	private int diId;
	private String ehaInfo;
	private Date ehaTime;
	public int getEhaId() {
		return ehaId;
	}
	public void setEhaId(int ehaId) {
		this.ehaId = ehaId;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public int getDiId() {
		return diId;
	}
	public void setDiId(int diId) {
		this.diId = diId;
	}
	public String getEhaInfo() {
		return ehaInfo;
	}
	public void setEhaInfo(String ehaInfo) {
		this.ehaInfo = ehaInfo;
	}
	public String getEhaTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(ehaTime);
	}
	public void setEhaTime(Date ehaTime) {
		this.ehaTime = ehaTime;
	}
	@Override
	public String toString() {
		return "HumitureAlarm [ehaId=" + ehaId + ", dId=" + dId + ", diId=" + diId + ", ehaInfo=" + ehaInfo
				+ ", ehaTime=" + ehaTime + "]";
	}
}
