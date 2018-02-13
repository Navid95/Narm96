package DAO_Implementation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAOs.Car_DAO;
import DAOs.Driver_DAO;
import DAOs.Driver_Rate_DAO;
import DAOs.Trip_DAO;
import DataBase_Connection.mysql_Connection;
import Trip.Trip;
import users.Car;
import users.Driver;
import users.Driver_Rate;
import users.Passenger;
import users.Rate;

public class Driver_DAO_Implementation implements Driver_DAO {
	
	private mysql_Connection mysql_Connection;
	private Car_DAO carDAO;
	private Driver_Rate_DAO driverRateDAO;
	private Trip_DAO trip_DAO;
	
	public Driver_DAO_Implementation() throws ClassNotFoundException, SQLException {
		
		mysql_Connection = new mysql_Connection();
		carDAO = new Car_DAO_Implementation();
		driverRateDAO = new Driver_Rate_DAO_Implementation();
		trip_DAO = new Trip_DAO_Implementation();
		
	}
	
	
	// *********************************************************************************************************
	
	@Override
	public int NewDriver() {
		
		Car car = new Car();
		
		car = carDAO.CreateNew("CarName" , 0);
		
		Driver_Rate rate = driverRateDAO.CreateNew(Rate.star_3);
		
		if (car !=null && rate !=null) {
				
			String sql;
			
			sql = "INSERT INTO `driver` (`driver_id`, `rate_id`, `car_id`) VALUES "
					+ "(NULL,"+rate.getId()+", "+car.getId()+");";
	
			try {
	
				mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
				mysql_Connection.conn.commit();
	
				int autoIncKeyFromApi = -1;
	
				if (mysql_Connection.rs.next()) {
	
					autoIncKeyFromApi = mysql_Connection.rs.getInt(1);
					
					if (autoIncKeyFromApi != -1) {
	
						return autoIncKeyFromApi;
	
					} else {
	
						return -1;
	
					}
	
				} else {
	
					System.out.println("No id returned");
					return -1;
					
				}
				
			} catch (SQLException e) {
	
				if (mysql_Connection.conn != null)
	
					try {
	
						mysql_Connection.conn.rollback();
	
					} catch (SQLException e1) {
	
						e1.printStackTrace();
	
						return -1;
					}
	
				e.printStackTrace();
	
				return -1;
			}
			
		}
		else {
			
			return -1;
		}

	}
	
	// *********************************************************************************************************
	
	@Override
	public Driver ShowDriver(int id) {
		
		Driver driver = new Driver();
		Car car ;
		Driver_Rate rate ;
		List<Trip> trips;
		
		String sql;
		
		sql = "SELECT `car_id`,	`rate_id` FROM `driver` WHERE driver_id = "+id+";";
		
		
		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			mysql_Connection.rs.next();
			
			car = carDAO.Show(mysql_Connection.rs.getInt("car_id"));
			rate = driverRateDAO.Show(mysql_Connection.rs.getInt("rate_id"));
			trips = trip_DAO.GetDriverTrips(id);
			
			driver.setCar(car);
			driver.setRate(rate);
			driver.setTrips(trips);
			driver.setId(id);
			
			return driver;
			
		} catch (SQLException e) {

			if (mysql_Connection.conn != null)

				try {

					mysql_Connection.conn.rollback();

				} catch (SQLException e1) {

					e1.printStackTrace();

					return null;
				}

			e.printStackTrace();

			return null;
		}
		
	}
	
	// *********************************************************************************************************
	
		@Override
		public boolean DeleteDriver(int driver_id) {
			
			Driver driver = ShowDriver(driver_id);
			
			String sql;
			
			if( driverRateDAO.Delete(driver.getRate().getId()) && carDAO.Delete(driver.getCar().getId()) ) {
			
				sql = "DELETE FROM `driver` WHERE driver_id = "+driver_id+";";
				
				int result = -1;
				
				try {
					
					result = mysql_Connection.stmt.executeUpdate(sql);
					
					mysql_Connection.conn.commit();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
					return false;
				}
				
				if (result != 1) {
					
					return false;
					
				}
				
				return true;
				
			}
			else {
				
				return false;
				
			}
		}
		
// *********************************************************************************************************
	
	
		public Driver EditCar(String carName , int sitNum , int driver_id) {
			
			Driver driver = ShowDriver(driver_id);
			
			Car car = new Car();
			
			if(driver != null) {
				
				car = carDAO.EditInfo(carName, sitNum, driver.getCar().getId());
				
				if (car != null) {
					
					driver.setCar(car);
					return driver;
				}
				else {
					return null;
				}
			
			}
			
			else {
				
				return null;
			}
				
		}
		
// *********************************************************************************************************
		
		public Driver EditRate(Rate rate , int driver_id ) {
			
		Driver driver = ShowDriver(driver_id);
			
		Driver_Rate driverRate ;
			
			if(driver != null) {
				
				driverRate = driverRateDAO.EditRate(rate, driver.getRate().getId());
				
				if (driverRate != null) {
					
					driver.setRate(driverRate);
					return driver;
				}
				else {
					return null;
				}
			
			}
			
			else {
				
				return null;
			}
		}
		
// *********************************************************************************************************
		
		public Driver AddNewTrip(int Sits , double sitPrice , int driver_id) {
			
			Driver driver;
			Trip trip = trip_DAO.NewTrip(Sits, sitPrice, driver_id);
			
			if(trip != null) {
				
				driver = new Driver();
				driver = ShowDriver(driver_id);
				
				return driver;
				
			}
			else {
				return null;	
			}
			
		}
		
// *********************************************************************************************************

		public boolean DeleteTripDriver(int driver_id , int trip_id) {
			
			Driver driver = ShowDriver(driver_id);
			
			List<Trip> trips = new ArrayList<Trip>();
			
			trips = trip_DAO.GetDriverTrips(driver_id);
			
			int id=0;
			
			while (id < trips.size() -1) {
				
				Trip trip = trips.get(id);
				
				
				if (trip.getId() == trip_id) {
					
					return trip_DAO.DeleteTrip(trip_id);
					
				}
				id++;
				
			}
			
			return false;
			
		}
		
// *********************************************************************************************************
			
	public Driver EditTripInfo(int Sits , double sitPrice , int trip_id , int driver_id) {
		
		Driver driver;
		
		List<Trip> trips = new ArrayList<Trip>();
		
		trips = trip_DAO.GetDriverTrips(driver_id);
		
		int id=0;
		
		while (id < trips.size() -1) {
			
			Trip trip = trips.get(id);
			
			if (trip.getId() == trip_id) {
				
				trip_DAO.EditTripInfo(Sits, sitPrice, trip_id);
				driver = ShowDriver(driver_id);
				return driver;
			}
			id++;
			
		}
		return null;
	}
// *********************************************************************************************************
	
	public Driver EditTripLocation(String origin, String destination , int trip_id , int driver_id) {
		
		Driver driver;
		driver = new Driver();
		driver = ShowDriver(driver_id);
		
		List<Trip> trips = new ArrayList<Trip>();
		
		trips = trip_DAO.GetDriverTrips(driver_id);
		
		int id=0;
		
		while (id < trips.size() -1) {
			
			Trip trip = trips.get(id);
			
			if (trip.getId() == trip_id) {
				
				trip_DAO.EditTripLocation(origin, destination, trip_id);
				driver = ShowDriver(driver_id);
				return driver;
			}
			id++;
		}
		return null;
		
	}
	
	// *********************************************************************************************************
	
	public Driver EditTripPayment(double Price , int trip_id , int driver_id) {
		
		Driver driver;
		driver = new Driver();
		driver = ShowDriver(driver_id);
		
		List<Trip> trips = new ArrayList<Trip>();
		
		trips = trip_DAO.GetDriverTrips(driver_id);
		
		int id=0;
		
		while (id < trips.size() -1) {
			
			Trip trip = trips.get(id);
			
			if (trip.getId() == trip_id) {
				
				trip_DAO.EditTripPayment(Price, trip_id);
				driver = ShowDriver(driver_id);
				return driver;
			}
			id++;
		}
		return null;
		
	}
	
	// *********************************************************************************************************
	
	public Driver PayPayment(int trip_id , int driver_id) {
		
		Driver driver;
		driver = new Driver();
		driver = ShowDriver(driver_id);
		
		List<Trip> trips = new ArrayList<Trip>();
		
		trips = trip_DAO.GetDriverTrips(driver_id);
		
		int id=0;
		
		while (id < trips.size() -1) {
			
			Trip trip = trips.get(id);
			
			if (trip.getId() == trip_id) {
				
				trip_DAO.PayPayment(trip_id);
				driver = ShowDriver(driver_id);
				return driver;
			}
			id++;
		}
		return null;
		
	}
	
	// *********************************************************************************************************
	
	// *********************************************************************************************************
		
}
