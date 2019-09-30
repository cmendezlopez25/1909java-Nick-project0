package com.revature.pojo;

public class Offer {
	public static enum OfferStatus {
		PENDING, REJECTED, ACCEPTED;
	}

	private OfferStatus offerStatus;
	private double moneyAmount;
	private User owner;

	public OfferStatus getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

	public double getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(double moneyAmount) {
		double rounded = moneyAmount * 100;
		rounded = Math.round(rounded) / 100.0;
		this.moneyAmount = rounded;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public Offer() {
		super();
	}

	public Offer(OfferStatus offerStatus, double moneyAmount, User owner) {
		super();
		this.offerStatus = offerStatus;
		setMoneyAmount(moneyAmount);
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(moneyAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((offerStatus == null) ? 0 : offerStatus.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Offer other = (Offer) obj;
		if (Double.doubleToLongBits(moneyAmount) != Double.doubleToLongBits(other.moneyAmount))
			return false;
		if (offerStatus != other.offerStatus)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [offerStatus=" + offerStatus + ", moneyAmount=" + moneyAmount + ", owner=" + owner + "]";
	}
}
