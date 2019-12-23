package com.edut.pojo.web;

import java.util.List;

public class Page <T>{
//-----------------  私有属性  ------------------------------
	
	//当前第几页
	private int pageNo;
	
	//当前页的List
	private List<T> list ; 
	
	//当前页显示多少条记录
	private int pageSize = 3 ; 
	
	//共有多少条记录
	private int totalItemNumber ;
	
	
//-----------------  构造函数  ------------------------------
	/**
	 * 构造器中需要对 pageNo 进行初始化
	 */
	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}

	
//-----------------  方法实现  ------------------------------
	/**
	 * 获取总页数
	 */
	public int getTotalPageNumber() {
		int totalPageNumber = totalItemNumber / pageSize ;
		if(totalItemNumber%pageSize!=0) {
			totalPageNumber++ ; 
		}
		return totalPageNumber ;
	}
	
	/**
	 * 有下一页？ 
	 */
	public boolean hasNextPage() {
		return getPageNo()<getTotalPageNumber() ; 
	}
	/**
	 * 有上一页？
	 */
	public boolean hasPrevPage() {
		return getPageNo()>1; 
	}
	
	public int getNextPage() {
		if(hasNextPage()) {
			return getPageNo() + 1  ; 
		}else {
			return getPageNo()  ; 
		}
	}
	public int getPrevPage() {
		if(hasPrevPage()) {
			return getPageNo() -1  ; 
		}else{
			return getPageNo() ; 
			
		}
	}
	
	
	public void setPageNo(int pageNo) {
		//从第一页开始
		if (pageNo <= 0) {
			pageNo = 1 ; 
		}else if(pageNo > getTotalPageNumber()) {
			pageNo = getTotalPageNumber() ; 
		}
		this.pageNo = pageNo;
	}


	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalItemNumber() {
		return totalItemNumber;
	}


	public void setTotalItemNumber(int totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}

	public int getPageNo() {
		return pageNo;
	}

	
}
