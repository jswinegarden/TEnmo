package com.techelevator.tenmo.model;

public class Transfer {
	
	private Long transferId;
	private Long transferTypeId; //from transfer_types.transfer_type_id
	private String transferType; //from transfer_types.transfer_type_desc
	private Long transferStatusId; //from transfer_statuses.transfer_status_id
	private String transferStatus; //from transfer_statuses.transfer_status_desc
	private int accountFromUserId;
	private String accountFromUsername; //username is not stored in transfers table; would have to get from users table based on id
	private int accountToUserId;
	private String accountToUsername; //username is not stored in transfers table; would have to get from users table based on id
	
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
	public int getAccountFromUserId() {
		return accountFromUserId;
	}
	public void setAccountFromUserId(int accountFromUserId) {
		this.accountFromUserId = accountFromUserId;
	}
	public String getAccountFromUsername() {
		return accountFromUsername;
	}
	public void setAccountFromUsername(String accountFromUsername) {
		this.accountFromUsername = accountFromUsername;
	}
	public int getAccountToUserId() {
		return accountToUserId;
	}
	public void setAccountToUserId(int accountToUserId) {
		this.accountToUserId = accountToUserId;
	}
	public String getAccountToUsername() {
		return accountToUsername;
	}
	public void setAccountToUsername(String accountToUsername) {
		this.accountToUsername = accountToUsername;
	}
	
}
