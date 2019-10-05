package com.revature.pojo;

import java.io.Serializable;

public class Payment implements Serializable {
	private double startingPayment;
	private double monthlyPayment;
	private double remainingPayment;
	private int months;
	private String ownerUsername;
	private String carVin;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(double startingPayment, double monthlyPayment, double remainingPayment, int months,
			String ownerUsername, String carVin) {
		super();
		setStartingPayment(startingPayment);
		setMonthlyPayment(monthlyPayment);
		setRemainingPayment(remainingPayment);
		this.months = months;
		this.ownerUsername = ownerUsername;
		this.carVin = carVin;
	}

	public double getStartingPayment() {
		return startingPayment;
	}

	public void setStartingPayment(double startingPayment) {
		double roundedPayment = startingPayment * 100;
		roundedPayment = Math.round(roundedPayment);
		this.startingPayment = roundedPayment / 100;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		double roundedPayment = monthlyPayment * 100;
		roundedPayment = Math.round(roundedPayment);
		this.monthlyPayment = roundedPayment / 100;
	}

	public double getRemainingPayment() {
		return remainingPayment;
	}

	public void setRemainingPayment(double remainingPayment) {
		double roundedPayment = remainingPayment * 100;
		roundedPayment = Math.round(roundedPayment);
		this.remainingPayment = roundedPayment / 100;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public String getCarVin() {
		return carVin;
	}

	public void setCarVin(String carVin) {
		this.carVin = carVin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carVin == null) ? 0 : carVin.hashCode());
		long temp;
		temp = Double.doubleToLongBits(monthlyPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + months;
		result = prime * result + ((ownerUsername == null) ? 0 : ownerUsername.hashCode());
		temp = Double.doubleToLongBits(remainingPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(startingPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Payment other = (Payment) obj;
		if (carVin == null) {
			if (other.carVin != null)
				return false;
		} else if (!carVin.equals(other.carVin))
			return false;
		if (Double.doubleToLongBits(monthlyPayment) != Double.doubleToLongBits(other.monthlyPayment))
			return false;
		if (months != other.months)
			return false;
		if (ownerUsername == null) {
			if (other.ownerUsername != null)
				return false;
		} else if (!ownerUsername.equals(other.ownerUsername))
			return false;
		if (Double.doubleToLongBits(remainingPayment) != Double.doubleToLongBits(other.remainingPayment))
			return false;
		if (Double.doubleToLongBits(startingPayment) != Double.doubleToLongBits(other.startingPayment))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [startingPayment=" + startingPayment + ", monthlyPayment=" + monthlyPayment
				+ ", remainingPayment=" + remainingPayment + ", months=" + months + ", ownerUsername=" + ownerUsername
				+ ", carVin=" + carVin + "]";
	}
}
