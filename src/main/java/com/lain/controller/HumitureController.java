package com.lain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.entity.HumitureAlarm;
import com.lain.entity.HumitureManage;
import com.lain.service.HumitureService;

@Controller
@RequestMapping("/humiture")
public class HumitureController {
	@Autowired
	private HumitureService humitureService;
	@RequestMapping(value="/findHumitureManageAll",method=RequestMethod.GET)
	@ResponseBody
	public List<HumitureManage> findHumitureManageAll(){
		return humitureService.findHumitureManageAll();
	}
	@RequestMapping(value="/insertHumitureManage",method=RequestMethod.POST)
	@ResponseBody
	public boolean insertHumitureManage(@RequestBody HumitureManage humitureManage){
		return humitureService.insertHumitureManage(humitureManage);
	}
	@RequestMapping(value="/deleteHumitureManageById/{ehmId}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteHumitureManageById(@PathVariable("ehmId") int ehmId){
		return humitureService.deleteHumitureManageById(ehmId);
	}
	@RequestMapping(value="/findHumitureAlarmAll",method=RequestMethod.GET)
	@ResponseBody
	public List<HumitureAlarm> findHumitureAlarmAll(){
		return humitureService.findHumitureAlarmAll();
	}
}
