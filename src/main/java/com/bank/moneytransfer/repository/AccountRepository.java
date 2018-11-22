package com.bank.moneytransfer.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.moneytransfer.model.Account;

public interface AccountRepository extends CrudRepository<Account, String> {
	
}
