package com.lain.entity;

import java.util.Date;

public class Log {
	private int logId;
	private String logType;
	private String content;
	private int uId;
	private Date genTime;
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public Date getGenTime() {
		return genTime;
	}
	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}
	@Override
	public String toString() {
		return "Log [logId=" + logId + ", logType=" + logType + ", content=" + content + ", uId=" + uId + ", genTime="
				+ genTime + "]";
	}
}
