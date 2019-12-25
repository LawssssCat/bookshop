package com.edut.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edut.pojo.domain.Book;
import com.edut.pojo.web.CriteriaBook;
import com.edut.pojo.web.Page;
import com.edut.service.BookService;

public class BookServlet extends HttpServlet {
	
	private BookService bookService = new BookService() ; 

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
		String methodName = req.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, 
					HttpServletRequest.class , HttpServletResponse.class) ;
			method.setAccessible(true); 
			method.invoke(this, req , resp ) ; 
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
	
	/**
	 * 展示书详细
	 */
	protected void getBook(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		//根据 id 获取book
		String bookIDStr = req.getParameter("bookID");
		
		Integer bookID = -1 ;
		try {
			bookID = Integer.parseInt(bookIDStr) ; 
		}catch (NumberFormatException e) {}
		
		Book book = bookService.getBookById(bookID) ;
		req.setAttribute("book", book);
		
		req.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(req, resp);
		
	}
	
	/**
	 * 展示 所有书 
	 */
	protected void getBooks(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo");
		String minPriceStr = req.getParameter("minPrice");
		String maxPriceStr = req.getParameter("maxPrice");
		
		int pageNo = 1 ; 
		int minPrice = 0 ; 
		int maxPrice = Integer.MAX_VALUE ; 
		
		try {
			pageNo = Integer.parseInt(pageNoStr) ; 
		}catch (NumberFormatException e) {}
		try {
			minPrice = Integer.parseInt(minPriceStr) ; 
		}catch (NumberFormatException e) {}
		try {
			maxPrice = Integer.parseInt(maxPriceStr) ; 
		}catch (NumberFormatException e) {}
		
		
		CriteriaBook cb = new CriteriaBook(minPrice, maxPrice, pageNo);
		
		Page<Book> page = bookService.getPage(cb);
		if(page.getList().size() ==0) {
			req.getRequestDispatcher("/error-01.jsp").forward(req, resp);
			return ;
		}else {
		}
		req.setAttribute("page", page);
		req.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(req, resp);
	}
}
