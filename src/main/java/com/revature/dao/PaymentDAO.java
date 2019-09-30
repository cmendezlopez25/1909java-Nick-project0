package com.revature.dao;

import java.util.List;

import com.revature.pojo.Payment;

public interface PaymentDAO {
	public void CreatePaymentFile(List<Payment> paymentList, String filename);
	public List<Payment> ReadAllPaymentsFile(String filename);
}
