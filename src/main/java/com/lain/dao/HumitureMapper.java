package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.HumitureAlarm;
import com.lain.entity.HumitureManage;

public interface HumitureMapper {
	
	/**HUMITURE_MANAGE*/
	//��ѯ��ַ��
	List<Integer> findHumitureAddress(int diId);
	//��ѯ�����������
	List<HumitureManage> findHumitureManageAll();
	boolean deleteHumitureManageById(int ehmId);
	boolean insertHumitureManage(HumitureManage humitureManage);
	boolean updateHumitureRealTimeData(	@Param("ehmTem") double ehmTem,
										@Param("ehmHum") double ehmHum,
										@Param("ehmDeviceAddress") int ehmDeviceAddress,
										@Param("diId") int diId);
	//������ʷ����
	boolean saveHumitureHistory(@Param("ehhTem") double ehhTem,
								@Param("ehhHum") double ehhHum,
								@Param("ehmDeviceAddress") int ehmDeviceAddress,
								@Param("diId") int diId);
	int findIntervalTime(	@Param("ehmDeviceAddress") int ehmDeviceAddress,
							@Param("diId") int diId);
	//��ѯ��������
	List<HumitureAlarm> findHumitureAlarmAll();
	
	/**HUMITURE_ALARM*/
	boolean saveHumitureAlarm(	@Param("dId")int dId, 
								@Param("diId")int diId,
								@Param("ehaInfo")String ehaInfo);
}
