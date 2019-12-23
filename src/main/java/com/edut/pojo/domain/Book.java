package com.edut.pojo.domain;

import java.util.Date;

public class Book {
	private Integer book_id ;
	private String author ; 
	private String title ; 
	private Double price ; 
	private Date publishing_Date ; 
	private Integer sales_amount ; 
	private Integer Store_Number ; 
	private String remark ;
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getPublishing_Date() {
		return publishing_Date;
	}
	public void setPublishing_Date(Date publishing_Date) {
		this.publishing_Date = publishing_Date;
	}
	public Integer getSales_amount() {
		return sales_amount;
	}
	public void setSales_amount(Integer sales_amount) {
		this.sales_amount = sales_amount;
	}
	public Integer getStore_Number() {
		return Store_Number;
	}
	public void setStore_Number(Integer store_Number) {
		Store_Number = store_Number;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", author=" + author + ", title=" + title + ", price=" + price
				+ ", publishing_Date=" + publishing_Date + ", sales_amount=" + sales_amount + ", Store_Number="
				+ Store_Number + ", remark=" + remark + "]";
	}
	
	
	
	
	
}
