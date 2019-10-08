package com.revature.dao;

import java.util.List;

import com.revature.pojo.Payment;

public interface PaymentDAO {
	public void CreatePaymentFile(List<Payment> paymentList, String filename);
	public void addPayment(Payment payment);
	public List<Payment> ReadAllPaymentsFile(String filename);
	public void updatePayment(Payment payment);
}
