package service;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import utils.DatabaseConnection;
import utils.PasswordHashing;

public class ClientDao {
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private boolean isSuccess;
	private String query;

	public ClientDao() throws SQLException, ClassNotFoundException {
		conn = DatabaseConnection.getConnection();
	}

	/**
	 * Retrieves login information for a client from the database and returns value
	 * accordingly.
	 * 
	 * @param username The username of type String.
	 * @param password The password of type string.
	 * @return true if the provided username and password match those stored in the
	 *         database, false otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public boolean getClientLoginInfo(String username, String password) throws SQLException {
		query = "SELECT * FROM client WHERE clientUsername = ?";
		statement = conn.prepareStatement(query);

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

	/**
	 * Retrieves a list of all client records from the database.
	 * 
	 * @return A List of Client objects representing all clients in the database.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public List<Client> getListOfClient() throws SQLException {
		query = "SELECT * FROM client";
		statement = conn.prepareStatement(query);
		resultSet = statement.executeQuery();

		List<Client> listOfClients = new ArrayList<Client>();

		while (resultSet.next()) {
			Client client = new Client();

			client.setClientId(resultSet.getInt("clientId"));
			client.setFullName(resultSet.getString("fullName"));
			client.setClientUsername(resultSet.getString("clientUsername"));
			client.setEmail(resultSet.getString("email"));
			client.setPhoneNumber(resultSet.getLong("phoneNumber"));
			client.setLocation(resultSet.getString("location"));
			client.setPassword(resultSet.getString("password"));
			client.setImage_data(
					resultSet.getBlob("image_data").getBytes(1, (int) resultSet.getBlob("image_data").length()));
			client.setImage_name(resultSet.getString("image_name"));

			listOfClients.add(client);
		}
		return listOfClients;
	}

	/**
	 * Saves client information into the database.
	 * 
	 * @param client The Client object containing the information to be saved.
	 * @return true if the client information is successfully saved, false
	 *         otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public boolean saveClient(Client client) throws SQLException {
		query = "select count(*) from client";

		statement = conn.prepareStatement(query);
		resultSet = statement.executeQuery();

		if (resultSet.next()) {

			boolean isFind = checkBox(client);

			if (isFind) {
				isSuccess = false;
			} else {
				int row = insertClientData(client);
				if (row > 0) {
					isSuccess = true;
				}
			}
		}

		return isSuccess;
	}

	/**
	 * Checks if a client already exists in the database based on username, email,
	 * and phone number.
	 * 
	 * @param client The Client object to be checked.
	 * @return true if a matching record is found, false otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	private boolean checkBox(Client client) throws SQLException {

		query = "select clientUsername, email, phoneNumber from client";

		statement = conn.prepareStatement(query);

		resultSet = statement.executeQuery();

		boolean isFind = false;

		while (resultSet.next()) {
			String usernameFromDb = resultSet.getString("clientUsername");
			String emailFromDb = resultSet.getString("email");
			long phoneNumberFromDb = resultSet.getLong("phoneNumber");

			if (client.getClientUsername().equals(usernameFromDb) || client.getEmail().equals(emailFromDb)
					|| client.getPhoneNumber() == phoneNumberFromDb) {
				isFind = true;
				break;
			}
		}

		return isFind;
	}

	/**
	 * Inserts client data into the database.
	 * 
	 * @param client The Client object containing the data to be inserted.
	 * @return The number of rows affected by the insert operation.
	 * @throws SQLException if there is an error accessing the database.
	 */
	private int insertClientData(Client client) throws SQLException {

		query = "insert into client(fullName, clientUsername, email, phoneNumber, location, password, image_data, image_name)"
				+ "values(?,?,?,?,?,?,?,?)";

		Blob blob = conn.createBlob();
		blob.setBytes(1, client.getImage_data());
		statement = conn.prepareStatement(query);
		statement.setString(1, client.getFullName());
		statement.setString(2, client.getClientUsername());
		statement.setString(3, client.getEmail());
		statement.setLong(4, client.getPhoneNumber());
		statement.setString(5, client.getLocation());
		statement.setString(6, client.getPassword());
		statement.setBlob(7, blob);
		statement.setString(8, client.getImage_name());

		int row = statement.executeUpdate();
		return row;
	}

	/**
	 * Retrieves the client ID based on the provided client username.
	 * 
	 * @param clientUsername The username of the client.
	 * @return The client ID associated with the username, or -1 if no matching
	 *         client is found.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public int getClientByUsername(String clientUsername) throws SQLException {
		int clientId = -1;
		query = "SELECT clientId FROM client WHERE clientUsername = ?";
		statement = conn.prepareStatement(query);

		statement.setString(1, clientUsername);
		resultSet = statement.executeQuery();

		if (resultSet.next()) {
			clientId = resultSet.getInt("clientId");
		}

		return clientId;
	}

	/**
	 * Retrieves details of a client based on the provided client ID.
	 * 
	 * @param clientId The ID of the client.
	 * @return A Client object containing details of the client, or an empty Client
	 *         object if no matching client is found.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public Client getClientDetails(int clientId) throws SQLException {
		Client client = new Client();
		query = "SELECT * FROM client WHERE clientId = ?";
		statement = conn.prepareStatement(query);

		statement.setInt(1, clientId);

		resultSet = statement.executeQuery();

		if (resultSet.next()) {

			client.setClientId(resultSet.getInt("clientId"));
			client.setFullName(resultSet.getString("fullName"));
			client.setClientUsername(resultSet.getString("clientUsername"));
			client.setEmail(resultSet.getString("email"));
			client.setPhoneNumber(resultSet.getLong("phoneNumber"));
			client.setLocation(resultSet.getString("location"));
			client.setPassword(resultSet.getString("password"));
			client.setImage_data(
					resultSet.getBlob("image_data").getBytes(1, (int) resultSet.getBlob("image_data").length()));
			client.setImage_name(resultSet.getString("image_name"));
		}

		return client;
	}

	/**
	 * Updates client information in the database.
	 * 
	 * @param client The Client object containing the updated information.
	 * @return The number of rows affected by the update operation, or 0 if the
	 *         client's username, phone number, or email is already taken.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public int updateClient(Client client) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		if (!isClientTaken(client.getClientUsername(), client.getPhoneNumber(), client.getEmail())) {
			return row;
		} else {
			Blob blob = conn.createBlob();
			blob.setBytes(1, client.getImage_data());

			query = "UPDATE client SET fullName=?, clientUsername=?, email=?, phoneNumber=?, "
					+ "location=?, password=?, image_data=?, image_name=? WHERE clientId=?";
			statement = conn.prepareStatement(query);

			statement.setString(1, client.getFullName());
			statement.setString(2, client.getClientUsername());
			statement.setString(3, client.getEmail());
			statement.setLong(4, client.getPhoneNumber());
			statement.setString(5, client.getLocation());
			statement.setString(6, client.getPassword());
			statement.setBlob(7, blob);
			statement.setString(8, client.getImage_name());
			statement.setInt(9, client.getClientId());

			row = statement.executeUpdate();
			return row;
		}
	}

	/**
	 * Checks if a client with the given username, phone number, and email already
	 * exists in the database.
	 * 
	 * @param clientUsername The username of the client to be checked.
	 * @param phoneNumber    The phone number of the client to be checked.
	 * @param email          The email of the client to be checked.
	 * @return true if a client with the given username, phone number, and email
	 *         already exists, false otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	private boolean isClientTaken(String clientUsername, long phoneNumber, String email) throws SQLException {
		query = "SELECT * FROM client WHERE clientUsername = ? AND phoneNumber = ? AND email = ?";
		statement = conn.prepareStatement(query);
		statement.setString(1, clientUsername);
		statement.setLong(2, phoneNumber);
		statement.setString(3, email);

		resultSet = statement.executeQuery();

		if (resultSet.next()) {
			return true;
		}

		return false;
	}

	/**
	 * Retrieves client details based on the provided client ID.
	 * 
	 * @param clientId The ID of the client.
	 * @return A Client object containing details of the client with the specified
	 *         ID, or an empty Client object if no matching client is found.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public Client getClientById(int clientId) throws SQLException {
		// TODO Auto-generated method stub
		query = "SELECT * FROM client WHERE clientId = ?";
		statement = conn.prepareStatement(query);

		statement.setInt(1, clientId);

		resultSet = statement.executeQuery();
		Client client = new Client();

		if (resultSet.next()) {
			client.setClientId(clientId);
			client.setClientUsername(resultSet.getString("clientUsername"));
			client.setEmail(resultSet.getString("email"));
			client.setFullName(resultSet.getString("fullName"));
			client.setPhoneNumber(resultSet.getLong("phoneNumber"));
			client.setLocation(resultSet.getString("location"));
			client.setPassword(resultSet.getString("password"));

			client.setImage_data(
					resultSet.getBlob("image_data").getBytes(1, (int) resultSet.getBlob("image_data").length()));
			client.setImage_name(resultSet.getString("image_name"));
		}
		return client;
	}
}