package com.edut.pojo.domain;

public class User {
	private Integer userId ; 
	private String username ; 
	private Integer accountId ;
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
	
	
}
