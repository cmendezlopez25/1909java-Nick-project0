package com.revature.services;

import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;
import static com.revature.util.LoggerUtil.log;
import com.revature.pojo.System;

public class MainMenuImpl implements MainMenu {
	// singleton
	public static final MainMenuImpl mainMenu = new MainMenuImpl();

	private MainMenuImpl() {
		log.trace("Creating Main Menu");
	}

	@Override
	public Menu display() {
		log.trace("Inside Main Menu");

		sysout.println("Welcome to Cars Incorporated! We have cars, you do too!");
		sysout.println("For customers or employees, press 1");
		sysout.println("For new users looking to register, press 2");
		sysout.println("To close, press 3");
		
		int number = isValidInput();
		while (number == -1)
		{
			sysout.println("Invalid Input. Try again.");
			number = isValidInput();
		}
		
		switch (number){
		case 1:
			System.menuSystem.setCurrentMenu(LoginMenuImpl.loginMenu);
			break;
			
		case 2:
			System.menuSystem.setCurrentMenu(RegisterUserMenuImpl.registerMenu);
			break;
			
		case 3:
			System.menuSystem.setCurrentMenu(null);
			break;
		}

		log.trace("Exiting Main Menu");
		return System.menuSystem.getCurrentMenu();
	}

	private int isValidInput() {
		if (scanner.hasNextInt())
		{
			int number = Integer.parseInt(scanner.next());
			if (number >= 1 && number <= 3)
			{
				return number;
			}
		}
		else
		{
			scanner.next();
		}
		return -1;
	}
}
