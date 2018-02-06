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
		this.StuNumber = StuNum;
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

	// ************************************************** Getters ***************************************************

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
	
//	********************************************* Setters *******************************************************
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	@JsonProperty("FirstName")
	public void setFirstName(String firstName) {
		
		this.FirstName = firstName;
		
	}
	@JsonProperty("llllll")
	public void setLastName(String lastName) {
		
		this.LastName = lastName;
		
	}
	
	@JsonProperty("UserName")
	public void setUserName(String userName) {
		
		this.UserName = userName;
		
	}
	
	@JsonProperty("PassWord")
	public void setPassWord(String passWord) {
		
		this.PassWord = passWord;
		
	}
	
	@JsonProperty("sssssssssss")
	public void setStuNum(int stuNum) {
		
		this.StuNumber = stuNum;
		
	}
	@JsonProperty("NationalNum")
	public void setNationalNum(int nationalNum) {
		
		this.NationalNum= nationalNum;
		
	}
	@JsonProperty("Gender")
	public void setGender(String gender) {
		
		this.Gender= gender;
		
	}
}
