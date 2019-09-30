package com.revature.services;

import static com.revature.util.LoggerUtil.log;
import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;

import com.revature.dao.LotDAOSerialization;
import com.revature.pojo.Lot;
import com.revature.pojo.User;
import com.revature.pojo.User.AccessLevel;
import com.revature.pojo.System;

public class LotMenuImpl implements LotMenu {
	//singleton
	public static final LotMenuImpl lotMenu = new LotMenuImpl();
	private LotDAOSerialization lotSerializer = LotDAOSerialization.lotSerializer;
	private System menuSystem = System.menuSystem;
	private User user = null;
	private Lot lot = null;
	
	private LotMenuImpl() {
		log.trace("Creating Lot Menu object");
	}

	@Override
	public Menu display() {
		log.trace("Entering Lot Menu");
		
		if (lot == null) {
			lot = lotSerializer.ReadLotFile();
			if (lot == null) {
				lot = new Lot();
			}
		}
		
		user = menuSystem.getUser();
		displayCars();
		if (lot.getCars() == null) {
			sysout.println("1. Return to the previous menu.");
		}
		else {
			sysout.println((lot.getCars().size() + 1) + ". Return to the previous menu.");
		}
		
		if (user.getAccessLevel() == AccessLevel.CUSTOMER) {
			if (lot.getCars() != null && lot.getCars().size() > 0) {
			sysout.println("Select a number to make an offer.");
			}
		}
		else {
			if (lot.getCars() != null && lot.getCars().size() > 0) {
				sysout.println("Select a number to remove a car.");
			}
			sysout.print("Select y to add a new car to the lot.");
		}
		
		log.trace("Exiting Lot Menu");
		return System.menuSystem.getCurrentMenu();
	}

	private void displayCars() {
		if (lot.getCars() == null || lot.getCars().size() == 0) {
			sysout.println("There are no cars in the lot.");
		}
		else {
			for (int i = 0; i < lot.getCars().size(); ++i) {
				sysout.println((i+1) + ". " + lot.getCars().get(i));
			}
		}
	}
	
	public void setLotDAOSerializer(LotDAOSerialization lotSerializer) {
		this.lotSerializer = lotSerializer;
	}
	
	public void setSystem(System menuSystem) {
		this.menuSystem = menuSystem;
	}
}
