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

import com.revature.dao.OfferDAO;
import com.revature.dao.OfferDAOPostgres;
import com.revature.pojo.Offer;
import com.revature.pojo.Offer.OfferStatus;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class OfferDAOPostgresTest {
	private OfferDAOPostgres offerDAO;
	private Offer offer;

	@Mock
	private Connection conn;

	@Spy
	private PreparedStatement addStmt = ConnectionFactory.getConnection()
			.prepareStatement("insert into test.offer(username, vin, price, offerstatus) values (?, ?, ?, ?)");

	@Spy
	private PreparedStatement readStmt = ConnectionFactory.getConnection().prepareStatement("select * from test.offer");

	@Spy
	private PreparedStatement updateStmt;

	{
		Connection myConn = ConnectionFactory.getConnection();
		myConn.setSchema("test");
		updateStmt = myConn.prepareStatement("update test.offer set offerstatus = ? where vin = ?");
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		offerDAO = new OfferDAOPostgres();

		offer = new Offer();
		offer.setCarVin("12345678912345678");
		offer.setOwnerUsername("human");
		offer.setMoneyAmount(50000.00);
		offer.setOfferStatus(OfferStatus.PENDING);

		offerDAO.setSchemaName("test");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void addOfferNull() {
		offerDAO.addOffer(null);
	}

	@Test
	public void addOfferPostgres() {
		try {
			when(conn.prepareStatement("insert into test.offer(username, vin, price, offerstatus) values (?, ?, ?, ?)"))
					.thenReturn(addStmt);
			offerDAO.setConnection(conn);

			offerDAO.addOffer(offer);
			Mockito.verify(addStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readOffersPostgres() {
		try {
			when(conn.prepareStatement("select * from test.offer")).thenReturn(readStmt);
			offerDAO.setConnection(conn);

			offerDAO.ReadAllOffersFiles("offer");
			Mockito.verify(readStmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = NullPointerException.class)
	public void updateOfferNull() {
		offerDAO.updateOffer(null);
	}

	@Test
	public void updateOfferPostgres() {
		try {
			when(conn.prepareStatement("update test.offer set offerstatus = ? where vin = ?")).thenReturn(updateStmt);
			offerDAO.setConnection(conn);

			offerDAO.updateOffer(offer);
			Mockito.verify(updateStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public OfferDAOPostgresTest() throws SQLException {
		super();
	}
}
