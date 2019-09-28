package com.revature.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.services.CustomerMenuImpl;
import com.revature.services.LoginMenuImpl;
import com.revature.services.MainMenuImpl;
import com.revature.services.Menu;

public class MainMenuImplTest {
	private Menu mainMenuImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mainMenuImpl = MainMenuImpl.mainMenu;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void exitLoginMenu() {
		assertEquals("Menu should exit", null, mainMenuImpl.display());
	}

	@Test
	public void goIntoCustomerMenu() {
		assertEquals("Menu should go to Customer Menu", CustomerMenuImpl.customerMenu, mainMenuImpl.display());
	}
}
