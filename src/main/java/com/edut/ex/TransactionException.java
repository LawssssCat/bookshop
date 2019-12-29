package com.edut.ex;

public class TransactionException  extends Exception  {
	private String errorMsg ; 

	public TransactionException(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getErrorMsg() {
		return errorMsg ;
	}

}
