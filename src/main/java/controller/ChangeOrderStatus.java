package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appConstant.ApplicationConstant;
import service.OrderDao;

/**
 * Servlet implementation class ChangeOrderStatus
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/change-order-status" })
public class ChangeOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao orderDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeOrderStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			orderDao = new OrderDao();
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
		doPut(request, response);
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

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			int isStatusUpdated = orderDao.updateOrderStatus(orderId);

			if (isStatusUpdated == 1) {
				response.sendRedirect(request.getContextPath() + ApplicationConstant.ADMIN_ORDER_SERVLET);
			} 
		} catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
		}
	}

}
