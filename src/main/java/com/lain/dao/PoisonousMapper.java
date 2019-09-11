package com.lain.dao;

import java.util.List;

import com.lain.entity.PoisonousManagePojo;

public interface PoisonousMapper {
	/** ��ȡ��λ�ж������ַ*/
	public List<Integer> findPoisonousAddress(int ipId);
	/** �����ж�����ʵʱ����*/
	public void updPoisonousRealTimeData(double currentData, int address, int ipId);
	/** ͨ���豸��ַ��ȡ�ж���������Ƽ����ڵ�ַ*/
	public PoisonousManagePojo getPoisonous(int address, int ipId);

}
