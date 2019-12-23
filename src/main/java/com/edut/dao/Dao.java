package com.edut.dao;

import java.util.List;

public interface Dao<T> {
	/**
	 * 执行 INSERT 操作，返回插入后的记录的 ID
	 * (因为结账插入时候，需要返回id)
	 * @return 插入新记录的 id
	 */
	public Long insert(String sql , Object ... args );
	
	public void update(String sql , Object ... args );
	
	public T query(String sql , Object ... args );
	
	public List<T> queryForList(String sql , Object ... args );
	
	/**
	 * 獲取某一個值 
	 */
	public <V> V getSingleVal(String sql ,  Object ... args ) ;
	
	/**
	 * 批量 修改/添加/刪除 數據
	 */
	public void batch(String sql , Object[] ... params);
}
