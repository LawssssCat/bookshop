package com.edut.test.pojo;

import org.junit.Test;

import com.edut.dao.BookDao;
import com.edut.dao.imp.BookDaoImpl;
import com.edut.pojo.domain.Book;
import com.edut.pojo.domain.ShoppingCartItem;

public class Test_ShoppingCartItem {
	
	private BookDao bookDao ; 
	private ShoppingCartItem item ; 
	
	{
		bookDao = new BookDaoImpl() ; 
		Book book = bookDao.getBook(1);
		item = new ShoppingCartItem(book) ; 
	}
	
	
	@Test
	public void testGetBook() {
		Book book = item.getBook();
		System.out.println(book);
	}
	
	@Test
	public void testGetQuantity_$$_Increment_$$_SetQuantity_$$_GetItemMoney() {
		System.out.println("-------  getQuantity  ---------------");
		System.out.println("quantity="+item.getQuantity());
		
		item.increment();
		System.out.println("添加亿本书...");
		
		System.out.println("quantity="+item.getQuantity());
		
		System.out.println("------  setQuantity  -----------");
		Integer n = 33; 
		System.out.println("设置为："+n);
		try {
			item.setQuantity(n);
			System.out.println("设置成功！");
		}catch (IllegalArgumentException e) {
			System.out.println("@@@@@  --- 参数设置错误！"+n);
		}
		
		n=-1 ; 
		System.out.println("设置为："+n);
		try {
			item.setQuantity(n);
		}catch (IllegalArgumentException e) {
			System.out.println("@@@@@  --- 参数设置错误！"+n);
		}
		
		System.out.println("----------  getMoney  --------------");
		System.out.println("quantity="+item.getQuantity());
		System.out.println("item's single book price:"+item.getBook().getPrice());
		System.out.println("money:"+item.getMoney());
	}
	

	
}
