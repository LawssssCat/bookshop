package com.edut.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JEditorPane;

import com.edut.pojo.web.ConnectionContext;
import com.edut.tools.JdbcUtils;
import com.edut.tools.Utils;

public class TransactionFilter extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Connection conn = null ; 
		try {
			//1. 获取连接
			conn = JdbcUtils.getConn() ; 
			//2. 开启事务
			conn.setAutoCommit(false);
			//3. 利用 ThreadLocal 把连接和当前线程绑定
			ConnectionContext.getInstance().bind(conn);
			//4. 把请求转给目标 Servlet
			chain.doFilter(request, response);
			
			//5. 提交事务
			conn.commit();
		} catch (Exception e) {
			//6. 回滚事务
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			//7. 解除绑定
			ConnectionContext.getInstance().release(); 
			//8. 关闭连接
			JdbcUtils.close(conn);
		}
	}
}
