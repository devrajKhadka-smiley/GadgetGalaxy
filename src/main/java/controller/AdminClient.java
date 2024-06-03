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
import model.Client;
import service.ClientDao;

/**
 * Servlet implementation class AdminClient
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-client" })
public class AdminClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminClient() {
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
			ClientDao clientDao = new ClientDao();
			List<Client> listOfClient;
			listOfClient = clientDao.getListOfClient();
			request.setAttribute("clientList", listOfClient);
			request.getRequestDispatcher(ApplicationConstant.ADMIN_CLIENT_PAGE).forward(request, response);
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
