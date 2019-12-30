package com.edut.pojo.domain;

import java.util.Date;
import java.util.Set;

public class Trade {
	private Integer tradeId ; 
	private Date tradeTime ; 
	
	private Integer userId ;
	
	private Set<TradeItem> items ; 
	
	
	public Trade() {
		super();
	}

	public Trade(Integer userId  , Date tradeTime , Set<TradeItem> items ) {
		this.userId = userId ; 
		this.tradeTime = tradeTime ; 
		this.items = items; 
	}
	
	public Set<TradeItem> getItems() {
		return items ; 
	}
	
	
	
	public void setItems(Set<TradeItem> items) {
		this.items = items;
	}

	public Integer getTradeId() {
		return tradeId;
	}
	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", tradeTime=" + tradeTime + ", userId=" + userId + "]";
	} 
	
}
