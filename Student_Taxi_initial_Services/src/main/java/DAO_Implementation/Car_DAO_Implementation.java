package DAO_Implementation;

import java.sql.SQLException;
import java.sql.Statement;

import DAOs.Car_DAO;
import DataBase_Connection.mysql_Connection;
import users.Car;

public class Car_DAO_Implementation implements Car_DAO {
	
	public Car_DAO_Implementation() throws ClassNotFoundException, SQLException {
		new mysql_Connection();
	}
	
	// *********************************************************************************************************

	@Override
	public Car CreateNew(Car car) {
		
		String sql;

		sql = " INSERT INTO car (CarName , SitNum) VALUES ("+car.getCarName()+","+car.getSitNum()+") ;";

		try {

			mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
			mysql_Connection.conn.commit();
			
			int autoIncKeyFromApi = -1;
			
			if (mysql_Connection.rs.next()) {

				autoIncKeyFromApi = mysql_Connection.rs.getInt(1);
				
				if (autoIncKeyFromApi != -1) {

					car.setId(autoIncKeyFromApi);

					return car;

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
	public Car CreateNew(String carName , int sitNum) {
		
		String sql;
		Car car;

		sql = " INSERT INTO car (CarName , SitNum) VALUES ('"+carName+"',"+sitNum+") ;";

		try {

			mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
			mysql_Connection.conn.commit();
			
			car = new Car();
			
			car.setCarName(carName);
			car.setSitNum(sitNum);
			
			int autoIncKeyFromApi = -1;
			
			if (mysql_Connection.rs.next()) {

				autoIncKeyFromApi = mysql_Connection.rs.getInt(1);
				
				if (autoIncKeyFromApi != -1) {

					car.setId(autoIncKeyFromApi);

					return car;

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

}
