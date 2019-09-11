package com.lain.service;

import java.util.List;

import com.lain.entity.Log;

public interface LogService {
	List<Log> findLogBydate(String startTime,String endTime,String logType);
}
