package com.edut.pojo.domain;

import java.util.Set;

public class User {
	private Integer userId ; 
	private String username ; 
	private Integer accountId ;
	
	private Set<Trade> trades ; 
	
	public User() {
		super();
	}
	public User(Integer userId, String username, Integer accountId) {
		super();
		this.userId = userId;
		this.username = username;
		this.accountId = accountId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", accountId=" + accountId + "]";
	}
	public Set<Trade> getTrades() {
		return trades;
	}
	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	} 
	
	
}
