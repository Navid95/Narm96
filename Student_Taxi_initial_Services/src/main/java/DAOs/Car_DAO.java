package DAOs;

import users.Car;

public interface Car_DAO {
	
	public Car CreateNew(Car car);

	public Car CreateNew(String carName, int sitNum);
	
	

}
