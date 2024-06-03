package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Orders;
import utils.DatabaseConnection;

public class OrderDao {
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private boolean isSuccess;
	private String query;

	public OrderDao() throws ClassNotFoundException, SQLException {
		conn = DatabaseConnection.getConnection();
	}

	/**
	 * Inserts a new order into the database.
	 * 
	 * @param order The Orders object containing the order details.
	 * @return true if the order is successfully inserted, false otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public boolean insertOrder(Orders order) throws SQLException {
		isSuccess = false;

		query = "INSERT INTO orders (orderDate, totalAmount, orderStatus) VALUES (?,?,?)";
		statement = conn.prepareStatement(query);
		statement.setString(1, order.getOrderDate());
		statement.setInt(2, order.getTotalAmount());
		statement.setString(3, order.getOrderStatus());

		int row = statement.executeUpdate();

		if (row > 0) {
			isSuccess = true;
		}

		return isSuccess;
	}

	/**
	 * Retrieves the ID of the most recently inserted order from the database.
	 * 
	 * @return The ID of the most recently inserted order, or -1 if no orders exist.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public int getOrderId() throws SQLException {
		// TODO Auto-generated method stub
		int orderId = -1;
		query = "SELECT orderId FROM orders WHERE orderId = (" + "SELECT MAX(orderId) FROM orders" + ")";
		statement = conn.prepareStatement(query);

		resultSet = statement.executeQuery();

		if (resultSet.next()) {
			orderId = resultSet.getInt("orderId");
		}

		return orderId;
	}

	/**
	 * Retrieves a list of all orders from the database, including details such as
	 * order ID, client username, order date, and order status.
	 * 
	 * @return A List of Orders objects representing all orders in the database.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public List<Orders> getListOfOrders() throws SQLException {
		query = "SELECT o.orderId, c.clientUsername, o.orderDate, o.orderStatus " + "FROM orders o "
				+ "JOIN order_detail od " + "ON o.orderId = od.orderId " + "JOIN client c "
				+ "ON od.clientId = c.clientId ORDER BY o.orderId DESC";

		statement = conn.prepareStatement(query);
		resultSet = statement.executeQuery();

		List<Orders> listOfOrders = new ArrayList<Orders>();

		while (resultSet.next()) {
			Orders order = new Orders();

			order.setOrderId(resultSet.getInt("orderId"));
			order.setClientUsername(resultSet.getString("clientUsername"));
			order.setOrderDate(resultSet.getString("orderDate"));
			order.setOrderStatus(resultSet.getString("orderStatus"));

			listOfOrders.add(order);

		}
		return listOfOrders;
	}

	/**
	 * Retrieves a list of orders placed by a client with the specified username.
	 * 
	 * @param name The username of the client.
	 * @return A List of Orders objects representing the orders placed by the
	 *         client.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public List<Orders> getOrdersByName(String name) throws SQLException {
		query = "SELECT o.orderId, o.orderDate, o.totalAmount, o.orderStatus " + "FROM orders o "
				+ "JOIN order_detail od " + "ON o.orderId = od.orderId " + "JOIN client c "
				+ "ON od.clientId = c.clientId " + "WHERE c.clientUsername = ? " + "ORDER BY o.orderId DESC";
		statement = conn.prepareStatement(query);
		statement.setString(1, name);

		resultSet = statement.executeQuery();
		List<Orders> listOfClientOrders = new ArrayList<Orders>();

		while (resultSet.next()) {
			Orders order = new Orders();

			order.setOrderId(resultSet.getInt("orderId"));
			order.setTotalAmount(resultSet.getInt("totalAmount"));
			order.setOrderDate(resultSet.getString("orderDate"));
			order.setOrderStatus(resultSet.getString("orderStatus"));

			listOfClientOrders.add(order);

		}

		List<Orders> finalListOfOrders = removeDuplicateOrders(listOfClientOrders);
		return finalListOfOrders;
	}

	/**
	 * Removes duplicate orders from the given list.
	 * 
	 * @param orders The List of Orders objects.
	 * @return A List of Orders objects with duplicates removed.
	 */
	private static List<Orders> removeDuplicateOrders(List<Orders> orders) {
		List<Orders> uniqueOrders = new ArrayList<Orders>();

		for (Orders currentOrder : orders) {

			boolean isDuplicate = false;

			for (Orders oldOrder : uniqueOrders) {
				if (currentOrder.getOrderId() == oldOrder.getOrderId()) {
					isDuplicate = true;
					break;
				}
			}

			if (!isDuplicate) {
				uniqueOrders.add(currentOrder);
			}

		}

		return uniqueOrders;
	}

	/**
	 * Updates the status of an order with the specified ID to "delivered".
	 * 
	 * @param orderId The ID of the order to be updated.
	 * @return 1 if the order status is successfully updated, 0 otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public int updateOrderStatus(int orderId) throws SQLException {
		int row = -1;

		query = "UPDATE orders SET orderStatus = ? WHERE orderId = ?";
		statement = conn.prepareStatement(query);
		statement.setString(1, "delivered");
		statement.setInt(2, orderId);

		row = statement.executeUpdate();

		if (row > 0) {
			row = 1;
		}

		return row;
	}
}
