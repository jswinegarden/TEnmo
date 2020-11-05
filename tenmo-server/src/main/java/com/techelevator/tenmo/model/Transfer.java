package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
	
	private Long transferId;
	private Long transferTypeId; //from transfer_types.transfer_type_id
	private String transferType; //from transfer_types.transfer_type_desc
	private Long transferStatusId; //from transfer_statuses.transfer_status_id
	private String transferStatus; //from transfer_statuses.transfer_status_desc
	private Long accountFrom; // this is account_id
	
	/* Omitting for now. implemented in SendMoneyDTO
	 * private String accountFromUsername; //username is not stored in transfers table; would have to get from users table based on id
	 */
	
	private Long accountTo;
	
	/* Omitting for now. implemented in SendMoneyDTO
	 * private String accountToUsername; //username is not stored in transfers table; would have to get from users table based on id
	 */
	
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
	
	/*
	public String viewPastTransfers() {
		return "\n--------------------------------------------" +
				"\n Transfers"
				+ " ID			From/To					Amount" +
				"\n--------------------------------------------" +
				"\n "+ transferId + "		From"
				"\n From: " + accountFrom +
				"\n To: " + accountTo +
				"\n Type: " + transferType +
				"\n Status: " + transferStatus +
				"\n Amount: $" + amount;
	}
	*/
	
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
