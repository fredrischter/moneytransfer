package com.bank.moneytransfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NotEnoughBalanceException extends MoneyTransferException {

	private static final long serialVersionUID = -8769849277471285981L;
	
}
