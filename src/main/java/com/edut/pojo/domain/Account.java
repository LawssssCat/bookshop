package com.edut.pojo.domain;

public class Account {
	private Integer accountId  ; 
	private Integer balance ;
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + "]";
	}
	
	
	
}
