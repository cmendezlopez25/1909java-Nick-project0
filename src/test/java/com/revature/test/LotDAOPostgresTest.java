package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import org.postgresql.core.BaseStatement;

import com.revature.dao.LotDAOPostgres;
import com.revature.util.ConnectionFactory;
import com.revature.pojo.Car;

@RunWith(MockitoJUnitRunner.class)
public class LotDAOPostgresTest {
	private LotDAOPostgres lotDao = new LotDAOPostgres();
	private Car car;
	private String sql;
	private Statement regularStatement;
	
	@Mock
	Connection conn;
	
	@Mock
	PreparedStatement stmt;
	
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
		//stmt = ConnectionFactory.getConnection().prepareStatement(sql);
		car = new Car();
		car.setVin("12345678912345680");
		car.setBasePrice(50000.00);
		car.setModel("Toyota");
		car.setYear(1994);
		car.setOwner("person");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addCarPostgres() {
		sql = "insert into lot(vin, username, model, year, baseprice) values(?, ?, ?, ?, ?)";
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt);
			lotDao.setConnection(conn);
			lotDao.setSchemaname("test");
			
			lotDao.addCarToLot(car);
			Mockito.verify(stmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readAllCarsPostgres() {
		sql = "select * from lot";
		try {
			lotDao.setSchemaname("test");
			when(conn.createStatement()).thenReturn(stmt);
			lotDao.setConnection(conn);
			lotDao.ReadLotFile("lot");
			Mockito.verify(stmt).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateCarsPostgres() {
		sql = "update lot set username = ? where vin = ?";
		try {
			lotDao.setSchemaname("test");
			when(conn.prepareStatement(sql)).thenReturn(stmt);
			lotDao.setConnection(conn);
			lotDao.updateCarOwner(car);
			Mockito.verify(stmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void removeCarPostgres() {
		sql = "delete from lot where vin = ?";

		try {
			lotDao.setSchemaname("test");
			when(conn.prepareStatement(sql)).thenReturn(stmt);
			lotDao.setConnection(conn);
			lotDao.removeCar(car);
			Mockito.verify(stmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
