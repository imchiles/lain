package com.lain.controller;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.dao.Ktr8052Mapper;
import com.lain.entity.Ktr8052;
import com.lain.service.Ktr8052Service;

@Controller
@RequestMapping("/ktr8052")
public class Ktr8052Controller {
	
	@Autowired
	private Ktr8052Service ktr8052Service;
	
	
	@ResponseBody
	@RequestMapping(value = "getKtr8052/{id}",method = RequestMethod.GET)
	public Ktr8052 getKtr8052(@PathVariable("id") int id) throws Exception {
		return ktr8052Service.getKtr8052(id);
	}

}
