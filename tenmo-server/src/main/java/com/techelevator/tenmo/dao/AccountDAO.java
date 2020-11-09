package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Account;

public interface AccountDAO {
	
	Account viewCurrentBalance(Long accountId);

//	Account updateSenderAccountBalance(Account account, Long accountId);
	
//	Account updateReceiverAccountBalance(Account account, Long accountId);
//	boolean updateSenderAccountBalance(Long accountId, BigDecimal amount, BigDecimal accountBalance);
	
	Account updateSenderAccountBalance(Long fromAccountId, Account updatedAccount);
	
//	boolean updateReceiverAccountBalance(Long accountId, BigDecimal amount, BigDecimal accountBalance);
	
	Account updateReceiverAccountBalance(Long toAccountId, Account updatedAccount);

	
	
	

}
