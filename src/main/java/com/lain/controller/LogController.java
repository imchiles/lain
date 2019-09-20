package com.lain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.entity.Log;
import com.lain.service.LogService;

@Controller
@RequestMapping("/log")
public class LogController {
	@Autowired
	private LogService logService;
	@RequestMapping(value="/findLogBydate/{startTime}/{endTime}",method=RequestMethod.GET)
	@ResponseBody
	public List<Log> findLogBydate(	@PathVariable("startTime") String startTime,
									@PathVariable("endTime") String endTime
									){
		System.out.println(startTime + "  " + endTime);
		return logService.findLogBydate(startTime, endTime);
	}
}
