package com.lain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.entity.Device;
import com.lain.entity.DeviceAlarm;
import com.lain.entity.DeviceIp;
import com.lain.service.DeviceIpService;

@Controller
@RequestMapping("/deviceIp")
public class DeviceIpController {
	@Autowired
	private DeviceIpService deviceIpService;
	/**  --查询事件--  */
	
	/*查询所有device_ip table信息*/
	@RequestMapping(value="/findDeviceIpAll",method=RequestMethod.GET)
	@ResponseBody
	public List<DeviceIp> findDeviceIpAll(){
		return deviceIpService.findDeviceIpAll();
	}
	
	/*查询所有device table信息*/
	@RequestMapping(value="/findDeviceAll",method=RequestMethod.GET)
	@ResponseBody
	public List<Device> findDeviceAll(){
		return deviceIpService.findDeviceAll();
	}
	
	/*查询所有device_alarm table信息*/
	@RequestMapping(value="/findDeviceAlarmAll",method=RequestMethod.GET)
	@ResponseBody
	public List<DeviceAlarm> findDeviceAlarmAll(){
		return deviceIpService.findDeviceAlarmAll();
	}
	
	/**  --添加事件--  */
	
	/*在device_ip table添加一条数据*/
	@RequestMapping(value="/insertDeviceIpAll",method=RequestMethod.POST)
	@ResponseBody
	public boolean insertDeviceIpAll(@RequestBody DeviceIp deviceIp){
		return deviceIpService.insertDeviceIpAll(deviceIp);
	}
	
	/**  --更新事件--  */
	
	/*根据deviceIp更新device_ip table的信息*/
	@RequestMapping(value="/updateDeviceIpById",method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateDeviceIpById(@RequestBody DeviceIp deviceIp){
		return deviceIpService.updateDeviceIpById(deviceIp);
	}
	
	/*根据deviceAlarm更新device_alarm table的信息*/
	@RequestMapping(value="/updateDeviceAlarmById",method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateDeviceAlarmById(@RequestBody DeviceAlarm deviceAlarm){
		return deviceIpService.updateDeviceAlarmById(deviceAlarm);
	}
	
	/**  --删除事件--  */
	
	/*根据deviceIp在device_ip table删除一条数据*/
	@RequestMapping(value="/deleteDeviceIpById/{diId}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteDeviceIpById(@PathVariable("diId") int diId){
		return deviceIpService.deleteDeviceIpById(diId);
	}
	
}
