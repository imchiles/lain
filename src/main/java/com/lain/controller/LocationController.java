package com.lain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.entity.LocationManage;
import com.lain.service.LocationService;

@Controller
@RequestMapping("/Location")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@ResponseBody
	@RequestMapping(value = "findLocationAddress",method = RequestMethod.GET)
	public List<Integer> findLocationAddress()throws Exception{
		return locationService.findLocationAddress(2);
	}
	
	@ResponseBody
	@RequestMapping(value = "getLocationAll",method = RequestMethod.GET)
	public List<LocationManage> getLocationAll()throws Exception{
		return locationService.getLocationAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "updateLocationStatusAndLen")
	public boolean updateLocationStatusAndLen() {
		boolean b = false;
		try {
			b = locationService.updateLocationStatusAndLen(1, 5, 1, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}
	
	
	
}
