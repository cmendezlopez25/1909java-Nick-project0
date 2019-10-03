package com.revature.services;

import static com.revature.util.LoggerUtil.log;
import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;

import java.util.List;

import com.revature.pojo.Car;
import com.revature.pojo.Customer;
import com.revature.pojo.Payment;
import com.revature.pojo.System;
import com.revature.pojo.User;
import com.revature.util.SystemUtil;

public class CustomerMenuImpl implements CustomerMenu {
	// singleton
	public static final CustomerMenuImpl customerMenu = new CustomerMenuImpl();
	private PaymentServiceImpl paymentService = new PaymentServiceImpl();
	User currentUser = null;
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
		
		currentUser = menuSystem.getUser();

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
	
	private void displayCars() {
		log.trace("Entering displayCars");
		List<Car> carList = menuSystem.retrieveCarsByUser(currentUser.getUsername());
		if (carList == null || carList.size() == 0) {
			sysout.println("You don't own any cars.");
		}
		else {
			for (int i = 0; i < carList.size(); ++i) {
				sysout.println((i+1) + ". " + carList.get(i).toString());
			}
		}
		
		log.trace("Exiting displayCars");
	}
	
	private void displayPayments() {
		log.trace("Entering displayPayments");
		List<Payment> paymentList = paymentService.retrievePaymentsByUsername(currentUser);
		if (paymentList == null || paymentList.size() == 0) {
			sysout.println("You don't have any payments.");
		}
		else {
			for (int i = 0; i < paymentList.size(); ++i) {
				sysout.println((i+1) + ". " + paymentList.get(i).toString());
			}
		}
		log.trace("Exiting displayPayments");
	}
	
	public void setSystem(System system) {
		this.menuSystem= system;
	}
}
