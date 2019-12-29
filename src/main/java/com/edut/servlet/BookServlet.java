package com.edut.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edut.ex.FindEmptyException;
import com.edut.ex.InsufficientBalanceException;
import com.edut.ex.NoSuchBookException;
import com.edut.ex.NoSuchUserException;
import com.edut.ex.TransactionException;
import com.edut.ex.UnderStoreException;
import com.edut.pojo.domain.Book;
import com.edut.pojo.domain.User;
import com.edut.pojo.web.CriteriaBook;
import com.edut.pojo.web.Page;
import com.edut.pojo.web.ShoppingCart;
import com.edut.service.BookService;
import com.edut.service.TradeService;
import com.edut.service.UserService;
import com.edut.tools.ShoppingCartUtils;
import com.edut.tools.Utils;
import com.google.gson.Gson;

public class BookServlet extends HttpServlet {

	private BookService bookService;
	private UserService userService;
	private TradeService tradeService;

	@Override
	public void init() throws ServletException {
		bookService = new BookService();
		userService = new UserService();
		tradeService = new TradeService(userService, bookService);
	}

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
		// 获取方法名
		String methodName = req.getParameter("method");
		try {
			// 转跳执行方法
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 支付！
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void cash(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// uri 数据
		try {
			String username = req.getParameter("username");
			Integer accountId = Utils.parseStr(req.getParameter("accountId"), -1) ; 
			ShoppingCart cart = ShoppingCartUtils.getShoppingCart(req);
			tradeService.cash(username, accountId, cart);
			resp.sendRedirect("success.jsp");
		} catch (TransactionException e) {
			String errorMsg = e.getErrorMsg();
			req.setAttribute("errorMsg", errorMsg);
			toPage(req, resp, "cash");
			throw new ServletException(e);
		}
	}

	/**
	 * 更新 item 里面 书的 数量
	 */
	protected void updateItemQuantity(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			Integer quantity = Integer.parseInt(req.getParameter("quantity"));

			ShoppingCart shoppingCart = ShoppingCartUtils.getShoppingCart(req);

			String json = ShoppingCartUtils.getJsonUpdateItemQuantity(shoppingCart, id, quantity);

			resp.setContentType("text/javascript");
			resp.getWriter().write(json);
		} catch (NumberFormatException e) {
			toErrorPage(req, resp);
		}
	}

	protected void removeItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer itemId = Integer.parseInt(req.getParameter("itemId"));

		// ShoppingCart cart = ShoppingCartService.getShoppingCart(req, cartStr);
		ShoppingCart cart = ShoppingCartUtils.getShoppingCart(req);
		cart.removeItem(itemId);

		if (cart.isEmpty()) {
			getBooks(req, resp);
		} else {
			toPage(req, resp, "cart");
		}

	}

	protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ShoppingCartUtils.clearCart(req);
		getBooks(req, resp);
	}

	protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 1. 获取商品的 id
			// "bookServlet?method=addToCart&pageNo=${page.pageNo }&bookID=${book.bookID}"
			Integer bookID = Integer.parseInt(req.getParameter("bookID"));
			// 2. 获取购物车对象
			ShoppingCart cart = ShoppingCartUtils.getShoppingCart(req);
			// 3. 调用 BookService 的 addToCart() 方法 把商品放到购物车中
			bookService.addToCart(bookID, cart);
			// 4. 直接调用 getBooks() 方法
			getBooks(req, resp);
		} catch (NoSuchBookException | NumberFormatException e) {
			toErrorPage(req, resp);
		}

	}

	/**
	 * 展示书详细
	 */
	protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 根据 id 获取book
			String bookIDStr = req.getParameter("bookID");
			Integer bookID = Integer.parseInt(bookIDStr);

			// 根据 ID 获取 Book
			Book book = bookService.getBookById(bookID);

			req.setAttribute("book", book);
			req.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(req, resp);

		} catch (NumberFormatException | NoSuchBookException e) {
			toErrorPage(req, resp);
		}
	}

	/**
	 * 展示 所有书
	 */
	protected void getBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int pageNo = Utils.parseStr(req.getParameter("pageNo"), 1);
			int minPrice = Utils.parseStr(req.getParameter("minPrice"), 0);
			int maxPrice = Utils.parseStr(req.getParameter("maxPrice"), Integer.MAX_VALUE);

			CriteriaBook cb = new CriteriaBook(minPrice, maxPrice, pageNo);
			// FindEmptyException
			Page<Book> page = bookService.getPage(cb);
			req.setAttribute("page", page);
			req.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(req, resp);
		} catch (FindEmptyException e) {
			toErrorPage(req, resp);
		}
	}

	protected void toPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		toPage(req, resp, page);
	}

	protected void toPage(HttpServletRequest req, HttpServletResponse resp, String page)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/" + page + ".jsp").forward(req, resp);
	}

	private void toErrorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/error-01.jsp").forward(req, resp);
	}
}
