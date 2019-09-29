package com.revature.services;

import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;
import static com.revature.util.LoggerUtil.log;

import com.revature.dao.UserDAOSerialization;
import com.revature.pojo.System;
import com.revature.pojo.User;

public class RegisterUserMenuImpl implements RegisterUserMenu {
	// singleton
	public static final RegisterUserMenuImpl registerMenu = new RegisterUserMenuImpl();
	private UserDAOSerialization userSerializer = UserDAOSerialization.userSerializer;

	private RegisterUserMenuImpl() {
		log.trace("Creating Register User menu");
	}

	@Override
	public Menu display() {
		log.trace("Entering Register User menu");

		sysout.println("Register as a new user.");
		User user = new User();
		boolean usernameExist = doesUsernameExist(user);
		if (usernameExist) {
			sysout.println("Already existing username. Would you like to return to the Main Menu? y or n");
			boolean isValidInput = !isValidInput();
			while (isValidInput) {
				isValidInput = !isValidInput();
			}
			usernameExist = isValidInput;
		}
		else {
			sysout.println("Enter your full name");
			user.setName(scanner.next());
			sysout.println("Enter your password");
			user.setPassword(scanner.next());
			sysout.println("User creation is successful! Enjoy your new account!");
			userSerializer.CreateUser(user);
			System.menuSystem.setCurrentMenu(CustomerMenuImpl.customerMenu);
		}
		
		log.trace("Exiting Register User menu");
		return System.menuSystem.getCurrentMenu();
	}

	private boolean doesUsernameExist(User u) {
		log.trace("Entering doesUsernameExist()");
		sysout.println("Enter your username.");
		u.setUsername(scanner.next());
		User user = userSerializer.ReadUserFile(u.getUsername());
		log.trace("Exiting doesUsernameExist()");
		return user != null;
	}
	
	private boolean isValidInput() {
		log.trace("Entering isValidInput");
		String input = scanner.next();
		if (input.length() == 1) {
			if (input.toLowerCase().charAt(0) == 'y') {
				System.menuSystem.setCurrentMenu(MainMenuImpl.mainMenu);
				log.trace("Exiting isValidInput");
				return true;
			} else if (input.toLowerCase().charAt(0) == 'n') {
				System.menuSystem.setCurrentMenu(registerMenu);
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
