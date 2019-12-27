package com.edut.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.edut.dao.BookDao;
import com.edut.dao.imp.BookDaoImpl;
import com.edut.pojo.domain.Book;
import com.edut.pojo.web.CriteriaBook;
import com.edut.pojo.web.Page;
import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;

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

	public void validateStore(ShoppingCart cart) throws UnderStoreException {
		Collection<ShoppingCartItem> items = cart.getItemsCollection();
		List<Book> underStoreBooks = new ArrayList<>(); 
		for (ShoppingCartItem item : items) {
			Integer quantity = item.getQuantity();
			Integer bookId = item.getItemId();
			Book book = getBookById(bookId);
			Integer storeNumber = book.getStoreNumber();
			if(storeNumber < quantity ) {
				underStoreBooks.add(book) ; 
			}
		}
		if(underStoreBooks.size()>0) {
			throw new UnderStoreException(underStoreBooks) ; 
		}
	}
	
}
