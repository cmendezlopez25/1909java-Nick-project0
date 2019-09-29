package com.revature.services;

import com.revature.pojo.Car;

public interface LotService {
	public boolean addCarToLot(Car c);
	public boolean removeCarFromLot(Car c);
}
