package com.lain.dao;

import java.util.List;

import com.lain.entity.LocationManagePojo;

public interface LocationMapper {
	List<Integer> findLocationAddress(int diId);

	/** ���涨λ©ˮʵʱ״̬������*/
	public void updLocationStatusAndLen(int status, double length, int address, int ipId);
	
	/** ͨ���豸��ַ��ȡ��λ©ˮ�����Ƽ����ڵ�ַ*/
	public LocationManagePojo getLocation(int address,int ipId);
}
