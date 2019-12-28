package com.edut.dao.imp;

import java.sql.SQLException;
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
	public void saveTrade(Integer userId, ShoppingCart cart)
			throws SQLException {
		//new trade
		Integer tradeId = insertNewTrade(
				userId , 
				new java.sql.Date(new Date().getTime())); 
		Collection<ShoppingCartItem> cartItems = cart.getItemsCollection();
		tradeItemDao.batchInsertItemDao(tradeId , cartItems) ;
	}

	@Override
	public Integer insertNewTrade(Integer userId, Date date) {
		String sql =  " insert into trade_table "
					+ " (trade_time , user_id) "
					+ " value(  ? , ? ) " ;
		long r = insert(sql , date , userId);
		return (int) r ; 
	}
	
	
	

}
