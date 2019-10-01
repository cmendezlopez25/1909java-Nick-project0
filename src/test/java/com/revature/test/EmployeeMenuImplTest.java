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

import com.revature.services.EmployeeMenuImpl;
import com.revature.services.LoginMenuImpl;
import com.revature.services.LotMenuImpl;
import com.revature.services.MainMenuImpl;
import com.revature.pojo.Customer;
import com.revature.pojo.Employee;
import com.revature.pojo.Offer;
import com.revature.pojo.Payment;
import com.revature.pojo.System;
import com.revature.pojo.Offer.OfferStatus;
import com.revature.pojo.User.AccessLevel;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeMenuImplTest {
	private EmployeeMenuImpl employeeMenu;
	private Employee employee;

	@Mock
	System menuSystem;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		employeeMenu = EmployeeMenuImpl.employeeMenu;
		employee = new Employee();
		employee.setAccessLevel(AccessLevel.EMPLOYEE);
		employee.setUsername("burrito");
		employee.setName("Burrito Man");
		employee.setPassword("1234");
		employee.setAllOffers(null);
		employee.setAllPayments(null);

		List<Payment> payments = new ArrayList<Payment>();
		payments.add(new Payment(123334.123123123, 24, "burrito"));
		payments.add(new Payment(2, 24, "burrito"));
		payments.add(new Payment(44.44, 24, "burrito"));
		payments.add(new Payment(3.8923, 24, "burrito"));
		
		List<Offer> offers = new ArrayList<Offer>();
		offers.add(new Offer(OfferStatus.PENDING, 12345.0, "burrito", "12345678912345678"));
		offers.add(new Offer(OfferStatus.ACCEPTED, 444.999, "burrito", "12345678912345678"));
		offers.add(new Offer(OfferStatus.REJECTED, 0123.04, "burrito", "12345678912345678"));
		offers.add(new Offer(OfferStatus.PENDING, 9999.55, "human", "12345678912345678"));
		offers.add(new Offer(OfferStatus.PENDING, 12394.0, "human", "12345678912345678"));
		offers.add(new Offer(OfferStatus.PENDING, 7.0, "human", "12345678912345678"));
		
		when(menuSystem.getUser()).thenReturn(employee);
		when(menuSystem.getPayments()).thenReturn(payments);
		when(menuSystem.getOffers()).thenReturn(offers);
		employeeMenu.setMenuSystem(menuSystem);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void enterLotMenu() {
		assertEquals("Should enter Lot Menu", LotMenuImpl.lotMenu, employeeMenu.display());
	}

	@Test
	public void enterLoginMenu() {
		assertEquals("Should enter Login Menu", LoginMenuImpl.loginMenu, employeeMenu.display());
	}

	@Test
	public void enterMainMenu() {
		assertEquals("Should enter Main Menu", MainMenuImpl.mainMenu, employeeMenu.display());
	}

	@Test
	public void reenterEmployeeMenu() {
		assertEquals("Should re-enter Employee Menu", EmployeeMenuImpl.employeeMenu, employeeMenu.display());
	}
}
