package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.dao.LotDAOSerialization;
import com.revature.pojo.Car;
import com.revature.services.LotServiceImpl;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LotServiceImplTest {
	private Car car;
	private LotServiceImpl lotService;
	
	@Mock
	private LotDAOSerialization lotSerializer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		car = new Car();
		car.setVin("This is a test VIN");
		lotService = new LotServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void carNullPointer() {
		lotService.addCarToLot(null);
	}

	@Test
	public void removeCarFromLotSuccess() {
		when(lotSerializer.RemoveCarFromLot(car)).thenReturn(true);
		lotService.setLotDAOSerializer(lotSerializer);
		assertTrue("Car should exist.", lotService.removeCarFromLot(car));
	}

	@Test
	public void removeCarFromLotFail() {
		when(lotSerializer.RemoveCarFromLot(car)).thenReturn(false);
		lotService.setLotDAOSerializer(lotSerializer);
		assertFalse("Car should not exist.", lotService.removeCarFromLot(car));
	}
}
