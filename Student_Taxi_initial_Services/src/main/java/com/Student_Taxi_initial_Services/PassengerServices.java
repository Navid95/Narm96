package com.Student_Taxi_initial_Services;

import javax.ws.rs.PathParam;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PreUpdate;
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
	
	private Passenger_DAO passengerDAO ;
	private String Error; 
	
	public PassengerServices() {
		try {
			passengerDAO = new Passenger_DAO_Implementation();
		} catch (ClassNotFoundException | SQLException e) {
			Error = "error";
			e.printStackTrace();
		}
	}

	// *********************************************** Services *******************************************************

	@GET
	@Path("NewPassenger%21{FirstName}%2C{LastName}%2C{Gender}%2C{UserName}%2C{PassWord}%2C{StuNum}%2C{NationalNum}")
	@Produces(MediaType.APPLICATION_JSON)
	public String CreateNew(@PathParam("FirstName") String FirstName, @PathParam("LastName") String LastName,
			@PathParam("Gender") String Gender, @PathParam("UserName") String UserName,
			@PathParam("PassWord") String PassWord, @PathParam("StuNum") String StuNum,
			@PathParam("NationalNum") String NationalNum) {
		
		
		Passenger passenger;
		
		System.out.println("Passenger created...\nSaving to Data Base");
		
		passenger = passengerDAO.CreateNew(FirstName, LastName, UserName, PassWord, Integer.parseInt(StuNum),
				Integer.parseInt(NationalNum), Gender);
		
		if(passenger==null) {
			
			return Error;
		}
		
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
	@Produces(MediaType.APPLICATION_JSON)
	public String Show(@PathParam("id") String id) {
		
		int user_id = Integer.parseInt(id);
		
		System.out.println(""+user_id);
		
		Passenger passenger;
		
		passenger = passengerDAO.Show(user_id);
		
	if(passenger==null) {
			
			return Error;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String ShowData=null;
		
		try {
			ShowData = mapper.writeValueAsString(passenger);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ShowData;
	}

	
//*****************************************************************************************************************
	
	@GET
	@Path("EditPassenger%21{FirstName}%2C{LastName}%2C{Gender}%2C{UserName}%2C{PassWord}%2C{StuNum}%2C{NationalNum}%2C{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String EditInfo(@PathParam("FirstName")String Firstname, @PathParam("LastName")String lastname,
			@PathParam("Gender")String gender,@PathParam("UserName")String username,@PathParam("PassWord")String password,
			@PathParam("StuNum")String stunum,@PathParam("NationalNum")String nationalnum,@PathParam("ID")String id) {
	
		Passenger passenger;
		
		passenger = passengerDAO.EditInfo(Firstname, lastname, username, password,Integer.parseInt(stunum) ,Integer.parseInt(nationalnum) , gender,Integer.parseInt(id));
	
		
	if(passenger==null) {
			
			return Error;
		}
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		

		String EditData = null;
		try {
			EditData = mapper.writeValueAsString(passenger);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return EditData;
	}
	
//************************************************************************************************************
	
	
	@GET
	@Path("Delete{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String Delete(@PathParam("ID") String id) {
		
		
		boolean report;
		 
		report=passengerDAO.Delete(Integer.parseInt(id));
		
		if(report==true) {
			return "delete is successfull.";
		}
		
		
			return "delete failed."; 
		
		
	}
	
	//************************************************************************************************************

	@GET
	@Path("showAllPassenger%20{offset}%20{rownum}")
	@Produces(MediaType.APPLICATION_JSON)
	public String showalls(@PathParam("offset") String offset, @PathParam("rownum") String row) {
		
		List<Passenger> list;

		list=passengerDAO.showAll(Integer.parseInt(offset),Integer.parseInt(row));
	
		
		if(list==null) {
				
				return Error;
			}
			
	ObjectMapper mapper = new ObjectMapper();
		

		String listData = null;
		try {
			listData = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
			return listData;
		
	
	}
	
	
	
	
	
	
	
	
	
	
}
