package com.edut.dao;

import com.edut.pojo.domain.User;

/**
 * 根据 
 */
public interface UserDao {
	/**
	 * 根据用户名获取 User 对象
	 * 
	 */
	public abstract User getUser(String username) ; 
}
