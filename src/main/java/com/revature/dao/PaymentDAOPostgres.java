package com.revature.dao;

import static com.revature.util.LoggerUtil.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Payment;
import com.revature.util.ConnectionFactory;

public class PaymentDAOPostgres implements PaymentDAO {
	private Connection conn = ConnectionFactory.getConnection();
	private String schemaName = "project0";

	@Override
	public void CreatePaymentFile(List<Payment> paymentList, String filename) {
		// TODO
	}

	@Override
	public void addPayment(Payment payment) {
		log.trace("Entering PaymentDAOPostgres's addPayment");
		if (payment == null) {
			log.error("Payment is null!");
			throw new NullPointerException();
		}
		
		String sql = "insert into " + schemaName + ".payment(username, vin, monthlyprice, startingprice, remainingprice, months) values(?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, payment.getOwnerUsername());
			stmt.setString(2, payment.getCarVin());
			stmt.setDouble(3, payment.getMonthlyPayment());
			stmt.setDouble(4, payment.getStartingPayment());
			stmt.setDouble(5, payment.getRemainingPayment());
			stmt.setInt(6, payment.getMonths());
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.error("Could not add payment!");
			e.printStackTrace();
		}
		
		log.trace("Exiting PaymentDAOPostgres's addPayment");
	}

	@Override
	public List<Payment> ReadAllPaymentsFile(String filename) {
		log.trace("Entering PaymentDAOPostgres's readAllPaymentsFile");
		String sql = "select * from " + schemaName + ".payment";
		
		List<Payment> paymentList = new ArrayList<Payment>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs != null && rs.next()) {
				Payment payment = new Payment();
				payment.setOwnerUsername(rs.getString(1));
				payment.setCarVin(rs.getString(2));
				payment.setMonthlyPayment(rs.getDouble(3));
				payment.setStartingPayment(rs.getDouble(4));
				payment.setRemainingPayment(rs.getDouble(5));
				payment.setMonths(rs.getInt(6));
				paymentList.add(payment);
			}
		} catch (SQLException e) {
			log.error("Could not read all payments!");
			e.printStackTrace();
			paymentList = null;
		}
		
		log.trace("Exiting PaymentDAOPostgres's readAllPaymentFile");
		return paymentList;
	}

	@Override
	public void updatePayment(Payment payment) {
		log.trace("Entering PaymentDAOPostgres's updatePayment");
		if (payment == null) {
			log.error("Payment is null!");
			throw new NullPointerException();
		}
		
		String sql = "update " + schemaName + ".payment set remainingprice = ?, months = ? where vin = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, payment.getRemainingPayment());
			stmt.setInt(2, payment.getMonths());
			stmt.setString(3, payment.getCarVin());
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.error("Could not update payment!");
			e.printStackTrace();
		}
		
		log.trace("Exiting PaymentDAOPostgres's updatePayment");
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}
