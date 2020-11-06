package com.techelevator.tenmo.models;

import java.math.BigDecimal;

public class Transfer {
	
	private Long transferId;
	private Long transferTypeId;
	private Long transferStatusId;
	private Long accountFromId;
	private Long accountToId;
	private BigDecimal amount;
	
	public Transfer(Long transferId, Long transferTypeId, Long transferStatusId, Long accountFromId,
			Long accountToId, BigDecimal amount) {
		super();
		this.transferId = transferId;
		this.transferTypeId = transferTypeId;
		this.transferStatusId = transferStatusId;
		this.accountFromId = accountFromId;
		this.accountToId = accountToId;
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

	public Long getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Long transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public Long getAccountFromId() {
		return accountFromId;
	}

	public void setAccountFromId(Long accountFromId) {
		this.accountFromId = accountFromId;
	}

	public Long getAccountToId() {
		return accountToId;
	}

	public void setAccountToId(Long accountToId) {
		this.accountToId = accountToId;
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
			   "\n From: " + accountFromId +
			   "\n To: " + accountToId +
			   "\n Amount: " + amount +
			   "\n Type: " + transferTypeId +
			   "\n Status: " + transferStatusId;
	}
}
	
