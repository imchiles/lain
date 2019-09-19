package com.lain.entity;

public class DeviceIp extends Device{
	private int diId;
	private String diAddress;
	private int diPort;
	private int diIsConnect;
	private int diOperate;
	private int dId;
	private int gId;
	public int getDiId() {
		return diId;
	}
	public void setDiId(int diId) {
		this.diId = diId;
	}
	public String getDiAddress() {
		return diAddress;
	}
	public void setDiAddress(String diAddress) {
		this.diAddress = diAddress;
	}
	public int getDiPort() {
		return diPort;
	}
	public void setDiPort(int diPort) {
		this.diPort = diPort;
	}
	public int getDiIsConnect() {
		return diIsConnect;
	}
	public void setDiIsConnect(int diIsConnect) {
		this.diIsConnect = diIsConnect;
	}
	public int getDiOperate() {
		return diOperate;
	}
	public void setDiOperate(int diOperate) {
		this.diOperate = diOperate;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public int getgId() {
		return gId;
	}
	public void setgId(int gId) {
		this.gId = gId;
	}
	@Override
	public String toString() {
		return "DeviceIp [diId=" + diId + ", diAddress=" + diAddress + ", diPort=" + diPort + ", diIsConnect="
				+ diIsConnect + ", diOperate=" + diOperate + ", dId=" + dId + ", gId=" + gId + "]";
	}
}
