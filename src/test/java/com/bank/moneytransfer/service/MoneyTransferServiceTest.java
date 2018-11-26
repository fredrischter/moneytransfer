package com.bank.moneytransfer.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.moneytransfer.exception.AccountCreatedException;
import com.bank.moneytransfer.exception.AccountNotFoundException;
import com.bank.moneytransfer.exception.NotEnoughBalanceException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MoneyTransferServiceTest {

	private static final String INEXISTENT_ID = "12312312";
	private static final String ACCOUNT_A = "11111";
	private static final String ACCOUNT_B = "22222";

	@Autowired
	MoneyTransferService moneyTransferService;

	@Test(expected = AccountNotFoundException.class)
	public void accountNotFoundTest() throws AccountNotFoundException {
		// When
		moneyTransferService.getBalance(INEXISTENT_ID);
	}

	@Test(expected = AccountCreatedException.class)
	public void creatingAccount() throws IOException, AccountCreatedException, AccountNotFoundException {
		// When
		moneyTransferService.deposit(INEXISTENT_ID, new BigDecimal(10));
	}

	@Test
	public void transfering() throws IOException, AccountCreatedException, AccountNotFoundException, NotEnoughBalanceException {
		// Given
		createA100B100();
		
		// When
		moneyTransferService.transfer(ACCOUNT_A, ACCOUNT_B, new BigDecimal(30));
	}

	private void createA100B100() {
		try {
			moneyTransferService.deposit(ACCOUNT_A, new BigDecimal(100));
		} catch(AccountCreatedException ok) {
			// It is ok!
		}
		try {
			moneyTransferService.deposit(ACCOUNT_B, new BigDecimal(100));
		} catch(AccountCreatedException ok) {
			// It is ok!
		}
	}

	@Test(expected = NotEnoughBalanceException.class)
	public void notEnoughBalance() throws IOException, AccountCreatedException, AccountNotFoundException, NotEnoughBalanceException {
		// Given
		createA100B100();
		
		// When
		moneyTransferService.transfer(ACCOUNT_A, ACCOUNT_B, new BigDecimal(130));
	}

	@Test
	public void balanceCheck() throws IOException, AccountCreatedException, AccountNotFoundException, NotEnoughBalanceException {
		// Given
		createA100B100();
		
		// When
		moneyTransferService.transfer(ACCOUNT_A, ACCOUNT_B, new BigDecimal(30));
		
		// Then
		assertEquals(new BigDecimal(130), moneyTransferService.getBalance(ACCOUNT_B));
		assertEquals(new BigDecimal(70), moneyTransferService.getBalance(ACCOUNT_A));
	}
	
}
