package com.lain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.entity.ElectricmeterManage;
import com.lain.service.ElectricmeterServcie;

@Controller
@RequestMapping("/Electricmeter")
public class ElectricmeterController {
	@Autowired
	private ElectricmeterServcie electricmeterServcie;
	@RequestMapping(value="/findElectricmeterAll",method=RequestMethod.GET)
	@ResponseBody
	public List<ElectricmeterManage> findElectricmeterAll(){
		return electricmeterServcie.findElectricmeterAll();
	}
	@RequestMapping(value="/insertElectricmeterManage",method=RequestMethod.POST)
	@ResponseBody
	public boolean insertElectricmeterManage(@RequestBody ElectricmeterManage em){
		return electricmeterServcie.insertElectricmeterManage(em);
	}
	@RequestMapping(value="/updateElectricmeterManage",method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateElectricmeterManage(@RequestBody ElectricmeterManage em){
		return electricmeterServcie.updateElectricmeterManage(em);
	}
	@RequestMapping(value="/deleteElectricmeterManage/{pemId}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteElectricmeterManage(@PathVariable("pemId") int pemId){
		return electricmeterServcie.deleteElectricmeterManage(pemId);
	}
}
