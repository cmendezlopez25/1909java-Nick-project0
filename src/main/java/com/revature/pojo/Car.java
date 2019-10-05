package com.revature.pojo;

import java.io.Serializable;

public class Car implements Serializable {
	private String vin;
	private String model;
	private int year;
	private String ownerUsername;
	private double basePrice;

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

	public String getOwner() {
		return ownerUsername;
	}

	public void setOwner(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}
	
	public double getBasePrice() {
		return basePrice;
	}
	
	public void setBasePrice(double basePrice) {
		double roundedPayment = basePrice * 100;
		roundedPayment = Math.round(roundedPayment);
		this.basePrice = roundedPayment / 100;
	}

	public Car() {
		super();
	}

	public Car(String vin, String model, int year, String ownerUsername, double basePrice) {
		super();
		this.vin = vin;
		this.model = model;
		this.year = year;
		this.ownerUsername = ownerUsername;
		setBasePrice(basePrice);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(basePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((ownerUsername == null) ? 0 : ownerUsername.hashCode());
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (Double.doubleToLongBits(basePrice) != Double.doubleToLongBits(other.basePrice))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (ownerUsername == null) {
			if (other.ownerUsername != null)
				return false;
		} else if (!ownerUsername.equals(other.ownerUsername))
			return false;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [vin=" + vin + ", model=" + model + ", year=" + year + ", ownerUsername=" + ownerUsername
				+ ", basePrice=" + basePrice + "]";
	}
}
