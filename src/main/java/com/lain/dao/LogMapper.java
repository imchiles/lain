package com.lain.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.Log;

public interface LogMapper {
	void insertLog(Log log);
	List<Log> findLogBydate(
			@Param("startTime") Date startTime,
			@Param("endTime") Date endTime);
}