package com.revature.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.revature.services.LoginMenuImpl;
import com.revature.services.Menu;

import com.revature.dao.*;
import com.revature.pojo.Offer.OfferStatus;

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
		if (getLot().getCars().remove(c)) {
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
		// TODO
	}
	
	public List<Payment> retrievePaymentsFromUsername(User u){
		List<Payment> paymentList = new ArrayList<Payment>();
		for (Payment payment : getPayments()) {
			if (payment.getOwnerUsername().equals(u.getUsername())) {
				paymentList.add(payment);
			}
		}
		return paymentList;
	}
	
	public void addOffer(Offer o) {
		getOffers().add(o);
		offerSerializer.CreateOfferFile(getOffers(), "AllOffers");
	}
	
	public boolean removeOffer(Offer o) {
		if  (getOffers().remove(o)) {
			offerSerializer.CreateOfferFile(getOffers(), "AllOffers");
			return true;
		}
		
		return false;
	}
	
	public void acceptOffer(Offer o) {
		if (getOffers().contains(o)) {
			Offer offer = getOffers().get(getOffers().indexOf(o));
			offer.setOfferStatus(OfferStatus.ACCEPTED);
			
			rejectAllOffersOfVin(offer.getCarVin());
		}
	}
	
	public void rejectOffer(Offer o) {
		if (getOffers().contains(o)) {
			Offer offer = getOffers().get(getOffers().indexOf(o));
			offer.setOfferStatus(OfferStatus.REJECTED);
		}
	}
	
	public void rejectAllOffersOfVin(String vin) {
		for (int i = 0; i < getOffers().size(); ++i) {
			Offer offer = getOffers().get(i);
			if (offer.getCarVin().equals(vin) && offer.getOfferStatus() == OfferStatus.PENDING) {
				offer.setOfferStatus(OfferStatus.REJECTED);
			}
		}
	}
	
	public List<Offer> retrievePendingOffers(){
		List<Offer> offerList = new ArrayList<Offer>();
		for (Offer offer : getOffers()) {
			if (offer.getOfferStatus() == OfferStatus.PENDING) {
				offerList.add(offer);
			}
		}
		return offerList;
	}
	
	public List<Offer> retrieveOffersFromUser(User u){
		List<Offer> offerList = new ArrayList<Offer>();
		for (Offer offer : getOffers()) {
			if (offer.getOwnerUsername().equals(u.getUsername())) {
				offerList.add(offer);
			}
		}
		return offerList;
	}
	
	public List<Offer> retrieveOffersFromVin(String vin){
		List<Offer> offerList = new ArrayList<Offer>();
		for (Offer offer : getOffers()) {
			if (offer.getCarVin().equals(vin)) {
				offerList.add(offer);
			}
		}
		return offerList;
	}
	
	public void createNewUser(User u) {
		userSerializer.CreateUser(u);
		setUser(u);
	}
}
