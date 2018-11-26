package com.bank.moneytransfer.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.moneytransfer.exception.AccountCreatedException;
import com.bank.moneytransfer.exception.AccountNotFoundException;
import com.bank.moneytransfer.exception.NotEnoughBalanceException;
import com.bank.moneytransfer.model.Account;
import com.bank.moneytransfer.repository.AccountRepository;

@Service
public class MoneyTransferService {

	@Autowired
	AccountRepository transferRepository;

	public BigDecimal getBalance(String account) throws AccountNotFoundException {
		Optional<Account> accountEntityOp = transferRepository.findById(account);
		if (!accountEntityOp.isPresent()) {
			throw new AccountNotFoundException();
		}
		return accountEntityOp.get().getBalance();
	}

	@Transactional
	public void transfer(String originAccountNumber, String destinationAccountNumber, BigDecimal amount) throws AccountNotFoundException, NotEnoughBalanceException {
		Optional<Account> originAccountOp = transferRepository.findById(originAccountNumber);
		Optional<Account> destinationAccountOp = transferRepository.findById(destinationAccountNumber);
		
		if (!originAccountOp.isPresent() || !destinationAccountOp.isPresent()) {
			throw new AccountNotFoundException();
		}
		Account originAccount = originAccountOp.get();
		Account destinationAccount = destinationAccountOp.get();

		if (originAccount.getBalance().compareTo(amount) < 0) {
			throw new NotEnoughBalanceException();
		}

		originAccount.setBalance(originAccount.getBalance().subtract(amount));
		destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

		transferRepository.save(originAccount);
		transferRepository.save(destinationAccount);
	}
	
	public void deposit(String account, BigDecimal amount) throws AccountCreatedException {
		Optional<Account> accountEntityOp = transferRepository.findById(account);
		
		if (accountEntityOp.isPresent()) {
			Account accountEntity = accountEntityOp.get();
			accountEntity.setBalance(accountEntity.getBalance().add(amount));
			transferRepository.save(accountEntity);
		} else {
			Account accountEntity = Account.builder().number(account).balance(amount).build();
			transferRepository.save(accountEntity);
			throw new AccountCreatedException();
		}
	}

}
