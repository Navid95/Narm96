package DataBase_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysql_Connection {

	// Attributes

	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3307/Student_Taxi";
	public static Connection conn = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

	// Database credentials

	public static final String USER = "root";
	public static final String PASS = "abcd";
	
	public mysql_Connection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(false);
		stmt = conn.createStatement();
		
	}
	
}
