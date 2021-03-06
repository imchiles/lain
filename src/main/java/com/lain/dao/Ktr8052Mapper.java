package com.lain.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.Ktr8052;
import com.lain.entity.Ktr8052Alarm;
import com.lain.entity.Ktr8052Pojo.Ktr8052StatusPojo;

public interface Ktr8052Mapper {
	List<Integer> findKtr8052Address(int diId);
	void updataKtr8052Galery(	@Param("status") int status,
								@Param("gallery") String gallery,
								@Param("address") int address,
								@Param("diId") int diId);
	List<Ktr8052> getKtr8052(int k_id);
	
	List<Ktr8052> findKtr8052All(int d_id);
	
	int insertAlarm(
			@Param("name")String name,
			@Param("time")String time,
			@Param("gallery")String gallery);
	
	List<Ktr8052Alarm> findKtr8052Alarm(
			@Param("startTime") Date startTime,
			@Param("endTime") Date endTime);
	
	List<Ktr8052Alarm> findKtr8052AlarmNot();
	
	String selectKtr8052Name(String gallery);
	
	List<Ktr8052StatusPojo> getKtr8052Status(int k_id);
}
