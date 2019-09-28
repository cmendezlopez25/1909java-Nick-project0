package com.revature.pojo;

public class Car {
	private String vin;
	private String model;
	private int year;
	private Customer owner;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

}
