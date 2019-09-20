package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.PoisonousManagePojo;

public interface PoisonousMapper {
	/** 获取定位有毒气体地址*/
	public List<Integer> findPoisonousAddress(int ipId);
	/** 保存有毒气体实时数据*/
	public void updPoisonousRealTimeData(
			@Param("ecm_currentData")double ecm_currentData,
			@Param("ecm_address") int ecm_address, 
			@Param("di_id")int di_id);
	
	/** 通过设备地址获取有毒气体的名称及所在地址*/
	public PoisonousManagePojo getPoisonous(
			@Param("ecm_address")int ecm_address,
			@Param("di_id") int di_id);

}
