package com.revature.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.revature.services.LoginMenuImpl;
import com.revature.services.MainMenuImpl;
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

	private LotDAO lotSerializer = new LotDAOPostgres();
	private PaymentDAO paymentSerializer = new PaymentDAOPostgres();
	private UserDAO userSerializer = new UserDAOPostgres();
	private OfferDAO offerSerializer = new OfferDAOPostgres();

	private System() {
		setCurrentMenu(MainMenuImpl.mainMenu);
		setUser(null);
		setLot(lotSerializer.ReadLotFile("Lot"));
		setPayments(paymentSerializer.ReadAllPaymentsFile("payment"));
		setOffers(offerSerializer.ReadAllOffersFiles("offer"));
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
		if (lot != null) {
			this.lot = lot;
		} else {
			this.lot = new Lot();
			this.lot.setCars(new ArrayList<Car>());
		}
	}

	public List<Payment> getPayments() {
		return allPayments;
	}

	public void setPayments(List<Payment> allPayments) {
		if (allPayments != null) {
			this.allPayments = allPayments;
		} else {
			this.allPayments = new ArrayList<Payment>();
		}
	}

	public List<Offer> getOffers() {
		return allOffers;
	}

	public void setOffers(List<Offer> allOffers) {
		if (allOffers != null) {
			this.allOffers = allOffers;
		} else {
			this.allOffers = new ArrayList<Offer>();
		}
	}

	public Car retrieveCarFromLotByVin(String vin) {
		Car car = null;
		if (vin != null) {
			for (Car c : getLot().getCars()) {
				if (c.getVin().equals(vin)) {
					car = c;
					break;
				}
			}
		}
		return car;
	}

	public List<Car> retrieveCarsByUser(String username) {
		List<Car> carList = new ArrayList<Car>();
		for (Car car : getLot().getCars()) {
			if (car.getOwner() != null && car.getOwner().equals(username)) {
				carList.add(car);
			}
		}
		return carList;
	}

	public void addCarToLot(Car c) {
		getLot().getCars().add(c);
		lotSerializer.addCarToLot(c);
	}

	public boolean removeCarFromLot(Car c) {
		if (getLot().getCars().remove(c)) {
			removeOffersOfVin(c.getVin());
			lotSerializer.removeCar(c);
			return true;
		}

		return false;
	}

	public List<Car> retrieveUnownedCars() {
		List<Car> carList = new ArrayList<Car>();
		if (getLot().getCars() != null) {
			for (Car car : getLot().getCars()) {
				if (car.getOwner() == null) {
					carList.add(car);
				}
			}
		}
		return carList;
	}

	public boolean doesVinExist(String vin) {
		if (getLot().getCars() != null) {
			for (Car c : getLot().getCars()) {
				if (c.getVin().equals(vin)) {
					return true;
				}
			}
		}
		return false;
	}

	public void addPayment(Payment p) {
		getPayments().add(p);
		paymentSerializer.addPayment(p);
	}

	public double calculateMonthlyPayment(double totalPayment, int months) {
		return totalPayment / months;
	}

	public List<Payment> retrievePaymentsFromUsername(User u) {
		List<Payment> paymentList = new ArrayList<Payment>();
		for (Payment payment : getPayments()) {
			if (payment.getOwnerUsername().equals(u.getUsername())) {
				paymentList.add(payment);
			}
		}
		return paymentList;
	}

	public void makePayment(Payment p) {
		p.setRemainingPayment(p.getRemainingPayment() - p.getMonthlyPayment());
		p.setMonths(p.getMonths() - 1);
		paymentSerializer.updatePayment(p);
	}

	public void addOffer(Offer o) {
		getOffers().add(o);
		offerSerializer.addOffer(o);
	}

	public boolean removeOffer(Offer o) {
		if (getOffers().remove(o)) {
			return true;
		}

		return false;
	}

	public void removeOffersOfVin(String vin) {
		List<Offer> offerList = getOffers();
		for (int i = 0; i < offerList.size(); ++i) {
			if (offerList.get(i).getCarVin().equals(vin)) {
				removeOffer(offerList.get(i));
				--i;
			}
		}
	}

	public void acceptOffer(Offer o, int months) {
		if (getOffers().contains(o)) {
			Offer offer = getOffers().get(getOffers().indexOf(o));
			offer.setOfferStatus(OfferStatus.ACCEPTED);

			Car c = retrieveCarFromLotByVin(o.getCarVin());
			c.setOwner(o.getOwnerUsername());
			o.setCarVin(c.getVin());
			lotSerializer.updateCarOwner(c);
			offerSerializer.updateOffer(o);
			addPayment(new Payment(o.getMoneyAmount(), calculateMonthlyPayment(o.getMoneyAmount(), months),
					o.getMoneyAmount(), months, o.getOwnerUsername(), c.getVin()));

			rejectAllOffersOfVin(offer.getCarVin());
		}
	}

	public void rejectOffer(Offer o) {
		if (getOffers().contains(o)) {
			Offer offer = getOffers().get(getOffers().indexOf(o));
			offer.setOfferStatus(OfferStatus.REJECTED);
			offerSerializer.updateOffer(offer);
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

	public List<Offer> retrievePendingOffers() {
		List<Offer> offerList = new ArrayList<Offer>();
		for (Offer offer : getOffers()) {
			if (offer.getOfferStatus() == OfferStatus.PENDING) {
				offerList.add(offer);
			}
		}
		return offerList;
	}

	public List<Offer> retrieveOffersFromUser(User u) {
		List<Offer> offerList = new ArrayList<Offer>();
		for (Offer offer : getOffers()) {
			if (offer.getOwnerUsername().equals(u.getUsername())) {
				offerList.add(offer);
			}
		}
		return offerList;
	}

	public List<Offer> retrieveOffersFromVin(String vin) {
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
