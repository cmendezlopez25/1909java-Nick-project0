package com.revature.services;

import static com.revature.util.SystemUtil.scanner;
import static com.revature.util.SystemUtil.sysout;
import static com.revature.util.LoggerUtil.log;
import com.revature.pojo.System;

public class RegisterUserMenuImpl implements RegisterUserMenu {
	//singleton
	public static final RegisterUserMenuImpl registerMenu = new RegisterUserMenuImpl();
	
	private RegisterUserMenuImpl () {
		log.trace("Creating Register User menu");
	}
	
	@Override
	public Menu display() {
		log.trace("Entering Register User menu");
		
		
		log.trace("Exiting Register User menu");
		return null;
	}

}
