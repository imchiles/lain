package com.lain.entity;

public class DeviceAlarm {
	private int daId;
	private String week;
	private String timeQuantumOne;
	private String timeQuantumTwo;
	private String timeQuantumThree;
	private int emailStatus;
	private int smsStatus;
	private int soundLightStatus;
	private int phoneStatus;
	private int intervalTime;
	public int getDaId() {
		return daId;
	}
	public void setDaId(int daId) {
		this.daId = daId;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getTimeQuantumOne() {
		return timeQuantumOne;
	}
	public void setTimeQuantumOne(String timeQuantumOne) {
		this.timeQuantumOne = timeQuantumOne;
	}
	public String getTimeQuantumTwo() {
		return timeQuantumTwo;
	}
	public void setTimeQuantumTwo(String timeQuantumTwo) {
		this.timeQuantumTwo = timeQuantumTwo;
	}
	public String getTimeQuantumThree() {
		return timeQuantumThree;
	}
	public void setTimeQuantumThree(String timeQuantumThree) {
		this.timeQuantumThree = timeQuantumThree;
	}
	public int getEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(int emailStatus) {
		this.emailStatus = emailStatus;
	}
	public int getSmsStatus() {
		return smsStatus;
	}
	public void setSmsStatus(int smsStatus) {
		this.smsStatus = smsStatus;
	}
	public int getSoundLightStatus() {
		return soundLightStatus;
	}
	public void setSoundLightStatus(int soundLightStatus) {
		this.soundLightStatus = soundLightStatus;
	}
	public int getPhoneStatus() {
		return phoneStatus;
	}
	public void setPhoneStatus(int phoneStatus) {
		this.phoneStatus = phoneStatus;
	}
	public int getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}
	@Override
	public String toString() {
		return "DeviceAlarm [daId=" + daId + ", week=" + week + ", timeQuantumOne=" + timeQuantumOne
				+ ", timeQuantumTwo=" + timeQuantumTwo + ", timeQuantumThree=" + timeQuantumThree + ", emailStatus="
				+ emailStatus + ", smsStatus=" + smsStatus + ", soundLightStatus=" + soundLightStatus + ", phoneStatus="
				+ phoneStatus + ", intervalTime=" + intervalTime + "]";
	}
}
