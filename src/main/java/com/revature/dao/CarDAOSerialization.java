package com.revature.dao;

import com.revature.pojo.Car;
import static com.revature.util.LoggerUtil.log;

public class CarDAOSerialization implements CarDAO {
	//singleton
	private static final CarDAOSerialization carSerializer = new CarDAOSerialization();
	
	private CarDAOSerialization() {
		log.trace("Creating CarDAOSerialization object");
	}

	@Override
	public void CreateCarFile(Car c) {
		// TODO Auto-generated method stub

	}

	@Override
	public Car ReadCarFile(String vin) {
		// TODO Auto-generated method stub
		return null;
	}

}
