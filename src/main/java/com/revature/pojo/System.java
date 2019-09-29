package com.revature.pojo;

import java.util.HashMap;
import java.util.List;

import com.revature.services.LoginMenuImpl;
import com.revature.services.Menu;

public class System {
	//singleton
	public static final System menuSystem = new System();
	private Menu currentMenu;
	private User user;
	private Lot lot;
	private List<Payment> allPayments;
	private List<Offer> allOffers;
	
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

	public List<Payment> getPayments() {
		return allPayments;
	}

	public void setPayments(List<Payment> allPayments) {
		this.allPayments = allPayments;
	}

	public List<Offer> getOffers() {
		return allOffers;
	}

	public void setOffers(List<Offer> allOffers) {
		this.allOffers = allOffers;
	}
	
	
}
