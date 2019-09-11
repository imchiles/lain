package com.lain.service;

import java.util.List;

import com.lain.entity.User;

public interface UserService {
	public List<User> findUserAll();
	public User findUserById(int uId);
	public boolean insertUser(User user);
	public boolean updateUserById(User user);
	public boolean deleteUserById(int uId);
	public boolean updateRIdById(User user);
}
