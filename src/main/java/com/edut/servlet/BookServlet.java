package com.edut.servlet;

import java.io.IOException;

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
		
		req.setAttribute("page", page);
		req.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(req, resp);
	}
}
