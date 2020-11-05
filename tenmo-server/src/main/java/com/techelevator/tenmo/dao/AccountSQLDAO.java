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
	public boolean updateSenderAccountBalance(Long accountId, BigDecimal amount, BigDecimal accountBalance) {
		String sql = "UPDATE accounts SET account_balance = ? WHERE accountId = ?";
		return jdbcTemplate.update(sql, amount) == 1;
	}
	
	
	
	@Override
	public boolean updateReceiverAccountBalance(Long accountId, BigDecimal amount) {
		// TODO Auto-generated method stub
		return false;
	}
	

private Account mapRowToAccount(SqlRowSet rs) {
    Account account = new Account();
    account.setAccountId(rs.getLong("account_id"));
    account.setUserId(rs.getLong("user_id"));
    account.setAccountBalance(rs.getBigDecimal("balance"));
    return account;
}




}
