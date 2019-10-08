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
import com.revature.pojo.Payment;
import com.revature.pojo.System;
import com.revature.pojo.User;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerMenuImplTest {
	private CustomerMenuImpl customerMenu;
	private User customer;

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
		customer = new User();
		
		customer.setAccessLevel(User.AccessLevel.CUSTOMER);
		customer.setName("Burrito Man");
		customer.setUsername("burrito");
		customer.setPassword("1234");
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
