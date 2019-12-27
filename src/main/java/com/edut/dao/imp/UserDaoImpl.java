package com.edut.dao.imp;

import com.edut.dao.UserDao;
import com.edut.pojo.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getUser(String username) {
		String sql =  " select "
					+ " user_id userId, "
					+ " username , "
					+ " account_id accountId "
					+ " from User_table "
					+ " where username = ?" ; 
		return query(sql, username);
	}

}
