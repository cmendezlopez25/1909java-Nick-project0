package com.revature.services;

import static com.revature.util.LoggerUtil.log;

public class LotMenuImpl implements LotMenu {
	//singleton
	public static final LotMenuImpl lotMenu = new LotMenuImpl();
	
	private LotMenuImpl() {
		log.trace("Creating Lot Menu object");
	}

	@Override
	public Menu display() {
		// TODO Auto-generated method stub
		return null;
	}

}
