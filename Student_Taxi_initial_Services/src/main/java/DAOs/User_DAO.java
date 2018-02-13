package DAOs;

import java.util.List;

import Trip.Trip;
import users.Driver;
import users.Passenger;
import users.Rate;
import users.User;

public interface User_DAO {
	
	public Passenger NewPassenger(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender);
	
	public Passenger ShowPassenger(int id);
	
	public boolean DeletePassenger(int id);
	
	public Passenger EditPassengerInfo(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender , int id);
	
	public Driver NewDriver(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender);
	
	public Driver ShowDriver(int driver_id);
	
	public boolean DeleteDriver(int id);
	
	public Driver EditDriverInfo(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender , int driver_id);
	
	public Driver EditDriverCar(String carName, int sitNum, int driver_id);

	public Driver EditDriverRate(Rate rate, int driver_id);
	
	public Driver AddNewTripDriver(int Sits , double sitPrice , int driver_id);
	
	public Driver DeleteTripDriver(int driver_id , int trip_id);
	
	public Driver EditTripInfoDriver(int Sits , double sitPrice , int trip_id , int driver_id);
	
	public Driver EditTripLocationDriver(String origin, String destination , int trip_id , int driver_id);
	
	public Driver EditTripPaymentDriver(double Price , int trip_id , int driver_id);
	
	public Driver PayPaymentDriver(int trip_id , int driver_id);
	
	public List<Trip> SearchTrip(String origin, String destination);
	
	public User Login(String userName , String passWord);
	
	public Passenger LoginPassenger(int user_id);
	
	public Driver LoginDriver(int user_id);
	
//	public boolean CheckUserName(String userName);
	

	
}
