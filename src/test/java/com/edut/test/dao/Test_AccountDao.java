package com.edut.test.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edut.dao.AccountDao;
import com.edut.dao.imp.AccountDaoImpl;
import com.edut.pojo.domain.Account;

public class Test_AccountDao {

	private AccountDao accountDao ; 
	{
		accountDao = new AccountDaoImpl() ; 
	}
	
	public void testGetAccount(int id ) {
		Account account = accountDao.getAccount(id); 
		System.out.println(account);
	}

	@Test
	public void testUpdateBalance() {
		int id = 1; 
		testGetAccount(id); 
		accountDao.updateBalance(id, 100.0);
		Account account = accountDao.getAccount(id);
		System.out.println(account);
	}

}
