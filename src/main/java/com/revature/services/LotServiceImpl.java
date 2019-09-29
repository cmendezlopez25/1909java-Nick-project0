package com.revature.services;

import static com.revature.util.LoggerUtil.log;

import com.revature.pojo.Car;

public class LotServiceImpl implements LotService {

	public LotServiceImpl() {
		log.trace("Creating LotServiceImpl object");
	}
	
	@Override
	public boolean addCarToLot(Car c) {
		log.trace("Entering addCarToLot");
		
		log.trace("Exiting addCarToLot");
		return false;
	}

	@Override
	public boolean removeCarFromLot(Car c) {
		log.trace("Entering removeCarFromLot");
		
		log.trace("Exiting removeCarFromLot");
		return false;
	}

}
