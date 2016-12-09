package com.studytrails.tutorials.springdeclarativetransactionsannotation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDao extends JdbcDaoSupport{
	
	
	public void insert(Account account){
		String insertSql ="INSERT INTO ACCOUNT (ACCOUNT_NUMBER, BALANCE) VALUES(?,?);"; 
		String accountNumber = account.getNumber();
		Double amount = account.getBalance();
		
		getJdbcTemplate().update(insertSql,new Object[]{accountNumber,amount});
		
	}
	
	public void update(Account account){
		String updateSql ="UPDATE ACCOUNT SET BALANCE = ? where ACCOUNT_NUMBER = ?;"; 
		Double amount = account.getBalance();
		String accountNumber = account.getNumber();
		
		
		getJdbcTemplate().update(updateSql,new Object[]{amount,accountNumber});
		
	}

	public Account select(String accountNumber) {
		String selectSql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_NUMBER = ?;";
		List<Account> accounts = getJdbcTemplate().query(selectSql, new Object[]{accountNumber},new AccountRowMapper());
		return accounts.get(0);
	}

	
	private class AccountRowMapper implements RowMapper<Account>{

		@Override
		public Account mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			Account account = new Account();
			account.setNumber(resultSet.getString("ACCOUNT_NUMBER"));
			account.setBalance(resultSet.getDouble("BALANCE"));
			return account;
		}
		
	}


}
