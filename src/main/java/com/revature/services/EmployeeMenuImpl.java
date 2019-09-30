package com.revature.services;

import static com.revature.util.LoggerUtil.log;
import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;

import com.revature.pojo.Employee;
import com.revature.pojo.Offer;
import com.revature.pojo.System;

public class EmployeeMenuImpl implements EmployeeMenu {
	//singleton
	public static final EmployeeMenuImpl employeeMenu = new EmployeeMenuImpl();
	private Employee currentUser = null;
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
		
		currentUser = (Employee)menuSystem.getUser();
		
		sysout.println("Welcome back, " + currentUser.getName() + "!");
		sysout.println("1. View the car lot.");
		sysout.println("2. View all offers.");
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
		
		if (scanner.hasNextInt()) {
			int input = scanner.nextInt();
			if (input >= 1 && input <= 5) {
				log.trace("Exiting userInput");
				return input;
			}
		}
		else {
			scanner.next();
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
		if (menuSystem.getOffers() == null || menuSystem.getOffers().size() == 0) {
			sysout.println("There are no offers.");
		}
		else {
			for (int i = 0; i < menuSystem.getOffers().size(); ++i) {
				sysout.println((i+1) + ". " + menuSystem.getOffers().get(i).toString());
			}
			sysout.println("Select an offer to accept or reject, or press c to cancel.");
			validOfferInput();
		}
		
		log.trace("Exiting displayOffers");
	}
	
	private void validOfferInput() {
		log.trace("Entering validOfferInput");
		
		String input = "-1";
		while (input.equals("-1")) {
			if (scanner.hasNextInt()) {
				input = scanner.next();
				if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > menuSystem.getOffers().size()) {
					input = "-1";
				}
			}
			else {
				input = scanner.next();
				if (!input.toLowerCase().equals("c")) {
					input = "-1";
				}
			}
			
			if (input.equals("-1")) {
				sysout.println("Incorrect input. Try again.");
			}
		}
		
		if (!input.toLowerCase().equals("c")) {
			Offer offer = menuSystem.getOffers().get(Integer.parseInt(input) -1);
			sysout.println("Press a to accept or r to reject offer: " + offer);
			acceptRejectOffer(offer);
		}
		
		log.trace("Exiting validOfferInput");
	}
	
	private void acceptRejectOffer(Offer offer) {
		log.trace("Entering acceptRejectOffers");
		
		String input = "-1";
		while (input.equals("-1")) {
			input = scanner.next();
			
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
