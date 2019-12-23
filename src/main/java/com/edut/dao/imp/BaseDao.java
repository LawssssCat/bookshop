package com.edut.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.QueryRunner;

import com.edut.dao.Dao;
import com.edut.tools.JdbcUtils;

public class BaseDao<T> implements Dao<T>{

	//apache 提供的工具类，简化代码 
	private QueryRunner queryRunner = new QueryRunner() ; 
	
	@Override
	public Long insert(String sql, Object... args) {
		Long id  =null ; 
		
		/*
		 * 因为 QueryRunner 无法获取返回值，因此要用原生的 jdbc 写
		 */
		Connection conn = null ; 
		PreparedStatement prepareStatement = null ;
		ResultSet generatedKeys  = null ; 
		try {
			conn = JdbcUtils.getConn() ;
			prepareStatement = conn.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
			
			if(args!=null ) {
				for (int i = 0; i < args.length; i++) {
					prepareStatement.setObject(i+1, args[i]);
				}
			}
			
			prepareStatement.executeUpdate() ; 
			generatedKeys= prepareStatement.getGeneratedKeys();
			if(generatedKeys.next()) {
				//第一列
				 id = generatedKeys.getLong(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JdbcUtils.close(conn , prepareStatement , generatedKeys );
		}
		
		
		return id;
	}

	@Override
	public void update(String sql, Object... args) {
		Connection conn = null;  
		try {
			conn = JdbcUtils.getConn();
			queryRunner.update(conn , sql , args) ; 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn);
		} 
	}

	@Override
	public T query(String sql, Object... args) {
		Connection conn = null;  
		try {
			conn = JdbcUtils.getConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn);
		} 
		return null;
	}
	
}
