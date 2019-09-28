package com.revature.dao;

import com.revature.pojo.Customer;
import com.revature.pojo.Payment;

public interface PaymentDAO {
	public void CreatePayment(Payment p);
	public Payment ReadPaymentFile(Customer owner);
}
