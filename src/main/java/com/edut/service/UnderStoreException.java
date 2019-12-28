package com.edut.service;

import java.util.List;

import com.edut.pojo.domain.Book;

public class UnderStoreException extends Exception {
	private List<Book> underStoreBooks ; 
	
	public UnderStoreException(List<Book> underStoreBooks) {
		this.underStoreBooks = underStoreBooks ; 
	}
	
	public List<Book> getUnderStoreBooks() {
		return underStoreBooks;
	}
}
