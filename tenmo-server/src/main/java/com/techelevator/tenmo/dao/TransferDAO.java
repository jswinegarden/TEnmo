package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	List<Transfer> viewOwnPastTransfers(Long accountId);
	
	Transfer viewTransferById(Long transferId);
	
	void sendTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
	
	//Helper method. Not in requirements.
	List<Transfer> getAllTransfers();
	
}