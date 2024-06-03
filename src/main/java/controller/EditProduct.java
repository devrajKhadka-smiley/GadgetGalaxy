package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import appConstant.ApplicationConstant;
import model.Product;
import service.ProductDao;

/**
 * Servlet implementation class EditProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/edit-product" })
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			productDao = new ProductDao();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			int modelNumber = Integer.parseInt(request.getParameter("modelNumber"));
			Product product = productDao.getProductById(modelNumber);
			String base64ImageData = Base64.getEncoder().encodeToString(product.getImage_data());
			product.setBase64ImageData(base64ImageData);
			request.setAttribute("product", product);
			request.setAttribute("modelNumber", modelNumber);

			request.getRequestDispatcher(ApplicationConstant.UPDATE_PRODUCT_PAGE).forward(request, response);
		} catch (SQLException | NullPointerException | NumberFormatException e) {
			// TODO Auto-generated catch block
			request.getRequestDispatcher(ApplicationConstant.ERROR_PAGE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
