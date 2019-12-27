package com.edut.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.edut.pojo.web.ShoppingCart;
import com.edut.pojo.web.ShoppingCartItem;
import com.google.gson.Gson;

public class ShoppingCartUtils {

	public static ShoppingCart getShoppingCart(HttpServletRequest req ) {
		String cartStr = "cart" ; 
		HttpSession session = req.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute(cartStr);
		
		if(cart == null ) {
			cart = new  ShoppingCart() ; 
			session.setAttribute(cartStr, cart);
		}
		
		return cart ; 
	}

	public static String getJsonUpdateItemQuantity(ShoppingCart shoppingCart, Integer id, Integer quantity) {
		shoppingCart.setBookQuantity(id, quantity);
		HashMap<Object, Object> map = new HashMap<>();
		//====  返回的 json  ======================== 
		//单个 item 钱
		Double itemMoney = shoppingCart.getItemMoney(id);
		map.put("itemMoney", itemMoney ) ; 
		//quantity
		map.put("quantity", quantity);
		//总价
		Double totalMoney = shoppingCart.getTotalMoney();
		map.put("totalMoney", totalMoney) ; 
		//总书 number
		Integer bookNumber = shoppingCart.getBookNumber();
		map.put("bookNumber", bookNumber); 
		
		Gson gson = new Gson() ; 
		String json = gson.toJson(map);
		
		return json;
	}
}
