package users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Passenger extends User {

	// Constructor
	public Passenger(String FirstName, String LastName, String UserName, String PassWord, int StuNum, int NationalNum,
			String Gender) {
		
		this.Role = 1;
		
		this.EditInfo(FirstName, LastName, UserName, PassWord, StuNum, NationalNum, Gender);
		
	}
	
	public Passenger() {
		// TODO Auto-generated constructor stub
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

	public void SearchTrip() {
	}

	public void ChooseTrip() {
	}

	public void CancelTrip() {
	}

	public void RateTrip() {
	}

	

}
