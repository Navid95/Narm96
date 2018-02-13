package DAOs;

import java.util.List;

import Trip.Trip;
import users.Passenger;

public interface Passenger_DAO {

	public int NewPassenger();
	
	public boolean DeletePassenger(int id);
	
	public Passenger Show(int id);
	
	public List<Passenger> showAll(int offset , int rowNum);
	
	public List<Trip> SearchTrip(String origin, String destination);
	
//	public Passenger ReqTrip(int trip_id , int passenger_id);
	
//	public Passenger UnReqTrip(int trip_id , int passenger_id);

}
