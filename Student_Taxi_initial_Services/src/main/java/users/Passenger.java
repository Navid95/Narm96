package users;

public class Passenger extends User {

	// Constructor
	public Passenger(String FirstName, String LastName, String UserName, String PassWord, int StuNum, int NationalNum,
			String Gender) {
		this.Role = 1;
		this.EditInfo(FirstName, LastName, UserName, PassWord, StuNum, NationalNum, Gender);
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
		return this.StuNum;
	}

	public int getNationalNum() {
		return this.NationalNum;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
