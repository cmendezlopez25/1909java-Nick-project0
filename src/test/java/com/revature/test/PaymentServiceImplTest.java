package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.services.PaymentServiceImpl;
import com.revature.pojo.Payment;
import com.revature.pojo.System;
import com.revature.pojo.User;
import com.revature.pojo.User.AccessLevel;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {
	private PaymentServiceImpl paymentService;
	private List<Payment> paymentList;
	private User burrito;
	private User human;
	
	@Mock
	private System menuSystem;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		paymentService = new PaymentServiceImpl();
		
		paymentList = new ArrayList<Payment>();
		
		paymentList.add(new Payment(1235.03, 26, "human"));
		paymentList.add(new Payment(123145.12983, 8, "human"));
		
		burrito = new User(AccessLevel.CUSTOMER, "burrito", "Burrito Man", "1234");
		human = new User(AccessLevel.EMPLOYEE, "human", "Human Person", "12345");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void addPaymentNull() {
		paymentService.addPaymentToFile(null);
	}

	@Test(expected = NullPointerException.class)
	public void retrievePaymentsNull() {
		paymentService.retrievePaymentsByUsername(null);
	}
	
	@Test
	public void retrievePaymentsEmpty() {
		when(menuSystem.retrievePaymentsFromUsername(burrito)).thenReturn(new ArrayList<Payment>());
		paymentService.setMenu(menuSystem);
		assertEquals("Should be an empty list.", new ArrayList<Payment>(), paymentService.retrievePaymentsByUsername(burrito));
	}
	
	@Test
	public void retrievePaymentsFilled() {
		when(menuSystem.retrievePaymentsFromUsername(human)).thenReturn(paymentList);
		paymentService.setMenu(menuSystem);
		assertEquals("Should be a filled list.", paymentList, paymentService.retrievePaymentsByUsername(human));
	}
}
