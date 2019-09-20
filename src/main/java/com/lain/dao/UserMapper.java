package com.lain.dao;

import java.util.List;

import com.lain.entity.User;
/**
 * 
 * @author luser���dao
 *
 */
public interface UserMapper {
	List<User> findUserAll();
	User findUserById(int uId);
	boolean insertUser(User user);
	boolean updateUserById(User user);
	boolean deleteUserById(int id);
	boolean updateRIdById(User user);
	
	/** ����username��ѯ�û������룬����security������У��*/
	User findUserByUsername(String username);
}
