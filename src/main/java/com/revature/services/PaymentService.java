package com.revature.services;

import java.util.List;

import com.revature.pojo.Payment;
import com.revature.pojo.User;

public interface PaymentService {
	public void addPaymentToFile(Payment payment);
	public List<Payment> retrievePaymentsByUsername(User u);
}
