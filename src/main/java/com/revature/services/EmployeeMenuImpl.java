package com.revature.services;

import static com.revature.util.LoggerUtil.log;

public class EmployeeMenuImpl implements EmployeeMenu {
	//singleton
	public static final EmployeeMenuImpl employeeMenu = new EmployeeMenuImpl();
	
	private EmployeeMenuImpl() {
		log.trace("Creating Employee Menu");
	}
	
	@Override
	public Menu display() {
		log.trace("Entering Employee Menu");
		
		
		
		log.trace("Exiting Employee Menu");
		return null;
	}
}
