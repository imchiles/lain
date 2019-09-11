package com.lain.entity;

public class Device {
	private int dId;
	private String name;
	private int isShow;
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	@Override
	public String toString() {
		return "Device [dId=" + dId + ", name=" + name + ", isShow=" + isShow + "]";
	}
}
