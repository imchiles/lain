package com.lain.entity;

import java.util.List;

public class Group {
	private int gId;
	private int parentId;
	private String gName;
	private List<Group> children;
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
	
	public void setChildren(List<Group> children) {
		this.children = children;
	}
	public List<Group> getChildren() {
		return children;
	}
	public void tree(List<Group> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Group [gId=" + gId + ", parentId=" + parentId + ", gName=" + gName + ", children=" + children + "]";
	}
}
