package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.ApplicationConstant;
import model.Cart;
import model.Product;
import service.ProductDao;

/**
 * Servlet implementation class UpdateCart
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/update-cart" })
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao;

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCart() {
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
		doPut(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		int modelNumber = Integer.parseInt(request.getParameter("modelNumber"));
		HttpSession session = request.getSession(false);
		@SuppressWarnings("unchecked")
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
		Product product = new Product();
		if (action != null && modelNumber >= 1) {
			if (action.equals("inc")) {
				for (Cart cart : cartList) {
					int quantity = cart.getQuantity();
					try {
						product = productDao.getProductById(modelNumber);
						int stockQuantity = product.getStockQuantity();
						if (cart.getModelNumber() == modelNumber) {
							if (stockQuantity > quantity) {

								quantity++;
								cart.setQuantity(quantity);
								response.sendRedirect(request.getContextPath() + ApplicationConstant.CART_SERVLET);
							} else {
								response.sendRedirect(request.getContextPath() + ApplicationConstant.CART_SERVLET);

							}
						}
					} catch (SQLException | NullPointerException e) {
						// TODO Auto-generated catch block
						request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
					}

				}
			}

			if (action.equals("sub")) {
				for (Cart cart : cartList) {
					int quantity = cart.getQuantity();

					if (cart.getModelNumber() == modelNumber && quantity > 1) {
						quantity--;
						cart.setQuantity(quantity);
						response.sendRedirect(request.getContextPath() + ApplicationConstant.CART_SERVLET);
					}
				}
			}
		}

	}
}
