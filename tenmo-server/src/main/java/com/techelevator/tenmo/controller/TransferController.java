package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.model.Transfer;

@RestController
public class TransferController {
	
	private TransferDAO transferDAO;
	
	public TransferController(TransferDAO transferDAO){
		this.transferDAO = transferDAO;
	}
	
	@RequestMapping(value = "/accounts/{id}/transfers", method = RequestMethod.GET)
	public List<Transfer> viewOwnPastTransfers(@PathVariable("id") Long accountId){
		return transferDAO.viewOwnPastTransfers(accountId);
	}
	
	@RequestMapping(value = "transfers/{id}", method = RequestMethod.GET)
	public Transfer viewTransfersById(@PathVariable("id") Long transferId) {
		return transferDAO.viewTransferById(transferId);
	}
	
	//create the sendTransfer method here

	
}