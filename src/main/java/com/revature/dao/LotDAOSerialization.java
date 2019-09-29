package com.revature.dao;

import static com.revature.util.LoggerUtil.log;

import com.revature.pojo.Car;
import com.revature.pojo.Lot;

public class LotDAOSerialization implements LotDAO {
	//singleton
	public static final LotDAOSerialization lotSerializer = new LotDAOSerialization();
	
	private LotDAOSerialization() {
		log.trace("Creating a LotDAOSerialization object");
	}

	@Override
	public void AddCarIntoLot(Car c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean RemoveCarFromLot(Car c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Lot ReadLotFile() {
		// TODO Auto-generated method stub
		return null;
	}

}
