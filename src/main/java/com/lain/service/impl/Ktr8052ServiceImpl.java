package com.lain.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lain.dao.Ktr8052Mapper;
import com.lain.entity.Ktr8052;
import com.lain.entity.Ktr8052Alarm;
import com.lain.entity.Ktr8052Pojo.Ktr8052StatusPojo;
import com.lain.service.Ktr8052Service;

@Service
public class Ktr8052ServiceImpl implements Ktr8052Service{
	
	@Autowired
	private Ktr8052Mapper ktr8052Mapper;

	@Override
	public List<Ktr8052> getKtr8052(int k_id) throws Exception {
		return ktr8052Mapper.getKtr8052(k_id);
	}

	@Override
	public List<Ktr8052> findKtr8052All(int d_id) throws Exception {
		// TODO Auto-generated method stub
		return ktr8052Mapper.findKtr8052All(d_id);
	}

	@Override
	public int insertAlarm(String name, String time, String gallery) throws Exception {
		// TODO Auto-generated method stub
		return ktr8052Mapper.insertAlarm(name, time, gallery);
	}

	@Override
	public List<Ktr8052Alarm> findKtr8052Alarm(String startTime, String endTime) throws Exception {
		// TODO Auto-generated method stub
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

		return ktr8052Mapper.findKtr8052Alarm(start,end);
	}

	@Override
	public List<Ktr8052StatusPojo> getKtr8052Status(int k_id) throws Exception {
		// TODO Auto-generated method stub
		return ktr8052Mapper.getKtr8052Status(k_id);
	}

	@Override
	public List<Ktr8052Alarm> findKtr8052AlarmNot() throws Exception {
		// TODO Auto-generated method stub
		return ktr8052Mapper.findKtr8052AlarmNot();
	}

	


}
