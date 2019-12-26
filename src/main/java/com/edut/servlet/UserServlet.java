package com.edut.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edut.pojo.web.ShoppingCart;
import com.edut.service.ShoppingCartService;
import com.edut.service.UserService;
import com.edut.tools.Utils;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String methodStr = req.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, req,resp) ; 
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private UserService userService = new UserService() ; 
	
	protected void cash(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
			String username = req.getParameter("username");
			String accountIdStr = req.getParameter("accountId");
			
			Integer accountId = Utils.parseStr(accountIdStr, -1);
			if(accountId!=-1) {
				if(userService.validateUser(username , accountId)) {
					ShoppingCart cart = ShoppingCartService.getShoppingCart(req, "cart");
					try {
						userService.carsh(cart , username , accountId) ;
						req.getRequestDispatcher("success.jsp").forward(req, resp);
					}catch (Exception e) {
						//TODO 创建一个库存异常Exception 
						req.getRequestDispatcher("error-03.jsp").forward(req, resp);
					}
					return ; 
				}
			}
		req.getRequestDispatcher("error-02.jsp").forward(req, resp);
	}
	
}
