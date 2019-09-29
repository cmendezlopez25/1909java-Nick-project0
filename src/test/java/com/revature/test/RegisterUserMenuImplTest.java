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
import com.revature.services.MainMenuImpl;
import com.revature.services.RegisterUserMenuImpl;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RegisterUserMenuImplTest {
	private RegisterUserMenuImpl registerMenu;
	private User user;
	
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
		registerMenu = RegisterUserMenuImpl.registerMenu;
		user = new User();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void enterCustomerMenu() {
		user.setName("burrito");
		log.debug("Entering enterCustomerMenu()");
		when(userSerializer.ReadUserFile("burrito")).thenReturn(user);
		registerMenu.setUserDAOSerialization(userSerializer);
		assertEquals("Should enter customer menu", CustomerMenuImpl.customerMenu, registerMenu.display());
	}

	@Test
	public void enterMainMenu() {
		when(userSerializer.ReadUserFile("burrito")).thenReturn(new User());
		registerMenu.setUserDAOSerialization(userSerializer);
		assertEquals("Should enter Main Menu", MainMenuImpl.mainMenu, registerMenu.display());
	}
	
	@Test
	public void reenterRegisterUserMenu() {
		when(userSerializer.ReadUserFile("burrito")).thenReturn(new User());
		registerMenu.setUserDAOSerialization(userSerializer);
		assertEquals("Should re-enter Register User menu", RegisterUserMenuImpl.registerMenu, registerMenu.display());
	}
}
