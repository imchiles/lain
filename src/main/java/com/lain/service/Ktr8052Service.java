package com.lain.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lain.dao.Ktr8052Mapper;
import com.lain.entity.Ktr8052;

public interface Ktr8052Service {
	
	Ktr8052 getKtr8052(int id) throws Exception;

}
