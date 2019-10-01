package com.revature.main;

import static com.revature.util.LoggerUtil.log;

import com.revature.pojo.System;

public class Driver {
	private static System menuSystem = System.menuSystem;
	
	public static void main(String[] args) {
		log.trace("Program start.");
		while(menuSystem.getCurrentMenu().display() != null);
		log.trace("Exit program.");
	}
}
