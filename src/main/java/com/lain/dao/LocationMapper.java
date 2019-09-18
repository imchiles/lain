package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.LocationManagePojo;

public interface LocationMapper {
	List<Integer> findLocationAddress(int diId);

	/** 保存定位漏水实时状态及长度*/
	public void updLocationStatusAndLen(@Param("elm_status")int elm_status,@Param("elm_length")double elm_length,@Param("elm_address")int elm_address,@Param("di_id") int di_id);
	
	/** 通过设备地址获取定位漏水的名称及所在地址*/
	public LocationManagePojo getLocation(@Param("elm_address")int elm_address,@Param("di_id")int di_id);
	
	public List<LocationManagePojo> selectLocationAll();
}
