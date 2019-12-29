package com.edut.pojo.web;

import java.sql.Connection;

public class ConnectionContext {
	private ThreadLocal<Connection> threadLocal = new ThreadLocal<>()   ; 

	//单例
	private static ConnectionContext instance = new ConnectionContext() ;  
	private ConnectionContext() {}
	public static ConnectionContext getInstance() {
		return instance ; 
	}
	
	public void bind(Connection connection) {
		threadLocal.set(connection);
	}
	
	public Connection getConnection() {
		return threadLocal.get() ; 
	}
	
	public void release() {
		threadLocal.remove(); 
	}
}
