package com.lain.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.lain.dao.Ktr8052Mapper;
import com.lain.entity.Ktr8052;
import com.lain.entity.Ktr8052Alarm;
import com.lain.entity.Ktr8052Pojo.Ktr8052StatusPojo;

public interface Ktr8052Service {
	
	List<Ktr8052> getKtr8052(int k_id) throws Exception;
	List<Ktr8052> findKtr8052All(int d_id)throws Exception;
	int insertAlarm(String name,String time,String gallery)throws Exception;
	List<Ktr8052Alarm> findKtr8052Alarm(String startTime, String endTime) throws Exception;
	List<Ktr8052Alarm> findKtr8052AlarmNot() throws Exception;
	List<Ktr8052StatusPojo> getKtr8052Status(int k_id) throws Exception;
}
