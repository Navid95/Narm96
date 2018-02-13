package DAOs;

import Trip.Trip;
import users.Car;
import users.Driver;
import users.Rate;

public interface Driver_DAO {
	
	public int NewDriver();
	
	public boolean DeleteDriver(int driver_id);
	
	public Driver ShowDriver(int id);
	
	public Driver EditCar(String carName , int sitNum , int driver_id);
	
	public Driver EditRate(Rate rate , int driver_id);
	
	public Driver AddNewTrip(int Sits , double sitPrice , int driver_id);
	
	public boolean DeleteTripDriver(int driver_id , int trip_id);
	
	public Driver EditTripInfo(int Sits , double sitPrice , int trip_id , int driver_id);
	
	public Driver EditTripLocation(String origin, String destination , int trip_id , int driver_id);
	
	public Driver EditTripPayment(double Price , int trip_id , int driver_id);
	
	public Driver PayPayment(int trip_id , int driver_id);
	
	
}
