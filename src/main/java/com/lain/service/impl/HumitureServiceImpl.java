package com.lain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lain.dao.HumitureMapper;
import com.lain.entity.HumitureAlarm;
import com.lain.entity.HumitureManage;
import com.lain.service.HumitureService;

@Service("HumitureService")
public class HumitureServiceImpl implements HumitureService{
	@Autowired
	private HumitureMapper humitureMapper;
	@Override
	public List<HumitureManage> findHumitureManageAll() {
		return humitureMapper.findHumitureManageAll();
	}
	@Override
	public boolean deleteHumitureManageById(int ehmId) {
		return humitureMapper.deleteHumitureManageById(ehmId);
	}
	@Override
	public boolean insertHumitureManage(HumitureManage humitureManage) {
		return humitureMapper.insertHumitureManage(humitureManage);
	}
	@Override
	public List<HumitureAlarm> findHumitureAlarmAll() {
		return humitureMapper.findHumitureAlarmAll();
	}
}
