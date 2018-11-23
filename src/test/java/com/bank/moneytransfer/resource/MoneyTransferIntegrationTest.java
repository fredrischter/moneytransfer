package com.bank.moneytransfer.resource;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MoneyTransferIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() throws Exception {
	    RestAssured.port = port;
	}
	
	@Test
	public void httpRequestTestGetBalance() {
		given()
			.port(port)
		.when()
			.get("/v1/moneytransfer/balance?account=123")
		.then().statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void creatingAccount() {
		//post deposit
	}
	
	@Test
	public void transfering() {
		//post deposit
		//post deposit
		//transfer
	}
	
	@Test
	public void gettingBalance() {
		//creatingAccount()
		//post deposito
	}
	
}
