package com.edut.dao.imp;

import java.util.List;

import com.edut.dao.BookDao;
import com.edut.pojo.domain.Book;
import com.edut.pojo.web.CriteriaBook;
import com.edut.pojo.web.Page;

public class BookDaoImpl  extends BaseDao<Book> implements BookDao{

	@Override
	public Book getBook(int id) {
		String sql = "SELECT *  "
				+ "from book_table "
				+ "where book_id = ? " ; 
		return query(sql , id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql = "select count(book_id) "
				+ "from book_table "
				+ "where price>=? and price <=? " ; 
		return getSingleVal(sql, cb.getMinPrice() , cb.getMaxPrice());
	}

	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql = "select * "
				+ "from book_table "
				+ "where price >= ? and price <= ? "
				+ "limit ? , ? " ; 
		
		int startItem = pageSize*(cb.getPageNo()-1); 
		
		return queryForList(sql, cb.getMinPrice() , cb.getMaxPrice() ,
				 startItem , pageSize );
	}

	@Override
	public int getStoreNumber(Integer id) {
		String sql = "Select Store_Number from book_table "
				+ "where book_id = ? " ; 
		return getSingleVal(sql, id);
	}

}
