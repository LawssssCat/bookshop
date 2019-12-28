package com.edut.dao.imp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;

import com.edut.dao.TradeItemDao;
import com.edut.pojo.domain.TradeItem;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;
import com.edut.tools.JdbcUtils;

public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao {

	@Override
	public void batchInsert(Long tradeId , Set<TradeItem> items) 
			throws SQLException  {
		String sql =  " insert into trade_item_table "
					+ " ( QUANTITY , BOOK_ID , TRADE_ID ) " 
					+ " value "
					+ " ( ? , ? , ? ) " ; 
		Object[][] params = new Object[items.size()][] ;
		int index = 0 ; 
		for (TradeItem item : items) {
			Integer quantity = item.getQuantity();
			Integer bookId = item.getBookId();
			params[index] = new Object[] {quantity , bookId , tradeId} ;
			index++ ; 
		}
		
		batch(sql, params);
	}


}
