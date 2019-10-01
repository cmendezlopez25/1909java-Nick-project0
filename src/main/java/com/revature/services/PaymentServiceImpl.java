package com.revature.services;

import static com.revature.util.LoggerUtil.log;

import java.util.List;

import com.revature.pojo.Payment;
import com.revature.pojo.System;
import com.revature.pojo.User;

public class PaymentServiceImpl implements PaymentService {
	private System menuSystem = System.menuSystem;

	@Override
	public void addPaymentToFile(Payment payment) {
		log.trace("Entering addPaymentToFile");
		if (payment == null) {
			log.error("Payment is null!");
			throw new NullPointerException();
		}
		menuSystem.addPayment(payment);
		
		log.trace("Exiting addPaymentToFile");
	}

	@Override
	public List<Payment> retrievePaymentsByUsername(User u) {
		log.trace("Entering retrievePaymentsByUsername");
		if (u == null) {
			log.error("User does not exist!");
			throw new NullPointerException();
		}
		
		List<Payment> paymentList = menuSystem.retrievePaymentsFromUsername(u);
		
		log.trace("Exiting retrievePaymentsByUsername");
		return paymentList;
	}

	public void setMenu(System menuSystem) {
		this.menuSystem = menuSystem;
	}
}
