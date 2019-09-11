package com.lain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lain.dao.GroupMapper;
import com.lain.entity.Group;
import com.lain.service.GroupService;

@Service("GroupService")
public class GroupServiceImpl implements GroupService{
	@Autowired
	private GroupMapper groupMapper;
	@Override
	public List<Group> findGroupAll() {
		return groupMapper.findGroupAll();
	}
}
