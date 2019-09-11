package com.lain.dao;

import java.util.List;

import com.lain.entity.PoisonousManagePojo;

public interface PoisonousMapper {
	/** 获取定位有毒气体地址*/
	public List<Integer> findPoisonousAddress(int ipId);
	/** 保存有毒气体实时数据*/
	public void updPoisonousRealTimeData(double currentData, int address, int ipId);
	/** 通过设备地址获取有毒气体的名称及所在地址*/
	public PoisonousManagePojo getPoisonous(int address, int ipId);

}
