package com.edut.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.edut.pojo.domain.Trade;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;

public interface TradeItemDao {
	void batchInsertItemDao(Integer tradeId , Collection<ShoppingCartItem> cartItems) throws SQLException ;
}
