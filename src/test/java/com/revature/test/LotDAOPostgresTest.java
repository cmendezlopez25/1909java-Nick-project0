package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import com.revature.dao.LotDAOPostgres;
import com.revature.pojo.Car;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class LotDAOPostgresTest {
	private LotDAOPostgres lotDao = new LotDAOPostgres();
	private Car car;
	private String sql;

	@Mock
	Connection conn;

	@Spy
	PreparedStatement insertStmt = ConnectionFactory.getConnection()
			.prepareStatement("insert into test.lot(vin, username, model, year, baseprice) values(?, ?, ?, ?, ?)");

	@Spy
	PreparedStatement selectStmt = ConnectionFactory.getConnection().prepareStatement("select * from test.lot");

	@Spy
	PreparedStatement updateStmt = ConnectionFactory.getConnection()
			.prepareStatement("update test.lot set username = ? where vin = ?");

	@Spy
	PreparedStatement deleteStmt = ConnectionFactory.getConnection()
			.prepareStatement("delete from test.lot where vin = ?");

	@Mock
	ResultSet rs;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		car = new Car();
		car.setVin("12345678912345680");
		car.setBasePrice(50000.00);
		car.setModel("Toyota");
		car.setYear(1994);
		car.setOwner("human");

		lotDao.setSchemaname("test");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addCarPostgres() {
		sql = "insert into test.lot(vin, username, model, year, baseprice) values(?, ?, ?, ?, ?)";
		try {
			when(conn.prepareStatement(sql)).thenReturn(insertStmt);
			lotDao.setConnection(conn);

			lotDao.addCarToLot(car);
			Mockito.verify(insertStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readAllCarsPostgres() {
		sql = "select * from test.lot";
		try {
			when(conn.prepareStatement(sql)).thenReturn(selectStmt);
			lotDao.setConnection(conn);
			lotDao.ReadLotFile("lot");
			Mockito.verify(selectStmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateCarsPostgres() {
		sql = "update test.lot set username = ? where vin = ?";
		try {
			when(conn.prepareStatement(sql)).thenReturn(updateStmt);
			lotDao.setConnection(conn);
			lotDao.updateCarOwner(car);
			Mockito.verify(updateStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void removeCarPostgres() {
		sql = "delete from test.lot where vin = ?";

		try {
			when(conn.prepareStatement(sql)).thenReturn(deleteStmt);
			lotDao.setConnection(conn);
			lotDao.removeCar(car);
			Mockito.verify(deleteStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public LotDAOPostgresTest() throws SQLException {

	}
}
