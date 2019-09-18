package com.lain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lain.util.Alarm;

@Controller
@RequestMapping("/ktr8060")
public class Ktr8060Controller {
	
	@RequestMapping("/open8060")
	public void open8060() {
		Alarm.SwitchOne(2, "192.168.1.250", 1031, 1);
	}

}
