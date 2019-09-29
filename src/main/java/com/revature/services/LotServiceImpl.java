package com.revature.services;

import static com.revature.util.LoggerUtil.log;
import static com.revature.util.SystemUtil.sysout;

import com.revature.dao.LotDAOSerialization;
import com.revature.pojo.Car;
import com.revature.pojo.Lot;

public class LotServiceImpl implements LotService {
	private LotDAOSerialization lotSerializer = LotDAOSerialization.lotSerializer;

	public LotServiceImpl() {
		log.trace("Creating LotServiceImpl object");
	}
	
	@Override
	public void addCarToLot(Car c) {
		log.trace("Entering addCarToLot");
		if (c == null) {
			log.error("Car doesn't exist!");
			throw new NullPointerException();
		}
		
		lotSerializer.AddCarIntoLot(c);
		
		log.trace("Exiting addCarToLot");
	}

	@Override
	public boolean removeCarFromLot(Car c) {
		log.trace("Entering removeCarFromLot");
		if (c == null) {
			log.error("Car doesn't exist!");
		}
		
		if (lotSerializer.RemoveCarFromLot(c)) {
			sysout.println("Removed " + c.getVin() + " from Lot");
			log.trace("Exiting removeCarFromLot");
			return true;
		}
		
		sysout.println("Car is not in lot.");
		log.trace("Exiting removeCarFromLot");
		return false;
	}

	public void setLotDAOSerializer(LotDAOSerialization lotSerializer) {
		this.lotSerializer = lotSerializer;
	}
}
