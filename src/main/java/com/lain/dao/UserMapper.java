package com.lain.dao;

import java.util.List;

import com.lain.entity.User;
/**
 * 
 * @author luser表的dao
 *
 */
public interface UserMapper {
	List<User> findUserAll();
	User findUserById(int uId);
	boolean insertUser(User user);
	boolean updateUserById(User user);
	boolean deleteUserById(int id);
	boolean updateRIdById(User user);
	
	/** 根据username查询用户的密码，用于security的密码校验*/
	User findUserByUsername(String username);
}
