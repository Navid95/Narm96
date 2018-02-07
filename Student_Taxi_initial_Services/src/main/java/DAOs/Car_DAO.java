package DAOs;

import users.Car;

public interface Car_DAO {
	
	public Car CreateNew(Car car);

	public Car CreateNew(String carName, int sitNum);
	
	public Car Show(int id);
	
	public Car EditInfo(String CarName,int SitNum, int id);
	
	public boolean Delete(int id);
	
	

}
