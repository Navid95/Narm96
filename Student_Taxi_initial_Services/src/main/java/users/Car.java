package users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
	
	private int id;
	private String CarName;
	private int SitNum;
	
	public Car( String carName , int sitNum) {
		
		CarName = carName;
		
		SitNum = sitNum;
	}
	
	public Car() {}
	
	
	@JsonProperty("CarName")
	public void setCarName(String carName) {
		CarName = carName;
	}
	
	@JsonProperty("SitNum")
	public void setSitNum(int sitNum) {
		SitNum = sitNum;
	}
	
	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCarName() {
		return CarName;
	}
	
	public int getSitNum() {
		return SitNum;
	}
	
	public int getId() {
		return id;
	}
	
}
