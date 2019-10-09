package com.revature.services;

import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;
import static com.revature.util.LoggerUtil.log;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOPostgres;
import com.revature.dao.UserDAOSerialization;
import com.revature.pojo.System;
import com.revature.pojo.User;
import com.revature.pojo.User.AccessLevel;
import com.revature.util.LoggerUtil;
import com.revature.util.SystemUtil;

public class RegisterUserMenuImpl implements RegisterUserMenu {
	// singleton
	public static final RegisterUserMenuImpl registerMenu = new RegisterUserMenuImpl();
	private UserDAO userSerializer = new UserDAOPostgres();
	private boolean isExitMenuPrompt = false;

	private RegisterUserMenuImpl() {
		log.trace("Creating Register User menu");
	}

	@Override
	public Menu display() {
		log.trace("Entering Register User menu");

		isExitMenuPrompt = false;
		
		sysout.println();
		sysout.println("Register as a new user.");
		
		enterUserInfo();

		log.trace("Exiting Register User menu");
		return System.menuSystem.getCurrentMenu();
	}

	private boolean doesUsernameExist(User u) {
		log.trace("Entering doesUsernameExist()");
		sysout.println("Enter your username.");
		u.setUsername(SystemUtil.nextLine());
		if (u.getUsername().isEmpty()) {
			return true;
		}
		User user = userSerializer.ReadUserFile(u.getUsername());
		log.trace("Exiting doesUsernameExist()");
		return user != null;
	}
	
	private void enterUserInfo() {
		User user = new User();
		boolean usernameExist = doesUsernameExist(user);
		String input = null;
		if (usernameExist) {
			sysout.println("Unable to create user. Would you like to return to the Main Menu? [y] or [n]");
			isExitMenuPrompt = true;
			input = SystemUtil.nextLine();
			while (!isValidInput(input)) {
				input = SystemUtil.nextLine();
			}
		} else {
			sysout.println("Enter your full name");
			input = SystemUtil.nextLine();
			while(!isValidInput(input)) {
				input = SystemUtil.nextLine();
			}
			user.setName(input);
			sysout.println("Enter your password");
			input = SystemUtil.nextLine();
			while(!isValidInput(input)) {
				input = SystemUtil.nextLine();
			}
			user.setPassword(input);
			sysout.println("User creation is successful! Enjoy your new account!");
			user.setAccessLevel(AccessLevel.CUSTOMER);
			userSerializer.CreateUser(user);
			System.menuSystem.setUser(user);
			System.menuSystem.setCurrentMenu(CustomerMenuImpl.customerMenu);
		}
	}

	private boolean isValidInput(String input) {
		log.trace("Entering isValidInput");
		if (isExitMenuPrompt) {
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
		}
		else {
			if (SystemUtil.isValidInput(input, false, null)) {
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
