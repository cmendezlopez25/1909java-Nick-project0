package com.revature.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.PaymentDAOSerialization;
import com.revature.pojo.Car;
import com.revature.pojo.Payment;
import com.revature.pojo.System;

public class PaymentDAOSerializationTest {
	private PaymentDAOSerialization paymentSerializer;
	private List<Payment> paymentList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		paymentSerializer = PaymentDAOSerialization.paymentSerializer;
		paymentList = new ArrayList<Payment>();
		paymentList.add(new Payment(5555.00, System.menuSystem.calculateMonthlyPayment(5555.00, 24), 5555.00, 24, "burrito", "12345678912345678"));
		paymentList.add(new Payment(1234.99, System.menuSystem.calculateMonthlyPayment(1234.99, 12), 1234.99, 12, "burrito", "12345678912345679"));
		paymentList.add(new Payment(8888.10230, System.menuSystem.calculateMonthlyPayment(8888.10230, 5), 8888.10230, 5, "human", "12345678912345680"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void paymentListNull() {
		paymentSerializer.CreatePaymentFile(paymentList, null);
	}

	@Test(expected = NullPointerException.class)
	public void paymentFilenameNull() {
		paymentSerializer.CreatePaymentFile(null, "AllPayments");
	}

	@Test(expected = NullPointerException.class)
	public void paymentListFilenameNull() {
		paymentSerializer.CreatePaymentFile(null, null);
	}

	@Test
	public void createPaymentFile() {
		paymentSerializer.CreatePaymentFile(paymentList, "AllPayments");
		
		List<Payment> paymentList2 = null;
		
		// try with resources
		try (FileInputStream fis = new FileInputStream("AllPayments.pay");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			paymentList2 = (List<Payment>)ois.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}

		assertEquals("File should have been created.", paymentList, paymentList2);
	}
	
	@Test(expected=NullPointerException.class)
	public void readNull() {
		paymentSerializer.ReadAllPaymentsFile(null);
	}
	
	@Test
	public void readPaymentFile() {
		assertEquals("Should be the same list", paymentList, paymentSerializer.ReadAllPaymentsFile("AllPayments"));
	}
	
	@Test
	public void readNotFoundFile() {
		assertEquals("Should be a new list of 0", new ArrayList<Payment>(), paymentSerializer.ReadAllPaymentsFile("notfound"));
	}
}
