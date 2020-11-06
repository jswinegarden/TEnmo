package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class SendMoneyDTO {
	
	private String accountToUsername;
	private String accountFromUsername;
	private BigDecimal amount;
	
	public String getAccountToUsername() {
		return accountToUsername;
	}
	public void setAccountToUsername(String accountToUsername) {
		this.accountToUsername = accountToUsername;
	}
	public String getAccountFromUsername() {
		return accountFromUsername;
	}
	public void setAccountFromUsername(String accountFromUsername) {
		this.accountFromUsername = accountFromUsername;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	

}
