package com.Student_Taxi_initial_Services;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO_Implementation.Payment_DAO_Implementation;
import DAOs.Payment_DAO;
import Trip.Payment;
import users.Passenger;

@Path("Payment")
public class PaymentServices {
	
	private Payment_DAO paymentDAO;
	private String Error;
	
	public PaymentServices() {
		
		try {
			
			paymentDAO = new Payment_DAO_Implementation();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	// *********************************************** Services *******************************************************
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("NewPayment%21{Price}%2C{State}")
	public String createNew(@PathParam("Price") double Price ,@PathParam("State") boolean State ) {
		
		Payment payment = new Payment();
		
//		payment.setPrice(Price);
//		payment.setState(State);
		payment = paymentDAO.CreateNew(Price , State);
		
		ObjectMapper mapper = new ObjectMapper();
		String paymentData;
		try {
			paymentData = mapper.writeValueAsString(payment);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Error;
		}
		
		return paymentData;
	}
	
//	********************************************************************************************************
	@GET
	@Path("show{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String Show(@PathParam("id") String id) {
		
		int user_id = Integer.parseInt(id);
		
		System.out.println(""+user_id);
		
		Payment payment;
		
		payment = paymentDAO.Show(user_id);
		
	if(payment==null) {
			
			return Error;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String ShowData=null;
		
		try {
			ShowData = mapper.writeValueAsString(payment);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ShowData;
	}
	
	//*****************************************************************************************************************
	
		@GET
		@Path("EditPrice%21{Price}%2C{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String EditInfo(@PathParam("Price") double Price , @PathParam("id") int id) {
		
			Payment payment;
			
			payment = paymentDAO.EditPrice(Price, id);
		
			
		if(payment==null) {
				
				return Error;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			

			String EditData = null;
			try {
				EditData = mapper.writeValueAsString(payment);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return EditData;
		}
		
		//*****************************************************************************************************************
		
		@GET
		@Path("pay{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String pay(@PathParam("id") int id) {
			
			Payment payment = new Payment();
			String jsonData;
			
			payment = paymentDAO.Pay(id);
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				
				jsonData = mapper.writeValueAsString(payment);
				
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
				return "Error while converting object as Json String";
			}
			
			return jsonData;
		}
		
		//*****************************************************************************************************************
		
		@GET
		@Path("Delete{ID}")
		@Produces(MediaType.APPLICATION_JSON)
		public String Delete(@PathParam("ID") String id) {
			
			
			boolean report;
			 
			report=paymentDAO.Delete(Integer.parseInt(id));
			
			if(report==true) {
				return "delete is successfull.";
			}
			
			
				return "delete failed."; 
			
			
		}
	
}
