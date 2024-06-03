package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import appConstant.ApplicationConstant;
import model.Cart;
import service.ProductDao;

/**
 * Servlet implementation class ViewCart
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/view-cart" })
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewCart() {
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
		HttpSession session = request.getSession(false);

		if (session.getAttribute("sessionUsername") == null) {
			response.sendRedirect(request.getContextPath() + ApplicationConstant.LOGIN_SERVLET);
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
			List<Cart> cartProduct = null;
			int totalPrice = 0;
			if (cartList != null) {
				try {
					ProductDao productDao = new ProductDao();
					cartProduct = productDao.getCartProducts(cartList);
					totalPrice = productDao.getTotalCartAmount(cartList);

					for (Cart cart : cartProduct) {
						String base64ImageData = Base64.getEncoder().encodeToString(cart.getImage_data());
						cart.setBase64ImageData(base64ImageData);
					}
					request.setAttribute("cartEmptyMessage", "");
					request.setAttribute("cartList", cartProduct);
					request.setAttribute("totalPrice", totalPrice);
					session.setAttribute("cart-list", cartProduct);
					request.setAttribute("cartMessage", "Thank You for the purchase");
					request.getRequestDispatcher(ApplicationConstant.CART_PAGE).forward(request, response);
				} catch (ClassNotFoundException | SQLException | NullPointerException e) {
					request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
				}
			} else {
				request.setAttribute("totalPrice", 0);
				request.setAttribute("cartMessage", "");
				request.setAttribute("cartEmptyMessage", "No products has been added to the cart yet");
				request.getRequestDispatcher(ApplicationConstant.CART_PAGE).forward(request, response);

			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
