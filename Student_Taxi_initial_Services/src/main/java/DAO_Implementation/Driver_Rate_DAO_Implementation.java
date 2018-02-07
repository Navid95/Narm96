package DAO_Implementation;

import java.sql.SQLException;
import java.sql.Statement;

import DAOs.Driver_Rate_DAO;
import DataBase_Connection.mysql_Connection;
import users.Driver_Rate;
import users.Passenger;
import users.Rate;

public class Driver_Rate_DAO_Implementation implements Driver_Rate_DAO {
	
	public Driver_Rate_DAO_Implementation() throws ClassNotFoundException, SQLException {
		new mysql_Connection();
	}
	
	// *********************************************************************************************************
	
	@Override
	public Driver_Rate CreateNew(Rate Rate) {
		
		String sql;
		Driver_Rate rate;

		sql = "INSERT INTO driver_rate (Rate) VALUES ("+Rate.getRate()+");";

		try {

			mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
			mysql_Connection.conn.commit();
			

			int autoIncKeyFromApi = -1;


			if (mysql_Connection.rs.next()) {

				autoIncKeyFromApi = mysql_Connection.rs.getInt(1);

			} else {

				System.out.println("No id returned");
				
			}

			if (autoIncKeyFromApi != -1) {

				rate = Show(autoIncKeyFromApi);

				return rate;

			} else {

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
	
	// *********************************************************************************************************
	
	@Override
	public Driver_Rate Show(int id) {

		String sql;

		Driver_Rate rate;

		sql = "SELECT `Rate` FROM `driver_rate` WHERE ID = "+id+";";

		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			mysql_Connection.rs.next();
			
			rate = new Driver_Rate();
			
			rate.setRate(Rate.calculateRate(mysql_Connection.rs.getDouble("Rate")));
			rate.setId(id);
			
			return rate;

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
	
	public Driver_Rate EditRate(Rate rate , int id) {


		String sql;
		
		Driver_Rate dRate = new Driver_Rate();

		sql = "UPDATE driver_rate SET Rate = "+rate.getRate() +"WHERE ID =" +id+";";

		int count = -1000 ;
		
		try {

			count = mysql_Connection.stmt.executeUpdate(sql);
			
			mysql_Connection.conn.commit();
			
			dRate = Show(id);
			
			return dRate;
			
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
	
	// *********************************************************************************************************
	@Override
	public boolean Delete(int id) {
		
		String sql;
		
		sql = "DELETE FROM `driver_rate` WHERE `driver_rate`.`ID` = "+id+";";
		
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
	
}
