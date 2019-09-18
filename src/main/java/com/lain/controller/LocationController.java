package com.lain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.dao.LocationMapper;
import com.lain.entity.LocationManagePojo;
import com.lain.service.LocationService;

@Controller
@RequestMapping("/Location")
public class LocationController {

	@Autowired
	private LocationService locationService;
	@Autowired
	private LocationMapper locationMapper;
	
	@ResponseBody
	@RequestMapping(value = "/selectLocationAll")
	public List<LocationManagePojo> selectLocationAll() throws Exception {
		return locationService.selectLocationAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getLocation")
	public LocationManagePojo getLocation() throws Exception {
		System.out.println("hh");
		return locationService.getLocation(1,2);
	}
}
