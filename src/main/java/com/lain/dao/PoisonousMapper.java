package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.PoisonousManagePojo;

public interface PoisonousMapper {
	/** ��ȡ��λ�ж������ַ*/
	public List<Integer> findPoisonousAddress(int ipId);
	/** �����ж�����ʵʱ����*/
	public void updPoisonousRealTimeData(
			@Param("ecm_currentData")double ecm_currentData,
			@Param("ecm_address") int ecm_address, 
			@Param("di_id")int di_id);
	
	/** ͨ���豸��ַ��ȡ�ж���������Ƽ����ڵ�ַ*/
	public PoisonousManagePojo getPoisonous(
			@Param("ecm_address")int ecm_address,
			@Param("di_id") int di_id);

}
