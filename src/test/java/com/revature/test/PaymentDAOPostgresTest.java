package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.dao.PaymentDAOPostgres;
import com.revature.pojo.Payment;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class PaymentDAOPostgresTest {
	private PaymentDAOPostgres paymentDAO;
	private Payment payment;
	
	@Mock
	private Connection conn;
	
	@Spy
	private PreparedStatement addStmt = ConnectionFactory.getConnection().prepareStatement("insert into test.payment(username, vin, monthlyprice, startingprice, remainingprice, months) values(?, ?, ?, ?, ?, ?)");
	
	@Spy
	private PreparedStatement readStmt = ConnectionFactory.getConnection().prepareStatement("select * from test.payment");
	
	@Spy
	private PreparedStatement updateStmt = ConnectionFactory.getConnection().prepareStatement("update test.payment set remainingprice = ?, months = ? where vin = ?");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		paymentDAO = new PaymentDAOPostgres();
		
		payment = new Payment();
		payment.setCarVin("12345678912345678");
		payment.setOwnerUsername("human");
		payment.setMonths(24);
		payment.setStartingPayment(50000.0);
		payment.setRemainingPayment(50000.0);
		payment.setMonthlyPayment(50000.0 / 24);
		
		paymentDAO.setSchemaName("test");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void addPaymentNull() {
		paymentDAO.addPayment(null);
	}
	
	@Test
	public void addPaymentPostgres() {
		try {
			when(conn.prepareStatement("insert into test.payment(username, vin, monthlyprice, startingprice, remainingprice, months) values(?, ?, ?, ?, ?, ?)")).thenReturn(addStmt);
			paymentDAO.setConnection(conn);
			
			paymentDAO.addPayment(payment);
			Mockito.verify(addStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void readPaymentsPostgres() {
		try {
			when(conn.prepareStatement("select * from test.payment")).thenReturn(readStmt);
			paymentDAO.setConnection(conn);
			
			paymentDAO.ReadAllPaymentsFile("payment");
			Mockito.verify(readStmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Test(expected = NullPointerException.class)
	public void updatePaymentNull() {
		paymentDAO.updatePayment(null);
	}
	
	@Test
	public void updatePaymentPostgres() {
		try {
			when(conn.prepareStatement("update test.payment set remainingprice = ?, months = ? where vin = ?")).thenReturn(updateStmt);
			paymentDAO.setConnection(conn);
			
			paymentDAO.updatePayment(payment);
			Mockito.verify(updateStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PaymentDAOPostgresTest() throws SQLException {
		
	}
}
