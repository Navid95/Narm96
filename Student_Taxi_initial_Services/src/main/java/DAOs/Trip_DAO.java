package DAOs;

import java.util.ArrayList;
import java.util.List;

import Trip.Trip;

public interface Trip_DAO {
	
	public Trip NewTrip(int Sits , double sitPrice , int driver_id);
	
	public Trip ShowTrip(int trip_id);
	
	public List<Trip> GetDriverTrips(int driver_id);
//	
	public boolean DeleteTrip(int trip_id);
//	
	public Trip EditTripInfo(int Sits , double sitPrice , int trip_id );
//	
	public Trip EditTripLocation(String origin, String destination , int trip_id);
//	
	public Trip EditTripPayment(double Price , int trip_id);
//	
	public Trip PayPayment(int trip_id);
	
	public List<Trip> SearchReqableTrips();
//	

	
	
}
