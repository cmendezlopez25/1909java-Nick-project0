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

import com.revature.dao.OfferDAOSerialization;
import com.revature.pojo.Offer;
import com.revature.pojo.Payment;
import com.revature.pojo.Offer.OfferStatus;

public class OfferDAOSerializationTest {
	private OfferDAOSerialization offerSerializer;
	private List<Offer> offerList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		offerSerializer = OfferDAOSerialization.offerSerializer;
		offerList = new ArrayList<Offer>();
		offerList.add(new Offer(OfferStatus.PENDING, 1234.99, "burrito"));
		offerList.add(new Offer(OfferStatus.ACCEPTED, 133344.091, "human"));
		offerList.add(new Offer(OfferStatus.ACCEPTED, 2.091, "human"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void createListNull() {
		offerSerializer.CreateOfferFile(null, "AllOffers");
	}

	@Test(expected = NullPointerException.class)
	public void createFilenameNull() {
		offerSerializer.CreateOfferFile(offerList, null);
	}

	@Test(expected = NullPointerException.class)
	public void createListFilenameNull() {
		offerSerializer.CreateOfferFile(null, null);
	}

	@Test
	public void createListFile() {
		offerSerializer.CreateOfferFile(offerList, "AllOffers");
		
		List<Offer> offerList2 = null;
		
		// try with resources
		try (FileInputStream fis = new FileInputStream("AllOffers.ofr");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			offerList2 = (List<Offer>)ois.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}

		assertEquals("File should have been created.", offerList, offerList2);
	}
	
	@Test(expected = NullPointerException.class)
	public void readListNull() {
		offerSerializer.ReadAllOffersFiles(null);
	}
	
	@Test
	public void readListFile() {
		assertEquals("Should be offer list.", offerList, offerSerializer.ReadAllOffersFiles("AllOffers"));
	}
	
	@Test
	public void readListNotFound() {
		assertEquals("Should be a new offer list.", new ArrayList<Offer>(), offerSerializer.ReadAllOffersFiles("notfound"));
	}
}
