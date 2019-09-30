package com.revature.dao;

import java.util.List;

import com.revature.pojo.Customer;
import com.revature.pojo.Offer;
import com.revature.pojo.Payment;

public interface OfferDAO {
	public void CreateOfferFile(List<Offer> offerList, String filename);
	public List<Offer> ReadAllOffersFiles(String filename);
}
