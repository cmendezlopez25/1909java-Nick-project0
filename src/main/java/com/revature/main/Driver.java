package com.revature.main;

import static com.revature.util.LoggerUtil.log;
import static com.revature.util.SystemUtil.sysout;

import com.revature.pojo.System;

public class Driver {
	private static System menuSystem = System.menuSystem;
	
	public static void main(String[] args) {
		log.trace("Program start.");
		while(menuSystem.getCurrentMenu().display() != null);
		sysout.println("Thank you for using our service here at Cars Incorporated! We hope you continue to have cars!");
		log.trace("Exit program.");
	}
}
