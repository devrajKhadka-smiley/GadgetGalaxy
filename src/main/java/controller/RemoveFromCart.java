package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.ApplicationConstant;
import model.Cart;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/remove-from-cart" })
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFromCart() {
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
		doDelete(request, response);
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

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// TODO Auto-generated method stub
		String modelNumber = request.getParameter("modelNumber");
		if (modelNumber != null) {
			@SuppressWarnings("unchecked")
			ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession(false).getAttribute("cart-list");
			if (cartList != null) {
				for (Cart cart : cartList) {
					if (cart.getModelNumber() == Integer.parseInt(modelNumber)) {
						cartList.remove(cart);
						session.setAttribute("cart-list", cartList);
						break;
					}
				}
				response.sendRedirect(request.getContextPath() + ApplicationConstant.CART_SERVLET);
			}
		} else {
			response.sendRedirect(request.getContextPath() + ApplicationConstant.CART_SERVLET);
		}
	}

}
