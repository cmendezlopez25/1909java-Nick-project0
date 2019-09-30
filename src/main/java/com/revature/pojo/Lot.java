package com.revature.pojo;

import java.util.List;
import java.io.Serializable;

public class Lot implements Serializable {
	private List<Car> cars;

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	public Lot() {
		super();
	}

	public Lot(List<Car> cars) {
		super();
		this.cars = cars;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cars == null) ? 0 : cars.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lot other = (Lot) obj;
		if (cars == null) {
			if (other.cars != null)
				return false;
		} else if (!cars.equals(other.cars))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lot [cars=" + cars + "]";
	}
}
