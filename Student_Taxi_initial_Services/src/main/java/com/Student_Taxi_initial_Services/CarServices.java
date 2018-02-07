package com.Student_Taxi_initial_Services;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO_Implementation.Car_DAO_Implementation;
import DAOs.Car_DAO;
import users.Car;
import users.Passenger;

@Path("Car")
public class CarServices {
	
	Car_DAO carDAO;
	String Error;
	
	public CarServices() {
		
		try {
			
			carDAO = new Car_DAO_Implementation();
			
		} catch (ClassNotFoundException | SQLException e) {
			Error = "error";
			e.printStackTrace();
		}
	}
	
	// *********************************************** Services *******************************************************
	
	@GET
	@Path("NewCar%21{CarName}%2C{SitNum}")
	@Produces(MediaType.APPLICATION_JSON)
	public String CreateNew(@PathParam("CarName") String CarName, @PathParam("SitNum") int SitNum) {
		
		
		Car car;
		
		car = carDAO.CreateNew(CarName, SitNum);
		
		if(car == null) {
			
			return Error;
		}
				
		ObjectMapper mapper = new ObjectMapper();
		
		String JsonData = null;
		
		try {
			
			JsonData = mapper.writeValueAsString(car);
			
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
			
		}
		
		return JsonData;
	}

}
