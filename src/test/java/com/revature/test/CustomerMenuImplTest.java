package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static com.revature.util.LoggerUtil.log;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.services.CustomerMenuImpl;
import com.revature.services.LoginMenuImpl;
import com.revature.services.LotMenuImpl;
import com.revature.services.MainMenuImpl;
import com.revature.pojo.Car;
import com.revature.pojo.Customer;
import com.revature.pojo.Payment;
import com.revature.pojo.System;
import com.revature.pojo.User;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerMenuImplTest {
	private CustomerMenuImpl customerMenu;
	private Customer customer;

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
		customerMenu = CustomerMenuImpl.customerMenu;
		customer = new Customer();
		
		List<Car> listCars = new ArrayList<Car>();
		listCars.add(new Car("VIN number", "Toyota", 1990, customer.getUsername(), 50000.00));
		listCars.add(new Car("VIN number", "Lamborghini", 2010, customer.getUsername(), 50000.00));
		listCars.add(new Car("VIN number", "Burrito", 1999, customer.getUsername(), 50000.00));
		
		List<Payment> listPayments = new ArrayList<Payment>();
		listPayments.add(new Payment(123334.123123123, System.menuSystem.calculateMonthlyPayment(123334.123123123, 24), 123334.123123123, 24, customer.getName(), listCars.get(0).getVin()));
		listPayments.add(new Payment(2, System.menuSystem.calculateMonthlyPayment(2, 24), 2, 24, customer.getName(), listCars.get(1).getVin()));
		listPayments.add(new Payment(44.44, System.menuSystem.calculateMonthlyPayment(44.44, 24), 44.44, 24, customer.getName(), listCars.get(2).getVin()));
		
		customer.setAccessLevel(User.AccessLevel.CUSTOMER);
		customer.setMyPayments(listPayments);
		customer.setName("Burrito Man");
		customer.setUsername("burrito");
		customer.setPassword("1234");
		customer.setOwnedCars(listCars);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void enterLotMenu() {
		when(menuSystem.getUser()).thenReturn(customer);
		customerMenu.setSystem(menuSystem);
		assertEquals("Should enter Lot Menu", LotMenuImpl.lotMenu, customerMenu.display());
	}

	@Test
	public void enterLoginMenu() {
		when(menuSystem.getUser()).thenReturn(customer);
		customerMenu.setSystem(menuSystem);
		assertEquals("Should enter Login Menu", LoginMenuImpl.loginMenu, customerMenu.display());
	}

	@Test
	public void enterMainMenu() {
		when(menuSystem.getUser()).thenReturn(customer);
		customerMenu.setSystem(menuSystem);
		assertEquals("Should enter Main Menu", MainMenuImpl.mainMenu, customerMenu.display());
	}

	@Test
	public void reenterCustomerMenu() {
		when(menuSystem.getUser()).thenReturn(customer);
		customerMenu.setSystem(menuSystem);
		assertEquals("Should reenter Customer Menu", CustomerMenuImpl.customerMenu, customerMenu.display());
	}
}
