package com.edut.test.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.edut.dao.TradeDao;
import com.edut.dao.imp.TradeDaoImpl;
import com.edut.pojo.domain.Trade;
import com.edut.pojo.web.ConnectionContext;
import com.edut.tools.JdbcUtils;

public class Test_TradeDao {

	private TradeDao tradeDao = new TradeDaoImpl() ; 
	
	{
		Connection connection = null;
		try {
			connection = JdbcUtils.getConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionContext.getInstance().bind(connection );
	}
	
	
	@Test
	public void test_getTradesWithItems() throws SQLException {
		List<Trade> trades = tradeDao.getTradesWithItems(1);
		for (Trade trade : trades) {
			System.out.println(trade);
		}
	}
}
