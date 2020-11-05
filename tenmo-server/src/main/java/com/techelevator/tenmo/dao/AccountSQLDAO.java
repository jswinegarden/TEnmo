package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
	
	public AccountSQLDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
	public boolean updateSenderAccountBalance(Long accountId, BigDecimal amount, BigDecimal accountBalance) { //not working currently 
		BigDecimal account = accountBalance;
		BigDecimal sub = amount;
		//if(amount.compareTo(new BigDecimal(Double.toString(accountBalance))) > 0) {
		if((account.compareTo(new BigDecimal(sub)) = - 1) == true) {
			return false;
			
		}
		String sql = "UPDATE accounts SET account_balance = ? WHERE accountId = ?";
		BigDecimal diff = account.add(sub);
		return jdbcTemplate.update(sql, diff) == 1;
	}
	
	
	
	@Override
	public boolean updateReceiverAccountBalance(Long accountId, BigDecimal amount, BigDecimal accountBalance) { //not working currently
		String sql = "UPDATE accounts SET account_balance = ? WHERE accountId = ?";
		BigDecimal account = new BigDecimal(accountBalance);
		BigDecimal add = new BigDecimal(amount);
		BigDecimal sum = account.add(add);
		return jdbcTemplate.update(sql, sum) == 1;
	}
	

private Account mapRowToAccount(SqlRowSet rs) {
    Account account = new Account();
    account.setAccountId(rs.getLong("account_id"));
    account.setUserId(rs.getLong("user_id"));
    account.setAccountBalance(rs.getBigDecimal("balance"));
    return account;
}




}
