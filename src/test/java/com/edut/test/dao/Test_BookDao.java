package com.edut.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.edut.dao.BookDao;
import com.edut.dao.imp.BookDaoImpl;
import com.edut.pojo.domain.Book;
import com.edut.pojo.web.CriteriaBook;
import com.edut.pojo.web.Page;

public class Test_BookDao {
	private BookDao bookDao = new BookDaoImpl() ; 

	@Test
	public void testGetBook() {
		Book book = bookDao.getBook(2);
		System.out.println(book);
	}

	private CriteriaBook cb = new CriteriaBook(0, Integer.MAX_VALUE, 1);
	
	
	@Test
	public void testGetPage() {
		Page<Book> page = bookDao.getPage(cb);
		
		System.out.println("@@@@@@@@@  ---------  Page Info  -----------");
		System.out.println("page:"+page);
		System.out.println("PageNo:"+page.getPageNo());
		System.out.println("PageSize:"+page.getPageSize());
		System.out.println("TotalItemNumber:"+page.getTotalItemNumber());
		System.out.println("TotalPageNumber:"+page.getTotalPageNumber());
		page.getList().forEach(b -> System.out.println(b));
		System.out.println("@@@@@@@@@  ---------  Page Info 2  -----------");
		System.out.println("hasNextPage:"+page.hasNextPage());
		System.out.println("nextPage:"+page.getNextPage());
		System.out.println("hasPrevPage:"+page.hasPrevPage());
		System.out.println("prevPage:"+page.getPrevPage());
		
	}
	

	@Test
	public void testGetTotalBookNumber() {
		long totalBookNumber = bookDao.getTotalBookNumber(cb);
		
		System.out.println("totalBookNumber : "+totalBookNumber);
	}

	@Test
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
