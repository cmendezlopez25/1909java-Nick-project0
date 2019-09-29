package com.revature.services;

import com.revature.pojo.Car;

public interface LotService {
	public void addCarToLot(Car c);
	public boolean removeCarFromLot(Car c);
}
