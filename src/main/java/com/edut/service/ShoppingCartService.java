package com.edut.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.edut.pojo.domain.ShoppingCart;
import com.edut.pojo.domain.ShoppingCartItem;

public class ShoppingCartService {

	public static ShoppingCart getShoppingCart(HttpServletRequest req , String cartStr) {
		HttpSession session = req.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute(cartStr);
		
		if(cart == null ) {
			cart = new  ShoppingCart() ; 
			session.setAttribute(cartStr, cart);
		}
		
		return cart ; 
	}
}
