package com.lain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lain.dao.DeviceIpMapper;
import com.lain.entity.Device;
import com.lain.entity.DeviceAlarm;
import com.lain.entity.DeviceIp;
import com.lain.service.DeviceIpService;

@Service("DeviceIpService")
public class DeviceIpServiceImpl implements DeviceIpService{
	@Autowired
	private DeviceIpMapper deviceIpMapper;
	@Override
	public List<DeviceIp> findDeviceIpAll() {
		return deviceIpMapper.findDeviceIpAll();
	}
	@Override
	public boolean insertDeviceIpAll(DeviceIp deviceIp) {
		return deviceIpMapper.insertDeviceIpAll(deviceIp);
	}
	@Override
	public boolean updateDeviceIpById(DeviceIp deviceIp) {
		return deviceIpMapper.updateDeviceIpById(deviceIp);
	}
	@Override
	public boolean deleteDeviceIpById(int diId) {
		return deviceIpMapper.deleteDeviceIpById(diId);
	}
	@Override
	public boolean updIpConnectStatus(int diIsConnect,int diOperate,String diAddress,int diPort) {
		return deviceIpMapper.updIpConnectStatus(diIsConnect,diOperate,diAddress,diPort);
	}
	@Override
	public List<Device> findDeviceAll() {
		return deviceIpMapper.findDeviceAll();
	}
	@Override
	public List<DeviceAlarm> findDeviceAlarmAll() {
		return deviceIpMapper.findDeviceAlarmAll();
	}
	@Override
	public boolean updateDeviceAlarmById(DeviceAlarm deviceAlarm) {
		return deviceIpMapper.updateDeviceAlarmById(deviceAlarm);
	}
}
