package com.lain.dao;

import java.util.List;

public interface PoisonousMapper {
	/** 获取定位有毒气体地址*/
	public List<Integer> getPoisonous(int ipId);

}
