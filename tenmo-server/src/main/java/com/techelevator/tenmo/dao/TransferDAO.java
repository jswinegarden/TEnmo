package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	List<Transfer> findOwnPastTransfers(); //this will use viewPastTransfers
	
	Transfer findTransferById(Long id);
	
	boolean createTransferTypeSend(Long fromUserId, Long toUserId, BigDecimal amount); //is this method name okay?
	
}