package users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"FirstName","LastName","UserName","PassWord","StuNum","NationalNum","Gender","id","Role"})
public class User {
	
	public User() {
	}
	
	protected String FirstName;
	protected String LastName;
	protected String UserName;
	protected String PassWord;
	protected int StuNum;
	protected int NationalNum;
	protected String Gender;
	protected int id;
	protected int Role;
	

	// ***************************************************** Getters **********************************************************

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

	public int getStuNum() {
		return this.StuNum;
	}

	public int getNationalNum() {
		return this.NationalNum;
	}
	
	public int getRole() {
		return Role;
	}

	public int getId() {
		return this.id;
	}
	
	
//	******************************************************* Setters *************************************************************
	@JsonProperty("id")
	public void setId(int id) {
		
		this.id = id;
	}
	
	@JsonProperty("FirstName")
	public void setFirstName(String firstName) {
		
		this.FirstName = firstName;
		
	}
	@JsonProperty("LastName")
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
	
	@JsonProperty("StuNum")
	public void setStuNum(int stuNum) {
		
		this.StuNum = stuNum;
		
	}
	@JsonProperty("NationalNum")
	public void setNationalNum(int nationalNum) {
		
		this.NationalNum= nationalNum;
		
	}
	@JsonProperty("Gender")
	public void setGender(String gender) {
		
		this.Gender= gender;
		
	}
	
	@JsonProperty("Role")
	public void setRole(int role) {
		Role = role;
	}
	
}
