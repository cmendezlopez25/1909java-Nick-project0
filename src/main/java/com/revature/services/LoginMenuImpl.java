package com.revature.services;

import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;
import static com.revature.util.LoggerUtil.log;

public class LoginMenuImpl implements LoginMenu {
	//singleton
	public static Menu loginMenu = new LoginMenuImpl();
	
	private LoginMenuImpl() {
		log.trace("Creating Login Menu");
	}
	
	@Override
	public Menu display() {
		log.trace("Inside Login Menu");
		
		
		log.trace("Exiting Login Menu");
		return null;
	}
	
	private boolean isValidInput(String message) {
		return false;
	}
}
