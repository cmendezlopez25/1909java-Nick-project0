package com.revature.services;

import static com.revature.util.LoggerUtil.log;
import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;

import com.revature.pojo.Employee;
import com.revature.pojo.System;

public class EmployeeMenuImpl implements EmployeeMenu {
	//singleton
	public static final EmployeeMenuImpl employeeMenu = new EmployeeMenuImpl();
	private Employee currentUser = null;
	private System menuSystem = System.menuSystem;
	
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
				System.menuSystem.setCurrentMenu(OfferMenuImpl.offerMenu);
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
	
	public void setMenuSystem(System menuSystem) {
		this.menuSystem = menuSystem;
	}
}
