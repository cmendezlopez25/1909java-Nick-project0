package com.revature.pojo;

public class User {
	public static enum AccessLevel {
		NONE, CUSTOMER, EMPLOYEE;
	}

	private AccessLevel accessLevel;
	private String username;
	private String name;
	private String password;

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public User() {
		super();
		this.accessLevel = AccessLevel.NONE;
		this.username = "";
		this.name = "";
		this.password = "";
	}

	public User(AccessLevel accessLevel, String username, String name, String password) {
		super();
		this.accessLevel = accessLevel;
		this.username = username;
		this.name = name;
		this.password = password;
	}

	
}
