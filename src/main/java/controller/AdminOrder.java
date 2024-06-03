package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appConstant.ApplicationConstant;
import model.Orders;
import service.OrderDao;

/**
 * Servlet implementation class AdminOrder
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-order" })
public class AdminOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			OrderDao orderDao = new OrderDao();
			List<Orders> listOfOrders;
			listOfOrders = orderDao.getListOfOrders();
			request.setAttribute("orderList", listOfOrders);
			request.getRequestDispatcher(ApplicationConstant.ADMIN_ORDER_PAGE).forward(request, response);
			
		} catch (ClassNotFoundException | SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
