package com.edut.dao.imp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.edut.dao.Dao;
import com.edut.pojo.web.ConnectionContext;
import com.edut.tools.JdbcUtils;
import com.edut.tools.ReflectionUtils;

public class BaseDao<T> implements Dao<T>{

	/*
	 * apache 提供的工具类，简化代码
	 * 
	 *  一个就可以了，因为是线程安全的！
	 */
	private QueryRunner queryRunner = new QueryRunner() ; 
	
	private Class<T> clazz  ; 
	
	
	/**
	 * 需要在构造器里面确定 clazz
	 */
	public  BaseDao() {
		
	/*
	 * 	不用工具类 ReflectionUtils 实现
	 * -------------------------------------------------------*
		// 得到父类，带泛型的类型
		Type superClass = getClass().getGenericSuperclass() ;
		
		//如果确实是带参的，就进行强转
		if(superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType)superClass ;
			
			//获取参数
			Type[] typeArgs = parameterizedType.getActualTypeArguments();
			
			//再次确认，有参，且参数至少一个
			if(typeArgs != null && typeArgs.length >0  ) {
				
				//第一个参数是一个 class ==> 是泛型
				if(typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0] ; 
				}
			}
		}
	
		/*
		 * 	用工具类 ReflectionUtils 实现
		 * -------------------------------------------------------*/
		clazz = ReflectionUtils.getSuperGenericType(getClass()) ; 
	}
	
	@Override
	public Long insert(String sql, Object... args) throws SQLException {
		Long id  =null ; 
		
		/*
		 * 因为 QueryRunner 无法获取返回值，因此要用原生的 jdbc 写
		 */
		Connection conn = null ; 
		PreparedStatement prepareStatement = null ;
		ResultSet generatedKeys  = null ; 
		try {
			conn = ConnectionContext.getInstance().getConnection() ; 
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
			
		} finally {
			
			JdbcUtils.close(prepareStatement , generatedKeys );
		}
		
		
		return id;
	}

	@Override
	public void update(String sql, Object... args) throws SQLException {
		Connection conn = null;  
		conn = ConnectionContext.getInstance().getConnection() ; 
		queryRunner.update(conn , sql , args) ; 

	}

	@Override
	public T query(String sql, Object... args) throws SQLException {
		Connection conn = null;  
		conn = ConnectionContext.getInstance().getConnection() ; 
		
		/*
		 * BeanHandler 封装成传入的 clazz实体类型
		 */
		//BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中
		//（把每条记录封装成对象，适合取一条记录）
		return queryRunner.query(conn, sql, new BeanHandler<T>(clazz), args); 
	}

	@Override
	public List<T> queryForList(String sql, Object... args) throws SQLException {
		Connection conn = null ; 
		conn = ConnectionContext.getInstance().getConnection() ; 
		
		// BeanListHandler 结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里
		return queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), args) ;
	}

	@Override
	public <V> V getSingleVal(String sql,  Object... args) throws SQLException {
		Connection conn = null ; 
		conn = ConnectionContext.getInstance().getConnection() ; 			
		//ScalarHandler 将结果集第一行的某一列放到某个对象中
		return queryRunner.query(conn, sql, new ScalarHandler<V>(), args) ; 
	}

	@Override
	public void batch(String sql, Object[]... params) throws SQLException {
		Connection  conn = null ; 
		conn = ConnectionContext.getInstance().getConnection() ; 			
		queryRunner.batch(conn, sql, params) ; 
	}
	
}
