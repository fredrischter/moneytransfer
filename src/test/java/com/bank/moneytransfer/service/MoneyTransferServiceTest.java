package com.bank.moneytransfer.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.moneytransfer.exception.AccountCreatedException;
import com.bank.moneytransfer.exception.AccountNotFoundException;
import com.bank.moneytransfer.exception.NotEnoughBalanceException;
import com.bank.moneytransfer.model.Account;
import com.bank.moneytransfer.repository.AccountRepository;

@RunWith(SpringRunner.class)
public class MoneyTransferServiceTest {

	private static final String EXISTING_ID = "1";
	private static final String INEXISTENT_ID = "12312312";
	private static final String ACCOUNT_A = "11111";
	private static final String ACCOUNT_B = "22222";

	@InjectMocks
	MoneyTransferService moneyTransferService;

	@Mock
	AccountRepository accountRepository;

	@Before
	public void setUp() {
		Mockito.when(accountRepository.findById(EXISTING_ID)).thenReturn(Optional.of(new Account()));
	}

	@Test(expected = AccountNotFoundException.class)
	public void accountNotFoundTest() throws AccountNotFoundException {
		// When
		moneyTransferService.getBalance(INEXISTENT_ID);
	}

	@Test
	public void creatingAccount() throws IOException, AccountCreatedException, AccountNotFoundException {
		// When
		moneyTransferService.deposit(INEXISTENT_ID, new BigDecimal(10));
	}

	@Test
	public void transfering() throws IOException, AccountCreatedException, AccountNotFoundException, NotEnoughBalanceException {
		// Given
		moneyTransferService.deposit(ACCOUNT_A, new BigDecimal(100));
		moneyTransferService.deposit(ACCOUNT_B, new BigDecimal(100));
		
		// When
		moneyTransferService.transfer(ACCOUNT_A, ACCOUNT_B, new BigDecimal(30));
	}

	@Test(expected = NotEnoughBalanceException.class)
	public void notEnoughBalance() throws IOException, AccountCreatedException, AccountNotFoundException, NotEnoughBalanceException {
		// Given
		moneyTransferService.deposit(ACCOUNT_A, new BigDecimal(100));
		moneyTransferService.deposit(ACCOUNT_B, new BigDecimal(100));
		
		// When
		moneyTransferService.transfer(ACCOUNT_A, ACCOUNT_B, new BigDecimal(130));
	}

	@Test
	public void balanceCheck() throws IOException, AccountCreatedException, AccountNotFoundException, NotEnoughBalanceException {
		// Given
		moneyTransferService.deposit(ACCOUNT_A, new BigDecimal(100));
		moneyTransferService.deposit(ACCOUNT_B, new BigDecimal(100));
		
		// When
		moneyTransferService.transfer(ACCOUNT_A, ACCOUNT_B, new BigDecimal(30));
		
		// Then
		assertEquals(new BigDecimal(130), moneyTransferService.getBalance(ACCOUNT_B));
		assertEquals(new BigDecimal(70), moneyTransferService.getBalance(ACCOUNT_A));
	}
	
}
