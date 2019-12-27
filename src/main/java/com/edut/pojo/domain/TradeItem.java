package com.edut.pojo.domain;

public class TradeItem {
	private Integer itemId ; 
	private Integer quantity ; 
	private Integer bookId ; 
	private Integer tradeId ;
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getTradeId() {
		return tradeId;
	}
	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}
	@Override
	public String toString() {
		return "TradeItem [itemId=" + itemId + ", quantity=" + quantity + ", bookId=" + bookId + ", tradeId=" + tradeId
				+ "]";
	} 
	
}
