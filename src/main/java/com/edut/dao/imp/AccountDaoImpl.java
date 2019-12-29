package com.edut.dao.imp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.edut.dao.AccountDao;
import com.edut.pojo.domain.Account;

public class AccountDaoImpl extends BaseDao<Account> 
implements AccountDao{

	@Override
	public Account getAccount(Integer accountId) throws SQLException {
		String sql =  " select "
					+ " account_id accountid,"
					+ " balance "
					+ " from account_table "
					+ " where account_id = ? " ; 
		return query(sql, accountId) ;
	}

	@Override
	public void updateBalance(Integer accountId, Double amount) throws SQLException {
		String sql =  " update account_table set "
					+ " balance = balance - ? "
					+ " where account_id = ? " ;
		update(sql, amount , accountId);
	}
	

}
