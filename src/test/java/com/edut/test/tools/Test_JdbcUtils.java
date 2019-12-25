package com.edut.test.tools;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.edut.tools.JdbcUtils;

public class Test_JdbcUtils {
	
	@Test
	public void test_JdbcUtils_conn$close() {
		Connection conn  = null  ; 
		try {
			conn = JdbcUtils.getConn();
			System.out.println("@@@@@@@@@@@ connection="+conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn);
		} 
	}
}
