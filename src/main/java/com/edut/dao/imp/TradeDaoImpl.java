package com.edut.dao.imp;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.edut.dao.BookDao;
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
		Set<TradeItem> items = trade.getItems();
		tradeItemDao.batchInsert(trade.getTradeId(), items) ;
	}

	@Override
	public void  createEmptyTrade(Trade trade) throws SQLException {
		//创建一个空trade
		String sql =  " insert into trade_table "
					+ " (trade_id , TRADE_TIME , USER_ID) "
					+ " value "
					+ " ( null , ? ,?   )" ;
		Long i = insert(sql , trade.getTradeTime() , trade.getUserId());
		Integer tradeId = Integer.parseInt(""+ i);  //得到 tradeId
		trade.setTradeId(tradeId);
	}

	@Override
	public List<Trade> getTradesWithItems(Integer userId) throws SQLException {
		String sql =  " select "
					+ " TRADE_ID tradeId , "
					+ " TRADE_TIME tradeTime , "
					+ " USER_ID userId "
					+ " from trade_table "
					+ " where user_id = ? " ;
		List<Trade> trades = queryForList(sql, userId);
		for (Trade trade : trades) {
			Integer tradeId = trade.getTradeId();
			List<TradeItem> items = tradeItemDao.getItemsWithBook(tradeId);
			trade.setItems(new HashSet<TradeItem>(items));
		}
		return trades ;
	}




	
	

}
