package com.revature.services;

import static com.revature.util.LoggerUtil.log;
import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;

import com.revature.pojo.Customer;
import com.revature.pojo.System;

public class CustomerMenuImpl implements CustomerMenu {
	// singleton
	public static final CustomerMenuImpl customerMenu = new CustomerMenuImpl();
	Customer currentUser = null;
	System menuSystem = System.menuSystem;

	private CustomerMenuImpl() {
		log.trace("Creating Customer Menu object");
	}

	@Override
	public Menu display() {
		log.trace("Entering Customer Menu");
		
		if (menuSystem.getUser() == null) {
			log.error("User does not exist!");
			return null;
		}
		
		currentUser = (Customer)menuSystem.getUser();

		sysout.println("Welcome back, " + currentUser.getName() + "!");
		sysout.println("1. View your owned cars.");
		sysout.println("2. View your remaining payments.");
		sysout.println("3. View cars in the lot.");
		sysout.println("4. Return to the login menu.");
		sysout.println("5. Return to the main menu.");

		int input = -1;
		while (input == -1) {
			input = userInput();
			switch (input) {
			case 1:
				displayCars();
				System.menuSystem.setCurrentMenu(customerMenu);
				break;

			case 2:
				displayPayments();
				System.menuSystem.setCurrentMenu(customerMenu);
				break;

			case 3:
				System.menuSystem.setCurrentMenu(LotMenuImpl.lotMenu);
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

		log.trace("Exiting Customer Menu");
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
	
	private void displayCars() {
		log.trace("Entering displayCars");
		
		if (currentUser.getOwnedCars() == null || currentUser.getOwnedCars().size() == 0) {
			sysout.println("You don't own any cars.");
		}
		else {
			for (int i = 0; i < currentUser.getOwnedCars().size(); ++i) {
				sysout.println((i+1) + ". " + currentUser.getOwnedCars().get(i).toString());
			}
		}
		
		log.trace("Exiting displayCars");
	}
	
	private void displayPayments() {
		log.trace("Entering displayPayments");
		if (currentUser.getMyPayments() == null || currentUser.getMyPayments().size() == 0) {
			sysout.println("You don't have any payments.");
		}
		else {
			for (int i = 0; i < currentUser.getMyPayments().size(); ++i) {
				sysout.println((i+1) + ". " + currentUser.getMyPayments().get(i).toString());
			}
		}
		log.trace("Exiting displayPayments");
	}
	
	public void setSystem(System system) {
		this.menuSystem= system;
	}
}
