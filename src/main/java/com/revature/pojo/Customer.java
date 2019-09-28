package com.revature.pojo;

import java.util.List;

public class Customer extends User {
	List<Car> ownedCars;
	List<Payment> myPayments;
	List<Offer> myOffers;

	public List<Car> getOwnedCars() {
		return ownedCars;
	}

	public void setOwnedCars(List<Car> ownedCars) {
		this.ownedCars = ownedCars;
	}

	public List<Payment> getMyPayments() {
		return myPayments;
	}

	public void setMyPayments(List<Payment> myPayments) {
		this.myPayments = myPayments;
	}

	public List<Offer> getMyOffers() {
		return myOffers;
	}

	public void setMyOffers(List<Offer> myOffers) {
		this.myOffers = myOffers;
	}

}
