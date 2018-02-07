package com.Student_Taxi_initial_Services;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO_Implementation.Location_DAO_Implementation;
import DAOs.Location_DAO;
import Trip.Location;
import users.Driver_Rate;
import users.Rate;

@Path("Location")
public class LocationServices {
	
	private Location_DAO locationDAO;
	private String Error = "Error";
	
	public LocationServices() {
		
		try {
			locationDAO = new Location_DAO_Implementation();
		} catch (ClassNotFoundException | SQLException e) {
			Error = "error";
			e.printStackTrace();
		}
	}
	
//	********************************************************************************************************
	@GET
	@Path("Show{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String Show(@PathParam("id") int id) {
		
		Location location;
		
		location = locationDAO.Show(id);
		
		if(location==null) {
			
			return Error;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String ShowData=null;
		
		try {
			ShowData = mapper.writeValueAsString(location);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ShowData;
	}

//	********************************************************************************************************
	
	@GET
	@Path("New%21{Origin}%2C{Destination}")
	@Produces(MediaType.APPLICATION_JSON)
	public String CreateNew(@PathParam("Origin") String Origin , @PathParam("Destination") String Destination) {
		Location location;
		
		location = locationDAO.CreateNew(Origin , Destination);
		
		if(location==null) {
			
			return Error;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String JsonData = null;
		try {
			JsonData = mapper.writeValueAsString(location);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return JsonData;
	}
	
//	********************************************************************************************************
	
	@GET
	@Path("Edit%21{Origin}%2C{Destination}%2C{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String Editlocation(@PathParam("Origin") String Origin , @PathParam("Destination") String Destination 
			,@PathParam("id") int id) {
		
		Location location;
		
		location = locationDAO.EditLocation(Origin ,Destination, id);
	
		
		if(location==null) {
			
			return Error;
		}
		
		ObjectMapper mapper = new ObjectMapper();

		String EditData = null;
		
		try {
			
			EditData = mapper.writeValueAsString(location);
			
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
			
		}
		
		return EditData;
		
	}
	
//	********************************************************************************************************
	
	@GET
	@Path("Delete{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String Delete(@PathParam("ID") int id) {
		
		boolean report;
		 
		report=locationDAO.Delete(id);
		
		if(report==true) {
			return "delete is successfull.";
		}

		return "delete failed."; 
		
	}

}
