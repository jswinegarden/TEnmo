package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	List<Transfer> viewOwnPastTransfers();
	
	Transfer findTransferById(Long id);
	
	boolean createTransferTypeSend(BigDecimal amount, Long userId); //is this method name okay?
	
}