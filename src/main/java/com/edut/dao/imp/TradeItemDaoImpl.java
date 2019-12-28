package com.edut.dao.imp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.commons.dbutils.QueryRunner;

import com.edut.dao.TradeItemDao;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;
import com.edut.tools.JdbcUtils;

public class TradeItemDaoImpl implements TradeItemDao {

	private QueryRunner queryRunner = new QueryRunner() ;

	@Override
	public void batchInsertItemDao(Integer tradeId, Collection<ShoppingCartItem> cartItems)
			throws SQLException {
		Connection conn = null ; 
		try {
			conn = JdbcUtils.getConn();
			
			String sql =  " insert into trade_item_table "
					+ " values( "
					+ " null , "
					+ " ? , " //quantity
					+ " ?  ," // bbook_id
					+ " ? ) ";  //trade_id  
			
			Object[][] args = new Object[cartItems.size()][];
			int index = 0 ; 
			for (ShoppingCartItem item : cartItems) {
				Integer quantity = item.getQuantity();
				Integer bookId = item.getBook().getBookID();
				args[index] = new Object[] {quantity , bookId , tradeId} ; 
				index++ ; 
			}
			queryRunner.batch(conn , sql , args) ; 
		} catch (SQLException e) {
			throw e ; 
		} finally {
			JdbcUtils.close(conn);
		}		
	} 
	
	

}
