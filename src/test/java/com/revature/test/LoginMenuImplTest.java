package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static com.revature.util.LoggerUtil.log;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.UserDAOSerialization;
import com.revature.pojo.User;
import com.revature.services.CustomerMenuImpl;
import com.revature.services.EmployeeMenuImpl;
import com.revature.services.LoginMenuImpl;
import com.revature.services.MainMenuImpl;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginMenuImplTest {
	private LoginMenuImpl loginMenu;
	private User customer;
	private User employee;
	
	@Mock
	private UserDAOSerialization userSerializer;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		loginMenu = LoginMenuImpl.loginMenu;
		customer = new User();
		employee = new User();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void enterCustomerMenu() {
		customer.setAccessLevel(User.AccessLevel.CUSTOMER);
		customer.setUsername("burrito");
		customer.setPassword("1234");
		when(userSerializer.ReadUserFile("burrito")).thenReturn(customer);
		employee.setAccessLevel(User.AccessLevel.EMPLOYEE);
		employee.setUsername("human");
		employee.setPassword("1234");
		when(userSerializer.ReadUserFile("human")).thenReturn(employee);
		loginMenu.setUserDAOSerialization(userSerializer);
		assertEquals("Should enter Customer Menu", CustomerMenuImpl.customerMenu, loginMenu.display());
	}

	@Test
	public void enterEmployeeMenu() {
		customer.setAccessLevel(User.AccessLevel.CUSTOMER);
		customer.setUsername("burrito");
		customer.setPassword("1234");
		when(userSerializer.ReadUserFile("burrito")).thenReturn(customer);
		employee.setAccessLevel(User.AccessLevel.EMPLOYEE);
		employee.setUsername("human");
		employee.setPassword("1234");
		when(userSerializer.ReadUserFile("human")).thenReturn(employee);
		loginMenu.setUserDAOSerialization(userSerializer);
		assertEquals("Should enter Customer Menu", EmployeeMenuImpl.employeeMenu, loginMenu.display());
	}
	
	@Test
	public void enterMainMenu() {
		customer.setAccessLevel(User.AccessLevel.CUSTOMER);
		customer.setUsername("burrito");
		customer.setPassword("1234");
		when(userSerializer.ReadUserFile("burrito")).thenReturn(customer);
		employee.setAccessLevel(User.AccessLevel.EMPLOYEE);
		employee.setUsername("human");
		employee.setPassword("1234");
		when(userSerializer.ReadUserFile("human")).thenReturn(employee);
		loginMenu.setUserDAOSerialization(userSerializer);
		assertEquals("Should enter Customer Menu", MainMenuImpl.mainMenu, loginMenu.display());
	}
	
	@Test
	public void reenterLoginMenu() {
		customer.setAccessLevel(User.AccessLevel.CUSTOMER);
		customer.setUsername("burrito");
		customer.setPassword("1234");
		when(userSerializer.ReadUserFile("burrito")).thenReturn(customer);
		employee.setAccessLevel(User.AccessLevel.EMPLOYEE);
		employee.setUsername("human");
		employee.setPassword("1234");
		when(userSerializer.ReadUserFile("human")).thenReturn(employee);
		loginMenu.setUserDAOSerialization(userSerializer);
		assertEquals("Should enter Customer Menu", LoginMenuImpl.loginMenu, loginMenu.display());
	}
}
