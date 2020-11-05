package com.techelevator.tenmo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Transfer amount exceeds balance.")
	public class BalanceOverdrawException extends Exception {
	    private static final long serialVersionUID = 1L;

	    public BalanceOverdrawException() {
	        super("Transfer amount exceeds balance.");
	    }
}
