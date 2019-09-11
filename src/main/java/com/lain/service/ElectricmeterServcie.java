package com.lain.service;

import java.util.List;

import com.lain.entity.ElectricmeterManage;

public interface ElectricmeterServcie {
	List<ElectricmeterManage> findElectricmeterAll();
	boolean insertElectricmeterManage(ElectricmeterManage em);
	boolean updateElectricmeterManage(ElectricmeterManage em);
	boolean deleteElectricmeterManage(int pemId);
}
