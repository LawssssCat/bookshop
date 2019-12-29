package com.edut.test.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Test_Check {
	private Properties properties ; 
	private DataSource dataSource ; 
	private Connection conn ; 
	private QueryRunner queryRunner ; 
	
	{
		try {
			properties = new Properties();
			properties.load(getClass().getResourceAsStream("/c3p0.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Class.forName(properties.getProperty("c3p0.driverClass")) ;
			conn = DriverManager.getConnection(
					properties.getProperty("c3p0.jdbcUrl"), 
					properties.getProperty("c3p0.user"), 
					properties.getProperty("c3p0.password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queryRunner = new QueryRunner() ; 
	}
	
	@Test
	public void testProperties() {
		System.out.println("======  testProperties  =====================");
		System.out.println("c3p0.driverClass"+properties.getProperty("c3p0.driverClass"));
		System.out.println("c3p0.jdbcUrl:"+properties.get("c3p0.jdbcUrl"));
		System.out.println("c3p0.user:"+properties.get("c3p0.user"));
		System.out.println("c3p0.password:"+properties.get("c3p0.password"));
	}
	
	/*
	 * database:test_db 
	 * table:test1
	 * col:id/stock
	 */
	
	
	public void findAll() throws SQLException {
		String sql = " select * from test1 ; " ;
		List<Object[]> query = queryRunner.query(conn , sql ,new ArrayListHandler());
		for (Object[] objects : query) {
			System.out.println(Arrays.toString(objects));
		}
	}
	
	@Test
	public void testFinAll() throws SQLException {
		System.out.println("======  testFinAll  =====================");
		findAll(); 
	}
	
	@Test
	public void testUpdate() throws SQLException {
		System.out.println("======  testUpdate  =====================");
		findAll();
		System.out.println("++++++++++++++++++++");
		String sql1 = " update test1 set stock = 2 ;  " ;
		queryRunner.update(conn , sql1) ; 
		findAll();
		System.out.println("++++++++++++++++++++");
		String sql2 = " update test1 set stock = -2 ;  " ;
		queryRunner.update(conn , sql2) ; 
		findAll();
		
	}
	
}
