package com.revature.dao;

import static com.revature.util.LoggerUtil.log;

import com.revature.pojo.Customer;
import com.revature.pojo.Offer;

public class OfferDAOSerialization implements OfferDAO {
	// singleton
	public static final OfferDAOSerialization offerSerializer = new OfferDAOSerialization();
	
	private OfferDAOSerialization() {
		log.trace("Creating OfferDAOSerialization object");
	}

	@Override
	public void CreateOfferFile(Offer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public Offer ReadOfferFile(Customer owner) {
		// TODO Auto-generated method stub
		return null;
	}

}
