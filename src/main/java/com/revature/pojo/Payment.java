package com.revature.pojo;

public class Payment {
	private int totalPayment;
	private int months;
	private Customer owner;

	public int getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}
}
