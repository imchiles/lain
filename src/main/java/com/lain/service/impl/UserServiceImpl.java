package com.lain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lain.dao.UserMapper;
import com.lain.entity.User;
import com.lain.service.UserService;
/**
 * 
 * UserServic 的实现类
 *
 */
@Service("UserService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> findUserAll() {
		return userMapper.findUserAll();
	}
	@Override
	public User findUserById(int uId) {
		return userMapper.findUserById(uId);
	}
	@Override
	public boolean insertUser(User user) {
		return userMapper.insertUser(user);
	}
	@Override
	public boolean updateUserById(User user) {
		user.setPassword(new Md5PasswordEncoder().encodePassword(user.getPassword(), user.getUsername()));
		return userMapper.updateUserById(user);
	}
	@Override
	public boolean deleteUserById(int uId) {
		return userMapper.deleteUserById(uId);
	}
	@Override
	public boolean updateRIdById(User user) {
		return userMapper.updateRIdById(user);
	}
}
