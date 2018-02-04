package com.Student_Taxi_initial_Services;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO_Implementation.Passenger_DAO_Implementation;
import DAOs.Passenger_DAO;
import users.Passenger;

@Path("Passenger")
public class PassengerServices {

	public Passenger_DAO passengerDAO = new Passenger_DAO_Implementation();

	// ************************************** Services ************************************************

	@GET
	@Path("NewDriver%21{FirstName}%2C{LastName}%2C{Gender}%2C{UserName}%2C{PassWord}%2C{StuNum}%2C{NationalNum}")
	@Produces(MediaType.TEXT_PLAIN)
	public String CreateNew(@PathParam("FirstName") String FirstName, @PathParam("LastName") String LastName,
			@PathParam("Gender") String Gender, @PathParam("UserName") String UserName,
			@PathParam("PassWord") String PassWord, @PathParam("StuNum") String StuNum,
			@PathParam("NationalNum") String NationalNum) {
		
		
		Passenger passenger;
		
		passenger = new Passenger(FirstName, LastName, UserName, PassWord, Integer.parseInt(StuNum),
				Integer.parseInt(NationalNum), Gender);
		
		System.out.println("Passenger created...\nSaving to Data Base");
		
		passenger = passengerDAO.CreateNew(passenger);
		
		System.out.println("Retrieved ID : " + passenger.getId());
		
		return "Retrieved ID : " + passenger.getId();
	}

}
