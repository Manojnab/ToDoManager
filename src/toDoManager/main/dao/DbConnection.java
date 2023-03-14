package toDoManager.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnection {
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver loaded");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentdb","root","root");
	            System.out.println("Connected");
			
		}
		catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		catch (SQLException e) {
            e.printStackTrace();
        }
		return conn;
	}
}

