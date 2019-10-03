package com.revature.util;

import java.io.PrintStream;
import java.util.Scanner;

public class SystemUtil {
	public static Scanner scanner = new Scanner(System.in);
	public static PrintStream sysout = new PrintStream(System.out);
	
	public static String nextLine() {
		return scanner.nextLine();
	}
	
	public static boolean isValidInput(String input, boolean isCaseSensitive, String ... validInput) {
		if (input == null) {
			return false;
		}
		
		if (isCaseSensitive) {
			for (String valid : validInput) {
				if (input.equals(valid)) {
					return true;
				}
			}
		}
		else {
			for (String valid : validInput) {
				if (input.toLowerCase().equals(valid.toLowerCase())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean isInt(String input) {
		try {
			Integer.parseInt(input);
		} catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
		} catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}
}
