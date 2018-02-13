package DAO_Implementation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAOs.Payment_DAO;
import DataBase_Connection.mysql_Connection;
import Trip.Payment;
import users.Passenger;

public class Payment_DAO_Implementation implements Payment_DAO {

	private mysql_Connection mysql_Connection;
	
	public Payment_DAO_Implementation() throws ClassNotFoundException, SQLException {
		mysql_Connection = new mysql_Connection();
	}
	
	// *********************************************************************************************************
	
	@Override
	public Payment CreateNewEmpty() {
		
		String sql;
		
		Payment payment;
		
		sql = "INSERT INTO `payment` (`ID`, `State`, `Price`) VALUES (NULL, NULL, NULL);";

		try {

			mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
			mysql_Connection.conn.commit();

			int autoIncKeyFromApi = -1;
			
			if (mysql_Connection.rs.next()) {

				autoIncKeyFromApi = mysql_Connection.rs.getInt(1);
				
				if (autoIncKeyFromApi != -1) {
					
					payment = new Payment();

					payment.setId(autoIncKeyFromApi);

					return payment;

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

	}
	
	// *********************************************************************************************************

	@Override
	public Payment CreateNew(double Price, boolean State) {
		
		String sql;
		Payment payment;
		
		sql = "INSERT INTO payment (State , Price) VALUES ("+State+" , "+Price+");";

		try {

			mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
			mysql_Connection.conn.commit();
			
			int autoIncKeyFromApi = -1;

			if (mysql_Connection.rs.next()) {

				autoIncKeyFromApi = mysql_Connection.rs.getInt(1);
				
				if (autoIncKeyFromApi != -1) {
					
					payment = Show(autoIncKeyFromApi);

					return payment;

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
		
	}

	// *********************************************************************************************************
	
	@Override
	public Payment Show(int id) {
		
		String sql;
		
		Payment payment;
		
		sql = "SELECT `State` , `Price` FROM `payment` WHERE ID = "+id+";";
		
		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();
			
			mysql_Connection.rs.next();
			
			payment = new Payment();
			payment.setPrice(mysql_Connection.rs.getDouble("Price"));
			payment.setState(mysql_Connection.rs.getBoolean("State"));
			payment.setId(id);
			
			return payment;


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
	public Payment EditPrice(double Price , int id) {
		
		String sql;
		Payment payment;
		
		sql = "UPDATE payment SET Price = "+Price+ " WHERE ID = "+id+";";
		
		int count = -1000 ;
		
		try {

			count = mysql_Connection.stmt.executeUpdate(sql);
			
			mysql_Connection.conn.commit();
			
			payment = new Payment();
			
			payment = Show(id);
			
			return payment;
			
		} catch (SQLException e) {

			if (mysql_Connection.conn != null || count == -1000 || count < 1)

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
	public Payment Pay(int id) {
		
		String sql;
		Payment payment;
		
		payment = Show(id);
		
		if(payment.getState() == false) {
					
			sql = "UPDATE payment SET State = "+true+ " WHERE ID = "+id+";";
			
			int count = -1000 ;
			
			try {
	
				count = mysql_Connection.stmt.executeUpdate(sql);
				
				mysql_Connection.conn.commit();
				
				payment = new Payment();
				
				payment = Show(id);
				
			} catch (SQLException e) {
	
				if (mysql_Connection.conn != null || count == -1000 || count < 1)
	
					try {
	
						mysql_Connection.conn.rollback();
	
					} catch (SQLException e1) {
	
						e1.printStackTrace();
	
						return null;
					}
	
				e.printStackTrace();
	
				return null;
			}
					
			return payment;
		}
		else {
			
			return null;
			
		}
	}
	
	// *********************************************************************************************************
		@Override
		public boolean Delete(int id) {
			
			String sql;
			
			sql = "DELETE FROM `payment` WHERE `payment`.`ID` ="+ id+";";
			
			int result = -1;
			
			try {
				
				result = mysql_Connection.stmt.executeUpdate(sql);
				
				mysql_Connection.conn.commit();
				
				if (result != 1) {
					
					return false;
					
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
				return false;
			}
			
			return true;
		}
		
//	**************************************************************************************************
		
		public List<Payment> SearchFalseStates(){
			
			String sql;
			
			sql = "SELECT * FROM `payment` WHERE `State`= false;";
			
			List<Payment> payments;
			
			try {
				
				mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
				
				mysql_Connection.conn.commit();
				
				payments = new ArrayList<Payment>();
				
				while (mysql_Connection.rs.next()) {
					
					Payment payment = new Payment();
					payment = Show(mysql_Connection.rs.getInt("ID"));
					
					payments.add(payment);
				}
				
				return payments;

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
}
