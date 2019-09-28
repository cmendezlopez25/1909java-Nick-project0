package com.revature.dao;

import com.revature.pojo.Car;

public interface CarDAO {
	public void CreateCarFile(Car c);
	public Car ReadCarFile(String vin);
}
