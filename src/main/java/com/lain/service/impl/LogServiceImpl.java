package com.lain.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lain.dao.LogMapper;
import com.lain.entity.Log;
import com.lain.service.LogService;

@Service("LogService")
public class LogServiceImpl implements LogService{
	@Autowired
	private LogMapper logMapper;
	@Override
	public List<Log> findLogBydate(String startTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;
		Date end = null;
		try {
			if(!"null".equals(startTime)){
				start = sdf.parse(startTime);
			}
			if(!"null".equals(endTime)){
				end = sdf.parse(endTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return logMapper.findLogBydate(start, end);
	}
}
