package com.revature.services;

import java.util.List;

import com.revature.pojo.Offer;
import com.revature.pojo.User;

public interface OfferService {
	public void addOffer(Offer offer);
	public boolean removeOffer(Offer offer);
	public void acceptOffer(Offer offer);
	public void rejectOffer(Offer offer);
	public List<Offer> retrievePendingOffers();
	public List<Offer> retrieveOffersFromUser(User u);;
	public List<Offer> retrieveOffersFromVin(String vin);
}
