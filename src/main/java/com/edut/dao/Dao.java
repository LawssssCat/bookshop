package com.edut.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao 接口, 定义 Dao 的基本操作, 由 BaseDao 提供实现. 
 * @param <T>: Dao 实际操作的泛型类型
 */
public interface Dao<T> {
	
	/**
	 * 执行 INSERT 操作, 返回插入后的记录的 ID
	 * (因为结账插入时候，需要返回id)
	 * @param sql: 待执行的 SQL 语句
	 * @param args: 填充占位符的可变参数
	 * @return: 插入新记录的 id
	 */
	public abstract Long insert(String sql , Object ... args ) throws SQLException ;
	
	/**
	 * 执行 UPDATE 操作, 包括 INSERT(但没有返回值), UPDATE, DELETE
	 * @param sql: 待执行的 SQL 语句
	 * @param args: 填充占位符的可变参数
	 */
	public abstract  void update(String sql , Object ... args ) throws SQLException ;
	
	/**
	 * 执行单条记录的查询操作, 返回与记录对应的类的一个对象
	 * @param sql: 待执行的 SQL 语句
	 * @param args: 填充占位符的可变参数
	 * @return: 与记录对应的类的一个对象
	 */
	public abstract T query(String sql , Object ... args ) throws SQLException ;
	
	/**
	 * 执行多条记录的查询操作, 返回与记录对应的类的一个 List
	 * @param sql: 待执行的 SQL 语句
	 * @param args: 填充占位符的可变参数
	 * @return: 与记录对应的类的一个 List
	 */
	public abstract List<T> queryForList(String sql , Object ... args ) throws SQLException ;
	
	/**
	 * 执行一个属性或值的查询操作, 
	 * 例如
	 *    查询某一条记录的一个字段,
	 *    或查询某个统计信息, 
	 * 返回要查询的值
	 * @param sql: 待执行的 SQL 语句
	 * @param args: 填充占位符的可变参数
	 * @return: 执行一个属性或值的查询操作, 例如查询某一条记录的一个字段, 或查询某个统计信息, 返回要查询的值
	 */
	public abstract <V> V getSingleVal(String sql ,  Object ... args )  throws SQLException ;
	
	/**
	 * 执行批量 update 操作
	 * 
	 * @param sql: 待执行的 SQL 语句
	 * @param args: 填充占位符的可变参数
	 */
	public abstract void batch(String sql , Object[] ... params) throws SQLException;
}
