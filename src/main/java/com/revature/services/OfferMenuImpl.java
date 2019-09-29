package com.revature.services;

import static com.revature.util.LoggerUtil.log;

public class OfferMenuImpl implements OfferMenu {
	//singleton
	public static final OfferMenuImpl offerMenu = new OfferMenuImpl();
	
	private OfferMenuImpl() {
		log.trace("Creating Offer Menu");
	}

	@Override
	public Menu display() {
		// TODO Auto-generated method stub
		return null;
	}

}
