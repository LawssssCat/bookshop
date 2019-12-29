package com.edut.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

import com.edut.pojo.domain.Trade;
import com.edut.pojo.domain.TradeItem;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;

public interface TradeItemDao {

	void batchInsert(Integer tradeId , Set<TradeItem> items) throws SQLException;
}
