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
 * 	SECURITY�ӿ�ʵ����
 *
 *	������ʵ�������У��
 *	����������������ֵ
 */
@Service
public class myUserDetailServiceImpl implements UserDetailsService{
	/**
	 * user table ����ɾ�Ĳ����
	 */
	@Autowired
	private UserMapper userMapper;
	/**
	 * �����û�����ȡ����
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.findUserByUsername(username);
		if (StringUtils.isEmpty(user)) {
			System.out.println("�û����������벻��ȷ��");
            throw new DisabledException("�û����������벻��ȷ��");
        }
		return user;
	}
}
