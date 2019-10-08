package com.revature.dao;

import java.util.List;

import com.revature.pojo.Offer;
import com.revature.pojo.Payment;

public interface OfferDAO {
	public void CreateOfferFile(List<Offer> offerList, String filename);
	public void addOffer(Offer o);
	public List<Offer> ReadAllOffersFiles(String filename);
	public void updateOffer(Offer o);
}
