package com.lain.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	
	//查询温湿度设备信息
	@RequestMapping(value="/findHumitureManageAll",method=RequestMethod.GET)
	@ResponseBody
	public List<HumitureManage> findHumitureManageAll(){
		return humitureService.findHumitureManageAll();
	}
	
	//插入温湿度信息
	@RequestMapping(value="/insertHumitureManage",method=RequestMethod.POST)
	@ResponseBody
	public boolean insertHumitureManage(@RequestBody HumitureManage humitureManage){
		return humitureService.insertHumitureManage(humitureManage);
	}
	
	//根据ID删除温湿度设备
	@RequestMapping(value="/deleteHumitureManageById/{ehmId}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteHumitureManageById(@PathVariable("ehmId") int ehmId){
		return humitureService.deleteHumitureManageById(ehmId);
	}
	
	//查询温湿度报警信息
	@RequestMapping(value="/findHumitureAlarmAll",method=RequestMethod.GET)
	@ResponseBody
	public List<HumitureAlarm> findHumitureAlarmAll(){
		return humitureService.findHumitureAlarmAll();
	}
}
