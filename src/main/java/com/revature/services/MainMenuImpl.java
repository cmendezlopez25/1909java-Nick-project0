package com.revature.services;

import static com.revature.util.SystemUtil.sysout;

public class MainMenuImpl implements MainMenu {
	//singleton
	public static final MainMenuImpl mainMenu = new MainMenuImpl();
	
	private MainMenuImpl() {
		
	}

	@Override
	public Menu display() {
		sysout.println("Welcome to Cars Incorporated! We have cars, you do too!");
		sysout.println("For customers or employees, press 1");
		sysout.println("For new users looking to register, press 2");
		sysout.println("To close, press 3");
		
		return null;
	}

}
