package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	static String URL = "jdbc:mysql://localhost:3306/recipes";
	static String USER = "root";
	static String PASS = "rootadmin";
	static String SELECT = "SELECT";
	static String INSERT = "INSERT";
	static String DELETE = "DELETE";
	
	public ResultSet connectAndExecute(String query,String type) {
		ResultSet rs = null;
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(URL,USER,PASS);
			Statement stmt = conn.createStatement();
			switch(type) {
				case "SELECT":
					rs = stmt.executeQuery(query);
					break;
				case "INSERT":
					result = stmt.executeUpdate(query);
					break;
				case "DELETE":
					result = stmt.executeUpdate(query);
					break;
				default:
					throw new Exception("Invalid type provided for query");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return rs;
	}
}
