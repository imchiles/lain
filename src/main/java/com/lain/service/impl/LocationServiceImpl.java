package com.lain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lain.dao.LocationMapper;
import com.lain.entity.LocationPojo;
import com.lain.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationMapper locationMapper;
	
	@Override
	public List<LocationPojo> getLocationAll() throws Exception {
		return locationMapper.getLocationAll();
	}


	

}
