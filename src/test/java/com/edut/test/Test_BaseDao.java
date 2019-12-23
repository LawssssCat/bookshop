package com.edut.test;


import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.edut.dao.BookDao;
import com.edut.dao.Dao;
import com.edut.dao.imp.BaseDao;
import com.edut.dao.imp.BookDaoImpl;
import com.edut.pojo.domain.Book;

public class Test_BaseDao {
	
	private Dao baseDao = new BaseDao() ; 
	private BookDaoImpl bookDao = new BookDaoImpl();
	
	
	//@Test
	public void test_insert() {
		String sql = "INSERT INTO Trade_table (user_id , trade_time) VALUES (? ,? ) " ;
		Long id = baseDao.insert(sql, 1, new Date(new java.util.Date().getTime())); 
		
		System.out.println("id="+id);
	}
	
	//@Test 
	public void test_update() {
		String sql = "UPDATE  Trade_table set  user_id= ? where user_id= ?;" ;
		baseDao.update(sql, 2 ,1 );
		System.out.println("test_update。。。");
	}
	
	//@Test
	public void test_query() {
		String sql = "select * from book_table WHERE BOOK_ID = ? " ;
		Book query = bookDao.query(sql , 1);
		System.out.println("\n@@@@@@@@@@@@@@ ===  test_query  =============\n");
		System.out.println(query.toString() );
	}
	
	@Test
	public void test_queryForList() {
		String sql = "select * from book_table " ;
		List<Book> list = bookDao.queryForList(sql );
		System.out.println("\n@@@@@@@@@@@@@@ ===  test_queryForList  =============\n");
		for (Book book : list) {
			System.out.println(book);
		}
	}
	
}
