package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import utils.DatabaseConnection;
import utils.PasswordHashing;

public class AdminDao {

	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private boolean isSuccess;

	public AdminDao() throws SQLException, ClassNotFoundException {
		conn = DatabaseConnection.getConnection();
	}

	/**
	 * Retrieves all admin records from the database and returns a list of admins.
	 * 
	 * @return A List of Admin objects representing all user type admin in the
	 *         database.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public List<Admin> getAllAdmin() throws SQLException {

		// SQL Query to retrieve all admin records
		statement = conn.prepareStatement("select * from admin");
		resultSet = statement.executeQuery();

		// Initializing a list of Class Admin
		List<Admin> listOfAdmin = new ArrayList<Admin>();

		while (resultSet.next()) {
			// Creating a object of Admin
			Admin admin = new Admin();

			admin.setAdminId(resultSet.getInt("adminId"));
			admin.setAdminUsername(resultSet.getString("adminUsername"));
			admin.setAdminEmail(resultSet.getString("adminEmail"));
			admin.setAdminContact(resultSet.getLong("adminContact"));
			admin.setPassword(resultSet.getString("password"));

			// Adding the admin to the list
			listOfAdmin.add(admin);
		}

		// Returning a list of admin
		return listOfAdmin;
	}

	/**
	 * Retrieves login information for an admin from the database and returns true or false based on the matching of data.
	 * 
	 * @param username: username of type String.
	 * @param password: The password of type String.
	 * @return true if the provided username and password match those stored in the
	 *         database, false otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public boolean getAdminLoginInfo(String username, String password) throws SQLException {

		statement = conn.prepareStatement("SELECT * FROM admin WHERE adminUsername = ?");

		statement.setString(1, username);
		resultSet = statement.executeQuery();

		isSuccess = false;

		if (resultSet.next()) {
			String passwordFromDb = resultSet.getString("password");
			if (PasswordHashing.checkPassword(password, passwordFromDb)) {
				isSuccess = true;
			} else {
				isSuccess = false;
			}
		} else {
			isSuccess = false;
		}
		return isSuccess;
	}

}