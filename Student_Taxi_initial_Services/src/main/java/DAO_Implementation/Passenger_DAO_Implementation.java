package DAO_Implementation;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import DAOs.Passenger_DAO;
import DAOs.Trip_DAO;
import DataBase_Connection.mysql_Connection;
import Trip.Location;
import Trip.Trip;
import users.Passenger;

public class Passenger_DAO_Implementation implements Passenger_DAO {

	private mysql_Connection mysql_Connection;
	private Trip_DAO trip_DAO;
	
	public Passenger_DAO_Implementation() throws ClassNotFoundException, SQLException {
		mysql_Connection = new mysql_Connection();
		trip_DAO = new Trip_DAO_Implementation();
	}

	
	// *********************************************************************************************************
	
	@Override
	public int NewPassenger() {
		
		String sql;

		sql = "INSERT INTO `passenger` (`passenger_id`) VALUES (NULL);";

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
	
	// *********************************************************************************************************
		@Override
		public boolean DeletePassenger(int passenger_id) {
			
			String sql;
			
			sql = "DELETE FROM `passenger` WHERE passenger_id = "+passenger_id+";";
			
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
	
	// *********************************************************************************************************

	@Override
	public Passenger Show(int id) {

		String sql;

		Passenger passenger;

		sql = "SELECT FirstName,LastName,UserName,PassWord,StuNum,NationalNum,Gender from passenger WHERE ID = " + id
				+ ";";

		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			mysql_Connection.rs.next();
			
			passenger = new Passenger();
			
			passenger.setFirstName(mysql_Connection.rs.getString("FirstName"));
			
			passenger.setLastName(mysql_Connection.rs.getString("LastName"));
			
			passenger.setUserName(mysql_Connection.rs.getString("UserName"));
			
			passenger.setPassWord(mysql_Connection.rs.getString("PassWord"));
			
			passenger.setStuNum(Integer.parseInt(mysql_Connection.rs.getString("StuNum")));
			
			passenger.setNationalNum(Integer.parseInt(mysql_Connection.rs.getString("NationalNum")));
			
			passenger.setGender(mysql_Connection.rs.getString("Gender"));
			
			passenger.setId(id);
			
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
	public List<Passenger> showAll(int offset , int rowNum){
		
		String sql;
		
		List<Passenger> passengers = new ArrayList<Passenger>();
		
		sql = "SELECT * FROM `passenger` LIMIT "+offset+","+rowNum+" ;";
		
		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			while (mysql_Connection.rs.next()) {
				
				Passenger passenger = new Passenger();
				
				passenger.setFirstName(mysql_Connection.rs.getString("FirstName"));
				
				passenger.setLastName(mysql_Connection.rs.getString("LastName"));
				
				passenger.setUserName(mysql_Connection.rs.getString("UserName"));
				
				passenger.setPassWord(mysql_Connection.rs.getString("PassWord"));
				
				passenger.setStuNum(Integer.parseInt(mysql_Connection.rs.getString("StuNum")));
				
				passenger.setNationalNum(Integer.parseInt(mysql_Connection.rs.getString("NationalNum")));
				
				passenger.setGender(mysql_Connection.rs.getString("Gender"));
				
				passenger.setId(Integer.parseInt(mysql_Connection.rs.getString("ID")));
				
				passengers.add(passenger);
				
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
		
		return passengers;
		
	}

//	*****************************************************************************************************
	
	public List<Trip> SearchTrip(String origin, String destination){
		
		return trip_DAO.SearchReqableTrips();
		
	}
	
}
