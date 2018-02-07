package Trip;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	private int id;
	private String Origin;
	private String Destination;
	

//	******************************** Constructors ********************************************************
	
	public Location() {}
	
	public Location(String origin , String destination) {
		
		this.Origin = origin;
		
		this.Destination = destination;
		
	}

//	******************************************** Setters *******************************************
	
	@JsonProperty("Origin")
	public void setOrigin(String origin) {
		
		Origin = origin;
	}
	
	@JsonProperty("Destination")
	public void setDestination(String destination) {
		
		Destination = destination;
		
	}
	
//	******************************************** Getters *******************************************

	public String getOrigin() {
		
		return Origin;
		
	}
	
	public String getDestination() {
		
		return Destination;
		
	}	

	
	
	

}
