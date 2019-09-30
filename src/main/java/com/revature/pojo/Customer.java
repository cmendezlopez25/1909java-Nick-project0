package com.revature.pojo;

import java.util.List;

public class Customer extends User {
	List<Car> ownedCars;
	List<Payment> myPayments;

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

	public Customer() {
		super();
	}

	public Customer(List<Car> ownedCars, List<Payment> myPayments) {
		super();
		this.ownedCars = ownedCars;
		this.myPayments = myPayments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myPayments == null) ? 0 : myPayments.hashCode());
		result = prime * result + ((ownedCars == null) ? 0 : ownedCars.hashCode());
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
		Customer other = (Customer) obj;
		if (myPayments == null) {
			if (other.myPayments != null)
				return false;
		} else if (!myPayments.equals(other.myPayments))
			return false;
		if (ownedCars == null) {
			if (other.ownedCars != null)
				return false;
		} else if (!ownedCars.equals(other.ownedCars))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [ownedCars=" + ownedCars + ", myPayments=" + myPayments + "]";
	}
}
