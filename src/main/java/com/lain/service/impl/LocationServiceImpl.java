package com.lain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lain.dao.LocationMapper;
import com.lain.entity.LocationManagePojo;
import com.lain.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationMapper locationMapper;

	@Override
	public List<LocationManagePojo> selectLocationAll() {
		List<LocationManagePojo> list = locationMapper.selectLocationAll();
		for(LocationManagePojo po:list) {
			System.out.println(po.getElm_name());
		}
		return list;
	}

	@Override
	public LocationManagePojo getLocation(int elm_address, int di_id) {
		// TODO Auto-generated method stub
		return locationMapper.getLocation(elm_address, di_id);
	}
	

}
