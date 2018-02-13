package users;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import Trip.Trip;

public class Passenger extends User {
	
	private List<Trip> Trips;
	
	// Constructor
	public Passenger(String FirstName, String LastName, String UserName, String PassWord, int StuNum, int NationalNum,
			String Gender) {
		
		this.Role = 1;
		
		this.EditInfo(FirstName, LastName, UserName, PassWord, StuNum, NationalNum, Gender);
		
	}
	
	public Passenger() {
		this.Role = 1;	
	}

	// Functions

	public void EditInfo(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender) {

		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Gender = Gender;
		this.UserName = UserName;
		this.PassWord = PassWord;
		this.StuNum = StuNum;
		this.NationalNum = NationalNum;
		
	}
	
	public void addTrip(Trip trip) {
		this.Trips.add(trip);
	}

	public void SearchTrip() {
	}

	public void ChooseTrip() {
	}

	public void CancelTrip() {
	}

	public void RateTrip() {
	}
	
	public List<Trip> getTrips() {
		return this.Trips;
	}
	
	@JsonProperty("Trips")
	public void setTrips(List<Trip> trips) {
		Trips = trips;
	}
	
}
