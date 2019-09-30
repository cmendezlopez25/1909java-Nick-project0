package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
	private LotDAOSerialization lotSerializer;
	
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void enterCustomerMenu() {
		user.setAccessLevel(User.AccessLevel.CUSTOMER);
		when(menuSystem.getUser()).thenReturn(user);
		when(lotSerializer.ReadLotFile()).thenReturn(lot);
		lotMenu.setLotDAOSerializer(lotSerializer);
		lotMenu.setSystem(menuSystem);
		assertEquals("Should enter Customer Menu", CustomerMenuImpl.customerMenu, lotMenu.display());
	}

	public void enterEmployeeMenu() {
		user.setAccessLevel(User.AccessLevel.EMPLOYEE);
		when(menuSystem.getUser()).thenReturn(user);
		when(lotSerializer.ReadLotFile()).thenReturn(lot);
		lotMenu.setLotDAOSerializer(lotSerializer);
		lotMenu.setSystem(menuSystem);
		assertEquals("Should enter Employee Menu", EmployeeMenuImpl.employeeMenu, lotMenu.display());
	}
	
	public void reenterLotMenuEmployee() {
		user.setAccessLevel(User.AccessLevel.EMPLOYEE);
		when(menuSystem.getUser()).thenReturn(user);
		when(lotSerializer.ReadLotFile()).thenReturn(lot);
		lotMenu.setLotDAOSerializer(lotSerializer);
		lotMenu.setSystem(menuSystem);
		assertEquals("Should re-enter Lot Menu only if Employee", lotMenu, lotMenu.display());
	}
	
	public void reenterLotMenuCustomer() {
		user.setAccessLevel(User.AccessLevel.CUSTOMER);
		when(menuSystem.getUser()).thenReturn(user);
		when(lotSerializer.ReadLotFile()).thenReturn(lot);
		lotMenu.setLotDAOSerializer(lotSerializer);
		lotMenu.setSystem(menuSystem);
		assertEquals("Should re-enter Lot Menu only if Customer", lotMenu, lotMenu.display());
	}
}
