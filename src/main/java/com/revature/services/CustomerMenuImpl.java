package com.revature.services;

public class CustomerMenuImpl implements CustomerMenu {
	//singleton
	public static final CustomerMenuImpl customerMenu = new CustomerMenuImpl();
	
	private CustomerMenuImpl() {
		
	}
	
	@Override
	public Menu display() {
		return null;
	}
}
