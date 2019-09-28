package com.revature.pojo;

import java.util.HashMap;

import com.revature.services.LoginMenuImpl;
import com.revature.services.Menu;

public class System {
	//singleton
	public static final System menuSystem = new System();
	private Menu currentMenu;
	private User user;
	private Lot lot;
	private HashMap<String, Payment> allPaymentsByUsername;
	private HashMap<String, Offer> allOffersByUsername;
	
	private System() {
		setCurrentMenu(LoginMenuImpl.loginMenu);
		setUser(null);
		setLot(null);
		setPayments(null);
		setOffers(null);
	}

	public Menu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public HashMap<String, Payment> getPayments() {
		return allPaymentsByUsername;
	}

	public void setPayments(HashMap<String, Payment> allPaymentsByUsername) {
		this.allPaymentsByUsername = allPaymentsByUsername;
	}

	public HashMap<String, Offer> getOffers() {
		return allOffersByUsername;
	}

	public void setOffers(HashMap<String, Offer> allOffersByUsername) {
		this.allOffersByUsername = allOffersByUsername;
	}
	
	
}
