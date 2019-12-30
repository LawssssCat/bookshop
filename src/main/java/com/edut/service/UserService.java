package com.edut.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.edut.dao.AccountDao;
import com.edut.dao.TradeDao;
import com.edut.dao.UserDao;
import com.edut.dao.imp.AccountDaoImpl;
import com.edut.dao.imp.TradeDaoImpl;
import com.edut.dao.imp.UserDaoImpl;
import com.edut.ex.InsufficientBalanceException;
import com.edut.ex.NoMoneyException;
import com.edut.ex.NoSuchUserException;
import com.edut.ex.TransactionException;
import com.edut.pojo.domain.Account;
import com.edut.pojo.domain.Book;
import com.edut.pojo.domain.Trade;
import com.edut.pojo.domain.User;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;
import com.edut.servlet.BookServlet;

public class UserService {
	private UserDao userDao = new UserDaoImpl() ;
	private AccountDao accountDao  = new AccountDaoImpl() ;
	private TradeDao tradeDao = new TradeDaoImpl() ; 
	
	public User getUserByName(String username) throws NoSuchUserException {
		User user = userDao.getUser(username);
		if(user!=null) {
			return user ;  
		}else {
			throw new NoSuchUserException() ; 
		}
	}
	
	/**
	 * 验证 ==》 用户名、accountId
	 */
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

	/**
	 * 验证 ==》 余额
	 */
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

	public User getUserWithTrads(String username)
			throws NoSuchUserException, SQLException {
		
		//获取用户
		User user = getUserByName(username);
		
		//根据用户 ， 获取 同 userId 的 trade 
		List<Trade> trades = tradeDao.getTradesWithItems(user.getUserId());
		user.setTrades(new HashSet<Trade>(trades));
		
		return user; 
	}

}
