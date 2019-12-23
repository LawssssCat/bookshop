package com.edut.test;


import java.sql.Date;

import org.junit.Test;

import com.edut.dao.imp.BaseDao;

public class Test_BaseDao {
	
	private BaseDao baseDao = new BaseDao() ; 
	
	
	//@Test
	public void test_insert() {
		String sql = "INSERT INTO Trade_table (user_id , trade_time) VALUES (? ,? ) " ;
		Long id = baseDao.insert(sql, 1, new Date(new java.util.Date().getTime())); 
		
		System.out.println("id="+id);
	}
	
	@Test 
	public void test_update() {
		String sql = "UPDATE  Trade_table set  user_id= ? where user_id= ?;" ;
		baseDao.update(sql, 2 ,1 );
		System.out.println("test_update。。。");
	}
	
	
	
}
