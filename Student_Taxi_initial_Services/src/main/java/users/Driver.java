package users;

import Trip.*;
import java.util.List;

public class Driver extends User {

	private String Car;
	private List<Trip> Trip;
	private Rate Rate;

	// Constructor
	public Driver(String FirstName, String LastName, String UserName, String PassWord, int StuNum, int NationalNum,
			String Gender, String Car) {

		this.Role = 2;
		this.EditInfo(FirstName, LastName, UserName, PassWord, StuNum, NationalNum, Gender, Car);
	}

	// Functions

	public void EditInfo(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender, String Car) {

		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Gender = Gender;
		this.UserName = UserName;
		this.PassWord = PassWord;
		this.StuNumber = StuNum;
		this.NationalNum = NationalNum;
		this.Car = Car;

	}

	public void CreateTrip() {
	}

	public void EditTrip() {

	}

	public void CancelTrip(Trip Trip) {
		Trip = null;
	}

	public void ChoosePassenger() {
	}

	// Getters

	public String getFirstName() {
		return this.FirstName;
	}

	public String getLastName() {
		return this.LastName;
	}

	public String getUserName() {
		return this.UserName;
	}

	public String getPassWord() {
		return this.PassWord;
	}

	public String getGender() {
		return this.Gender;
	}

	public int getStuNUm() {
		return this.StuNumber;
	}

	public int getNationalNum() {
		return this.NationalNum;
	}

	public int getId() {
		return this.id;
	}

	public String getCar() {
		return this.Car;
	}

	public List<Trip> getTrips() {
		return this.Trip;
	}

	public Rate getRate() {
		return this.Rate;
	}

}
