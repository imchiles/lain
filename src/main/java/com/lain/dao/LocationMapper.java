package com.lain.dao;

import java.util.List;

import com.lain.entity.LocationManagePojo;

public interface LocationMapper {
	List<Integer> findLocationAddress(int diId);

	/** 保存定位漏水实时状态及长度*/
	public void updLocationStatusAndLen(int status, double length, int address, int ipId);
	
	/** 通过设备地址获取定位漏水的名称及所在地址*/
	public LocationManagePojo getLocation(int address,int ipId);
}
