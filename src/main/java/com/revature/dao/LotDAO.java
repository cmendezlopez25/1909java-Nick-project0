package com.revature.dao;

import com.revature.pojo.Car;
import com.revature.pojo.Lot;

public interface LotDAO {
	public void CreateLotFile(Lot lot, String filename);
	public Lot ReadLotFile(String filename);
}
