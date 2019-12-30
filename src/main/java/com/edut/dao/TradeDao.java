package com.edut.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.edut.pojo.domain.Trade;
import com.edut.pojo.web.ShoppingCart;

/**
 * 结账时候 
 */
public interface TradeDao {

	void saveTrade(Trade trade) throws SQLException;
	
	void createEmptyTrade(Trade trade)   throws SQLException ; 
	
	List<Trade> getTradesWithItems(Integer userId) throws SQLException ;  
}
