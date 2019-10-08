package com.revature.services;

import static com.revature.util.LoggerUtil.log;
import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;

import java.util.List;

import com.revature.pojo.Offer;
import com.revature.pojo.System;
import com.revature.pojo.User;
import com.revature.util.SystemUtil;

public class EmployeeMenuImpl implements EmployeeMenu {
	//singleton
	public static final EmployeeMenuImpl employeeMenu = new EmployeeMenuImpl();
	private User currentUser = null;
	private System menuSystem = System.menuSystem;
	private OfferServiceImpl offerService = new OfferServiceImpl();
	
	private EmployeeMenuImpl() {
		log.trace("Creating Employee Menu");
	}
	
	@Override
	public Menu display() {
		log.trace("Entering Employee Menu");
		
		if (menuSystem.getUser() == null) {
			log.error("User does not exist!");
			//System.menuSystem.setCurrentMenu(null);
			return null;
		}
		
		currentUser = menuSystem.getUser();
		
		sysout.println("Welcome back, " + currentUser.getName() + "!");
		sysout.println("1. View the car lot.");
		sysout.println("2. View all pending offers.");
		sysout.println("3. View all payments");
		sysout.println("4. Return to the Login Menu");
		sysout.println("5. Return to the Main Menu");
		
		int input = -1;
		while (input == -1) {
			input = userInput();
			switch (input) {
			case 1:
				System.menuSystem.setCurrentMenu(LotMenuImpl.lotMenu);
				break;

			case 2:
				displayOffers();
				System.menuSystem.setCurrentMenu(employeeMenu);
				break;

			case 3:
				displayPayments();
				System.menuSystem.setCurrentMenu(employeeMenu);
				break;

			case 4:
				System.menuSystem.setCurrentMenu(LoginMenuImpl.loginMenu);
				break;

			case 5:
				System.menuSystem.setCurrentMenu(MainMenuImpl.mainMenu);
				break;

			default:
				sysout.println("Invalid input. Try again.");
				break;
			}
		}
		
		log.trace("Exiting Employee Menu");
		return System.menuSystem.getCurrentMenu();
	}
	
	private int userInput() {
		log.trace("Entering userInput");
		
		String input = SystemUtil.nextLine();
		if (SystemUtil.isInt(input)) {
			int number = Integer.parseInt(input);
			if (number >= 1 && number <= 5) {
				log.trace("Exiting userInput");
				return number;
			}
		}
		
		log.trace("Exiting userInput");
		return -1;
	}
	
	private void displayPayments() {
		log.trace("Entering displayPayments");
		if (menuSystem.getPayments() == null || menuSystem.getPayments().size() == 0) {
			sysout.println("There are no payments.");
		}
		else {
			for (int i = 0; i < menuSystem.getPayments().size(); ++i) {
				sysout.println((i+1) + ". " + menuSystem.getPayments().get(i).toString());
			}
		}
		log.trace("Exiting displayPayments");
	}
	
	private void displayOffers() {
		log.trace("Entering displayOffers");
		List<Offer> offerList = menuSystem.retrievePendingOffers();
		if (offerList == null || offerList.size() == 0) {
			sysout.println("There are no offers.");
		}
		else {
			for (int i = 0; i < offerList.size(); ++i) {
				sysout.println((i+1) + ". " + offerList.get(i).toString());
			}
			sysout.println("Select an offer to accept or reject, or press c to cancel.");
			validOfferInput();
		}
		
		log.trace("Exiting displayOffers");
	}
	
	private void validOfferInput() {
		log.trace("Entering validOfferInput");
		List<Offer> offerList = menuSystem.retrievePendingOffers();
		
		String input = "-1";
		while (input.equals("-1")) {
			input = SystemUtil.nextLine();
			if (SystemUtil.isInt(input)) {
				if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > offerList.size()) {
					input = "-1";
				}
			}
			else {
				if (!input.toLowerCase().equals("c")) {
					input = "-1";
				}
			}
			
			if (input.equals("-1")) {
				sysout.println("Incorrect input. Try again.");
			}
		}
		
		if (!input.toLowerCase().equals("c")) {
			Offer offer = offerList.get(Integer.parseInt(input) -1);
			sysout.println("Press a to accept or r to reject offer: " + offer);
			acceptRejectOffer(offer);
		}
		
		log.trace("Exiting validOfferInput");
	}
	
	private void acceptRejectOffer(Offer offer) {
		log.trace("Entering acceptRejectOffers");
		
		String input = "-1";
		while (input.equals("-1")) {
			input = SystemUtil.nextLine();
			
			if (input.toLowerCase().equals("a")) {
				offerService.acceptOffer(offer);
				sysout.println("Accepted " + offer);
			}
			else if (input.toLowerCase().equals("r")) {
				offerService.rejectOffer(offer);
				sysout.println("Rejected " + offer);
			}
			else {
				input = "-1";
			}
			
			if (input.equals("-1")) {
				sysout.println("Incorrect input. Try again.");
			}
		}
		
		log.trace("Exiting acceptRejectOffers");
	}
	
	public void setMenuSystem(System menuSystem) {
		this.menuSystem = menuSystem;
	}
}
