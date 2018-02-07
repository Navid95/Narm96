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
	
	// *********************************************** Services *******************************************************
	
	@GET
	@Path("Show{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String Show(@PathParam("id") String id) {
		
		int user_id = Integer.parseInt(id);
		
		System.out.println(""+user_id);
		
		Car car;
		
		car = carDAO.Show(user_id);
		
	if(car==null) {
			
			return Error;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String ShowData=null;
		
		try {
			ShowData = mapper.writeValueAsString(car);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ShowData;
	}

	// *********************************************** Services *******************************************************
	
	@GET
	@Path("EditInfo%21{CarName}%2C{SitNum}%2C{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String EditInfo(@PathParam("CarName") String CarName, @PathParam("SitNum") int SitNum ,@PathParam("id") int id ) {
		
		Car car;
		
		car = carDAO.EditInfo(CarName, SitNum , id);
		
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
	
	//************************************************************************************************************
	
	
		@GET
		@Path("Delete{ID}")
		@Produces(MediaType.APPLICATION_JSON)
		public String Delete(@PathParam("ID") String id) {
			
			
			boolean report;
			 
			report=carDAO.Delete(Integer.parseInt(id));
			
			if(report==true) {
				return "delete is successfull.";
			}
			
			
				return "delete failed."; 
		}

}
