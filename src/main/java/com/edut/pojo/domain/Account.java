package com.edut.pojo.domain;

public class Account {
	private Integer account_id ; 
	private Integer balance ;
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", balance=" + balance + "]";
	} 
	
	
}
