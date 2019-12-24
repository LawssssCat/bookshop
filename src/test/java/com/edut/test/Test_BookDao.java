package com.edut.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.edut.dao.BookDao;
import com.edut.dao.imp.BookDaoImpl;
import com.edut.pojo.domain.Book;
import com.edut.pojo.web.CriteriaBook;

public class Test_BookDao {
	private BookDao bookDao = new BookDaoImpl() ; 

	@Test
	public void testGetBook() {
		Book book = bookDao.getBook(1);
		System.out.println(book);
	}

	@Test
	public void testGetPage() {
		fail("Not yet implemented");
	}
	
	
	private CriteriaBook cb = new CriteriaBook(0, Integer.MAX_VALUE, 2);
	

	@Test
	public void testGetTotalBookNumber() {
		long totalBookNumber = bookDao.getTotalBookNumber(cb);
		
		System.out.println("totalBookNumber : "+totalBookNumber);
	}

	//@Test
	public void testGetPageList() {
		List<Book> pageList = bookDao.getPageList(cb, 3);
		
		for (Book book : pageList) {
			System.out.println(book);
		}
	}

	//@Test
	public void testGetStoreNumber() {
		int storeNumber = bookDao.getStoreNumber(1);
		System.out.println("storeNumber="+ storeNumber);
	}

}
