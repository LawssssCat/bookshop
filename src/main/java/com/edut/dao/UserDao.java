package com.edut.dao;

import java.sql.SQLException;

import com.edut.ex.NoSuchUserException;
import com.edut.pojo.domain.User;

/**
 * 根据 
 */
public interface UserDao {
	/**
	 * 根据用户名获取 User 对象
	 * 
	 */
	public abstract User getUser(String username)  throws NoSuchUserException  ; 
}
