package com.edut.test.dao;


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
	
	//@Test
	public void test_queryForList() {
		String sql = "select * from book_table " ;
		List<Book> list = bookDao.queryForList(sql );
		System.out.println("\n@@@@@@@@@@@@@@ ===  test_queryForList  =============\n");
		for (Book book : list) {
			System.out.println(book);
		}
	}
	
	//@Test
	public void test_getSingleVal() {
		String sql = "select publishing_date from book_table where  book_id = ? " ;
		Date date  = bookDao.getSingleVal(sql, 1);
		System.out.println(date);
	}
	
	//@Test
	public void test_getSingleVal2() {
		String sql = "select count(book_id) from book_table " ;
		long count   = bookDao.getSingleVal(sql);
		System.out.println(count);
		
	}
	
	@Test
	public void test_batch() {
		test_queryForList() ;
		
		String sql = "update book_table set sales_amount = ? , STORE_NUMBER = ? Where book_id= ?" ;
		
		//注意，這裡！sql 是怎麼寫的！！
		bookDao.batch(sql, new Integer[][] {{1,1,1},{2,2 , 2 }});
		
		
		test_queryForList() ;
	}
}
