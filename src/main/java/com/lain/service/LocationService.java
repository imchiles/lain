package com.lain.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.LocationManage;



public interface LocationService {
	
	List<Integer> findLocationAddress(int di_id);
	
	List<LocationManage> getLocationAll()throws Exception;
	
	boolean updateLocationStatusAndLen(
			int elm_status, 
			double elm_length,
			int elm_address, 
			int di_id)throws Exception;
}
