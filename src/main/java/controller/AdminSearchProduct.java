package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appConstant.ApplicationConstant;
import model.Product;
import service.ProductDao;

/**
 * Servlet implementation class AdminSearchProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-search-product" })
public class AdminSearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			productDao = new ProductDao();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AdminSearchProduct() {
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
			int search = Integer.parseInt(request.getParameter("search"));
			System.out.println(search);
			List<Product> listOfSearchProduct = productDao.searchProductByPrice(search);

			if (listOfSearchProduct.size() > 0) {
				request.setAttribute("productList", listOfSearchProduct);
				request.getRequestDispatcher(ApplicationConstant.ADMIN_PRODUCT_PAGE).forward(request, response);
			} else {
				request.setAttribute("productList", listOfSearchProduct);
				request.getRequestDispatcher(ApplicationConstant.ADMIN_PRODUCT_PAGE).forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
		} catch (NumberFormatException ex) {
			
			try {
				String search = request.getParameter("search");
				
				List<Product> listOfSearchProduct;
				
				listOfSearchProduct = productDao.searchProduct(search);
				if (listOfSearchProduct.size() > 0) {
					request.setAttribute("productList", listOfSearchProduct);
					request.getRequestDispatcher(ApplicationConstant.ADMIN_PRODUCT_PAGE).forward(request, response);
				} else {
					request.setAttribute("productList", listOfSearchProduct);
					request.getRequestDispatcher(ApplicationConstant.ADMIN_PRODUCT_PAGE).forward(request, response);
				}
			} catch (SQLException | NullPointerException e) {
				// TODO Auto-generated catch block
				request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
			}

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
