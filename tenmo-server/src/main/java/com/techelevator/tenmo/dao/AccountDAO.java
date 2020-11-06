package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Account;

public interface AccountDAO {
	
	Account viewCurrentBalance(Long accountId);
	
	boolean updateSenderAccountBalance(Long accountId, BigDecimal amount, BigDecimal accountBalance);
	
	boolean updateReceiverAccountBalance(Long accountId, BigDecimal amount, BigDecimal accountBalance);
	
	
	

}
