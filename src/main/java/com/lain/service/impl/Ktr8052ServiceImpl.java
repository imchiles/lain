package com.lain.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lain.dao.Ktr8052Mapper;
import com.lain.entity.Ktr8052;
import com.lain.service.Ktr8052Service;

@Service
public class Ktr8052ServiceImpl implements Ktr8052Service{
	
	@Autowired
	private Ktr8052Mapper ktr8052Mapper;

	@Override
	public Ktr8052 getKtr8052(int id) throws Exception {
		return ktr8052Mapper.getKtr8052(id);
	}

}
