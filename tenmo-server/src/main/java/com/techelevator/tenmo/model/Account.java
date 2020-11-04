package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
	
	private Long accountId;
	private Long userId;
	private BigDecimal accountBalance;
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	//Went off of sample use case 3 for this method:
	@Override
	public String toString() {
		return "Your current account balance is: $" + accountBalance;
	}


}
