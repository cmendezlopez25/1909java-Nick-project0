package com.revature.services;

import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;

import com.revature.dao.UserDAOSerialization;

import static com.revature.util.LoggerUtil.log;

import com.revature.pojo.System;
import com.revature.pojo.User;
import com.revature.util.SystemUtil;

public class LoginMenuImpl implements LoginMenu {
	// singleton
	public static LoginMenuImpl loginMenu = new LoginMenuImpl();
	private UserDAOSerialization userSerializer = UserDAOSerialization.userSerializer;

	private LoginMenuImpl() {
		log.trace("Creating Login Menu");
	}

	@Override
	public Menu display() {
		log.trace("Inside Login Menu");

		sysout.println("Enter your username: ");
		String username = SystemUtil.nextLine();
		sysout.println("Enter your password: ");
		String password = SystemUtil.nextLine();

		User user = new User();

		boolean isValidUser = isValidUsernameAndPassword(username, password, user);
		if (isValidUser) {
			System.menuSystem.setUser(user);
			if (user.getAccessLevel() == User.AccessLevel.CUSTOMER) {
				System.menuSystem.setCurrentMenu(CustomerMenuImpl.customerMenu);
			} else {
				System.menuSystem.setCurrentMenu(EmployeeMenuImpl.employeeMenu);
			}
		} else {
			sysout.println("Invalid username. Would you like to return to the main menu? y or n");
			boolean isValidInput = isValidInput();
			while (!isValidInput) {
				isValidInput = isValidInput();
			}
		}

		log.trace("Exiting Login Menu");
		return System.menuSystem.getCurrentMenu();
	}

	private boolean isValidUsernameAndPassword(String username, String password, User u) {
		log.trace("Entering isValidUsernameAndPassword");
		User user = userSerializer.ReadUserFile(username);
		boolean ret = false;
		if (user != null) {
			if (password.equals(user.getPassword())) {
				u.setAccessLevel(user.getAccessLevel());
				u.setUsername(user.getUsername());
				u.setName(user.getName());
				u.setPassword(user.getPassword());
				ret = true;
			}
		}

		log.trace("Exiting isValidUsernameAndPassword");
		return ret;
	}

	private boolean isValidInput() {
		log.trace("Entering isValidInput");
		String input = SystemUtil.nextLine();
		if (input.length() == 1) {
			if (input.toLowerCase().charAt(0) == 'y') {
				System.menuSystem.setCurrentMenu(MainMenuImpl.mainMenu);
				log.trace("Exiting isValidInput");
				return true;
			} else if (input.toLowerCase().charAt(0) == 'n') {
				System.menuSystem.setCurrentMenu(loginMenu);
				log.trace("Exiting isValidInput");
				return true;
			}
		}
		sysout.println("Invalid input. Try again.");
		log.trace("Exiting isValidInput");
		return false;
	}

	public void setUserDAOSerialization(UserDAOSerialization userSerializer) {
		this.userSerializer = userSerializer;
	}
}
