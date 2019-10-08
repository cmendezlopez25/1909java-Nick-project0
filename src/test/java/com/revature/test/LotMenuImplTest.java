package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.services.CustomerMenuImpl;
import com.revature.services.EmployeeMenuImpl;
import com.revature.services.LotMenuImpl;
import com.revature.dao.LotDAOSerialization;
import com.revature.pojo.User;
import com.revature.pojo.Car;
import com.revature.pojo.Lot;
import com.revature.pojo.System;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LotMenuImplTest {
	private LotMenuImpl lotMenu;
	private User user;
	private Lot lot;
	
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
		lotMenu = LotMenuImpl.lotMenu;
		user = new User();
		lot = new Lot();
		lot.setCars(new ArrayList<Car>());
		lot.getCars().add(new Car("VIN number thing", "Toyota", 1994, new User().getUsername(), 50000.00));
		lot.getCars().add(new Car("Another VIN", "Ferrari", 1991, new User().getUsername(), 50000.00));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void enterCustomerMenu() {
		user.setAccessLevel(User.AccessLevel.CUSTOMER);
		when(menuSystem.getUser()).thenReturn(user);
		lotMenu.setSystem(menuSystem);
		assertEquals("Should enter Customer Menu", CustomerMenuImpl.customerMenu, lotMenu.display());
	}

	@Test
	public void enterEmployeeMenu() {
		user.setAccessLevel(User.AccessLevel.EMPLOYEE);
		when(menuSystem.getUser()).thenReturn(user);
		lotMenu.setSystem(menuSystem);
		assertEquals("Should enter Employee Menu", EmployeeMenuImpl.employeeMenu, lotMenu.display());
	}
	
	@Test
	public void reenterLotMenuEmployee() {
		user.setAccessLevel(User.AccessLevel.EMPLOYEE);
		when(menuSystem.getUser()).thenReturn(user);
		lotMenu.setSystem(menuSystem);
		assertEquals("Should re-enter Lot Menu only if Employee", lotMenu, lotMenu.display());
	}
	
	@Test
	public void reenterLotMenuCustomer() {
		user.setAccessLevel(User.AccessLevel.CUSTOMER);
		when(menuSystem.getUser()).thenReturn(user);
		lotMenu.setSystem(menuSystem);
		assertEquals("Should re-enter Lot Menu only if Customer", lotMenu, lotMenu.display());
	}
}
