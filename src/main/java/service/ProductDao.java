package service;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Product;
import utils.DatabaseConnection;

public class ProductDao {
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private boolean isSuccess;

	public ProductDao() throws ClassNotFoundException, SQLException {
		conn = DatabaseConnection.getConnection();
	}

	/**
	 * Adds a new product to the database.
	 * 
	 * @param product The Product object containing the product details.
	 * @return true if the product is successfully added, false otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public boolean addProduct(Product product) throws SQLException {
		String query = "SELECT count(*) from product";
		statement = conn.prepareStatement(query);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			boolean hasFound = checkProduct(product);
			if (hasFound) {
				isSuccess = false;
			} else {
				int row = insertProductData(product);
				if (row > 0) {
					isSuccess = true;
				} else {
					isSuccess = false;
				}
			}
		} else {
			int row = insertProductData(product);
			if (row > 0) {
				isSuccess = true;
			}
		}
		return isSuccess;
	}

	/**
	 * Inserts product data into the database.
	 * 
	 * @param product The Product object containing the product details.
	 * @return The number of rows affected by the insert operation, or 0 if the
	 *         product already exists in the database.
	 * @throws SQLException if there is an error accessing the database.
	 */
	private int insertProductData(Product product) throws SQLException {
		List<Product> listOfProduct = getListOfProduct();

		for (Product listProduct : listOfProduct) {
			if (product.getProductName().equals(listProduct.getProductName())) {
				return 0;
			}
		}

		String query = "INSERT INTO "
				+ "product(productName, releasedYear, productCategory, productDistributer, productDescription, stockQuantity, unitPrice, image_data, image_name)"
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		Blob blob = conn.createBlob();
		blob.setBytes(1, product.getImage_data());

		statement = conn.prepareStatement(query);
		statement.setString(1, product.getProductName());
		statement.setInt(2, product.getReleasedYear());
		statement.setString(3, product.getProductCategory());
		statement.setString(4, product.getProductDistributer());
		statement.setString(5, product.getProductDescription());
		statement.setInt(6, product.getStockQuantity());
		statement.setInt(7, product.getUnitPrice());

		statement.setBlob(8, blob);
		statement.setString(9, product.getImage_name());

		int row = statement.executeUpdate();
		return row;
	}

	/**
	 * Checks if a product with the same name already exists in the database.
	 * 
	 * @param product The Product object containing the product details.
	 * @return true if a product with the same name already exists, false otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	private boolean checkProduct(Product product) throws SQLException {
		String query = "SELECT modelNumber, productName FROM product";
		statement = conn.prepareStatement(query);
		resultSet = statement.executeQuery();
		boolean hasFound = false;
		while (resultSet.next()) {
			String productNameFromDb = resultSet.getString("productName");
			if (product.getProductName() == productNameFromDb) {
				hasFound = true;
				break;
			}
		}
		return hasFound;
	}

	/**
	 * Retrieves a list of all products from the database.
	 * 
	 * @return A List of Product objects representing all products in the database.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public List<Product> getListOfProduct() throws SQLException {
		statement = conn.prepareStatement("SELECT * FROM product ORDER BY modelNumber DESC");
		resultSet = statement.executeQuery();

		List<Product> listOfProducts = new ArrayList<Product>();

		while (resultSet.next()) {
			Product product = new Product();

			product.setModelNumber(resultSet.getInt("modelNumber"));
			product.setProductName(resultSet.getString("productName"));
			product.setReleasedYear(resultSet.getInt("releasedYear"));
			product.setProductCategory(resultSet.getString("productCategory"));
			product.setProductDistributer(resultSet.getString("productDistributer"));
			product.setProductDescription(resultSet.getString("productDescription"));
			product.setStockQuantity(resultSet.getInt("stockQuantity"));
			product.setUnitPrice(resultSet.getInt("unitPrice"));

			product.setImage_data(
					resultSet.getBlob("image_data").getBytes(1, (int) resultSet.getBlob("image_data").length()));
			product.setImage_name(resultSet.getString("image_name"));

			listOfProducts.add(product);

		}
		return listOfProducts;

	}

	/**
	 * Retrieves product details from the database based on the specified model
	 * number.
	 * 
	 * @param modelNumber The model number of the product.
	 * @return A Product object containing the details of the product with the
	 *         specified model number, or an empty Product object if no matching
	 *         product is found.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public Product getProductById(int modelNumber) throws SQLException {
		statement = conn.prepareStatement(
				"SELECT productName, releasedYear, productCategory, productDistributer, productDescription, stockQuantity, unitPrice, image_data, image_name FROM product WHERE modelNumber=?");
		statement.setInt(1, modelNumber);
		resultSet = statement.executeQuery();
		Product product = new Product();

		if (resultSet.next()) {
			// Change made in line 129
			product.setModelNumber(modelNumber);
			product.setProductName(resultSet.getString("productName"));
			product.setReleasedYear(resultSet.getInt("releasedYear"));
			product.setProductCategory(resultSet.getString("productCategory"));
			product.setProductDistributer(resultSet.getString("productDistributer"));
			product.setProductDescription(resultSet.getString("productDescription"));
			product.setStockQuantity(resultSet.getInt("stockQuantity"));
			product.setUnitPrice(resultSet.getInt("unitPrice"));
			product.setImage_data(
					resultSet.getBlob("image_data").getBytes(1, (int) resultSet.getBlob("image_data").length()));
			product.setImage_name(resultSet.getString("image_name"));
		}

		return product;
	}

	/**
	 * Updates the details of a product in the database.
	 * 
	 * @param product The Product object containing the updated product details.
	 * @return The number of rows affected by the update operation, or 0 if the
	 *         product name is not taken.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public int updateProduct(Product product) throws SQLException {
		int row = 0;

		if (!isProductNameTaken(product.getProductName(), product.getModelNumber())) {
			return row;
		} else {

			Blob blob = conn.createBlob();
			blob.setBytes(1, product.getImage_data());

			statement = conn.prepareStatement(
					"UPDATE product SET productName=?, releasedYear=?, productCategory=?, productDistributer=?, productDescription=?, stockQuantity=?, unitPrice=?, image_data=?, image_name=? WHERE modelNumber=?");
			statement.setString(1, product.getProductName());
			statement.setInt(2, product.getReleasedYear());
			statement.setString(3, product.getProductCategory());
			statement.setString(4, product.getProductDistributer());
			statement.setString(5, product.getProductDescription());
			statement.setInt(6, product.getStockQuantity());
			statement.setInt(7, product.getUnitPrice());
			statement.setBlob(8, blob);
			statement.setString(9, product.getImage_name());

			statement.setInt(10, product.getModelNumber());

			row = statement.executeUpdate();
			return row;
		}

	}

	/**
	 * Checks if a product name is already taken by another product in the database.
	 * 
	 * @param productName The name of the product to be checked.
	 * @param modelNumber The model number of the product to be excluded from the
	 *                    check.
	 * @return true if the product name is already taken, false otherwise.
	 * @throws SQLException if there is an error accessing the database.
	 */
	private boolean isProductNameTaken(String productName, int modelNumber) throws SQLException {
		statement = conn.prepareStatement("SELECT * FROM product WHERE productName=? AND modelNumber=?");
		statement.setString(1, productName);
		statement.setInt(2, modelNumber);

		resultSet = statement.executeQuery();

		if (resultSet.next()) {

			return true;
		}

		return false;
	}

	/**
	 * Deletes a product from the database based on the specified model number.
	 * 
	 * @param modelNumber The model number of the product to be deleted.
	 * @return The number of rows affected by the delete operation.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public int deleteProduct(int modelNumber) throws SQLException {
		int row;
		statement = conn.prepareStatement("DELETE FROM order_detail WHERE modelNumber=?");
		statement.setInt(1, modelNumber);

		row = statement.executeUpdate();

		statement = conn.prepareStatement("DELETE FROM product WHERE modelNumber=?");

		statement.setInt(1, modelNumber);

		row = statement.executeUpdate();
		System.out.println(row);
		return row;
	}

	/**
	 * Searches for products in the database based on the specified search term.
	 * 
	 * @param search The search term to be used for product search.
	 * @return A List of Product objects matching the search criteria.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public List<Product> searchProduct(String search) throws SQLException {
		statement = conn.prepareStatement("SELECT * FROM product WHERE productName LIKE '%" + search
				+ "%' OR productCategory LIKE '%" + search + "%'" + "OR productDistributer LIKE '%" + search + "%'");

		resultSet = statement.executeQuery();

		List<Product> listOfProduct = new ArrayList<Product>();
		Product product;

		while (resultSet.next()) {
			product = new Product();
			product.setModelNumber(resultSet.getInt("modelNumber"));
			product.setProductName(resultSet.getString("productName"));
			product.setReleasedYear(resultSet.getInt("releasedYear"));
			product.setProductCategory(resultSet.getString("productCategory"));
			product.setProductDistributer(resultSet.getString("productDistributer"));
			product.setProductDescription(resultSet.getString("productDescription"));
			product.setStockQuantity(resultSet.getInt("stockQuantity"));
			product.setUnitPrice(resultSet.getInt("unitPrice"));
			product.setImage_data(
					resultSet.getBlob("image_data").getBytes(1, (int) resultSet.getBlob("image_data").length()));
			product.setImage_name(resultSet.getString("image_name"));

			listOfProduct.add(product);
		}

		return listOfProduct;
	}

	/**
	 * Searches for products in the database based on the specified price range.
	 * 
	 * @param search The price value around which to search for products.
	 * @return A List of Product objects within the specified price range.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public List<Product> searchProductByPrice(int search) throws SQLException {
		statement = conn.prepareStatement("SELECT * FROM product WHERE unitPrice >= ? AND unitPrice <= ?");
		statement.setInt(1, (search - 100));
		statement.setInt(2, (search + 100));

		resultSet = statement.executeQuery();

		List<Product> listOfProduct = new ArrayList<Product>();
		Product product;

		while (resultSet.next()) {
			product = new Product();
			product.setModelNumber(resultSet.getInt("modelNumber"));
			product.setProductName(resultSet.getString("productName"));
			product.setReleasedYear(resultSet.getInt("releasedYear"));
			product.setProductCategory(resultSet.getString("productCategory"));
			product.setProductDistributer(resultSet.getString("productDistributer"));
			product.setProductDescription(resultSet.getString("productDescription"));
			product.setStockQuantity(resultSet.getInt("stockQuantity"));
			product.setUnitPrice(resultSet.getInt("unitPrice"));
			product.setImage_data(
					resultSet.getBlob("image_data").getBytes(1, (int) resultSet.getBlob("image_data").length()));
			product.setImage_name(resultSet.getString("image_name"));

			listOfProduct.add(product);
		}

		return listOfProduct;
	}

	/**
	 * Retrieves details of products in the cart from the database.
	 * 
	 * @param cartList The list of Cart items containing the model numbers and
	 *                 quantities of products in the cart.
	 * @return A List of Cart objects containing details of products in the cart.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public List<Cart> getCartProducts(ArrayList<Cart> cartList) throws SQLException {
		List<Cart> products = new ArrayList<Cart>();

		if (cartList.size() > 0) {
			for (Cart cartItem : cartList) {
				statement = conn.prepareStatement("SELECT * FROM product WHERE modelNumber = ?");
				statement.setInt(1, cartItem.getModelNumber());

				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					Cart cartRow = new Cart();
					cartRow.setModelNumber(resultSet.getInt("modelNumber"));
					cartRow.setProductName(resultSet.getString("productName"));
					cartRow.setProductCategory(resultSet.getString("productCategory"));
					cartRow.setProductDistributer(resultSet.getString("productDistributer"));
					cartRow.setUnitPrice(resultSet.getInt("unitPrice"));
					cartRow.setPrice(resultSet.getInt("unitPrice") * cartItem.getQuantity());
					cartRow.setQuantity(cartItem.getQuantity());
					cartRow.setReleasedYear(resultSet.getInt("releasedYear"));
					cartRow.setProductDescription(resultSet.getString("productDescription"));
					cartRow.setStockQuantity(resultSet.getInt("stockQuantity"));
					cartRow.setImage_data(resultSet.getBlob("image_data").getBytes(1,
							(int) resultSet.getBlob("image_data").length()));
					cartRow.setImage_name(resultSet.getString("image_name"));
					products.add(cartRow);
				}
			}
		}

		return products;
	}

	/**
	 * Calculates the total amount of products in the cart.
	 * 
	 * @param cartList The list of Cart items containing the model numbers and
	 *                 quantities of products in the cart.
	 * @return The total amount of products in the cart.
	 * @throws SQLException if there is an error accessing the database.
	 */

	public int getTotalCartAmount(ArrayList<Cart> cartList) throws SQLException {
		int totalAmount = 0;

		if (cartList.size() > 0) {
			for (Cart cart : cartList) {
				statement = conn.prepareStatement("SELECT unitPrice FROM product where modelNumber = ?");
				statement.setInt(1, cart.getModelNumber());
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					totalAmount += resultSet.getInt("unitPrice") * cart.getQuantity();
				}
			}
		}

		return totalAmount;
	}

	/**
	 * Updates the stock quantity of a product in the database after a purchase.
	 * 
	 * @param modelNumber The model number of the product to be updated.
	 * @param quantity    The quantity of the product purchased.
	 * @throws SQLException if there is an error accessing the database.
	 */
	public void updateProductQuantity(int modelNumber, int quantity) throws SQLException {
		statement = conn.prepareStatement("SELECT * FROM product WHERE modelNumber = ?");
		statement.setInt(1, modelNumber);

		resultSet = statement.executeQuery();

		if (resultSet.next()) {
			statement = conn.prepareStatement("UPDATE product SET stockQuantity = ? WHERE modelNumber = ?");
			statement.setInt(1, (resultSet.getInt("stockQuantity") - quantity));
			statement.setInt(2, resultSet.getInt("modelNumber"));

			statement.executeUpdate();

		}
	}
}