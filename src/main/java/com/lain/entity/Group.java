package com.lain.entity;

public class Group {
	private int gId;
	private int parentId;
	private String gName;
	public int getgId() {
		return gId;
	}
	public void setgId(int gId) {
		this.gId = gId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	@Override
	public String toString() {
		return "Group [gId=" + gId + ", parentId=" + parentId + ", gName=" + gName + "]";
	}
}
