package com.bank.moneytransfer.resource;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.moneytransfer.exception.AccountNotFoundException;
import com.bank.moneytransfer.service.MoneyTransferService;

@RestController
@RequestMapping("/v1/moneytransfer")
public class AccountResource {

	@Autowired
	MoneyTransferService moneyTransferService;

	@PostMapping("/transfer")
	public void transfer(@RequestParam String originAccount, @RequestParam String destinationAccount, @RequestParam BigDecimal amount) throws AccountNotFoundException {
		moneyTransferService.transfer(originAccount, destinationAccount, amount);
	}

	@GetMapping("/balance")
	public BigDecimal transfer(@RequestParam String account) throws AccountNotFoundException {
		return moneyTransferService.getBalance(account);
	}

	@PostMapping("/deposit")
	public void deposit(@RequestParam String account, @RequestParam BigDecimal amount) throws AccountNotFoundException {
		moneyTransferService.deposit(account, amount);
	}
	
}
