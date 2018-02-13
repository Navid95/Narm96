package DAO_Implementation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAOs.Location_DAO;
import DAOs.Payment_DAO;
import DAOs.Trip_DAO;
import DataBase_Connection.mysql_Connection;
import Trip.Location;
import Trip.Payment;
import Trip.Trip;

public class Trip_DAO_Implementation implements Trip_DAO {
	
	private mysql_Connection mysql_Connection;
	private Location_DAO locationDao;
	private Payment_DAO payment_DAO;
	
	public Trip_DAO_Implementation() throws ClassNotFoundException, SQLException {
		
		mysql_Connection = new mysql_Connection();
		locationDao = new Location_DAO_Implementation();
		payment_DAO = new Payment_DAO_Implementation();
		
	}
	
//	**************************************************************************************************
	
	@Override
	public Trip NewTrip(int sits , double sitPrice , int driver_id) {
		
		String sql;
		
		Location location = locationDao.CreateNew("Origin", "destination") ;
		Payment payment = payment_DAO.CreateNew(0, false);
		Trip trip;
		
		if(location != null && payment != null) {
			
			sql = "INSERT INTO `trip` (`ID`, `Sits`, `SitPrice`, `driver_id`, `payment_id`, `location_id`) VALUES "
					+ "(NULL, '4', '3500', '"+driver_id+"', '"+payment.getId()+"', '"+location.getId()+"');";
			
			try {
				
				mysql_Connection.stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
				mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
				mysql_Connection.conn.commit();
	
				int autoIncKeyFromApi = -1;
	
				if (mysql_Connection.rs.next()) {
	
					autoIncKeyFromApi = mysql_Connection.rs.getInt(1);
					
					if (autoIncKeyFromApi != -1) {
						
						trip = new Trip();
						trip = ShowTrip(autoIncKeyFromApi);
						
						return trip;
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
	
					} catch (SQLException e1) {
	
						e1.printStackTrace();
	
						return null;
					}
	
				e.printStackTrace();
	
				return null;
			}
		}else {
			return null;
		}
		
	}
	
//	**************************************************************************************************
	@Override
	public Trip ShowTrip(int trip_id) {
		
		String sql;
		
		sql = "SELECT * FROM `trip` WHERE ID = "+trip_id+";";
		
		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			mysql_Connection.rs.next();
			
			Location location = locationDao.Show(mysql_Connection.rs.getInt("location_id"));
			Payment payment = payment_DAO.Show(mysql_Connection.rs.getInt("payment_id"));
			
			if(location != null && payment != null) {
			
				Trip trip = new Trip();
				
				trip.setId(mysql_Connection.rs.getInt("ID"));
				trip.setSits(mysql_Connection.rs.getInt("Sits"));
				trip.setSitPrice(mysql_Connection.rs.getDouble("SitPrice"));
				trip.setLocation(location);
				trip.setPayment(payment);
				
				return trip;
				
			}
			else {
				return null;
			}
			
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

//	**************************************************************************************************
	
	public List<Trip> GetDriverTrips(int driver_id){
		
		String sql;
		
		sql = "SELECT * FROM `trip` WHERE `driver_id`="+driver_id+";";
		
		List<Trip> trips;
		
		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			trips = new ArrayList<Trip>();
			
			while(mysql_Connection.rs.next()) {
				
				System.out.println("tripDAO locationID : "+mysql_Connection.rs.getInt("location_id"));
				System.out.println("tripDAO paymentID : "+mysql_Connection.rs.getInt("payment_id"));
				
				Location location = locationDao.Show(mysql_Connection.rs.getInt("location_id"));
				Payment payment = payment_DAO.Show(mysql_Connection.rs.getInt("payment_id"));
				
				if(location != null && payment != null) {
				
					Trip trip = new Trip();
					
					trip.setId(mysql_Connection.rs.getInt("ID"));
					trip.setSits(mysql_Connection.rs.getInt("Sits"));
					trip.setSitPrice(mysql_Connection.rs.getDouble("SitPrice"));
					trip.setLocation(location);
					trip.setPayment(payment);
					
					trips.add(trip);
					
				}
				else {
					return null;
				}
				
			}
			
			return trips;
			
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
	
//	**************************************************************************************************
	
	public boolean DeleteTrip(int trip_id) {
		
		String sql;
		
		Trip trip = new Trip();
		trip = ShowTrip(trip_id);
		
		if (locationDao.Delete(trip.getLocation().getId()) && payment_DAO.Delete(trip.getPayment().getId())) {
			
			sql = "DELETE FROM `trip` WHERE `trip`.`ID` = "+trip_id+";";
			
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
			System.out.println("tripDao no delet location and payment");
			return false;
		}
		
		
	}
	
//	**************************************************************************************************
	
	public Trip EditTripInfo(int Sits , double sitPrice , int trip_id ) {
		
		String sql;
		
		sql = "UPDATE `trip` SET `Sits` = '"+Sits+"', `SitPrice` = '"+sitPrice+"' WHERE `trip`.`ID` = "+trip_id+";";
		
		Trip trip ;
		
		int count = -1000 ;
		
		try {

			count = mysql_Connection.stmt.executeUpdate(sql);
			
			mysql_Connection.conn.commit();
			
			trip = new Trip();
			
			trip = ShowTrip(trip_id);
			
			return trip;
			
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
		
	}
	
//	**************************************************************************************************
	
	public Trip EditTripLocation(String origin, String destination , int trip_id) {
		
		Location location;
		location = new Location();
		
		Trip trip = new Trip();
		trip = ShowTrip(trip_id);
		
		location = locationDao.EditLocation(origin, destination, trip.getLocation().getId());
		
		if (location != null) {
			
			trip.setLocation(location);
			return trip;
			
		} else {
			return null;
		}
	}
	
//	**************************************************************************************************
	
	public Trip EditTripPayment(double Price , int trip_id) {
		
		Trip trip = new Trip();
		trip = ShowTrip(trip_id);
		
		Payment payment = new Payment();
		payment = payment_DAO.EditPrice(Price, trip.getPayment().getId());
		
		if (payment != null) {
			
			trip.setPayment(payment);
			return trip;
			
		} else {
			
			return null;
		}
		
	}
	
//	**************************************************************************************************
	
	public Trip PayPayment(int trip_id) {
		
		Trip trip = new Trip();
		trip = ShowTrip(trip_id);
		
		Payment payment = new Payment();
		payment = payment_DAO.Pay(trip.getPayment().getId());
		
		if (payment != null) {
			
			trip.setPayment(payment);
			return trip;
			
		} else {
			
			return null;
		}
	}
//	**************************************************************************************************
	
	public List<Trip> SearchReqableTrips(){	

		List<Trip> trips = new ArrayList<Trip>();
		
		String sql = "SELECT `ID` FROM `trip`;";
		
		
		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			List<Integer> trip_ids = new ArrayList<Integer>();
			
			while(mysql_Connection.rs.next()) {
				trip_ids.add(mysql_Connection.rs.getInt("ID"));
			}
			
			while(trip_ids.iterator().hasNext()) {
				
				trips.add(ShowTrip(trip_ids.iterator().next()));
				
			}
			
			return trips;
			
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
	
//	**************************************************************************************************
	
//	**************************************************************************************************
	
//	**************************************************************************************************
}
