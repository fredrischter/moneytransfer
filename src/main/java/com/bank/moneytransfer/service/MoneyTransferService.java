package com.bank.moneytransfer.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.moneytransfer.exception.AccountCreatedException;
import com.bank.moneytransfer.exception.AccountNotFoundException;
import com.bank.moneytransfer.exception.NotEnoughBalanceException;
import com.bank.moneytransfer.repository.AccountRepository;

@Service
public class MoneyTransferService {

	@Autowired
	AccountRepository transferRepository;
	
	public BigDecimal getBalance(String account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return new BigDecimal(123123);
	}

	public void transfer(String originAccount, String destinationAccount, BigDecimal amount) throws AccountNotFoundException, NotEnoughBalanceException {
		// TODO Auto-generated method stub
		
	}

	public void deposit(String account, BigDecimal amount) throws AccountCreatedException {
		
		// TODO Auto-generated method stub
		
		throw new AccountCreatedException();
	}

}
