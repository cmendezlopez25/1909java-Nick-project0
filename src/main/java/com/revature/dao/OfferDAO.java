package com.revature.dao;

import com.revature.pojo.Customer;
import com.revature.pojo.Offer;

public interface OfferDAO {
	public void CreateOfferFile(Offer o);
	public Offer ReadOfferFile(Customer owner);
}
