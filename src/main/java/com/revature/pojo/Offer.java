package com.revature.pojo;

import java.io.Serializable;

public class Offer implements Serializable {
	public static enum OfferStatus {
		PENDING, REJECTED, ACCEPTED;
	}

	private OfferStatus offerStatus;
	private double moneyAmount;
	private String ownerUsername;
	private String carVin;

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

	public Offer() {
		super();
	}

	public Offer(OfferStatus offerStatus, double moneyAmount, String ownerUsername, String carVin) {
		super();
		this.offerStatus = offerStatus;
		setMoneyAmount(moneyAmount);
		this.ownerUsername = ownerUsername;
		this.carVin = carVin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carVin == null) ? 0 : carVin.hashCode());
		long temp;
		temp = Double.doubleToLongBits(moneyAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((offerStatus == null) ? 0 : offerStatus.hashCode());
		result = prime * result + ((ownerUsername == null) ? 0 : ownerUsername.hashCode());
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
		if (carVin == null) {
			if (other.carVin != null)
				return false;
		} else if (!carVin.equals(other.carVin))
			return false;
		if (Double.doubleToLongBits(moneyAmount) != Double.doubleToLongBits(other.moneyAmount))
			return false;
		if (offerStatus != other.offerStatus)
			return false;
		if (ownerUsername == null) {
			if (other.ownerUsername != null)
				return false;
		} else if (!ownerUsername.equals(other.ownerUsername))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [offerStatus=" + offerStatus + ", moneyAmount=" + moneyAmount + ", ownerUsername=" + ownerUsername
				+ ", carVin=" + carVin + "]";
	}
}
