package com.revature.pojo;

public class Payment {
	private double totalPayment;
	private int months;
	private Customer owner;

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

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	
	public Payment() {
		super();
	}

	public Payment(double totalPayment, int months, Customer owner) {
		super();
		setTotalPayment(totalPayment);
		setMonths(months);
		setOwner(owner);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + months;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (Double.doubleToLongBits(totalPayment) != Double.doubleToLongBits(other.totalPayment))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [totalPayment=" + totalPayment + ", months=" + months + ", owner=" + owner + "]";
	}
	
	
}
