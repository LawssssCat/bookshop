package com.edut.tools;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	static  DataSource dataSource  ; 
	static {
		dataSource = new ComboPooledDataSource();
	}
	public static Connection getConn() throws SQLException {
		return dataSource.getConnection() ; 
	}
	public static void close(AutoCloseable ... closables) {
		for (AutoCloseable c : closables) {
			if(c!=null) {
				try {
					c.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					c = null ; 
				}
			}
		}
	}
}
