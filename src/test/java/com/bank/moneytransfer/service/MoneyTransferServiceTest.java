package com.bank.moneytransfer.service;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.moneytransfer.exception.AccountNotFoundException;
import com.bank.moneytransfer.model.Account;
import com.bank.moneytransfer.repository.AccountRepository;

@RunWith(SpringRunner.class)
public class MoneyTransferServiceTest {

	private static final String EXISTING_ID = "1";
	private static final String INEXISTENT_ID = "12312312";

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
		moneyTransferService.getBalance(INEXISTENT_ID);
	}

	@Test
	public void getBalanceExample() throws IOException {
		// When
		// BigDecimal balance
		
		// Then
		//assertTrue(ballance.equals(10));
	}
	
}
