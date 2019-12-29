package com.edut.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.edut.dao.TradeDao;
import com.edut.dao.imp.TradeDaoImpl;
import com.edut.ex.InsufficientBalanceException;
import com.edut.ex.NoSuchUserException;
import com.edut.ex.UnderStoreException;
import com.edut.pojo.domain.Trade;
import com.edut.pojo.domain.TradeItem;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;

public class TradeService {
	private UserService userService  ; 
	private BookService bookService ;
	
	private TradeDao tradeDao = new TradeDaoImpl() ; 
	
	public TradeService(UserService userService,BookService bookService ) {
		this.userService = userService ; 
		this.bookService = bookService ; 
	}

	/**
	 * 事务：交钱、给货、存记录 
	 */
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
		
		//trade 记录
		Integer userId = userService.getUserByName(username).getUserId();
		Trade trade = createTrade(userId , cart);
		tradeDao.saveTrade(trade); 
		
		//清理 session - cart
		cart.clear(); 
	}

	/**
	 * 
	 */
	private Trade createTrade(Integer userId, ShoppingCart cart) {
		
		Set<TradeItem> items = new HashSet<TradeItem>();
		Collection<ShoppingCartItem> cartItems = cart.getItemsCollection();
		for (ShoppingCartItem cartItem : cartItems) {
			TradeItem item = new TradeItem(cartItem) ;
			items.add(item) ; 
		}
		
		Date tradeTime = new java.sql.Date(new Date().getTime());
		return new Trade(userId , tradeTime  , items) ; 
	}
	
	

}
