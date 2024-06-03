package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Sale;
import utils.DatabaseConnection;

public class SaleDao {
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private boolean isSuccess;
	private String query;

	public SaleDao() throws ClassNotFoundException, SQLException {
		conn = DatabaseConnection.getConnection();
	}

	/**
	 * Inserts a sale record into the database.
	 * 
	 * @param sale The Sale object containing sale details to be inserted.
	 * @return True if the sale record is successfully inserted, false otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public boolean insertSale(Sale sale) throws SQLException {
		isSuccess = false;

		query = "INSERT INTO order_detail (orderId, clientId, modelNumber, orderQuantity)" + "VALUES (?,?,?,?)";
		statement = conn.prepareStatement(query);

		statement.setInt(1, sale.getOrderId());
		statement.setInt(2, sale.getClientId());
		statement.setInt(3, sale.getModelNumber());
		statement.setInt(4, sale.getOrderQuantity());

		int row = statement.executeUpdate();

		if (row > 0) {
			isSuccess = true;
		}

		return isSuccess;
	}

	/**
	 * Retrieves products associated with a specific order from the database.
	 * 
	 * @param orderId The ID of the order to retrieve products for.
	 * @return A list of Sale objects representing products in the order.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public List<Sale> getProductByOrder(int orderId) throws SQLException {
		query = "SELECT p.modelNumber, p.productName, p.productDistributer, p.image_data, od.orderQuantity "
				+ "FROM product p " + "JOIN order_detail od " + "ON p.modelNumber = od.modelNumber " + "JOIN orders o "
				+ "ON od.orderId = o.orderId " + "WHERE od.orderId = ?";
		statement = conn.prepareStatement(query);
		statement.setInt(1, orderId);

		resultSet = statement.executeQuery();
		List<Sale> listOfOrderProduct = new ArrayList<Sale>();

		while (resultSet.next()) {
			Sale sale = new Sale();

			sale.setModelNumber(resultSet.getInt("modelNumber"));
			sale.setProductName(resultSet.getString("productName"));
			sale.setOrderQuantity(resultSet.getInt("orderQuantity"));
			sale.setProductDistributer(resultSet.getString("productDistributer"));
			sale.setImage_data(
					resultSet.getBlob("image_data").getBytes(1, (int) resultSet.getBlob("image_data").length()));

			listOfOrderProduct.add(sale);
		}
		return listOfOrderProduct;
	}

}
