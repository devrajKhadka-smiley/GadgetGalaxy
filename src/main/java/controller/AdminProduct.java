package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import appConstant.ApplicationConstant;
import model.Product;
import service.ProductDao;

/**
 * Servlet implementation class AdminProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-product" })

public class AdminProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ProductDao productDao = new ProductDao();
			List<Product> listOfProduct;
			listOfProduct = productDao.getListOfProduct();
			for (Product product : listOfProduct) {

				String base64ImageData = Base64.getEncoder().encodeToString(product.getImage_data());
				product.setBase64ImageData(base64ImageData);
			}
			request.setAttribute("productList", listOfProduct);

			request.getRequestDispatcher(ApplicationConstant.ADMIN_PRODUCT_PAGE).forward(request, response);
		} catch (ClassNotFoundException | SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
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
