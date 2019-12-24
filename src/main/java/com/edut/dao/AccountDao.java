package com.edut.dao;

import com.edut.pojo.domain.Account;

public interface AccountDao {
	/**
	 * 根据 accountId 获取对应的  Account 对象
	 * @param accountId
	 * @return
	 */
	public abstract Account getAccount(Integer accountId) ;
	
	/**
	 * 
	 * @param accountId
	 * @param amount
	 */
	public abstract void updateBalance(Integer  accountId , float amount) ;
}