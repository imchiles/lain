package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.ElectricmeterData;
import com.lain.entity.ElectricmeterHistory;
import com.lain.entity.ElectricmeterManage;

public interface ElectricmeterMapper {
	List<ElectricmeterManage> findElectricmeterAll();
	List<Integer> findElectricmeterById(int diId);
	boolean insertElectricmeterManage(ElectricmeterManage em);
	boolean updateElectricmeterManage(ElectricmeterManage em);
	boolean deleteElectricmeterManage(int pemId);
	boolean updateElectricmeterRealTimeData(ElectricmeterData ed);
	void saveElectricmeterHistory(ElectricmeterHistory eh);
	int findIntervalTime(
			@Param("pemAddress") int pemAddress,
			@Param("diId") int diId);
}
