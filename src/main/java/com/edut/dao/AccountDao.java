package com.edut.dao;

import java.sql.SQLException;

import com.edut.pojo.domain.Account;

public interface AccountDao {
	/**
	 * 根据 accountId 获取对应的  Account 对象
	 * ----------------------------
	 * 判断余额是否足够
	 * 
	 * @param accountId
	 * @return
	 */
	public abstract Account getAccount(Integer accountId)  throws SQLException  ;
	
	/**
	 * 更新余额
	 * -------------
	 * 结账时候
	 * 
	 * @param accountId
	 * @param double1
	 */
	public abstract void updateBalance(Integer  accountId , Double double1) throws SQLException  ;
	
}
