package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.Co2ManagePojo;

public interface Co2Mapper {
	List<Integer> findCo2Address(int diId);
	
	/**��������,��������*/
	void updCo2RealTimeData(
			@Param("ecm_currentData")int ecm_currentData,
			@Param("ecm_address")int ecm_address, 
			@Param("di_id")int di_id);
	
	/** ͨ���豸��ַ��ȡCo2�����Ƽ����ڵ�ַ*/
	Co2ManagePojo getCo2(
			@Param("ecm_address")int ecm_address,
			@Param("di_id")int di_id);

}
