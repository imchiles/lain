package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.LocationManagePojo;

public interface LocationMapper {
	List<Integer> findLocationAddress(int diId);

	/** ���涨λ©ˮʵʱ״̬������*/
	public void updLocationStatusAndLen(@Param("elm_status")int elm_status,@Param("elm_length")double elm_length,@Param("elm_address")int elm_address,@Param("di_id") int di_id);
	
	/** ͨ���豸��ַ��ȡ��λ©ˮ�����Ƽ����ڵ�ַ*/
	public LocationManagePojo getLocation(@Param("elm_address")int elm_address,@Param("di_id")int di_id);
	
	public List<LocationManagePojo> selectLocationAll();
}
