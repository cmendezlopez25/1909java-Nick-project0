package com.revature.services;

import static com.revature.util.LoggerUtil.log;
import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;

import com.revature.dao.LotDAOSerialization;
import com.revature.pojo.Lot;
import com.revature.pojo.Offer;
import com.revature.pojo.User;
import com.revature.pojo.Offer.OfferStatus;
import com.revature.pojo.User.AccessLevel;
import com.revature.pojo.System;

public class LotMenuImpl implements LotMenu {
	//singleton
	public static final LotMenuImpl lotMenu = new LotMenuImpl();
	private LotDAOSerialization lotSerializer = LotDAOSerialization.lotSerializer;
	private LotServiceImpl lotService = new LotServiceImpl();
	private OfferServiceImpl offerService = new OfferServiceImpl();
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
		sysout.println("0. Return to the previous menu.");
		
		if (user.getAccessLevel() == AccessLevel.CUSTOMER) {
			if (lot.getCars() != null && lot.getCars().size() > 0) {
			sysout.println("Select a number to make an offer.");
			}
		}
		else {
			if (lot.getCars() != null && lot.getCars().size() > 0) {
				sysout.println("Select a number to remove a car.");
			}
			sysout.println("Select y to add a new car to the lot.");
		}
		String input = "-1";
		
		while (input.equals("-1")) {
			input = validInput();
			switch (input) {
			case "-1":
				sysout.println("Invalid input. Try again.");
				break;
				
			case "0":
				if (user.getAccessLevel() == AccessLevel.CUSTOMER) {
					System.menuSystem.setCurrentMenu(CustomerMenuImpl.customerMenu);
				}
				else {
					System.menuSystem.setCurrentMenu(EmployeeMenuImpl.employeeMenu);
				}
				break;
				
			default:
				System.menuSystem.setCurrentMenu(lotMenu);
				if (user.getAccessLevel() == AccessLevel.CUSTOMER) {
					Offer offer = makeOffer();
					offerService.addOffer(makeOffer());
					sysout.println("You made an offer of $" + offer.getMoneyAmount() + "!");
				}
				else {
					if (lotService.removeCarFromLot(lot.getCars().get(Integer.parseInt(input) - 1))) {
						sysout.println("Car #" + input + " was removed from the lot.");
					}
					else {
						sysout.println("Car #" + input + " could not be removed from the lot.");
					}
				}
				break;
			}
		}
		
		log.trace("Exiting Lot Menu");
		return System.menuSystem.getCurrentMenu();
	}

	private String validInput() {
		log.trace("Entering validInput");
		String ret = "-1";
		
		if (scanner.hasNextInt()) {
			ret = scanner.next();
			if (lot.getCars() != null && lot.getCars().size() > 0 && (Integer.parseInt(ret) > lot.getCars().size() || Integer.parseInt(ret) < 0)) {
				ret = "-1";
			}
			else if(Integer.parseInt(ret) < 0) {
				ret = "-1";
			}
		}
		else {
			ret = scanner.next();
			if (user.getAccessLevel() != User.AccessLevel.EMPLOYEE) {
				ret = "-1";
			}
			else if(!ret.equals("y")) {
				ret = "-1";
			}
		}
		
		log.trace("Exiting validInput");
		return ret;
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
	
	private Offer makeOffer() {
		log.trace("Entering makeOffer");
		
		sysout.println("How much would you like to offer?");
		Offer offer = null;
		double input = -1.0;
		while (input == -1.0) {
			if (scanner.hasNextDouble()) {
				input = Double.parseDouble(scanner.next());
				if (input <= 0.0){
					input = -1.0;
				}
			}
			else {
				scanner.next();
			}
			
			if (input == -1.0) {
				sysout.println("Invalid input. Try again.");
			}
		}
		
		
		offer = new Offer(OfferStatus.PENDING, input, user);
		
		log.trace("Exiting makeOffer");
		return offer;
	}
	
	public void setLotDAOSerializer(LotDAOSerialization lotSerializer) {
		this.lotSerializer = lotSerializer;
	}
	
	public void setSystem(System menuSystem) {
		this.menuSystem = menuSystem;
	}
}
