package com.edut.dao.imp;

import java.sql.SQLException;

import com.edut.dao.UserDao;
import com.edut.ex.NoSuchUserException;
import com.edut.pojo.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getUser(String username)   throws NoSuchUserException {
		String sql =  " select "
					+ " user_id userId, "
					+ " username , "
					+ " account_id accountId "
					+ " from User_table "
					+ " where username = ?" ; 
		try {
			return query(sql, username);
		} catch (SQLException e) {
			throw new NoSuchUserException() ; 
		}
	}

}
