package com.edut.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edut.ex.NoSuchUserException;
import com.edut.pojo.domain.User;
import com.edut.service.UserService;

public class UserServlet extends HttpServlet {
	
	private UserService userService = new UserService() ; 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String methodStr = req.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodStr, 
					HttpServletRequest.class ,  HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, req,resp) ; 
		} catch (Exception e) {
			throw new ServletException() ; 
		} 
	}
	
	protected void toLogin(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
	}
	
	protected void getTradesUser(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String username = req.getParameter("username");
		try {

			User user = userService.getUserWithTrads(username);
			req.setAttribute("user", user);
			toLogin(req, resp);
		}catch(NoSuchUserException | SQLException e) {
			String errorMsg = "没有这个用户 或者 查找失败！" ;
			req.setAttribute("errorMsg", errorMsg);
			toLogin(req, resp);
			throw new ServletException(e) ; 
		}
	}
	
}
