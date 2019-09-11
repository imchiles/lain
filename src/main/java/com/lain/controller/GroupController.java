package com.lain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.entity.Group;
import com.lain.service.GroupService;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	@RequestMapping(value="/findGroupAll",method=RequestMethod.GET)
	@ResponseBody
	public List<Group> findGroupAll(){
		return groupService.findGroupAll();
	}
}
