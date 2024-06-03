package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.ApplicationConstant;
import model.Orders;
import service.OrderDao;

/**
 * Servlet implementation class TrackOrder
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/track-order" })
public class TrackOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrackOrder() {
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
		String clientName = (String) session.getAttribute("sessionUsername");
		if (clientName == null) {
			response.sendRedirect(request.getContextPath() + ApplicationConstant.LOGIN_SERVLET);
		} else {
			try {
				OrderDao orderDao = new OrderDao();
				List<Orders> listOfClientOrder = orderDao.getOrdersByName(clientName);
				request.setAttribute("clientOrderList", listOfClientOrder);
				request.getRequestDispatcher(ApplicationConstant.TRACK_ORDER_PAGE).forward(request, response);

			} catch (ClassNotFoundException | SQLException e) {
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
