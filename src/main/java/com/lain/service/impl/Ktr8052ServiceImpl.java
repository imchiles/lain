package com.lain.service.impl;

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
	public List<Ktr8052Alarm> findKtr8052Alarm() throws Exception {
		// TODO Auto-generated method stub
		return ktr8052Mapper.findKtr8052Alarm();
	}

	@Override
	public List<Ktr8052StatusPojo> getKtr8052Status(int k_id) throws Exception {
		// TODO Auto-generated method stub
		return ktr8052Mapper.getKtr8052Status(k_id);
	}


}
