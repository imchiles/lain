package com.lain.service;

import java.util.List;

import com.lain.entity.Device;
import com.lain.entity.DeviceAlarm;
import com.lain.entity.DeviceIp;

public interface DeviceIpService {
	List<DeviceIp> findDeviceIpAll();
	boolean insertDeviceIpAll(DeviceIp deviceIp);
	boolean updateDeviceIpById(DeviceIp deviceIp);
	boolean deleteDeviceIpById(int diId);
	boolean updIpConnectStatus(int diIsConnect,int diOperate,String diAddress,int diPort);
	List<Device> findDeviceAll();
	List<DeviceAlarm> findDeviceAlarmAll();
	boolean updateDeviceAlarmById(DeviceAlarm deviceAlarm);
}
