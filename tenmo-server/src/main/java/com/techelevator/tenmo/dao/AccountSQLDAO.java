package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.exception.BalanceOverdrawException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

@Component
public class AccountSQLDAO implements AccountDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public AccountSQLDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Account viewCurrentBalance(Long accountId) {
		Account accounts = null;
		String sql = "SELECT * FROM accounts WHERE account_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
		while(results.next()) {
			accounts = mapRowToAccount(results);
		}
		return accounts;	
	}
	
	@Override
	public Account updateSenderAccountBalance(Account account, Long accountId) {  
		Transfer transfer = null;
		BigDecimal accountBalance = account.getAccountBalance();
		BigDecimal sub = transfer.getAmount();
		if((accountBalance.compareTo(sub) >= 0)) {
			String sql = "UPDATE accounts SET account_balance = ? WHERE accountId = ?";
			BigDecimal diff = accountBalance.subtract(sub);
		}
		
		return account;
	}
	
	@Override
	public Account updateReceiverAccountBalance(Account account, Long accountId) { 
		Transfer transfer = null;
		String sql = "UPDATE accounts SET account_balance = ? WHERE accountId = ?";
		BigDecimal accountBalance = account.getAccountBalance();
		BigDecimal add = transfer.getAmount();
		BigDecimal sum = accountBalance.add(add);
		jdbcTemplate.update(sql, sum);
		return account;
	}
	

private Account mapRowToAccount(SqlRowSet rs) {
    Account account = new Account();
    account.setAccountId(rs.getLong("account_id"));
    account.setUserId(rs.getLong("user_id"));
    account.setAccountBalance(rs.getBigDecimal("balance"));
    return account;
}




}