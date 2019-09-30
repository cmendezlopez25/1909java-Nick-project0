package com.revature.services;

import java.util.List;

import com.revature.pojo.Payment;

public interface PaymentService {
	public void addPaymentToFile(Payment payment);
	public List<Payment> retrievePaymentsByUsername(String ownerUsername);
}
