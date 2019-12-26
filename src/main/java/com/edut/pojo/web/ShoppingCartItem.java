package com.edut.pojo.web;

import com.edut.pojo.domain.Book;

/**
 * 属性
 * book
 * quantity
 */
public class ShoppingCartItem {
	private Book book ; 
	private Integer  quantity ;
	
	public Integer getItemId() {
		return book.getBookID();
	}
	
	public ShoppingCartItem(Book book) {
		this.book = book ; 
		quantity  = 1 ; 
	}
	
	/**
	 * 这个 Item 的总价 
	 */
	public Double getMoney() {
		return book.getPrice() * quantity  ;  
	}
	
	public void increment() {
		quantity++ ; 
	}
	
	public void reduction() {
		quantity-- ; 
	}
	public Book getBook() {
		return book;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) throws IllegalArgumentException {
		if(quantity<0) {
			throw new IllegalArgumentException() ; 
		}
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [getItemId()=" + getItemId() + ", getMoney()=" + getMoney() + ", getBook()="
				+ getBook() + ", getQuantity()=" + getQuantity() + "]";
	}
	
	
	
}
