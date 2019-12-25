package com.edut.service;

import com.edut.dao.BookDao;
import com.edut.dao.imp.BookDaoImpl;
import com.edut.pojo.domain.Book;
import com.edut.pojo.domain.ShoppingCart;
import com.edut.pojo.web.CriteriaBook;
import com.edut.pojo.web.Page;

public class BookService {
	
	private BookDao bookDao = new BookDaoImpl() ; 
	
	public Page<Book> getPage(CriteriaBook cb ) {
		return bookDao.getPage(cb) ; 
	}

	public Book getBookById(Integer bookID) {
		return bookDao.getBook(bookID);
	}

	public void addToCart(Integer bookID, ShoppingCart cart) throws Exception {
		Book book = getBookById(bookID);
		if(book!=null) {
			cart.add(book);
		}else {
			throw new Exception() ; 
		}
	}
	
}
