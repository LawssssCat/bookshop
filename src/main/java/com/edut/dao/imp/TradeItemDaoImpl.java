package com.edut.dao.imp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;

import com.edut.dao.BookDao;
import com.edut.dao.TradeItemDao;
import com.edut.pojo.domain.Book;
import com.edut.pojo.domain.TradeItem;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;
import com.edut.tools.JdbcUtils;

public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao {

	private BookDao bookDao = new BookDaoImpl() ; 
	
	@Override
	public void batchInsert(Integer tradeId , Set<TradeItem> items) 
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

	@Override
	public List<TradeItem> getItemsWithBook(Integer tradeId) throws SQLException {
		String sql =  " select "
					+ " ITEM_ID itemId , "
					+ " QUANTITY  , "
					+ " BOOK_ID bookId , "
					+ " TRADE_ID tradeId "
					+ " from trade_item_table "
					+ " where trade_id = ?  " ;
		List<TradeItem> items = queryForList(sql, tradeId);
		for (TradeItem item : items) {
			int id = item.getBookId();
			Book book = bookDao.getBook(id ); 
			item.setBook(book );
		}
		
		return items ;  
	}


}
