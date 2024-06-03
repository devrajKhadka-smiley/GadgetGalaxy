package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	/**
	 * Establishes a connection to the database.
	 * 
	 * @return A Connection object representing the database connection.
	 * @throws ClassNotFoundException if the MySQL JDBC driver class is not found.
	 * @throws SQLException           if there is an error connecting to the
	 *                                database.
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/gadgetgalaxy";
		String user = "root";
		String pass = "";

		return DriverManager.getConnection(url, user, pass);
	}

}
