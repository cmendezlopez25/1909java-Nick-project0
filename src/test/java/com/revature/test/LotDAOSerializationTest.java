package com.revature.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.LotDAOSerialization;
import com.revature.pojo.Car;
import com.revature.pojo.Lot;
import com.revature.pojo.Payment;

public class LotDAOSerializationTest {
	private LotDAOSerialization lotSerializer;
	private Lot lot;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		lotSerializer = LotDAOSerialization.lotSerializer;
		lot = new Lot();
		lot.setCars(new ArrayList<Car>());
		lot.getCars().add(new Car("12345678912345678", "Toyota", 1994, null));
		lot.getCars().add(new Car("12345678912345678", "Ferrari", 1990, null));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void lotFileLotNull() {
		lotSerializer.CreateLotFile(null, "Lot");
	}

	@Test(expected = NullPointerException.class)
	public void lotFileFilenameNull() {
		lotSerializer.CreateLotFile(lot, null);
	}

	@Test(expected = NullPointerException.class)
	public void lotFileLotFilenameNull() {
		lotSerializer.CreateLotFile(null, null);
	}

	@Test
	public void lotFile() {
		lotSerializer.CreateLotFile(lot, "Lot");
		
		Lot lot2 = null;
		
		// try with resources
		try (FileInputStream fis = new FileInputStream("Lot.lot");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			lot2 = (Lot)ois.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}

		assertEquals("File should have been created.", lot, lot2);
	}
	
	@Test(expected = NullPointerException.class)
	public void readLotNull() {
		lotSerializer.ReadLotFile(null);
	}
	
	@Test
	public void readLotFile() {
		assertEquals("Should be existing lot.", lot, lotSerializer.ReadLotFile("Lot"));
	}
	
	@Test
	public void readLotNotFound() {
		assertEquals("Should be new lot.", new Lot(), lotSerializer.ReadLotFile("notfound"));
	}
	
}
