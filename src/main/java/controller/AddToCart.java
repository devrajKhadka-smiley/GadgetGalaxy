package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
 * Servlet implementation class AddToCart
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/add-cart" })
public class AddToCart extends HttpServlet {
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
	public AddToCart() {
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


		ArrayList<Cart> cartList = new ArrayList<Cart>();

		int modelNumber = Integer.parseInt(request.getParameter("modelNumber"));
		int quantity = Integer.parseInt(request.getParameter("productQuantity"));

		Cart cart = new Cart();
		cart.setModelNumber(modelNumber);
		cart.setQuantity(quantity);
		System.out.println(cart.getStockQuantity());
		HttpSession session = request.getSession(false);

		if (session.getAttribute("sessionUsername") == null) {
			response.sendRedirect(request.getContextPath() + ApplicationConstant.LOGIN_SERVLET);
		} else {

			@SuppressWarnings("unchecked")
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

			if (cart_list == null) {
				cartList.add(cart);
				session.setAttribute("cart-list", cartList);
			} else {
				cartList = cart_list;

				boolean productExist = false;
				for (Cart cart2 : cartList) {
					if (cart2.getModelNumber() == modelNumber) {
						productExist = true;
					}

				}
				if (!productExist) {
					cartList.add(cart);
					session.setAttribute("cart-list", cartList);
				}

			}
			List<Product> listOfProduct;
			try {
				listOfProduct = productDao.getListOfProduct();
				for (Product product : listOfProduct) {

					String base64ImageData = Base64.getEncoder().encodeToString(product.getImage_data());
					product.setBase64ImageData(base64ImageData);
				}

				request.setAttribute("productList", listOfProduct);
				request.getRequestDispatcher(ApplicationConstant.HOME_PAGE).forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
