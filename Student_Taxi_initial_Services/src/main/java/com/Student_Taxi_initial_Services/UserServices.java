package com.Student_Taxi_initial_Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO_Implementation.Driver_DAO_Implementation;
import DAO_Implementation.Passenger_DAO_Implementation;
import DAO_Implementation.User_DAO_Implementation;
import DAOs.Driver_DAO;
import DAOs.Passenger_DAO;
import DAOs.User_DAO;
import Trip.Trip;
import users.Driver;
import users.Passenger;
import users.Rate;
import users.User;

@Path("User")
public class UserServices {

	private User_DAO userDAO;
	private Driver_DAO driverDAO;
	private Passenger_DAO passengerDAO;
	private String Error = "Error";
	
	public UserServices() {
		
		try {
			
			userDAO = new User_DAO_Implementation();
			driverDAO = new Driver_DAO_Implementation();
			passengerDAO = new Passenger_DAO_Implementation();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
//	********************************************************************************************************
	@GET
	@Path("ShowPassenger{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String ShowPassenger(@PathParam("id") int id) {
		
		
		Passenger passenger ;
		
		passenger = userDAO.ShowPassenger(id);
		
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
	
//	********************************************************************************************************

	@GET
	@Path("NewPassenger%21{FirstName}%2C{LastName}%2C{Gender}%2C{UserName}%2C{PassWord}%2C{StuNum}%2C{NationalNum}")
	@Produces(MediaType.APPLICATION_JSON)
	public String CreateNewPassenger(@PathParam("FirstName") String FirstName, @PathParam("LastName") String LastName,
			@PathParam("Gender") String Gender, @PathParam("UserName") String UserName,
			@PathParam("PassWord") String PassWord, @PathParam("StuNum") int StuNum,
			@PathParam("NationalNum") int NationalNum) {
		
		Passenger passenger;
		
		passenger = userDAO.NewPassenger(FirstName, LastName, UserName, PassWord, StuNum, NationalNum, Gender);
				
		
		if(passenger==null) {
			
			return Error;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
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
	@Path("DeletePassenger{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String DeletePassenger(@PathParam("id") int id) {
		
		boolean report;
		 
		report = userDAO.DeletePassenger(id);
		
		if(report==true) {
			return "delete is successfull.";
		}
		
			return Error; 
		
	}
	
	//*****************************************************************************************************************
	
		@GET
		@Path("EditPassengerInfo%21{FirstName}%2C{LastName}%2C{UserName}%2C{PassWord}%2C{StuNum}%2C{NationalNum}%2C{Gender}%2C{ID}")
		@Produces(MediaType.APPLICATION_JSON)
		public String EditPassengerInfo(@PathParam("FirstName")String Firstname, @PathParam("LastName")String lastname,
				@PathParam("Gender")String gender,@PathParam("UserName")String username,@PathParam("PassWord")String password,
				@PathParam("StuNum")int stunum,@PathParam("NationalNum")int nationalnum,@PathParam("ID")int id) {
		
			Passenger passenger;
			
			passenger = userDAO.EditPassengerInfo(Firstname, lastname, username, password,stunum ,nationalnum , gender,id);
		
			
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
		
//*******************************************************************************************************************

		@GET
		@Path("NewDriver%21{FirstName}%2C{LastName}%2C{Gender}%2C{UserName}%2C{PassWord}%2C{StuNum}%2C{NationalNum}")
		@Produces(MediaType.APPLICATION_JSON)
		public String CreateNewDriver(@PathParam("FirstName") String FirstName, @PathParam("LastName") String LastName,
				@PathParam("Gender") String Gender, @PathParam("UserName") String UserName,
				@PathParam("PassWord") String PassWord, @PathParam("StuNum") int StuNum,
				@PathParam("NationalNum") int NationalNum ) {
			
			Driver driver;
			
			driver = userDAO.NewDriver(FirstName, LastName, UserName, PassWord, StuNum, NationalNum, Gender);
			
			if(driver==null) {
				
				return Error;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			
			String JsonData = null;
			
			try {
				
				JsonData = mapper.writeValueAsString(driver);
				
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
				
			}
			
			return JsonData;
		}
		
		//*******************************************************************************************************************
		
		@GET
		@Path("ShowDriver{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String ShowDriver(@PathParam("id") int id) {
			
			
			Driver driver;
			
			driver = userDAO.ShowDriver(id);
			
			if(driver==null) {
					
					return Error;
				}
			
			ObjectMapper mapper = new ObjectMapper();
			
			String ShowData=null;
			
			try {
				
				ShowData = mapper.writeValueAsString(driver);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return ShowData;
		}
		
//	********************************************************************************************************
		
		@GET
		@Path("DeleteDriver{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String DeleteDriver(@PathParam("id") int id) {
			
			boolean report;
			 
			report = userDAO.DeleteDriver(id);
			
			if(report==true) {
				return "delete is successfull.";
			}
			
				return Error; 
			
		}
		
//*****************************************************************************************************************
		
		@GET
		@Path("EditDriverInfo%21{FirstName}%2C{LastName}%2C{UserName}%2C{PassWord}%2C{StuNum}%2C{NationalNum}%2C{Gender}%2C{ID}")
		@Produces(MediaType.APPLICATION_JSON)
		public String EditDriverInfo(@PathParam("FirstName")String Firstname, @PathParam("LastName")String lastname,
			@PathParam("Gender")String gender,@PathParam("UserName")String username,@PathParam("PassWord")String password,
			@PathParam("StuNum")int stunum,@PathParam("NationalNum")int nationalnum,@PathParam("ID")int id) {
	
			Driver driver;
			
			driver = userDAO.EditDriverInfo(Firstname, lastname, username, password,stunum ,nationalnum , gender,id);
		
			
			if(driver==null) {
				
				return Error;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			
			String EditData = null;
			
			try {
				
				EditData = mapper.writeValueAsString(driver);
				
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
				
			}
			
			return EditData;
		}
	//*******************************************************************************************************************
	
		@GET
		@Path("EditDriverCar%21{carName}%2C{sitNum}%2C{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String EditDriverCar(@PathParam("carName") String carName,@PathParam("sitNum") int sitNum,
				@PathParam("id") int id) {
			
			Driver driver;
			
			driver = userDAO.EditDriverCar(carName, sitNum, id);
			
			if(driver==null) {
					
					return Error;
				}
			
			ObjectMapper mapper = new ObjectMapper();
			
			String ShowData=null;
			
			try {
				
				ShowData = mapper.writeValueAsString(driver);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return ShowData;
		}
		
//*******************************************************************************************************************
		
			@GET
			@Path("EditDriverCar%21{rate}%2C{id}")
			@Produces(MediaType.APPLICATION_JSON)
			public String EditDriverRate(@PathParam("rate") double rate , @PathParam("id") int id) {
				
				Driver driver;
				
				driver = userDAO.EditDriverRate(Rate.calculateRate(rate), id);
				
				if(driver==null) {
						
						return Error;
					}
				
				ObjectMapper mapper = new ObjectMapper();
				
				String ShowData=null;
				
				try {
					
					ShowData = mapper.writeValueAsString(driver);
					
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				
				return ShowData;
			}
			
//*******************************************************************************************************************
			
	@GET
	@Path("AddNewTripDriver%21{Sits}%2C{sitPrice}%2C{driver_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String AddNewTripDriver(@PathParam("Sits") int Sits , @PathParam("sitPrice") double sitPrice ,
			@PathParam("driver_id") int driver_id ) {
		
		Driver driver;
		
		driver = userDAO.AddNewTripDriver(Sits, sitPrice, driver_id);
		
		if(driver==null) {
				
				return Error;
			}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String ShowData=null;
		
		try {
			
			ShowData = mapper.writeValueAsString(driver);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ShowData;
	}

//*******************************************************************************************************************
	
	@GET
	@Path("DeleteTripDriver%21{driver_id}%2C{trip_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String DeleteTripDriver(@PathParam("driver_id") int driver_id , @PathParam("trip_id") int trip_id) {
		
		Driver driver;
		
		driver = userDAO.DeleteTripDriver(driver_id, trip_id);
		
		if(driver==null) {
				
				return Error;
			}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String ShowData=null;
		
		try {
			
			ShowData = mapper.writeValueAsString(driver);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ShowData;
	}
	
	
	//*******************************************************************************************************************
	
		@GET
		@Path("EditTripInfoDriver%21{Sits}%2C{sitPrice}%2C{driver_id}%2C{trip_id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String EditTripInfoDriver(@PathParam("Sits") int Sits , @PathParam("sitPrice") double sitPrice 
				, @PathParam("driver_id") int driver_id , @PathParam("trip_id") int trip_id) {
			
			Driver driver;
			
			driver = userDAO.EditTripInfoDriver(Sits, sitPrice, trip_id, driver_id);
			
			if(driver==null) {
					
					return Error;
				}
			
			ObjectMapper mapper = new ObjectMapper();
			
			String ShowData=null;
			
			try {
				
				ShowData = mapper.writeValueAsString(driver);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return ShowData;
		}
		
//*******************************************************************************************************************
	
		@GET
		@Path("EditTripLocation%21{origin}%2C{destination}%2C{driver_id}%2C{trip_id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String EditTripLocation(@PathParam("origin") String origin , @PathParam("destination") String destination 
				, @PathParam("driver_id") int driver_id , @PathParam("trip_id") int trip_id) {
			
			Driver driver;
			
			driver = userDAO.EditTripLocationDriver(origin, destination, trip_id, driver_id);
			
			if(driver==null) {
					
					return Error;
				}
			
			ObjectMapper mapper = new ObjectMapper();
			
			String ShowData=null;
			
			try {
				
				ShowData = mapper.writeValueAsString(driver);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return ShowData;
		}	
	
//*******************************************************************************************************************
		
		@GET
		@Path("EditTripPaymentDriver%21{Price}%2C{driver_id}%2C{trip_id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String EditTripPaymentDriver(@PathParam("Price") double Price, @PathParam("driver_id") int driver_id , @PathParam("trip_id") int trip_id) {
			
			Driver driver;
			
			driver = userDAO.EditTripPaymentDriver(Price, trip_id, driver_id);
			
			if(driver==null) {
					
					return Error;
				}
			
			ObjectMapper mapper = new ObjectMapper();
			
			String ShowData=null;
			
			try {
				
				ShowData = mapper.writeValueAsString(driver);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return ShowData;
		}	
			
//*******************************************************************************************************************
		@GET
		@Path("PayPaymentDriver%21{driver_id}%2C{trip_id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String PayPaymentDriver(@PathParam("driver_id") int driver_id , @PathParam("trip_id") int trip_id) {
			
			Driver driver;
			
			driver = userDAO.PayPaymentDriver(trip_id, driver_id);
			
			if(driver==null) {
					
					return Error;
				}
			
			ObjectMapper mapper = new ObjectMapper();
			
			String ShowData=null;
			
			try {
				
				ShowData = mapper.writeValueAsString(driver);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return ShowData;
		}
		
//*******************************************************************************************************************
//		@GET
//		@Path("SearchTrip%21{origin}%2C{destination}")
//		@Produces(MediaType.APPLICATION_JSON)
//		public String SearchTrip(@PathParam("origin") String origin , @PathParam("destination") String destination) {
//			
//			List<Trip> trips = new ArrayList<Trip>();
//			
//			trips = userDAO.SearchTrip(origin, destination);
//			
//			if(trips==null) {
//					
//					return Error;
//				}
//			
//			ObjectMapper mapper = new ObjectMapper();
//			
//			String ShowData=null;
//			
//			try {
//				
//				ShowData = mapper.writeValueAsString(trips);
//				
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//			
//			return ShowData;
//		}
//	public List<Trip> SearchTrip(String origin, String destination)
	
//*******************************************************************************************************************
		@GET
		@Path("Login%21{userName}%2C{passWord}")
		@Produces(MediaType.APPLICATION_JSON)
		public String Login(@PathParam("userName") String userName , @PathParam("passWord") String passWord) {
			
			User user;

			user = userDAO.Login(userName, passWord);
			
			if(user==null) {
					
					return Error;
				}
					
			ObjectMapper mapper = new ObjectMapper();
			
			String ShowData=null;
			
			try {
				
				ShowData = mapper.writeValueAsString(user);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return ShowData;
		}

//*******************************************************************************************************************
		@GET
		@Path("LoginPassenger%21{user_id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String LoginPassenger(@PathParam("user_id") int user_id) {
			
			Passenger passenger;

			passenger = userDAO.LoginPassenger(user_id);
			
			if(passenger == null) {
					
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
//*******************************************************************************************************************
		@GET
		@Path("LoginDriver%21{user_id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String LoginDriver(@PathParam("user_id") int user_id) {
			
			Driver driver;

			driver = userDAO.LoginDriver(user_id);
			
			if(driver==null) {
					
					return Error;
				}
					
			ObjectMapper mapper = new ObjectMapper();
			
			String ShowData=null;
			
			try {
				
				ShowData = mapper.writeValueAsString(driver);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return ShowData;
		}
//*******************************************************************************************************************
		
}
