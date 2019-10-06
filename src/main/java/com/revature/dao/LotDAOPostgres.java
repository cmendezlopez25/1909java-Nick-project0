package com.revature.dao;

import static com.revature.util.LoggerUtil.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pojo.Car;
import com.revature.pojo.Lot;
import com.revature.util.ConnectionFactory;

public class LotDAOPostgres implements LotDAO {
	
	private Connection conn = ConnectionFactory.getConnection();
	
	private String schemaName = "project0";
	
	@Override
	public void CreateLotFile(Lot lot, String filename) {
		// TODO
	}
	
	@Override
	public void addCarToLot(Car car) {
		log.trace("Entering LotDAOPostgres's addCarToLot");
		String sql = "insert into lot(vin, username, model, year, baseprice) values(?, ?, ?, ?, ?)";
		
		try {
			conn.setSchema(schemaName);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getVin());
			stmt.setString(2, car.getOwner());
			stmt.setString(3, car.getModel());
			stmt.setInt(4, car.getYear());
			stmt.setDouble(5, car.getBasePrice());
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.error("Could not add car!");
			e.printStackTrace();
		}
		log.trace("Exiting LotDAOPostgres's addCarToLot");
	}

	@Override
	public Lot ReadLotFile(String filename) {
		log.trace("Entering LotDAOPostgres's readLotFile");
		String sql = "select * from lot";
		
		Lot lot = new Lot();
		
		try {
			conn.setSchema(schemaName);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs != null && rs.next()) {
				Car car = new Car();
				car.setVin(rs.getString(1));
				car.setOwner(rs.getString(2));
				car.setModel(rs.getString(3));
				car.setYear(rs.getInt(4));
				car.setBasePrice(rs.getDouble(5));
				lot.setCars(new ArrayList<Car>());
				lot.getCars().add(car);
			}
		} catch(SQLException e) {
			log.error("Couldn't read lot database!");
			e.printStackTrace();
		}
		
		if (lot.getCars() == null) {
			lot.setCars(new ArrayList<Car>());
		}
		
		log.trace("Exiting LotDAOPostgres's readLotFile");
		return lot;
	}

	@Override
	public void updateCarOwner(Car c) {
		log.trace("Entering LotDAOPostgres's updateCarOwner");
		String sql = "update lot set username = ? where vin = ?";
		
		try {
			conn.setSchema(schemaName);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getOwner());
			stmt.setString(2, c.getVin());
			stmt.executeUpdate();
		} catch(SQLException e) {
			log.error("Couldn't update lot database!");
			e.printStackTrace();
		}
		
		log.trace("Exiting LotDAOPostgres's updateCarOwner");
	}

	@Override
	public void removeCar(Car c) {
		log.trace("Entering LotDAOPostgres's removeCar");
		String sql = "delete from lot where vin = ?";
		
		try {
			conn.setSchema(schemaName);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getVin());
			stmt.executeUpdate();
		} catch(SQLException e) {
			log.error("Couldn't remove from lot database!");
			e.printStackTrace();
		}
		
		log.trace("Exiting LotDAOPostgres's removeCar");
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public void setSchemaname(String schemaName) {
		this.schemaName = schemaName;
	}
}
