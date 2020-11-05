package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	List<Transfer> viewOwnPastTransfers(Long currentUserId);
	
	Transfer viewTransferById(Long id);
	
	void sendTransfer(Long fromUserId, Long toUserId, BigDecimal amount);
	
	List<Transfer> getAllTransfers();
	
}