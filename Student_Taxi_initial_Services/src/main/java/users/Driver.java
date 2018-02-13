package users;

import Trip.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Driver extends User {

	private Car Car;
	private List<Trip> Trips;
	private Driver_Rate Rate;

	// Constructor

	public Driver() {
		this.Role = 2;
	}

	public Driver(String FirstName, String LastName, String UserName, String PassWord, int StuNum, int NationalNum,
			String Gender, Car Car) {

		this.Role = 2;
		Rate = new Driver_Rate();
		this.EditInfo(FirstName, LastName, UserName, PassWord, StuNum, NationalNum, Gender, Car);
	}

	// Functions

	public void EditInfo(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender, Car Car) {

		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Gender = Gender;
		this.UserName = UserName;
		this.PassWord = PassWord;
		this.StuNum = StuNum;
		this.NationalNum = NationalNum;
		this.Car = Car;

	}
	
	public void addTrip(Trip trip) {
		this.Trips.add(trip);
	}

	public void CreateTrip() {
	}

	public void EditTrip() {
	}
	
	public void ChoosePassenger() {
	}

	public void CancelTrip(Trip Trip) {
		Trip = null;
	}

	public Car getCar() {
		return this.Car;
	}

	public List<Trip> getTrips() {
		return this.Trips;
	}

	public Driver_Rate getRate() {
		return Rate;
	}

	@JsonProperty("Rate")
	public void setRate(Driver_Rate rate) {
		Rate = rate;
	}

	@JsonProperty("Car")
	public void setCar(Car car) {
		Car = car;
	}

	@JsonProperty("Trips")
	public void setTrips(List<Trip> trips) {
		Trips = trips;
	}

	//

}
