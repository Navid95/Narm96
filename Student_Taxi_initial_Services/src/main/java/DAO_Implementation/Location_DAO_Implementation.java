package DAO_Implementation;

import java.sql.SQLException;
import java.sql.Statement;

import DAOs.Location_DAO;
import DataBase_Connection.mysql_Connection;
import Trip.Location;

public class Location_DAO_Implementation implements Location_DAO {
	
	private mysql_Connection mysql_Connection;
	
	public Location_DAO_Implementation() throws ClassNotFoundException, SQLException   {
		
		mysql_Connection = new mysql_Connection();
		
	}
	
	// *********************************************************************************************************
		@Override
		public Location CreateNew(String origin, String destination) {

			String sql;

			Location location;

			sql = "INSERT INTO `location` (`Origin`, `Destination`) VALUES ( '"+origin+"', '"+destination+"');";

			try {
				
				mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
				mysql_Connection.conn.commit();

				int autoIncKeyFromApi = -1;

				if (mysql_Connection.rs.next()) {

					autoIncKeyFromApi = mysql_Connection.rs.getInt(1);
					
					if (autoIncKeyFromApi != -1) {

						location = Show(autoIncKeyFromApi);
						
						return location;

					} else {

						return null;

					}

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
		public Location Show(int id) {

			String sql;

			Location location;

			sql = "SELECT `Origin`,`Destination` FROM `location` WHERE ID = "+id+";";

			try {
				
				mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
				
				mysql_Connection.conn.commit();
				
				mysql_Connection.rs.next();
				
				location = new Location();
				
				location.setOrigin(mysql_Connection.rs.getString("Origin"));
				
				location.setDestination(mysql_Connection.rs.getString("Destination"));
				
				location.setId(id);
				
				return location;

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
		
//		// *********************************************************************************************************
		@Override
		public Location EditLocation(String origin, String destination, int id) {
			
			String sql;
			
			Location location = new Location();

			sql = "UPDATE location SET Origin = '"+origin+"' , Destination = '"+destination+"'  WHERE ID = "+id+";";

			int count = -1000 ;
			
			try {

				count = mysql_Connection.stmt.executeUpdate(sql);
				
				mysql_Connection.conn.commit();
				
				location = Show(id);
				
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
			
			return location;
		}
		
//		// *********************************************************************************************************
		@Override
		public boolean Delete(int id) {
			
			String sql;
			
			sql = "DELETE FROM `location` WHERE `location`.`ID` = "+id+";";
			
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
