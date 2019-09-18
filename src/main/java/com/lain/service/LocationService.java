package com.lain.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.LocationManagePojo;

public interface LocationService {

	LocationManagePojo getLocation(int elm_address,int di_id);
	List<LocationManagePojo> selectLocationAll();
}
