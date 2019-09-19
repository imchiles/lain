package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.LocationPojo;

public interface LocationMapper {
	
	List<Integer> findLocationAddress(int di_id);
	
	boolean updateLocationStatusAndLen(
			@Param("elm_status")int elm_status, 
			@Param("elm_length")double elm_length,
			@Param("elm_address")int elm_address, 
			@Param("elm_di_id")int di_id)throws Exception;

	LocationPojo getLocation(
			@Param("elm_address")int elm_address,
			@Param("di_id")int di_id)throws Exception;
	
	List<LocationPojo> getLocationAll()throws Exception;
}
