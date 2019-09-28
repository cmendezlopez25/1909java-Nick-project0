package com.revature.pojo;

public class Offer {
	public static enum OfferStatus {
		PENDING, REJECTED, ACCEPTED;
	}

	private OfferStatus offerStatus;
	private int moneyAmount;
	private Customer owner;

	public OfferStatus getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

	public int getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(int moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

}
