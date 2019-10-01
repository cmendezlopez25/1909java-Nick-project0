package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.services.OfferServiceImpl;
import com.revature.pojo.Offer;
import com.revature.pojo.System;
import com.revature.pojo.User;
import com.revature.pojo.Offer.OfferStatus;
import com.revature.pojo.User.AccessLevel;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {
	private OfferServiceImpl offerService;
	private Offer offer;
	private List<Offer> offerList;
	private List<Offer> offerListVin;
	private List<Offer> offerListPending;
	private User burrito;
	private User human;
	
	@Mock
	System menuSystem;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		offerService = new OfferServiceImpl();
		offer = new Offer();
		
		offerList = new ArrayList<Offer>();
		offerList.add(new Offer(OfferStatus.PENDING, 233123.44, "human", "12345678912345699"));
		offerList.add(new Offer(OfferStatus.ACCEPTED, 4433123.44, "human", "12345678912345678"));
		offerList.add(new Offer(OfferStatus.REJECTED, 1.0, "human", "12345678912345678"));
		
		offerListVin = new ArrayList<Offer>();
		offerListVin.add(new Offer(OfferStatus.REJECTED, 233123.44, "human", "12345678912345699"));
		offerListVin.add(new Offer(OfferStatus.ACCEPTED, 4433123.44, "burrito", "12345678912345699"));
		
		offerListPending = new ArrayList<Offer>();
		offerListVin.add(new Offer(OfferStatus.PENDING, 233123.44, "human", "12345678912345699"));
		offerListVin.add(new Offer(OfferStatus.PENDING, 4433123.44, "burrito", "12345678912345699"));
		
		burrito = new User(AccessLevel.CUSTOMER, "burrito", "Burrito Man", "1234");
		human = new User(AccessLevel.CUSTOMER, "human", "Human Person", "12345");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void addOfferNull() {
		offerService.addOffer(null);
	}

	@Test(expected = NullPointerException.class)
	public void removeOfferNull() {
		offerService.removeOffer(null);
	}
	
	@Test
	public void removeOfferSuccess() {
		when(menuSystem.removeOffer(offer)).thenReturn(true);
		offerService.setMenuSystem(menuSystem);
		assertTrue("Should remove successfully.", offerService.removeOffer(offer));
	}
	
	@Test
	public void removeOfferFail() {
		when(menuSystem.removeOffer(offer)).thenReturn(false);
		offerService.setMenuSystem(menuSystem);
		assertFalse("Should fail to remove.", offerService.removeOffer(offer));		
	}
	
	@Test(expected = NullPointerException.class)
	public void acceptOfferNull() {
		offerService.acceptOffer(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void rejectOfferNull() {
		offerService.rejectOffer(null);
	}
	
	@Test
	public void retrievePendingOffersEmpty() {
		when(menuSystem.retrievePendingOffers()).thenReturn(new ArrayList<Offer>());
		offerService.setMenuSystem(menuSystem);
		assertEquals("Should return empty list.", new ArrayList<Offer>(), offerService.retrievePendingOffers());
	}
	
	@Test
	public void retrievePendingOffersFilled() {
		when(menuSystem.retrievePendingOffers()).thenReturn(offerListPending);
		offerService.setMenuSystem(menuSystem);
		assertEquals("Should return filled list.", offerListPending, offerService.retrievePendingOffers());
	}
	
	@Test(expected = NullPointerException.class)
	public void retrieveOffersFromUserNull() {
		offerService.retrieveOffersFromUser(null);
	}
	
	@Test
	public void retrieveOffersFromUserEmpty() {
		when(menuSystem.retrieveOffersFromUser(burrito)).thenReturn(new ArrayList<Offer>());
		offerService.setMenuSystem(menuSystem);
		assertEquals("Should return empty list.", new ArrayList<Offer>(), offerService.retrieveOffersFromUser(burrito));
	}
	
	@Test
	public void retrieveOffersFromUserFilled() {
		when(menuSystem.retrieveOffersFromUser(human)).thenReturn(offerList);
		offerService.setMenuSystem(menuSystem);
		assertEquals("Should return filled list.", offerList, offerService.retrieveOffersFromUser(human));
	}
	
	@Test(expected = NullPointerException.class)
	public void retrieveOffersFromVinNull() {
		offerService.retrieveOffersFromVin(null);
	}
	
	@Test
	public void retrieveOffersFromVinEmpty() {
		when(menuSystem.retrieveOffersFromVin("12308109")).thenReturn(new ArrayList<Offer>());
		offerService.setMenuSystem(menuSystem);
		assertEquals("Should return empty list.", new ArrayList<Offer>(), offerService.retrieveOffersFromVin("12308109"));
	}
	
	@Test
	public void retrieveOffersFromVinFilled() {
		when(menuSystem.retrieveOffersFromVin("12345678912345699")).thenReturn(offerListVin);
		offerService.setMenuSystem(menuSystem);
		assertEquals("Should return filled list.", offerListVin, offerService.retrieveOffersFromVin("12345678912345699"));
	}
}
