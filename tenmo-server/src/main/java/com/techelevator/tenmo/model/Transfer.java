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
		return "\n--------------------------------------------" +
				"\n Transfer Details" +
				"\n--------------------------------------------" +
				"\n Transfer Id: " + transferId +
				"\n From: " + accountFrom +
				"\n To: " + accountTo +
				"\n Type: " + transferType +
				"\n Status: " + transferStatus +
				"\n Amount: $" + amount;
	}

}
