package DAO_Implementation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import DAO_Implementation.Passenger_DAO_Implementation;
import DAOs.Driver_DAO;
import DAOs.Passenger_DAO;
import DAOs.User_DAO;
import DataBase_Connection.mysql_Connection;
import Trip.Trip;
import users.Car;
import users.Driver;
import users.Driver_Rate;
import users.Passenger;
import users.Rate;
import users.User;

public class User_DAO_Implementation implements User_DAO {
	
	private mysql_Connection mysql_Connection;
	private Passenger_DAO passengerDAO;
	private Driver_DAO driverDAO;
	
	public User_DAO_Implementation() throws ClassNotFoundException, SQLException {
		mysql_Connection = new mysql_Connection();
		passengerDAO = new Passenger_DAO_Implementation();
		driverDAO = new Driver_DAO_Implementation();
	}

	// ******************************************************************************************************************

	@Override
	public Passenger NewPassenger(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender) {

		String sql;

		Passenger passenger;
		
		int passenger_id;
		
		passenger_id = passengerDAO.NewPassenger();

		if(passenger_id != -1) {
			
			try {
				
				sql = "INSERT INTO `user` (`FirstName`, `LastName`, `UserName`, `PassWord`,"
						+ " `Gender`, `Role`, `StuNum`, `NationalNum`, `passenger_id`) VALUES "
						+ "('"+FirstName+"', '"+LastName+"', '"+UserName+"', '"+PassWord+"', '"
						+Gender+"', 1, '"+StuNum+"', '"+NationalNum+"', '"+passenger_id+"');";
	
				mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
				mysql_Connection.conn.commit();
	
				int autoIncKeyFromApi = -1;
	
				if (mysql_Connection.rs.next()) {
	
					autoIncKeyFromApi = mysql_Connection.rs.getInt(1);
					
					if (autoIncKeyFromApi != -1) {
						
						passenger = ShowPassenger(passenger_id);
						
						return passenger;
					} else {
		
						return null;
		
					}
		
				} else {
	
					System.out.println("No id returned");
	
					return null;
	
				}
	
			} catch (SQLException e) {
	
				if (mysql_Connection.conn != null)
	
					try {
	
						mysql_Connection.conn.rollback();
						
						return null;
	
					} catch (SQLException e1) {
	
						e1.printStackTrace();
	
						return null;
					}
	
				e.printStackTrace();
	
				return null;
			}
		}
		
		else {
			return null;
		}
	}
	
	// ******************************************************************************************************************
	
	@Override
	public Passenger ShowPassenger(int passenger_id) {

		String sql;

		Passenger passenger;

		sql = "SELECT `FirstName`,`LastName`,`UserName`,`PassWord`,`Gender`,`StuNum`,`NationalNum`,"
				+ "`passenger_id` FROM `user` WHERE passenger_id = "+passenger_id+";";

		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			mysql_Connection.rs.next();
			
			passenger = new Passenger();
			
			passenger.setFirstName(mysql_Connection.rs.getString("FirstName"));
			
			passenger.setLastName(mysql_Connection.rs.getString("LastName"));
			
			passenger.setUserName(mysql_Connection.rs.getString("UserName"));
			
			passenger.setPassWord(mysql_Connection.rs.getString("PassWord"));
			
			passenger.setStuNum(mysql_Connection.rs.getInt("StuNum"));
			
			passenger.setNationalNum(mysql_Connection.rs.getInt("NationalNum"));
			
			passenger.setGender(mysql_Connection.rs.getString("Gender"));
			
			passenger.setId(mysql_Connection.rs.getInt("passenger_id"));
			
			return passenger;

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
		public boolean DeletePassenger(int passenger_id) {
			
			if(passengerDAO.DeletePassenger(passenger_id)) {
			
				String sql;
				
				sql = "DELETE FROM `user` WHERE passenger_id = "+passenger_id+";";
				
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
	
	// ******************************************************************************************************************
		
		@Override
		public Passenger EditPassengerInfo(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
				int NationalNum, String Gender , int passenger_id) {
			
			String sql;
			
			Passenger passenger = new Passenger();

			sql = "UPDATE user SET FirstName = '"+FirstName+"' , LastName = '"+LastName+"' , UserName = '"+UserName+"' ,"
					+ " PassWord='"+PassWord+"' , StuNum = '"+StuNum+"', NationalNum = '"+NationalNum+"', Gender = '" +Gender+"'"
							+ " WHERE passenger_id = "+passenger_id+";";

			int count = -1000 ;
			
			try {

				count = mysql_Connection.stmt.executeUpdate(sql);
				
				mysql_Connection.conn.commit();
				
				passenger = ShowPassenger(passenger_id);
				
			} catch (SQLException e) {

				if (mysql_Connection.conn != null || count == -1000 || count < 7)

					try {

						mysql_Connection.conn.rollback();

					} catch (SQLException e1) {

						e1.printStackTrace();

						return null;
					}

				e.printStackTrace();

				return null;
			}
			
			return passenger;
			
		}
		
// ******************************************************************************************************************
		
		@Override
		public Driver NewDriver(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
				int NationalNum, String Gender ) {
			
			String sql;

			Driver driver;
			
			int driver_id;
			
			driver_id = driverDAO.NewDriver();

			if(driver_id != -1) {
				
				try {
					
					sql = "INSERT INTO `user` (`FirstName`, `LastName`, `UserName`, `PassWord`,"
							+ " `Gender`, `Role`, `StuNum`, `NationalNum`, `driver_id`) VALUES "
							+ "('"+FirstName+"', '"+LastName+"', '"+UserName+"', '"+PassWord+"', '"
							+Gender+"', 2 , '"+StuNum+"', '"+NationalNum+"', '"+driver_id+"');";
		
					mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
					mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
					mysql_Connection.conn.commit();
		
					int autoIncKeyFromApi = -1;
		
					if (mysql_Connection.rs.next()) {
		
						autoIncKeyFromApi = mysql_Connection.rs.getInt(1);
						
						if (autoIncKeyFromApi != -1) {
							
							System.out.println("Driver ID : "+driver_id);
							driver = ShowDriver(driver_id);
							
							return driver;
						} else {
			
							return null;
			
						}
			
					} else {
		
						System.out.println("No id returned");
		
						return null;
		
					}
		
				} catch (SQLException e) {
		
					if (mysql_Connection.conn != null)
		
						try {
		
							mysql_Connection.conn.rollback();
							
							return null;
		
						} catch (SQLException e1) {
		
							e1.printStackTrace();
		
							return null;
						}
		
					e.printStackTrace();
		
					return null;
				}
			}
			
			else {
				return null;
			}
		}
		
// ******************************************************************************************************************
		
		@Override
		public Driver ShowDriver(int driver_id) {

			String sql;

			Driver driver;

			sql = "SELECT `FirstName`,`LastName`,`UserName`,`PassWord`,`Gender`,`StuNum`,`NationalNum`,"
					+ "`driver_id` FROM `user` WHERE driver_id = "+driver_id+";";

			try {
				
				mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
				
				mysql_Connection.conn.commit();
				
				mysql_Connection.rs.next();
				
				driver = new Driver();
				
				driver = driverDAO.ShowDriver(driver_id);
				
				driver.setFirstName(mysql_Connection.rs.getString("FirstName"));
				driver.setLastName(mysql_Connection.rs.getString("LastName"));
				driver.setUserName(mysql_Connection.rs.getString("UserName"));
				driver.setPassWord(mysql_Connection.rs.getString("PassWord"));
				driver.setStuNum(Integer.parseInt(mysql_Connection.rs.getString("StuNum")));
				driver.setNationalNum(Integer.parseInt(mysql_Connection.rs.getString("NationalNum")));
				driver.setGender(mysql_Connection.rs.getString("Gender"));
				driver.setId(mysql_Connection.rs.getInt("driver_id"));
				
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
		
// ******************************************************************************************************************
		
		@Override
		public boolean DeleteDriver(int driver_id) {
			
			if(driverDAO.DeleteDriver(driver_id)) {
			
				String sql;
				
				sql = "DELETE FROM `user` WHERE driver_id = "+driver_id+";";
				
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
		
// ******************************************************************************************************************
		@Override
		public Driver EditDriverInfo(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
				int NationalNum, String Gender , int driver_id) {
			
			String sql;
			
			Driver driver = new Driver();

			sql = "UPDATE user SET FirstName = '"+FirstName+"' , LastName = '"+LastName+"' , UserName = '"+UserName+"' ,"
					+ " PassWord='"+PassWord+"' , StuNum = '"+StuNum+"', NationalNum = '"+NationalNum+"', Gender = '" +Gender+"'"
							+ " WHERE driver_id = "+driver_id+";";

			int count = -1000 ;
			
			try {

				count = mysql_Connection.stmt.executeUpdate(sql);
				
				mysql_Connection.conn.commit();
				
				driver = ShowDriver(driver_id);
				
			} catch (SQLException e) {

				if (mysql_Connection.conn != null || count == -1000 || count < 7)

					try {

						mysql_Connection.conn.rollback();

					} catch (SQLException e1) {

						e1.printStackTrace();

						return null;
					}

				e.printStackTrace();

				return null;
			}
			
			return driver;
		}
		
// ******************************************************************************************************************
		@Override
		public Driver EditDriverCar(String carName , int sitNum , int driver_id) {
			
			Driver driver ,temp;
			
			temp = driverDAO.EditCar(carName, sitNum, driver_id) ;
			
			if(temp != null) {
				
				driver = ShowDriver(driver_id);
				
				return driver;
				
			}
			else {
				return null;
			}
		}
		
// ******************************************************************************************************************
		@Override
		public Driver EditDriverRate(Rate rate , int driver_id) {
			
			Driver driver ,temp;
			
			temp = driverDAO.EditRate(rate, driver_id);
			
			if(temp != null) {
				
				driver = ShowDriver(driver_id);
				
				return driver;
				
			}
			else {
				return null;
			}
		}
// ******************************************************************************************************************
				
		public Driver AddNewTripDriver(int Sits , double sitPrice , int driver_id) {
			
			Driver temp , driver;
			
			temp = new Driver();
			temp = driverDAO.AddNewTrip(Sits, sitPrice, driver_id);
			
			if(temp != null) {
				
				driver = new Driver();
				driver = ShowDriver(driver_id);
				driver.setTrips(temp.getTrips());
				driver.setCar(temp.getCar());
				driver.setRate(temp.getRate());
				
				return driver;
				
			}
			else {
				return null;
			}
			
		}
// ******************************************************************************************************************
		
	public Driver DeleteTripDriver(int driver_id , int trip_id) {
		
		if(driverDAO.DeleteTripDriver(driver_id, trip_id)) {
			Driver driver = new Driver();
			driver = driverDAO.ShowDriver(driver_id);
			return driver;
		}
		else {
			System.out.println("driverdao delete false");
			return null;
		}
		
	}
	
// ******************************************************************************************************************
	
	public Driver EditTripInfoDriver(int Sits , double sitPrice , int trip_id , int driver_id) {
		
		Driver temp = new Driver();
		
		temp = driverDAO.EditTripInfo(Sits, sitPrice, trip_id, driver_id);
		
		if(temp != null) {
			Driver driver = new Driver();
			driver = ShowDriver(driver_id);
			return driver;
		}
		
		else {
			return null;
		}
	}
	
// ******************************************************************************************************************
	
	public Driver EditTripLocationDriver(String origin, String destination , int trip_id , int driver_id) {
		
		Driver temp = new Driver();
		
		temp = driverDAO.EditTripLocation(origin, destination, trip_id, driver_id);
		
		if(temp != null) {
			Driver driver = new Driver();
			driver = ShowDriver(driver_id);
			return driver;
		}
		
		else {
			return null;
		}
		
	}
	
	// ******************************************************************************************************************
	
	public Driver EditTripPaymentDriver(double Price , int trip_id , int driver_id) {
		
		Driver temp = new Driver();
		
		temp = driverDAO.EditTripPayment(Price, trip_id, driver_id);
		
		if(temp != null) {
			Driver driver = new Driver();
			driver = ShowDriver(driver_id);
			return driver;
		}
		
		else {
			return null;
		}
		
	}
	
	// ******************************************************************************************************************
	
	public Driver PayPaymentDriver(int trip_id , int driver_id) {
		Driver temp = new Driver();
		
		temp = driverDAO.PayPayment(trip_id, driver_id);
		
		if(temp != null) {
			Driver driver = new Driver();
			driver = ShowDriver(driver_id);
			return driver;
		}
		
		else {
			return null;
		
		}
		
	}
	
	// ******************************************************************************************************************
	
	public List<Trip> SearchTrip(String origin, String destination){
		return passengerDAO.SearchTrip(origin, destination);
	}
	
	// ******************************************************************************************************************
	
	public User Login(String userName , String passWord) {
		
		String sql ;
		
		sql = "SELECT * FROM `user` WHERE `UserName`= '"+userName+"' AND `PassWord`= '"+passWord+"' LIMIT 1;";
		
		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			mysql_Connection.rs.next();
			
			User user = new User();
			
			user.setId(mysql_Connection.rs.getInt("ID"));
			user.setRole(mysql_Connection.rs.getInt("Role"));
			
			return user;

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
	
	// ******************************************************************************************************************
	public Passenger LoginPassenger(int user_id) {
		
		String sql;
		sql = "SELECT `passenger_id` FROM `user` WHERE `ID`="+user_id+";";
		
		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			mysql_Connection.rs.next();
			
			return ShowPassenger(mysql_Connection.rs.getInt("passenger_id"));

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
	// ******************************************************************************************************************
	public Driver LoginDriver(int user_id) {
		
		String sql;
		sql = "SELECT `driver_id` FROM `user` WHERE `ID` ="+user_id+" ;";
		
		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			mysql_Connection.rs.next();
			
			return ShowDriver(mysql_Connection.rs.getInt("driver_id"));

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
	// ******************************************************************************************************************
	
	// ******************************************************************************************************************
	
}
