package com.revature.services;

import java.util.Scanner;

public class LoginMenuImpl implements LoginMenu {

	@Override
	public void entry() {
		// TODO Auto-generated method stub
		display();
	}
	
	@Override
	public void display() {
		System.out.println("Welcome to Cars Incorporated! We have cars, you do too!");
		System.out.println("For customers or employees, press 1");
		System.out.println("For new users looking to register, press 2");
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}
}
