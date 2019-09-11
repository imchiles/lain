package com.lain.entity;

import java.util.Date;

public class ElectricmeterHistory {
	private int pehId;
	private double pehAvol;
	private double pehAcur;
	private double pehBvol;
	private double pehBcur;
	private double pehCvol;
	private double pehCcur;
	private double pehTpap;
	private double pehElequantity;
	private double pehElecharge;
	private String pehQuetime;
	private double pehUserdTime;
	private Date pehTime;
	private int diId;
	public int getPehId() {
		return pehId;
	}
	public void setPehId(int pehId) {
		this.pehId = pehId;
	}
	public double getPehAvol() {
		return pehAvol;
	}
	public void setPehAvol(double pehAvol) {
		this.pehAvol = pehAvol;
	}
	public double getPehAcur() {
		return pehAcur;
	}
	public void setPehAcur(double pehAcur) {
		this.pehAcur = pehAcur;
	}
	public double getPehBvol() {
		return pehBvol;
	}
	public void setPehBvol(double pehBvol) {
		this.pehBvol = pehBvol;
	}
	public double getPehBcur() {
		return pehBcur;
	}
	public void setPehBcur(double pehBcur) {
		this.pehBcur = pehBcur;
	}
	public double getPehCvol() {
		return pehCvol;
	}
	public void setPehCvol(double pehCvol) {
		this.pehCvol = pehCvol;
	}
	public double getPehCcur() {
		return pehCcur;
	}
	public void setPehCcur(double pehCcur) {
		this.pehCcur = pehCcur;
	}
	public double getPehTpap() {
		return pehTpap;
	}
	public void setPehTpap(double pehTpap) {
		this.pehTpap = pehTpap;
	}
	public double getPehElequantity() {
		return pehElequantity;
	}
	public void setPehElequantity(double pehElequantity) {
		this.pehElequantity = pehElequantity;
	}
	public double getPehElecharge() {
		return pehElecharge;
	}
	public void setPehElecharge(double pehElecharge) {
		this.pehElecharge = pehElecharge;
	}
	public String getPehQuetime() {
		return pehQuetime;
	}
	public void setPehQuetime(String pehQuetime) {
		this.pehQuetime = pehQuetime;
	}
	public double getPehUserdTime() {
		return pehUserdTime;
	}
	public void setPehUserdTime(double pehUserdTime) {
		this.pehUserdTime = pehUserdTime;
	}
	public Date getPehTime() {
		return pehTime;
	}
	public void setPehTime(Date pehTime) {
		this.pehTime = pehTime;
	}
	public int getDiId() {
		return diId;
	}
	public void setDiId(int diId) {
		this.diId = diId;
	}
	@Override
	public String toString() {
		return "ElectricmeterHistory [pehId=" + pehId + ", pehAvol=" + pehAvol + ", pehAcur=" + pehAcur + ", pehBvol="
				+ pehBvol + ", pehBcur=" + pehBcur + ", pehCvol=" + pehCvol + ", pehCcur=" + pehCcur + ", pehTpap="
				+ pehTpap + ", pehElequantity=" + pehElequantity + ", pehElecharge=" + pehElecharge + ", pehQuetime="
				+ pehQuetime + ", pehUserdTime=" + pehUserdTime + ", pehTime=" + pehTime + ", diId=" + diId + "]";
	}
}
