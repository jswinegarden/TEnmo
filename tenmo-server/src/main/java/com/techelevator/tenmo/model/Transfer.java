package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
	
	private Long transferId;
	private Long transferTypeId; //from transfer_types.transfer_type_id
	private String transferType; //from transfer_types.transfer_type_desc
	private Long transferStatusId; //from transfer_statuses.transfer_status_id
	private String transferStatus; //from transfer_statuses.transfer_status_desc
	private Long accountFromUserId;
	private String accountFrom; //username is not stored in transfers table; would have to get from users table based on id
	private Long accountToUserId;
	private String accountTo; //username is not stored in transfers table; would have to get from users table based on id
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
	public void setAccountTo(String accountToUsername) {
		this.accountTo = accountToUsername;
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
