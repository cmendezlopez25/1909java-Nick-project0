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
import com.revature.pojo.Lot;
import com.revature.pojo.Payment;

public class LotDAOSerialization implements LotDAO {
	//singleton
	public static final LotDAOSerialization lotSerializer = new LotDAOSerialization();
	
	private LotDAOSerialization() {
		log.trace("Creating a LotDAOSerialization object");
	}

	@Override
	public void CreateLotFile(Lot lot, String filename) {
		log.trace("Entering CreateLotFile");
		if (lot == null || filename == null) {
			log.error("Lot does not exist!");
			throw new NullPointerException();
		}

		filename = filename + ".lot";

		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(lot);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.trace("Exiting CreateLotFile");
	}

	@Override
	public Lot ReadLotFile(String filename) {
		if (filename == null) {
			log.error("Filename is null!");
			throw new NullPointerException();
		}
		
		Lot lot = null;
		try (FileInputStream fis = new FileInputStream(filename + ".lot");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			lot = (Lot)ois.readObject();
		} catch (FileNotFoundException e) {
			log.warn("File not found! Creating a new lot.");
			lot = new Lot();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return lot;
	}

}
