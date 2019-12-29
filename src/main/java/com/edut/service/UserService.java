package com.edut.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.edut.dao.AccountDao;
import com.edut.dao.UserDao;
import com.edut.dao.imp.AccountDaoImpl;
import com.edut.dao.imp.UserDaoImpl;
import com.edut.ex.InsufficientBalanceException;
import com.edut.ex.NoMoneyException;
import com.edut.ex.NoSuchUserException;
import com.edut.ex.TransactionException;
import com.edut.pojo.domain.Account;
import com.edut.pojo.domain.Book;
import com.edut.pojo.domain.User;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;

public class UserService {
	private UserDao userDao = new UserDaoImpl() ;
	private AccountDao accountDao  = new AccountDaoImpl() ; 

	public User getUserByName(String username) throws NoSuchUserException {
		User user = userDao.getUser(username);
		if(user!=null) {
			return user ;  
		}else {
			throw new NoSuchUserException() ; 
		}
	}
	
	public void validateUser(String username, Integer accountId) throws NoSuchUserException {
		User user = userDao.getUser(username);
		if(user!=null) {
			Integer initAccountId = user.getAccountId();
			if(initAccountId!=null && initAccountId.equals(accountId)) {
				return;
			}
		}
		throw new NoSuchUserException();
	}

	public void validateBalance(Integer accountId, ShoppingCart cart) 
			throws InsufficientBalanceException, SQLException{
		Account account;
		try {
			account = accountDao.getAccount(accountId);
			Double totalMoney = cart.getTotalMoney();
			if(totalMoney > account.getBalance() ) {
				throw new InsufficientBalanceException() ; 
			}
		} catch (SQLException e) {
			throw new SQLException() ; 
		}
	}

	public void updateBalance(Integer accountId, Double totalMoney) 
			throws SQLException  {
		accountDao.updateBalance(accountId, totalMoney);
	}

}
