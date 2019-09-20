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
	
	//获取定位漏水地址，这里不做接口使用
	@ResponseBody
	@RequestMapping(value = "findLocationAddress",method = RequestMethod.GET)
	public List<Integer> findLocationAddress()throws Exception{
		return locationService.findLocationAddress(2);
	}
	
	//获取定位漏水所有信息
	@ResponseBody
	@RequestMapping(value = "getLocationAll",method = RequestMethod.GET)
	public List<LocationManage> getLocationAll()throws Exception{
		return locationService.getLocationAll();
	}
	
	//更新定位漏水状态，不做接口使用
	@ResponseBody
	@RequestMapping(value = "updateLocationStatusAndLen")
	public boolean updateLocationStatusAndLen() throws Exception {
		return locationService.updateLocationStatusAndLen(1, 5, 1, 2);
	}
	
}
