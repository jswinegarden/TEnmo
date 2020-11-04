package com.techelevator.tenmo.models;

import java.math.BigDecimal;

public class Transfer {

	private Long transferId;
	private Long transferTypeId;
	private String transferType;
	private Long transferStatusId;
	private Long transferStatus;
	private Long accountFromUserId;
	private String accountFrom;
	private Long accountToUserId;
	private String accountTo;
	private BigDecimal amount;
	
	public Transfer() {
		
	}
	
	public Transfer(Long transferId, Long transferTypeId, String transferType, Long transferStatusId,
			Long transferStatus, Long accountFromUserId, String accountFrom, Long accountToUserId, String accountTo,
			BigDecimal amount) {
		super();
		this.transferId = transferId;
		this.transferTypeId = transferTypeId;
		this.transferType = transferType;
		this.transferStatusId = transferStatusId;
		this.transferStatus = transferStatus;
		this.accountFromUserId = accountFromUserId;
		this.accountFrom = accountFrom;
		this.accountToUserId = accountToUserId;
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

	public Long getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(Long transferStatus) {
		this.transferStatus = transferStatus;
	}

	public Long getAccountFromUserId() {
		return accountFromUserId;
	}

	public void setAccountFromUserId(Long accountFromUserId) {
		this.accountFromUserId = accountFromUserId;
	}

	public String getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(String accountFrom) {
		this.accountFrom = accountFrom;
	}

	public Long getAccountToUserId() {
		return accountToUserId;
	}

	public void setAccountToUserId(Long accountToUserId) {
		this.accountToUserId = accountToUserId;
	}

	public String getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(String accountTo) {
		this.accountTo = accountTo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String toString() {
		return "\n--------------------------------" +
			   "\n Transfer Details" +
			   "\n--------------------------------" +
			   "\n Transfer ID: " + transferId +
			   "\n From: " + accountFrom +
			   "\n To: " + accountTo +
			   "\n Type: " + transferType +
			   "\n Status: " + transferStatus +
			   "\n Amount: " + amount;
	}
}
	
