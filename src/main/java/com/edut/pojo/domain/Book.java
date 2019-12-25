package com.edut.pojo.domain;

import java.util.Date;

public class Book {
	private Integer bookID ;
	private String author ; 
	private String title ; 
	private Double price ; 
	private Date publishingDate ; 
	private Integer salesAmount ; 
	private Integer storeNumber ; 
	private String remark ;
	public Integer getBookID() {
		return bookID;
	}
	public void setBookID(Integer bookID) {
		this.bookID = bookID;
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
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	public Integer getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(Integer salesAmount) {
		this.salesAmount = salesAmount;
	}
	public Integer getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(Integer storeNumber) {
		this.storeNumber = storeNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", author=" + author + ", title=" + title + ", price=" + price
				+ ", publishingDate=" + publishingDate + ", salesAmount=" + salesAmount + ", storeNumber=" + storeNumber
				+ ", remark=" + remark + "]";
	}
	public Book(Integer bookID, String author, String title, Double price, Date publishingDate, Integer salesAmount,
			Integer storeNumber, String remark) {
		super();
		this.bookID = bookID;
		this.author = author;
		this.title = title;
		this.price = price;
		this.publishingDate = publishingDate;
		this.salesAmount = salesAmount;
		this.storeNumber = storeNumber;
		this.remark = remark;
	}
	public Book() {
		super();
	}
	
	
}
