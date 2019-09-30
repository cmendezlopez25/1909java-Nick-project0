package com.revature.pojo;

import java.io.Serializable;

public class Payment implements Serializable {
	private double totalPayment;
	private int months;
	private String ownerUsername;

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		double roundedPayment = totalPayment * 100;
		roundedPayment = Math.round(roundedPayment);
		this.totalPayment = roundedPayment / 100.0;
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
	
	public Payment() {
		super();
	}

	public Payment(double totalPayment, int months, String ownerUsername) {
		super();
		this.totalPayment = totalPayment;
		this.months = months;
		this.ownerUsername = ownerUsername;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + months;
		result = prime * result + ((ownerUsername == null) ? 0 : ownerUsername.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPayment);
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
		if (months != other.months)
			return false;
		if (ownerUsername == null) {
			if (other.ownerUsername != null)
				return false;
		} else if (!ownerUsername.equals(other.ownerUsername))
			return false;
		if (Double.doubleToLongBits(totalPayment) != Double.doubleToLongBits(other.totalPayment))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [totalPayment=" + totalPayment + ", months=" + months + ", ownerUsername=" + ownerUsername
				+ "]";
	}
}
