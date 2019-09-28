package com.revature.services;

public class EmployeeMenuImpl implements EmployeeMenu {
	//singleton
	public static final EmployeeMenuImpl employeeMenu = new EmployeeMenuImpl();
	
	private EmployeeMenuImpl() {
		
	}
	
	@Override
	public Menu display() {
		return null;
	}
}
