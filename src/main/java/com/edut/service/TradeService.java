package com.edut.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.edut.dao.TradeDao;
import com.edut.dao.imp.TradeDaoImpl;
import com.edut.ex.InsufficientBalanceException;
import com.edut.ex.NoSuchBookException;
import com.edut.ex.NoSuchUserException;
import com.edut.ex.TransactionException;
import com.edut.ex.UnderStoreException;
import com.edut.pojo.domain.Book;
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
	 * @throws SQLException 
	 */
	public void cash(String username, Integer accountId, ShoppingCart cart)
			throws TransactionException{
		String errorMsg = null  ; 
		try {
			//验证账号
			userService.validateUser(username, accountId) ; 
			
			//数据库 - 事务操作
			//1. 扣钱 
			//2. 扣库存 、 加发货
			//验证余额
			userService.validateBalance(accountId, cart);
			userService.updateBalance(accountId , cart.getTotalMoney());
			//验证库存
			bookService.validateStore(cart) ; 
			bookService.batchUpdateStoreNumberAndSalesAmount(cart);
			
			//trade 记录
			Integer userId = userService.getUserByName(username).getUserId();
			Trade trade = createTrade(userId , cart);
			tradeDao.saveTrade(trade); 
			
			//清理 session - cart
			cart.clear(); 
		}catch(NoSuchUserException ex) {
			errorMsg = "用户不存在或者账号错误!!";
		}catch (InsufficientBalanceException e) {
			errorMsg = "余额不足!!";
		}catch ( UnderStoreException   e) {
			//库存异常
			List<Book> underStoreBooks = e.getUnderStoreBooks();
			StringBuilder sb = new StringBuilder("下面库存不足：<br>") ; 
			for (Book book : underStoreBooks) {
				sb.append(book.getTitle()) ; 
				sb.append("<br>") ; 
			}
			errorMsg =  sb.toString() ; 
		} catch (SQLException e) {
			errorMsg = "操作异常！" ; 
		} finally {
			if(errorMsg!=null && errorMsg.length()>0) {
				throw new TransactionException(errorMsg) ; 
			}else {
				//controller ... 处理
			}
		}

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
