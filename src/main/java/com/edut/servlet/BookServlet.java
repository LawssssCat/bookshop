package com.edut.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edut.pojo.domain.Book;
import com.edut.pojo.domain.ShoppingCart;
import com.edut.pojo.web.CriteriaBook;
import com.edut.pojo.web.Page;
import com.edut.service.BookService;
import com.edut.service.ShoppingCartService;
import com.edut.tools.Utils;

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
		//获取方法名 
		String methodName = req.getParameter("method");
		try {
			//转跳执行方法
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
	
	protected void addToCart(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//1. 获取商品的 id
		//"bookServlet?method=addToCart&pageNo=${page.pageNo }&bookID=${book.bookID}"
		String bookIDStr = req.getParameter("bookID");
		Integer bookID = Utils.parseStr(bookIDStr, -1) ;
		
		if (bookID<=0) {
			toErrorPage(req, resp);
			return ;
		}
		
		//2. 获取购物车对象
		String cartStr = "cart" ; 
		ShoppingCart cart = ShoppingCartService.getShoppingCart(req, cartStr); 
		
		//3. 调用 BookService 的 addToCart() 方法 把商品放到购物车中
		try {
			bookService.addToCart(bookID , cart) ;
		} catch (Exception e) {
			toErrorPage(req, resp);
			return ;
		}
		
		//4. 直接调用 getBooks() 方法
		getBooks(req, resp);
	}
	/**
	 * 展示书详细
	 */
	protected void getBook(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		//根据 id 获取book
		String bookIDStr = req.getParameter("bookID");
		Integer bookID =Utils.parseStr(bookIDStr,	-1) ;
		
		if(bookID<=0) {
			toErrorPage(req, resp);
			return ;
		}
		
		//根据 ID 获取 Book
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
		
		

		int pageNo = Utils.parseStr(pageNoStr, 1) ; 
		int minPrice = Utils.parseStr(minPriceStr,0 )  ; 
		int maxPrice = Utils.parseStr(maxPriceStr,Integer.MAX_VALUE) ;  
		
		
		CriteriaBook cb = new CriteriaBook(minPrice, maxPrice, pageNo);
		
		Page<Book> page = bookService.getPage(cb);
		
		//查不到数据，转跳错误页
		if(page.getList().size() ==0) {
			toErrorPage(req , resp) ; 
			return ;
		}else {
			//TODO 。。。。
		}
		req.setAttribute("page", page);
		req.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(req, resp);
	}
	
	private void toErrorPage(HttpServletRequest req ,  HttpServletResponse resp) 
			throws ServletException, IOException {
		req.getRequestDispatcher("/error-01.jsp").forward(req, resp);
	}
}
