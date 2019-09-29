package com.revature.pojo;

import java.util.List;

public class Employee extends User {
	List<Offer> allOffers;
	List<Payment> allPayments;

	public List<Offer> getAllOffers() {
		return allOffers;
	}

	public void setAllOffers(List<Offer> allOffers) {
		this.allOffers = allOffers;
	}

	public List<Payment> getAllPayments() {
		return allPayments;
	}

	public void setAllPayments(List<Payment> allPayments) {
		this.allPayments = allPayments;
	}
	
	public Employee() {
		this.allOffers = null;
		this.allPayments = null;
	}

	public Employee(List<Offer> allOffers, List<Payment> allPayments) {
		super();
		this.allOffers = allOffers;
		this.allPayments = allPayments;
	}

	
}
