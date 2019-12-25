package com.edut.test.pojo;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.edut.dao.BookDao;
import com.edut.dao.imp.BookDaoImpl;
import com.edut.pojo.domain.Book;
import com.edut.pojo.domain.ShoppingCart;
import com.edut.pojo.domain.ShoppingCartItem;
import com.edut.pojo.web.CriteriaBook;

public class Test_ShopingCart {
	
	private static BookDao bookDao ;
	private static CriteriaBook cb  ;
	private static List<Book> books  ; 
	private static ShoppingCart shoppingCart  ;
	
	static {
		bookDao = new BookDaoImpl() ;
		
		int pageSize = 6;
		cb = new CriteriaBook(0, Integer.MAX_VALUE, 1); 
		books = bookDao.getPageList(cb, pageSize );
		shoppingCart = new ShoppingCart() ;
	}

	@Test
	public void testAdd() {
		System.out.println("@@@@@@@ ######## --  test addBook  ---------------");
		//加入数据库前6本
		for (Book book : books) {
			shoppingCart.add(book);
		}
		//再次
		//加入数据库前6本书
		for (Book book : books) {
			shoppingCart.add(book);
		}
	}

	@Test
	public void testHasBook() {
		testAdd();
		System.out.println("@@@@@@@ ######## --  test hasBook  ---------------");
		for (Book book : books) {
			System.out.println("bookID:"+book.getBookID()+" - HasBook:"+shoppingCart.hasBook(book ));
		}
		
	}

	@Test
	public void testGetItems() {
		testHasBook();
		System.out.println("@@@@@@@ ######## --  testGetItems  ---------------");
		Map<Integer, ShoppingCartItem> items = shoppingCart.getItems();
		Set<Entry<Integer, ShoppingCartItem>> entrySet = items.entrySet();
		for (Entry<Integer, ShoppingCartItem> entry : entrySet) {
			System.out.println("bookId:"+entry.getKey()+", book:"+entry.getValue());
		}
	}

	@Test
	public void testGetItemsCollection() {
		testGetItems();
		System.out.println("@@@@@@@ ######## --  testGetItemsCollection  ---------------");
		Collection<ShoppingCartItem> items = shoppingCart.getItemsCollection();
		for (ShoppingCartItem item : items) {
			System.out.println(item);
		}
	}

	@Test
	public void testGetBookNumber() {
		testGetItemsCollection();
		System.out.println("@@@@@@@ ######## --  testGetBookNumber  ---------------");
		Integer bookNumber = shoppingCart.getBookNumber();
		System.out.println("bookNumber:"+bookNumber);
	}

	@Test
	public void testGetTotalMoney() {
		testGetBookNumber() ;
		System.out.println("@@@@@@@ ######## --  testGetTotalMoney  ---------------");
		Double totalMoney = shoppingCart.getTotalMoney();
		System.out.println("totalMoney:"+totalMoney);
	}
	
	@Test
	public void testRemoveItem_$$_GetItemId() {
		testGetTotalMoney();
		System.out.println("@@@@@@@ ######## --  testRemoveItem  ---------------");
		Book book = books.get(0);
		
		shoppingCart.removeItem(shoppingCart.getItemId(book));
		
		Double totalMoney = shoppingCart.getTotalMoney();
		System.out.println("totalMoney:"+totalMoney);
	}

	@Test
	public void testIsEmpty() {
		testRemoveItem_$$_GetItemId();
		System.out.println("@@@@@@@ ######## --  testIsEmpty  ---------------");
		System.out.println("clear前，isEmpry : "+ shoppingCart.isEmpty());
	}

	
	
	@Test
	public void testClear() {
		testIsEmpty();
		System.out.println("@@@@@@@ ######## --  testClear  ---------------");
		shoppingCart.clear();
		System.out.println("clear后，isEmpry : "+ shoppingCart.isEmpty());
	}


}
