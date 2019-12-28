package com.edut.dao.imp;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

import com.edut.dao.TradeDao;
import com.edut.dao.TradeItemDao;
import com.edut.pojo.domain.Trade;
import com.edut.pojo.domain.TradeItem;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;

public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao  {
	
	private TradeItemDao tradeItemDao = new TradeItemDaoImpl() ; 
	
	@Override
	public void saveTrade(Trade trade) throws SQLException {
		Long tradeId = createEmptyTrade();
		String sql =  " update trade_table "
					+ " set "
					+ " Trade_time = ? , "
					+ " User_id = ? "
					+ " where Trade_id = ? " ; 
		Integer userId = trade.getUserId() ; 
		Date time = new Date(new java.util.Date().getTime()) ; 
		update(sql, time , userId , tradeId);
		
		//存 tradeitems
		Set<TradeItem> items = trade.getTradeItems();
		tradeItemDao.batchInsert(tradeId, items) ;
	}

	@Override
	public Long createEmptyTrade() {
		//创建一个空trade
		String sql =  " insert into trade_table "
					+ " (trade_id) "
					+ " value "
					+ " ( null )" ;
		return insert(sql) ;  //得到 tradeId
	}
	
	

}
