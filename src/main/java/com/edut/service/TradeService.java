package com.edut.service;

import java.sql.SQLException;

import com.edut.dao.TradeDao;
import com.edut.dao.imp.TradeDaoImpl;
import com.edut.ex.InsufficientBalanceException;
import com.edut.ex.NoSuchUserException;
import com.edut.pojo.web.ShoppingCart;

public class TradeService {
	private UserService userService  ; 
	private BookService bookService ;
	
	private TradeDao tradeDao = new TradeDaoImpl() ; 
	
	public TradeService(UserService userService,BookService bookService ) {
		this.userService = userService ; 
		this.bookService = bookService ; 
	}

	public void cash(String username, Integer accountId, ShoppingCart cart)
			throws NoSuchUserException, 
			InsufficientBalanceException,
			UnderStoreException, SQLException {
		//验证账号
		userService.validateUser(username, accountId) ; 
		//验证余额
		userService.validateBalance(accountId ,cart );
		//验证库存
		bookService.validateStore(cart) ; 
		
		//数据库 - 事务操作
		//1. 扣钱 
		//2. 扣库存 、 加发货
		userService.updateBalance(accountId , cart.getTotalMoney());
		bookService.batchUpdateStoreNumberAndSalesAmount(cart);
		
		
		Integer userId = userService.getUserByName(username).getUserId();
		//trade 记录
		tradeDao.saveTrade(userId , cart) ;
		
		//清理 session - cart
		cart.clear(); 
	}

}
