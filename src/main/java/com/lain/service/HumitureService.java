package com.lain.service;

import java.util.List;

import com.lain.entity.HumitureAlarm;
import com.lain.entity.HumitureManage;

public interface HumitureService {
	List<HumitureManage> findHumitureManageAll();
	boolean deleteHumitureManageById(int ehmId);
	boolean insertHumitureManage(HumitureManage humitureManage);
	List<HumitureAlarm> findHumitureAlarmAll();
}
