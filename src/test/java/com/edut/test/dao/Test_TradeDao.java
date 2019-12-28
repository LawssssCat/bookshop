package com.edut.test.dao;

import java.sql.Date;

import org.junit.Test;

import com.edut.dao.TradeDao;
import com.edut.dao.imp.TradeDaoImpl;

public class Test_TradeDao {

	private TradeDao tradeDao = new TradeDaoImpl() ; 
	
	@Test
	public void testInsertNewTrade() {
		int i = tradeDao.insertNewTrade(1, new Date(new java.util.Date().getTime())) ;
		System.out.println("tradeId = " + i );
	}
}
