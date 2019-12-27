package com.edut.dao.imp;

import java.util.Collection;
import java.util.Date;

import com.edut.dao.TradeDao;
import com.edut.dao.TradeItemDao;
import com.edut.pojo.domain.Trade;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;

public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao  {

	private TradeItemDao tradeItemDao  = new TradeItemDaoImpl() ;

	@Override
	public void saveTrade(Integer userId, ShoppingCart cart) {
		//new trade 
		insertNewTrade(userId , new java.sql.Date(new Date().getTime())) ; 
		Collection<ShoppingCartItem> items = cart.getItemsCollection();
		for (ShoppingCartItem item : items) {
			//tsga
			gasdgasd
		}
	}

	@Override
	public Integer insertNewTrade(Integer userId, Date date) {
		String sql =  " insert into trade_item "
					+ " value( null , ? , ? ) " ;
		long r = insert(sql , date , userId);
		return (int) r ; 
	}
	
	
	

}
