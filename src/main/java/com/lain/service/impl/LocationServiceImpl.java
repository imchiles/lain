package com.lain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lain.dao.LocationMapper;
import com.lain.entity.LocationManage;
import com.lain.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationMapper locationMapper;
	
	@Override
	public List<LocationManage> getLocationAll() throws Exception {
		return locationMapper.getLocationAll();
	}

	@Override
	public boolean updateLocationStatusAndLen(int elm_status, double elm_length, int elm_address, int di_id)
			throws Exception {
		// TODO Auto-generated method stub
		return locationMapper.updateLocationStatusAndLen(elm_status, elm_length, elm_address, di_id);
	}

	@Override
	public List<Integer> findLocationAddress(int di_id) {
		// TODO Auto-generated method stub
		return locationMapper.findLocationAddress(di_id);
	}


	

}
