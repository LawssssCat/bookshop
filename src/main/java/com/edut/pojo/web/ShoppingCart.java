package com.edut.pojo.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.edut.pojo.domain.Book;

/**
 * map数据结构 key - bookID value - ShoppingCartItem
 */
public class ShoppingCart {
	private Map<Integer, ShoppingCartItem> items  = new HashMap<>() ;
	
	

	/**
	 * 添加书本 - 进购物框
	 */
	public void add(Book book) {
		Integer itemId = getItemId(book);
		if (hasBook(book)) {
			// 已经有这本书了
			ShoppingCartItem item = items.get(itemId);
			item.increment();
		} else {
			// 还没有这本书
			ShoppingCartItem item = new ShoppingCartItem(book);
			items.put(item.getItemId(), item);
		}
	}

	public boolean hasBook(Book book) {
		return items.containsKey(book.getBookID());
	}

	public Map<Integer, ShoppingCartItem> getItems() {
		return items;
	}
	
	public Integer getItemId(Book book) {
		return book.getBookID() ; 
	}

	public Collection<ShoppingCartItem> getItemsCollection() {
		return items.values();
	}

	/**
	 * @return 总书数量
	 */
	public Integer getBookNumber() {
		Integer totalNumber = 0;

		if (items != null && items.size() > 0) {
			Collection<ShoppingCartItem> itemsVal = this.items.values();
			for (ShoppingCartItem item : itemsVal) {
				totalNumber += item.getQuantity();
			}
		}

		return totalNumber;
	}

	/**
	 * @return 购物篮 总价格
	 */
	public Double getTotalMoney() {

		Double totalMoney = 0.0;

		if (items != null && items.size() > 0) {
			Collection<ShoppingCartItem> cartItems = items.values();
			for (ShoppingCartItem shoppingCartItem : cartItems) {
				totalMoney += shoppingCartItem.getMoney();
			}
		}

		return totalMoney;
	}

	public boolean isEmpty() {
		return items.size() == 0;
	}

	public void clear() {
		items.clear();
	}


	public void removeItem(Integer itemId ) {
		items.remove(itemId) ; 
	}

	public ShoppingCartItem getItemById(Integer itemId) {
		return items.get(itemId) ; 
	}
	public void setBookQuantity(Integer id, Integer quantity) throws Exception {
		ShoppingCartItem item = getItemById(id);
		if(item!=null) {
			item.setQuantity(quantity); 
		}else {
			throw new Exception() ; 
		}
	}

	public Double getItemMoney(Integer id) {
		return getItemById(id).getMoney();
	}

}
