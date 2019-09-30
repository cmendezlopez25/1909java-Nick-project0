package com.revature.services;

import java.util.List;

import com.revature.pojo.Offer;
import com.revature.pojo.User;

public interface OfferService {
	public void AddOffer(Offer offer);
	public boolean RemoveOffer(Offer offer);
	public List<Offer> retrievePendingOffers();
	public List<Offer> retrieveOffersFromUser(User u);
}
