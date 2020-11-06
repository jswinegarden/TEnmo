package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
	
	private Long transferId;
	private Long transferTypeId;
	private String transferType;
	private Long transferStatusId;
	private String transferStatus;
	private Long accountFrom;
	private Long accountTo;
	private BigDecimal amount;
	
	public Transfer() {
		
	}
	
	public Transfer(Long transferId, Long transferTypeId, String transferType, Long transferStatusId, String transferStatus,
			Long accountFrom,Long accountTo, BigDecimal amount) {
		this.transferId = transferId;
		this.transferTypeId = transferTypeId;
		this.transferType = transferType;
		this.transferStatusId = transferStatusId;
		this.transferStatus = transferStatus;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.amount = amount;
	}

	public Long getTransferId() {
		return transferId;
	}
	public void setTransferId(Long transferId) {
		this.transferId = transferId;
	}
	public Long getTransferTypeId() {
		return transferTypeId;
	}
	public void setTransferTypeId(Long transferTypeId) {
		this.transferTypeId = transferTypeId;
	}
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	public Long getTransferStatusId() {
		return transferStatusId;
	}
	public void setTransferStatusId(Long transferStatusId) {
		this.transferStatusId = transferStatusId;
	}
	public String getTransferStatus() {
		return transferStatus;
	}
	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}
	public Long getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(Long accountFrom) {
		this.accountFrom = accountFrom;
	}

	public Long getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(Long accountTo) {
		this.accountTo = accountTo;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "\n--------------------------------" +
			   "\n Transfer Details" +
			   "\n--------------------------------" +
			   "\n Transfer ID: " + transferId +
			   "\n Transfer Type ID: " + transferTypeId +
			   "\n Transfer Type: " + transferType +
			   "\n Transfer Status ID: " + transferStatusId +
			   "\n Transfer Status: " + transferStatus +
			   "\n From: " + accountFrom +
			   "\n To: " + accountTo +
			   "\n Amount: " + amount;
	}

}
