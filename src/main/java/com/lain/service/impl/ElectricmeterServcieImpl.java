package com.lain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lain.dao.ElectricmeterMapper;
import com.lain.entity.ElectricmeterManage;
import com.lain.service.ElectricmeterServcie;

@Service("ElectricmeterServcie")
public class ElectricmeterServcieImpl implements ElectricmeterServcie{
	@Autowired
	private ElectricmeterMapper electricmeterMapper;
	@Override
	public List<ElectricmeterManage> findElectricmeterAll() {
		return electricmeterMapper.findElectricmeterAll();
	}
	@Override
	public boolean insertElectricmeterManage(ElectricmeterManage em) {
		return electricmeterMapper.insertElectricmeterManage(em);
	}
	@Override
	public boolean updateElectricmeterManage(ElectricmeterManage em) {
		return electricmeterMapper.updateElectricmeterManage(em);
	}
	@Override
	public boolean deleteElectricmeterManage(int pemId) {
		return electricmeterMapper.deleteElectricmeterManage(pemId);
	}
	
}
