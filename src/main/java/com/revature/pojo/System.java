package com.revature.pojo;

import java.util.HashMap;
import java.util.List;

import com.revature.services.LoginMenuImpl;
import com.revature.services.Menu;

import com.revature.dao.*;

public class System {
	// singleton
	public static final System menuSystem = new System();
	private Menu currentMenu;
	private User user;
	private Lot lot;
	private List<Payment> allPayments;
	private List<Offer> allOffers;
	
	private LotDAOSerialization lotSerializer = LotDAOSerialization.lotSerializer;
	private PaymentDAOSerialization paymentSerializer = PaymentDAOSerialization.paymentSerializer;
	private UserDAOSerialization userSerializer = UserDAOSerialization.userSerializer;
	private OfferDAOSerialization offerSerializer = OfferDAOSerialization.offerSerializer;

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

	public void addCarToLot(Car c) {
		getLot().getCars().add(c);
		lotSerializer.CreateLotFile(lot, "Lot");
	}
	
	public boolean removeCarFromLot(Car c) {
		if (getLot().getCars().contains(c)) {
			getLot().getCars().remove(c);
			lotSerializer.CreateLotFile(lot, "Lot");
			return true;
		}
		
		return false;
	}
	
	public void addPayment(Payment p) {
		getPayments().add(p);
		paymentSerializer.CreatePaymentFile(getPayments(), "AllPayments");
	}
	
	public void calculatePayment(Payment p) {
		
	}
	
	public void addOffer(Offer o) {
		getOffers().add(o);
		offerSerializer.CreateOfferFile(getOffers(), "AllOffers");
	}
	
	public void rejectAllOffersOfVin(String vin) {
		// TODO
		for (int i = 0; i < getOffers().size(); ++i) {
			
		}
	}
	
	public void createNewUser(User u) {
		userSerializer.CreateUser(u);
		setUser(u);
	}
}
