package com.revature.dao;

import static com.revature.util.LoggerUtil.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Offer;
import com.revature.pojo.Offer.OfferStatus;
import com.revature.util.ConnectionFactory;

public class OfferDAOPostgres implements OfferDAO {
	private Connection conn = ConnectionFactory.getConnection();
	private String schemaName = "project0";

	@Override
	public void CreateOfferFile(List<Offer> offerList, String filename) {
		// TODO
	}
	
	@Override
	public void addOffer(Offer offer) {
		log.trace("Entering OfferDAOPostgres's addOffer");
		if (offer == null) {
			log.error("Offer is null!");
			throw new NullPointerException();
		}
		
		String sql = "insert into " + schemaName + ".offer(username, vin, price, offerstatus) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, offer.getOwnerUsername());
			stmt.setString(2, offer.getCarVin());
			stmt.setDouble(3, offer.getMoneyAmount());
			stmt.setString(4, offer.getOfferStatus().name());
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.error("Could not add offer!");
			e.printStackTrace();
		}
		
		log.trace("Exiting OfferDAOPostgres's addOffer");
	}

	@Override
	public List<Offer> ReadAllOffersFiles(String filename) {
		log.trace("Entering OfferDAOPostgres's readAllOffersFiles");
		List<Offer> offerList = null;
		
		String sql = "select * from " + schemaName + ".offer";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				if (offerList == null) {
					offerList = new ArrayList<Offer>();
				}
				Offer offer = new Offer();
				offer.setOwnerUsername(rs.getString(1));
				offer.setCarVin(rs.getString(2));
				offer.setMoneyAmount(rs.getDouble(3));
				offer.setOfferStatus(OfferStatus.valueOf(rs.getString(4)));
				offerList.add(offer);
			}
		} catch (SQLException e) {
			log.error("Could not find offers!");
			e.printStackTrace();
		}

		log.trace("Exiting OfferDAOPostgres's readAllOffersFiles");
		return offerList;
	}
	
	@Override
	public void updateOffer(Offer offer) {
		log.trace("Entering OfferDAOPostgres's updateOffer");
		if (offer == null) {
			log.error("Offer is null!");
			throw new NullPointerException();
		}
		
		String sql = "update " + schemaName + ".offer set offerstatus = ? where vin = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, offer.getOfferStatus().name());
			stmt.setString(2, offer.getCarVin());
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.error("Could not update offers!");
			e.printStackTrace();
		}
		
		log.trace("Exiting OfferDAOPostgres's updateOffer");
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}
