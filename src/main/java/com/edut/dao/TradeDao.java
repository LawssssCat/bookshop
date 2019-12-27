package com.edut.dao;

import java.util.Date;

import com.edut.pojo.web.ShoppingCart;

/**
 * 结账时候 
 */
public interface TradeDao {
	void saveTrade(Integer userId , ShoppingCart cart ) ;
	Integer insertNewTrade(Integer userId , Date date) ; 
}
