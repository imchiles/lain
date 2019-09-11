package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.Device;
import com.lain.entity.DeviceAlarm;
import com.lain.entity.DeviceIp;

public interface DeviceIpMapper {
	DeviceIp findDeviceIpById(int diId);
	List<DeviceIp> findDeviceIpAll();
	boolean insertDeviceIpAll(DeviceIp deviceIp);
	boolean updateDeviceIpById(DeviceIp deviceIp);
	boolean deleteDeviceIpById(int diId);
	boolean updIpConnectStatus(	@Param("diIsConnect") int diIsConnect,
								@Param("diOperate") int diOperate,
								@Param("diAddress") String diAddress,
								@Param("diPort") int diPort);
	List<Device> findDeviceAll();
	List<DeviceAlarm> findDeviceAlarmAll();
	boolean updateDeviceAlarmById(DeviceAlarm deviceAlarm);
}
