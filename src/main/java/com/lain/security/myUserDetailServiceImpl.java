package com.lain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lain.dao.UserMapper;
import com.lain.entity.User;
/**
 *  
 * 	SECURITY接口实现类
 *
 *	在这里实现密码的校验
 *	可以在这里增加盐值
 */
@Service
public class myUserDetailServiceImpl implements UserDetailsService{
	/**
	 * user table 的增删改查操作
	 */
	@Autowired
	private UserMapper userMapper;
	/**
	 * 根据用户名获取密码
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.findUserByUsername(username);
		if (StringUtils.isEmpty(user)) {
			System.out.println("用户名或者密码不正确！");
            throw new DisabledException("用户名或者密码不正确！");
        }
		return user;
	}
}
