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

import com.revature.pojo.Offer;

public class OfferDAOSerialization implements OfferDAO {
	// singleton
	public static final OfferDAOSerialization offerSerializer = new OfferDAOSerialization();

	private OfferDAOSerialization() {
		log.trace("Creating OfferDAOSerialization object");
	}

	@Override
	public void CreateOfferFile(List<Offer> offerList, String filename) {
		log.trace("Entering CreateOfferFile");
		if (offerList == null || filename == null) {
			log.error("Offer list does not exist!");
			throw new NullPointerException();
		}

		String fileName;

		fileName = filename + ".ofr";

		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(offerList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.trace("Exiting CreateOfferFile");
	}

	@Override
	public List<Offer> ReadAllOffersFiles(String filename) {
		log.trace("Entering ReadAllOffersFiles");
		
		if (filename == null) {
			log.error("Filename is null!");
			throw new NullPointerException();
		}
		
		List<Offer> offerList = null;
		try (FileInputStream fis = new FileInputStream(filename + ".ofr");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			offerList = (List<Offer>)ois.readObject();
		} catch (FileNotFoundException e) {
			log.warn("File not found! Creating a new offer list");
			offerList = new ArrayList<Offer>();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		
		log.trace("Exiting ReadAllOffersFiles");
		return offerList;
	}

}
