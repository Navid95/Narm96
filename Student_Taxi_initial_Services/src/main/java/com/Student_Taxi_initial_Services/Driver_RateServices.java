package com.Student_Taxi_initial_Services;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO_Implementation.Driver_Rate_DAO_Implementation;
import DAOs.Driver_Rate_DAO;
import users.Driver_Rate;
import users.Passenger;
import users.Rate;

@Path("Rate")
public class Driver_RateServices {
	
	private Driver_Rate_DAO rateDAO;
	private String Error;
	
	public Driver_RateServices() {
		
		try {
			
			rateDAO = new Driver_Rate_DAO_Implementation();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
//	********************************************************************************************************
	
	@GET
	@Path("New%21{Rate}")
	@Produces(MediaType.APPLICATION_JSON)
	public String CreateNew(@PathParam("Rate") double rate) {
		
		Driver_Rate driverRate;
		
		driverRate = rateDAO.CreateNew(Rate.calculateRate(rate));
		
		if(driverRate==null) {
			
			return Error;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String JsonData = null;
		try {
			JsonData = mapper.writeValueAsString(driverRate);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return JsonData;
	}
	
//	********************************************************************************************************
	@GET
	@Path("Show{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String Show(@PathParam("id") int id) {
				
		Driver_Rate rate;
		
		rate = rateDAO.Show(id);
		
	if(rate==null) {
			
			return Error;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String ShowData=null;
		
		try {
			ShowData = mapper.writeValueAsString(rate);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ShowData;
	}
	
//	********************************************************************************************************
	
	@GET
	@Path("Edit%2C{Rate}%21{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String EditRate(@PathParam("Rate") double rate , @PathParam("id") int id) {
		
		Driver_Rate dRate;
		
		dRate = rateDAO.EditRate(Rate.calculateRate(rate), id);
	
		
		if(dRate==null) {
			
			return Error;
		}
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		

		String EditData = null;
		
		try {
			
			EditData = mapper.writeValueAsString(dRate);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return EditData;
		
	}
	
	//************************************************************************************************************
	
		@GET
		@Path("Delete{ID}")
		@Produces(MediaType.APPLICATION_JSON)
		public String Delete(@PathParam("ID") int id) {
			
			boolean report;
			 
			report=rateDAO.Delete(id);
			
			if(report==true) {
				return "delete is successfull.";
			}
	
			return "delete failed."; 
			
		}
}
