package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.ApplicationConstant;
import model.Client;
import service.ClientDao;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDao clientDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			clientDao = new ClientDao();
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
		try {
			HttpSession session = request.getSession(false);
			String sessionUsername = (String) session.getAttribute("sessionUsername");

			int clientId = clientDao.getClientByUsername(sessionUsername);
			Client client = clientDao.getClientDetails(clientId);
			
			String base64ImageData = Base64.getEncoder().encodeToString(client.getImage_data());
			client.setBase64ImageData(base64ImageData);
			
			request.setAttribute("client", client);
			request.getRequestDispatcher(ApplicationConstant.PROFILE_PAGE).forward(request, response);
		} catch (SQLException | NullPointerException e) {
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
