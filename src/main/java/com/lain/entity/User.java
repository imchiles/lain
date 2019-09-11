package com.lain.entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6642596071357360935L;
	/**
	 * ����
	 */
	private int uId;
	/**
     * �û���
     */
    private String username;
    /**
     * ����
     */
    private String password;
    /**
     * ����
     */
    private String vsername;
    /**
     * �绰����
     */
	private String telephone;
	/**
	 * ��������
	 */
	private String email;
	/**
	 * ֵ������
	 */
	private String weekday;
	/**
	 * ��ɫID
	 */
	private int rId;
	/**
	 * ��ɫ
	 */
	private String rName;
	/**
     * �Ƿ����
     */
    private Boolean enabled = true;

    /**
     *�û���ӵ�е�Ȩ��
     */
    private List<GrantedAuthority> authorities = null;

    /**
     * �û����˺��Ƿ����,���ڵ��˺��޷�ͨ����Ȩ��֤. true �˺�δ����
     */
    private Boolean accountNonExpired = true;

    /**
     * �û����˻��Ƿ�����,���������˻��޷�ͨ����Ȩ��֤. true �˺�δ����
     */
    private Boolean accountNonLocked = true;

    /**
     * �û���ƾ��(pasword) �Ƿ����,���ڵ�ƾ�ݲ���ͨ����֤. true û�й���,false �ѹ���
     */
    private Boolean credentialsNonExpired = true;
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
	    authorities = new ArrayList<GrantedAuthority>();      
	    authorities.add(new SimpleGrantedAuthority(rName));      
		return authorities; 
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getVsername() {
		return vsername;
	}

	public void setVsername(String vsername) {
		this.vsername = vsername;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", username=" + username + ", password=" + password + ", vsername=" + vsername
				+ ", telephone=" + telephone + ", email=" + email + ", weekday=" + weekday + ", rId=" + rId + ", rName="
				+ rName + ", enabled=" + enabled + ", authorities=" + authorities + ", accountNonExpired="
				+ accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired="
				+ credentialsNonExpired + "]";
	}
}
