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
		createEmptyTrade(trade);
		
		//存 tradeitems
		Set<TradeItem> items = trade.getTradeItems();
		tradeItemDao.batchInsert(trade.getTradeId(), items) ;
	}

	@Override
	public void createEmptyTrade(Trade trade) {
		//创建一个空trade
		String sql =  " insert into trade_table "
					+ " (trade_id , TRADE_TIME , USER_ID) "
					+ " value "
					+ " ( null , ? ,?   )" ;
		Long i = insert(sql , trade.getTradeTime() , trade.getUserId());
		Integer tradeId = Integer.parseInt(""+ i);  //得到 tradeId
		trade.setTradeId(tradeId);
	}


	
	

}
