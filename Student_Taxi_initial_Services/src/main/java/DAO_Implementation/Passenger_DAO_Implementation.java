package DAO_Implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DAOs.Passenger_DAO;
import DataBase_Connection.mysql_Connection;
import users.Passenger;

public class Passenger_DAO_Implementation implements Passenger_DAO {

	public Passenger_DAO_Implementation() throws ClassNotFoundException, SQLException {
		new mysql_Connection();
	}

	public Passenger CreateNew(Passenger passenger) {

		String sql;

		sql = "INSERT INTO `passenger`( `FirstName`, `LastName`, `Gender`, `UserName`, `PassWord`, `StuNum`, `NationalNum`)"
				+ " VALUES ('" + passenger.getFirstName() + "','" + passenger.getLastName() + "','"
				+ passenger.getGender() + "'," + "'" + passenger.getUserName() + "','" + passenger.getPassWord() + "',"
				+ passenger.getStuNUm() + "," + passenger.getNationalNum() + ")";

		try {

			mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
			mysql_Connection.conn.commit();

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

		int autoIncKeyFromApi = -1;

		try {

			if (mysql_Connection.rs.next()) {

				autoIncKeyFromApi = mysql_Connection.rs.getInt(1);

			} else {

				System.out.println("No id returned");

			}

		} catch (SQLException e) {

			e.printStackTrace();

			return null;

		}

		if (autoIncKeyFromApi != -1) {

			passenger.setId(autoIncKeyFromApi);

			return passenger;

		} else {

			return null;

		}

	}

	// *********************************************************************************************************

	public Passenger CreateNew(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender) {

		String sql;

		Passenger passenger;

		sql = "INSERT INTO `passenger`( `FirstName`, `LastName`, `Gender`, `UserName`, `PassWord`, `StuNum`, `NationalNum`)"
				+ " VALUES ('" + FirstName + "','" + LastName + "','" + Gender + "'," + "'" + UserName + "','"
				+ PassWord + "'," + StuNum + "," + NationalNum + ")";

		try {

			mysql_Connection.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			mysql_Connection.rs = mysql_Connection.stmt.getGeneratedKeys();
			mysql_Connection.conn.commit();

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

		passenger = new Passenger(FirstName, LastName, UserName, PassWord, StuNum, NationalNum, Gender);

		int autoIncKeyFromApi = -1;

		try {

			if (mysql_Connection.rs.next()) {

				autoIncKeyFromApi = mysql_Connection.rs.getInt(1);

			} else {

				System.out.println("No id returned");
				
				return null;
				
			}

		} catch (SQLException e) {

			e.printStackTrace();

			return null;

		}

		if (autoIncKeyFromApi != -1) {

			passenger.setId(autoIncKeyFromApi);

			return passenger;

		} else {

			return null;

		}

	}

	// *********************************************************************************************************

	public Passenger Show(int id) {

		String sql;

		Passenger passenger;

		sql = "SELECT FirstName,LastName,UserName,PassWord,StuNum,NationalNum,Gender from passenger WHERE ID = " + id
				+ ";";

		try {
			
			mysql_Connection.rs = mysql_Connection.stmt.executeQuery(sql);
			
			mysql_Connection.conn.commit();

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
		
		passenger = new Passenger();
		
		try {
			
			mysql_Connection.rs.next();
			
			passenger.setFirstName(mysql_Connection.rs.getString("FirstName"));
			
			passenger.setLastName(mysql_Connection.rs.getString("LastName"));
			
			passenger.setUserName(mysql_Connection.rs.getString("UserName"));
			
			passenger.setPassWord(mysql_Connection.rs.getString("PassWord"));
			
			passenger.setStuNum(Integer.parseInt(mysql_Connection.rs.getString("StuNum")));
			
			passenger.setNationalNum(Integer.parseInt(mysql_Connection.rs.getString("NationalNum")));
			
			passenger.setGender(mysql_Connection.rs.getString("Gender"));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
			
		}
		
		passenger.setId(id);
		
		return passenger;

	}
	
	// *********************************************************************************************************
	
	public Passenger EditInfo(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender , int id) {
		
		String sql;
		
		Passenger passenger = new Passenger();

		sql = "UPDATE passenger SET FirstName = '"+FirstName+"' , LastName = '"+LastName+"' , UserName = '"+UserName+"' ,"
				+ " PassWord='"+PassWord+"' , StuNum = '"+StuNum+"', NationalNum = '"+NationalNum+"', Gender = '" +Gender+"' WHERE ID = "+id+";";

		int count = -1000 ;
		
		try {

			count = mysql_Connection.stmt.executeUpdate(sql);
			
			mysql_Connection.conn.commit();
			
			passenger.setFirstName(FirstName);
			
			passenger.setLastName(LastName);
			
			passenger.setUserName(UserName);
			
			passenger.setPassWord(PassWord);
			
			passenger.setStuNum(StuNum);
			
			passenger.setNationalNum(NationalNum);
			
			passenger.setGender(Gender);
			
			
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
	
}
