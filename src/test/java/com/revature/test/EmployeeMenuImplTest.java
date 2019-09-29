package com.revature.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.services.EmployeeMenuImpl;
import com.revature.services.LoginMenuImpl;
import com.revature.services.LotMenuImpl;
import com.revature.services.MainMenuImpl;
import com.revature.services.OfferMenuImpl;

public class EmployeeMenuImplTest {
	private EmployeeMenuImpl employeeMenu;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		employeeMenu = EmployeeMenuImpl.employeeMenu;
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
	public void enterOfferMenu() {
		assertEquals("Should enter Offer Menu", OfferMenuImpl.offerMenu, employeeMenu.display());
	}
	
	@Test
	public void reenterEmployeeMenu() {
		assertEquals("Should re-enter Employee Menu", EmployeeMenuImpl.employeeMenu, employeeMenu.display());
	}
}
