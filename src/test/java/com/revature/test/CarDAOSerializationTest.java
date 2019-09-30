package com.revature.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.CarDAOSerialization;
import com.revature.pojo.Car;
import com.revature.pojo.User;

public class CarDAOSerializationTest {
	private CarDAOSerialization carSerializer;
	private Car car;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		carSerializer = CarDAOSerialization.carSerializer;
		car = new Car("12345678912345678", "Toyota", 1994, "burrito");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void createNull() {
		carSerializer.CreateCarFile(null);
	}

	@Test
	public void createCarFile() {
		carSerializer.CreateCarFile(car);
		
		Car car2 = null;
		
		// try with resources
		try (FileInputStream fis = new FileInputStream("12345678912345678.car");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			car2 = (Car) ois.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}

		assertEquals("File should have been created.", car.getVin(), car2.getVin());
	}
	
	@Test(expected = NullPointerException.class)
	public void readNull() {
		carSerializer.ReadCarFile(null);
	}
	
	@Test
	public void readCarFile() {
		assertEquals("Should return the existing car object.", car.getVin(), carSerializer.ReadCarFile(car.getVin()).getVin());
	}
	
	@Test
	public void readCarNotFound() {
		assertEquals("Should return null.", null, carSerializer.ReadCarFile("notfound"));
	}
}
