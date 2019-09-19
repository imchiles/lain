package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.LocationManage;

public interface LocationMapper {
	
	List<Integer> findLocationAddress(int di_id);
	
	boolean updateLocationStatusAndLen(
			@Param("elm_status")int elm_status, 
			@Param("elm_length")double elm_length,
			@Param("elm_address")int elm_address, 
			@Param("di_id")int di_id)throws Exception;

	LocationManage getLocation(
			@Param("elm_address")int elm_address,
			@Param("di_id")int di_id)throws Exception;
	
	List<LocationManage> getLocationAll()throws Exception;
}
