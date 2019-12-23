package com.edut.dao;

public interface Dao<T> {
	/**
	 * 执行 INSERT 操作，返回插入后的记录的 ID
	 * (因为结账插入时候，需要返回id)
	 * @return 插入新记录的 id
	 */
	public Long insert(String sql , Object ... args );
	
	public void update(String sql , Object ... args );
	
	public T query(String sql , Object ... args );
}
