package com.bank.moneytransfer.resource;

import static com.jayway.restassured.RestAssured.given;

import org.hamcrest.core.IsEqual;
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
		given()
			.port(port)
		.when()
			.post("/v1/moneytransfer/deposit?account=123&amount=1000")
		.then().statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void transfering() {
		given()
			.port(port)
		.when()
			.post("/v1/moneytransfer/deposit?account=111&amount=1000")
		.then().statusCode(HttpStatus.CREATED.value());

		given()
			.port(port)
		.when()
			.post("/v1/moneytransfer/deposit?account=222&amount=1000")
		.then().statusCode(HttpStatus.CREATED.value());

		given()
			.port(port)
		.when()
			.post("/v1/moneytransfer/transfer?originAccount=111&destinationAccount=222&amount=1000")
		.then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void gettingBalance() {
		given()
			.port(port)
		.when()
			.post("/v1/moneytransfer/deposit?account=111&amount=1000")
		.then().statusCode(HttpStatus.CREATED.value());

		given()
			.port(port)
		.when()
			.get("/v1/moneytransfer/balance?account=111")
		.then()
			.body(IsEqual.equalTo("1000.00"));
	}
	
}
