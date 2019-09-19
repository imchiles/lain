package com.lain.entity;

public class Ktr8052 extends DeviceIp{
	private int id;
	private String name;
	private int address;
	private String gallery;
	private int status;
	private int kId;
	private int diId;
	private String k_name;
	private String g_name;
	
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getK_name() {
		return k_name;
	}
	public void setK_name(String k_name) {
		this.k_name = k_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public String getGallery() {
		return gallery;
	}
	public void setGallery(String gallery) {
		this.gallery = gallery;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getkId() {
		return kId;
	}
	public void setkId(int kId) {
		this.kId = kId;
	}
	public int getDiId() {
		return diId;
	}
	public void setDiId(int diId) {
		this.diId = diId;
	}
	@Override
	public String toString() {
		return "Ktr8052 [id=" + id + ", name=" + name + ", address=" + address + ", gallery=" + gallery + ", status="
				+ status + ", kId=" + kId + ", diId=" + diId + "]";
	}
	
}
