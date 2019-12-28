package com.edut.dao;

import java.sql.SQLException;
import java.util.Date;

import com.edut.pojo.web.ShoppingCart;

/**
 * 结账时候 
 */
public interface TradeDao {
	void saveTrade(Integer userId , ShoppingCart cart ) throws SQLException  ;
	Integer insertNewTrade(Integer userId , Date date)  ; 
}
