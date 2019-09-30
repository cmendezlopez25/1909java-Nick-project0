package com.revature.dao;

import com.revature.pojo.Car;
import com.revature.pojo.User;

import static com.revature.util.LoggerUtil.log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CarDAOSerialization implements CarDAO {
	// singleton
	public static final CarDAOSerialization carSerializer = new CarDAOSerialization();

	private CarDAOSerialization() {
		log.trace("Creating CarDAOSerialization object");
	}

	@Override
	public void CreateCarFile(Car c) {
		if (c == null) {
			log.error("Car does not exist!");
			throw new NullPointerException();
		}

		String fileName;

		fileName = c.getVin() + ".car";

		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(c);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Car ReadCarFile(String vin) {
		if (vin == null) {
			log.error("Car does not exist!");
			throw new NullPointerException();
		}

		Car c = null;
		try (FileInputStream fis = new FileInputStream(vin + ".car");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			c = (Car) ois.readObject();
		} catch (FileNotFoundException e) {
			log.warn("File not found! Setting car to null.");
			c = null;
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return c;
	}

}
