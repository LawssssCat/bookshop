package com.edut.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.edut.dao.BookDao;
import com.edut.pojo.domain.Book;
import com.edut.pojo.web.CriteriaBook;
import com.edut.pojo.web.Page;
import com.edut.pojo.web.ShoppingCartItem;

public class BookDaoImpl  extends BaseDao<Book> implements BookDao{

	@Override
	public Book getBook(int id) throws SQLException {
		String sql = "SELECT "
				+ " book_id bookid, "
				+ " author , "
				+ " title , "
				+ " price , "
				+ " PUBLISHING_DATE publishingDate , "
				+ " SALES_AMOUNT salesAmount , "
				+ " STORE_NUMBER  StoreNumber , "
				+ " remark "
				+ " from book_table "
				+ " where book_id = ? " ; 
		return query(sql , id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) throws SQLException {
		
		//No
		Page<Book> page = new Page<>(cb.getPageNo());
		
		
		//List
		List<Book> list = getPageList(cb , page.getPageSize()) ;
		
		//检验cb的No正确性
		cb.setPageNo(page.getPageNo());
		
		//totalBookNumber
		Integer totalItemNumber = (int) getTotalBookNumber(cb);
		
		page.setTotalItemNumber(totalItemNumber);
		page.setList(list);
		
		return page;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) throws SQLException {
		String sql = "select count(book_id) "
				+ "from book_table "
				+ "where price>=? and price <=? " ; 
		return getSingleVal(sql, cb.getMinPrice() , cb.getMaxPrice());
	}

	/**
	 * MySQL 分页使用 LIMIT，其中 limit 从 0开始。
	 * @throws SQLException 
	 */
	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) throws SQLException {
		String sql = " select "
				+ " book_id bookid, "
				+ " author , "
				+ " title , "
				+ " price , "
				+ " PUBLISHING_DATE publishingDate , "
				+ " SALES_AMOUNT salesAmount , "
				+ " STORE_NUMBER  StoreNumber , "
				+ " remark "
				+ " from book_table "
				+ " where price >= ? and price <= ? "
				+ " limit ? , ? " ; 
		
		int startItem = pageSize*(cb.getPageNo()-1); 
		
		return queryForList(sql, cb.getMinPrice() , cb.getMaxPrice() ,
				 startItem , pageSize );
	}

	@Override
	public int getStoreNumber(Integer id) throws SQLException {
		String sql = "Select Store_Number StoreNumber "
				+ " from book_table "
				+ "where book_id = ? " ; 
		return getSingleVal(sql, id);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) throws SQLException {
		String sql =  " update book_table set  "
					+ " SALES_AMOUNT = SALES_AMOUNT + ? , "
					+ " STORE_NUMBER = STORE_NUMBER - ? "
					+ " where BOOK_ID = ?  " ;
		int sqlSize = items.size();
		Object[][] params = new Object[sqlSize][];
		int index = 0 ; 
		for (ShoppingCartItem item : items) {
			Integer quantity = item.getQuantity();
			Integer bookID = item.getBook().getBookID();
			params[index] = new Object[] {quantity  , quantity , bookID } ; 
			index++ ; 
		}
		batch(sql, params);
	}

}
