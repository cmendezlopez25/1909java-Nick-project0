package com.revature.pojo;

import java.util.List;

public class Employee {
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

}
