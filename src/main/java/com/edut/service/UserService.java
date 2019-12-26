package com.edut.service;

import com.edut.dao.AccountDao;
import com.edut.dao.UserDao;
import com.edut.dao.imp.AccountDaoImpl;
import com.edut.dao.imp.UserDaoImpl;
import com.edut.ex.NoMoneyException;
import com.edut.pojo.domain.Account;
import com.edut.pojo.domain.User;
import com.edut.pojo.web.ShoppingCart;

public class UserService {
	private UserDao userDao = new UserDaoImpl() ;
	private AccountDao accountDao  = new AccountDaoImpl() ; 
	private BookService bookService = new BookService() ; 

	public boolean validateUser(String username, Integer accountId) {
		User user = userDao.getUser(username);
		if(user!=null) {
			Integer initAccountId = user.getAccountId();
			if(initAccountId!=null && initAccountId.equals(accountId)) {
				return true ; 
			}
		}
		return false;
	}

	public void carsh(ShoppingCart cart, String username, Integer accountId) throws NoMoneyException {
		User user = userDao.getUser(username);
		Account account = accountDao.getAccount(accountId);
		
		if(account.getBalance() < cart.getTotalMoney()) {
			throw new NoMoneyException() ;
		}
		//扣钱
		accountDao.updateBalance(accountId, cart.getTotalMoney());
		
		//减库存
		
		cart.clear();
	} 
}
