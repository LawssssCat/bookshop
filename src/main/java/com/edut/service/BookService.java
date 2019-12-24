package com.edut.service;

import com.edut.dao.BookDao;
import com.edut.dao.imp.BookDaoImpl;
import com.edut.pojo.domain.Book;
import com.edut.pojo.web.CriteriaBook;
import com.edut.pojo.web.Page;

public class BookService {
	
	private BookDao bookDao = new BookDaoImpl() ; 
	
	public Page<Book> getPage(CriteriaBook cb ) {
		return bookDao.getPage(cb) ; 
	}
	
	
	
}
