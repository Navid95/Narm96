package DAO_Implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DAOs.Passenger_DAO;
import users.Passenger;

public class Passenger_DAO_Implementation implements Passenger_DAO {

	// Attributes

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3307/Student_Taxi";
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;

	// Database credentials

	static final String USER = "root";
	static final String PASS = "abcd";

	// Initiate DB connection

	public Passenger_DAO_Implementation() {
		try {
			init();
			
		System.out.println("\n\n\n\n\n\n********************************************* DB Connection initiated *********************************************	\n\n\n\n\n\n");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(
					"\n\n\n\n\n\n********************************************* Could not initiate DB Connection *********************************************\n\n\n\n\n\n");
			e.printStackTrace();
		}
	}

	public void init() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(false);
	}

	@Override
	public Passenger CreateNew(Passenger passenger) {

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql;
		sql = "INSERT INTO `passenger`( `FirstName`, `LastName`, `Gender`, `UserName`, `PassWord`, `StuNum`, `NationalNum`)"
				+ " VALUES ('" + passenger.getFirstName() + "','" + passenger.getLastName() + "','"
				+ passenger.getGender() + "'," + "'" + passenger.getUserName() + "','" + passenger.getPassWord() + "',"
				+ passenger.getStuNUm() + "," + passenger.getNationalNum() + ")";
		try {
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null)
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			e.printStackTrace();
		}

		int autoIncKeyFromApi = -1;
		try {
			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
			} else {
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (autoIncKeyFromApi != -1) {
			passenger.setId(autoIncKeyFromApi);
			return passenger;
		} else {
			return null;
		}

	}

	public void test() {
		try {
			init();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		Passenger passenger = new Passenger("Navid", "MHK", "nnn", "mmm", 92463142, 1361737131, "Male");
		passenger = CreateNew(passenger);

		System.out.println("Key returned from getGeneratedKeys():" + passenger.getId());
	}
}
