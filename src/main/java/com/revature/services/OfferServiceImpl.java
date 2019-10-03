package com.revature.services;

import static com.revature.util.LoggerUtil.log;

import java.util.List;

import com.revature.pojo.Offer;
import com.revature.pojo.User;
import com.revature.pojo.System;

public class OfferServiceImpl implements OfferService {
	private System menuSystem = System.menuSystem;

	@Override
	public void addOffer(Offer offer) {
		log.trace("Entering addOffer");
		if (offer == null) {
			log.error("Offer does not exist!");
			throw new NullPointerException();
		}
		
		menuSystem.addOffer(offer);

		log.trace("Exiting addOffer");
	}

	@Override
	public boolean removeOffer(Offer offer) {
		log.trace("Entering removeOffer");
		if (offer == null) {
			log.error("Offer does not exist!");
			throw new NullPointerException();
		}

		if (menuSystem.removeOffer(offer)) {
			log.trace("Exiting removeOffer");
			return true;
		}
		
		log.trace("Exiting removeOffer");
		return false;
	}
	
	@Override
	public void removeOffersOfVin(String vin) {
		log.trace("Entering removeOffersOfVin");
		if (vin == null) {
			log.error("Offer does not exist!");
			throw new NullPointerException();
		}

		menuSystem.removeOffersOfVin(vin);
		
		log.trace("Exiting removeOffer");
	}

	@Override
	public void acceptOffer(Offer offer) {
		log.trace("Entering acceptOffer");
		if (offer == null) {
			log.error("Offer does not exist!");
			throw new NullPointerException();
		}

		menuSystem.acceptOffer(offer, 24);
		log.trace("Exiting acceptOffer");
	}

	@Override
	public void rejectOffer(Offer offer) {
		log.trace("Entering rejectOffer");
		if (offer == null) {
			log.error("Offer does not exist!");
			throw new NullPointerException();
		}

		menuSystem.rejectOffer(offer);
		log.trace("Exiting rejectOffer");
	}

	@Override
	public List<Offer> retrievePendingOffers() {
		log.trace("Entering retrievePendingOffers");
		List<Offer> offers = menuSystem.retrievePendingOffers();
		log.trace("Exiting retrievePendingOffers");
		return offers;
	}

	@Override
	public List<Offer> retrieveOffersFromUser(User u) {
		log.trace("Entering retrieveOffersFromUser");
		if (u == null) {
			log.error("User does not exist!");
			throw new NullPointerException();
		}
		List<Offer> offerList = menuSystem.retrieveOffersFromUser(u);
		
		log.trace("Exiting retrieveOffersFromUser");
		return offerList;
	}

	public List<Offer> retrieveOffersFromVin(String vin){
		log.trace("Entering retrieveOffersFromVin");
		if (vin == null) {
			log.error("VIN is null!");
			throw new NullPointerException();
		}
		List<Offer> offerList = menuSystem.retrieveOffersFromVin(vin);
		
		log.trace("Exiting retrieveOffersFromVin");
		return offerList;
	}
	
	public void setMenuSystem(System menuSystem) {
		this.menuSystem = menuSystem;
	}
}
