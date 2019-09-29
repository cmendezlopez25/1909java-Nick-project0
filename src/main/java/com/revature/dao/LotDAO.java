package com.revature.dao;

import com.revature.pojo.Car;
import com.revature.pojo.Lot;

public interface LotDAO {
	public void AddCarIntoLot(Car c);
	public boolean RemoveCarFromLot(Car c);
	public Lot ReadLotFile();
}
