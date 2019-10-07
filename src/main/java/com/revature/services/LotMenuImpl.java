package com.revature.services;

import static com.revature.util.LoggerUtil.log;
import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;

import com.revature.dao.LotDAOSerialization;
import com.revature.pojo.Car;
import com.revature.pojo.Lot;
import com.revature.pojo.Offer;
import com.revature.pojo.User;
import com.revature.pojo.Offer.OfferStatus;
import com.revature.pojo.User.AccessLevel;
import com.revature.util.SystemUtil;
import com.revature.pojo.System;

public class LotMenuImpl implements LotMenu {
	//singleton
	public static final LotMenuImpl lotMenu = new LotMenuImpl();
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
		
		lot = new Lot(menuSystem.retrieveUnownedCars());
		
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
					Offer offer = makeOffer(lot.getCars().get(Integer.parseInt(input) - 1).getVin());
					offerService.addOffer(offer);
					sysout.println("You made an offer of $" + offer.getMoneyAmount() + "!");
				}
				else {
					if (input.toLowerCase().equals("y")) {
						addCar();
					}
					else {
						if (lot.getCars().size() > 0) {
							if (lotService.removeCarFromLot(lot.getCars().get(Integer.parseInt(input) - 1))) {
								sysout.println("Car #" + input + " was removed from the lot.");
							}
							else {
								sysout.println("Car #" + input + " could not be removed from the lot.");
							}
						}
						else {
							sysout.println("There are no cars to remove.");
						}
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
		String ret = SystemUtil.nextLine();
		
		if (SystemUtil.isInt(ret)) {
			if (lot.getCars() != null && lot.getCars().size() > 0 && (Integer.parseInt(ret) > lot.getCars().size() || Integer.parseInt(ret) < 0)) {
				ret = "-1";
			}
			else if(Integer.parseInt(ret) < 0) {
				ret = "-1";
			}
		}
		else {
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
	
	private Offer makeOffer(String carVin) {
		log.trace("Entering makeOffer");
		
		sysout.println("How much would you like to offer?");
		Offer offer = null;
		double number = -1.0;
		while (number == -1.0) {
			String input = SystemUtil.nextLine();
			if (SystemUtil.isDouble(input)) {
				number = Double.parseDouble(input);
				if (number <= 0.0){
					number = -1.0;
				}
			}
			
			if (number == -1.0) {
				sysout.println("Invalid input. Try again.");
			}
		}
		
		offer = new Offer(OfferStatus.PENDING, number, user.getUsername(), carVin);
		
		log.trace("Exiting makeOffer");
		return offer;
	}
	
	private void addCar() {
		log.trace("Entering addCar");
		Car car = new Car();
		sysout.println("Enter the Vehicle Identification Number [Enter exactly 17 letters/numbers with no spaces or dashes]");
		car.setVin(enterVin());
		sysout.println("Enter the model");
		car.setModel(enterModel());
		sysout.println("Enter the year.");
		car.setYear(enterYear());
		lotService.addCarToLot(car);
		log.trace("Exiting addCar");
	}
	
	private String enterVin() {
		log.trace("Entering enterVin");
		String input = "-1";
		while (input == "-1") {
			input = SystemUtil.nextLine();
			if (input.length() == 17) {
				for (int i = 0; i < 17; ++i) {
					if (!Character.isLetterOrDigit(input.charAt(i))) {
						input = "-1";
						sysout.println("Invalid input. Try again.");
						break;
					}
				}
			}
			else {
				input = "-1";
				sysout.println("Invalid input. Try again.");
			}
		}
		log.trace("Exiting enterVin");
		return input;
	}
	
	private String enterModel() {
		log.trace("Entering enterModel");
		String input = SystemUtil.nextLine();
		log.trace("Exiting enterModel");
		return input;
	}
	
	private int enterYear() {
		log.trace("Entering enterYear");
		String input = SystemUtil.nextLine();
		while(!SystemUtil.isInt(input)) {
			sysout.println("Invalid input. Try again!");
			input = SystemUtil.nextLine();
		}
		log.trace("Exiting enterYear");
		return Integer.parseInt(input);
	}
	
	public void setSystem(System menuSystem) {
		this.menuSystem = menuSystem;
	}
}
