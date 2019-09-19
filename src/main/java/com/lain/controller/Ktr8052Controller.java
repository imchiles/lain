package com.lain.controller;


import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.dao.Ktr8052Mapper;
import com.lain.entity.Ktr8052;
import com.lain.entity.Ktr8052Alarm;
import com.lain.entity.LocationPojo;
import com.lain.entity.Ktr8052Pojo.Ktr8052StatusPojo;
import com.lain.service.Ktr8052Service;
import com.lain.service.LocationService;

@Controller
@RequestMapping("/ktr8052")
public class Ktr8052Controller {
	
	@Autowired
	private Ktr8052Service ktr8052Service;
	
	//获取8052设备信息
	@ResponseBody
	@RequestMapping(value = "getKtr8052/{k_id}",method = RequestMethod.GET)
	public List<Ktr8052> getKtr8052(@PathVariable("k_id") int k_id) throws Exception {
		return ktr8052Service.getKtr8052(k_id);
	}
	
	//获取包括IP地址端口号的信息
	@ResponseBody
	@RequestMapping(value = "findKtr8052All/{d_id}",method = RequestMethod.GET)
	public List<Ktr8052> findKtr8052All(@PathVariable("d_id") int d_id) throws Exception {
		return ktr8052Service.findKtr8052All(d_id);
	}
	
	//获取8052报警信息
	@ResponseBody
	@RequestMapping(value = "findKtr8052Alarm",method = RequestMethod.GET)
	public List<Ktr8052Alarm> findKtr8052All() throws Exception {
		return ktr8052Service.findKtr8052Alarm();
	}
	
	//获取8052设备状态
		@ResponseBody
		@RequestMapping(value = "getKtr8052Status/{k_id}",method = RequestMethod.GET)
		public List<Ktr8052StatusPojo> getKtr8052Status(@PathVariable("k_id") int k_id) throws Exception {
			return ktr8052Service.getKtr8052Status(k_id);
		}

}
