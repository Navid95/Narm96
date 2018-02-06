package com.Student_Taxi_initial_Services;

import javax.ws.rs.PathParam;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO_Implementation.Passenger_DAO_Implementation;
import DAOs.Passenger_DAO;
import users.Passenger;
import users.User;

@Path("Passenger")
public class PassengerServices {
	
	public Passenger_DAO passengerDAO ;
	public String Error; 
	
	public PassengerServices() {
		try {
			passengerDAO= new Passenger_DAO_Implementation();
		} catch (ClassNotFoundException | SQLException e) {
			Error = "Could not connect to DataBase";
			e.printStackTrace();
		}
	}

	// *********************************************** Services *******************************************************

	@GET
	@Path("NewDriver%21{FirstName}%2C{LastName}%2C{Gender}%2C{UserName}%2C{PassWord}%2C{StuNum}%2C{NationalNum}")
	@Produces(MediaType.APPLICATION_JSON)
	public String CreateNew(@PathParam("FirstName") String FirstName, @PathParam("LastName") String LastName,
			@PathParam("Gender") String Gender, @PathParam("UserName") String UserName,
			@PathParam("PassWord") String PassWord, @PathParam("StuNum") String StuNum,
			@PathParam("NationalNum") String NationalNum) {
		
		
		Passenger passenger;
		
		System.out.println("Passenger created...\nSaving to Data Base");
		
		passenger = passengerDAO.CreateNew(FirstName, LastName, UserName, PassWord, Integer.parseInt(StuNum),
				Integer.parseInt(NationalNum), Gender);
		
//		System.out.println("Retrieved ID : " + ((Passenger) user1).getFirstName());
		
		ObjectMapper mapper = new ObjectMapper();
//		mapper.enableDefaultTyping();
		
		String JsonData = null;
		try {
			JsonData = mapper.writeValueAsString(passenger);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return JsonData;
	}
	
//	********************************************************************************************************
	@GET
	@Path("show{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String Show(@PathParam("id") String id) {
		
		int user_id = Integer.parseInt(id);
		
		Passenger passenger;
		
		passenger = passengerDAO.Show(user_id);
		
		return passenger.getPassWord();
	}

}
