package com.techelevator.tenmo.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.AccountSQLDAO;
import com.techelevator.tenmo.model.Account;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountDAO accountDAO;
	
	public AccountController () {
		this.accountDAO = new AccountSQLDAO(null);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Account viewCurrentBalance(@PathVariable Long accountId, Principal principal) {
		return accountDAO.viewCurrentBalance(accountId, principal.getName());
}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public boolean updateSenderAccountBalance(@Valid @RequestBody Long accountId)

	
}