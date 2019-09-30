package com.revature.dao;

import static com.revature.util.LoggerUtil.log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Car;
import com.revature.pojo.Payment;

public class PaymentDAOSerialization implements PaymentDAO {
	// singleton
	public static final PaymentDAOSerialization paymentSerializer = new PaymentDAOSerialization();

	private PaymentDAOSerialization() {
		log.trace("Creating PaymentDAOSerialization");
	}

	@Override
	public void CreatePaymentFile(List<Payment> paymentList, String filename) {
		if (paymentList == null || filename == null) {
			log.error("Payment list does not exist!");
			throw new NullPointerException();
		}

		String fileName;

		fileName = filename + ".pay";

		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(paymentList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Payment> ReadAllPaymentsFile(String filename) {
		if (filename == null) {
			log.error("Filename is null!");
			throw new NullPointerException();
		}
		
		List<Payment> paymentList = null;
		try (FileInputStream fis = new FileInputStream(filename + ".pay");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			paymentList = (List<Payment>)ois.readObject();
		} catch (FileNotFoundException e) {
			log.warn("File not found! Creating a new payment list");
			paymentList = new ArrayList<Payment>();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return paymentList;
	}
}
