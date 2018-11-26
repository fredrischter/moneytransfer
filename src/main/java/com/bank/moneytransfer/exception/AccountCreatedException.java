package com.bank.moneytransfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CREATED)
public class AccountCreatedException extends MoneyTransferException {

	private static final long serialVersionUID = 3913174016989339045L;
	
}
