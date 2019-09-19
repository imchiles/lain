package com.lain.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.LocationPojo;

public interface LocationService {
	
	List<LocationPojo> getLocationAll()throws Exception;
}
