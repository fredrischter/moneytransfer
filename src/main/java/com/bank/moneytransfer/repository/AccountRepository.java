package com.bank.moneytransfer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.moneytransfer.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
	
}
