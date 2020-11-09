package com.techelevator.tenmo.controller;

import java.math.BigDecimal;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.AccountSQLDAO;
import com.techelevator.tenmo.model.Account;

//@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountDAO accountDAO;
//	public AccountController () {
//		this.accountDAO = new AccountSQLDAO(null);
//
//	}
	
	public AccountController (AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	//@PreAuthorize("hasRole('USER')")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Account viewCurrentBalance(@PathVariable("id") Long accountId) {
		return accountDAO.viewCurrentBalance(accountId);
}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Account updateSenderAccountBalance(@PathVariable("id") Long accountId, @RequestBody Account account) {
		accountDAO.updateSenderAccountBalance(accountId, account);
		accountDAO.updateReceiverAccountBalance(accountId, account);
		return account;
	}
		
//	public Account updateSenderAccountBalance(@PathVariable("id") Long fromAccountId, Long toAccountId, @RequestBody Account updatedAccount) {
//		accountDAO.updateSenderAccountBalance(fromAccountId, updatedAccount);
//		accountDAO.updateReceiverAccountBalance(toAccountId, updatedAccount);
//		
//		return updatedAccount;
//	}
	/*
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Account updateReceiverAccountBalance(@PathVariable("id") Long toAccountId, @RequestBody Account account ) {
		return accountDAO.updateReceiverAccountBalance(toAccountId, account);
	}
	*/
}