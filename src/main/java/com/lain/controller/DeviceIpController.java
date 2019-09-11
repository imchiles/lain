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
	/**  --��ѯ�¼�--  */
	
	/*��ѯ����device_ip table��Ϣ*/
	@RequestMapping(value="/findDeviceIpAll",method=RequestMethod.GET)
	@ResponseBody
	public List<DeviceIp> findDeviceIpAll(){
		return deviceIpService.findDeviceIpAll();
	}
	
	/*��ѯ����device table��Ϣ*/
	@RequestMapping(value="/findDeviceAll",method=RequestMethod.GET)
	@ResponseBody
	public List<Device> findDeviceAll(){
		return deviceIpService.findDeviceAll();
	}
	
	/*��ѯ����device_alarm table��Ϣ*/
	@RequestMapping(value="/findDeviceAlarmAll",method=RequestMethod.GET)
	@ResponseBody
	public List<DeviceAlarm> findDeviceAlarmAll(){
		return deviceIpService.findDeviceAlarmAll();
	}
	
	/**  --����¼�--  */
	
	/*��device_ip table���һ������*/
	@RequestMapping(value="/insertDeviceIpAll",method=RequestMethod.POST)
	@ResponseBody
	public boolean insertDeviceIpAll(@RequestBody DeviceIp deviceIp){
		return deviceIpService.insertDeviceIpAll(deviceIp);
	}
	
	/**  --�����¼�--  */
	
	/*����deviceIp����device_ip table����Ϣ*/
	@RequestMapping(value="/updateDeviceIpById",method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateDeviceIpById(@RequestBody DeviceIp deviceIp){
		return deviceIpService.updateDeviceIpById(deviceIp);
	}
	
	/*����deviceAlarm����device_alarm table����Ϣ*/
	@RequestMapping(value="/updateDeviceAlarmById",method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateDeviceAlarmById(@RequestBody DeviceAlarm deviceAlarm){
		return deviceIpService.updateDeviceAlarmById(deviceAlarm);
	}
	
	/**  --ɾ���¼�--  */
	
	/*����deviceIp��device_ip tableɾ��һ������*/
	@RequestMapping(value="/deleteDeviceIpById/{diId}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteDeviceIpById(@PathVariable("diId") int diId){
		return deviceIpService.deleteDeviceIpById(diId);
	}
	
}
