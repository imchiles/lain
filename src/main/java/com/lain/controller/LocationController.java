package com.lain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.entity.LocationPojo;
import com.lain.service.LocationService;

@Controller
@RequestMapping("/Location")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@ResponseBody
	@RequestMapping(value = "getLocationAll",method = RequestMethod.GET)
	public List<LocationPojo> getLocationAll()throws Exception{
		return locationService.getLocationAll();
	}
	
	
	
}
