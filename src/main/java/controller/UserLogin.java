package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.ApplicationConstant;
import service.AdminDao;
import service.ClientDao;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static AdminDao adminDao;
	private static ClientDao clientDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			adminDao = new AdminDao();
			clientDao = new ClientDao();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(ApplicationConstant.LOGIN_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userProfile = request.getParameter("userProfile");
		System.out.println(userProfile);

		if ("client".equals(userProfile)) {
			try {
				boolean isSuccess = clientDao.getClientLoginInfo(username, password);
//				System.out.println(isSuccess);
				if (isSuccess) {
					HttpSession session = request.getSession();
					session.setAttribute("sessionUsername", username);
					session.setAttribute("userId", userProfile);
					session.setAttribute("role", "client");
					session.setMaxInactiveInterval(30 * 60);

//					-------------
//					cookies section
					Cookie userCookie = new Cookie("hello", username);
					userCookie.setMaxAge(30 * 60);
					response.addCookie(userCookie);
//					------------

					response.sendRedirect(request.getContextPath() + ApplicationConstant.HOME_SERVLET);
				} else {
					request.setAttribute("loginError", "Invalid username or password");
					request.getRequestDispatcher(ApplicationConstant.LOGIN_PAGE).forward(request, response);

				}
			} catch (SQLException | NullPointerException e) {
				request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
			}
		} else {
//			

			try {
				boolean isSuccess = adminDao.getAdminLoginInfo(username, password);
//				System.out.println(isSuccess);
				if (isSuccess) {
					HttpSession session = request.getSession();
					session.setAttribute("sessionUsername", username);
					session.setAttribute("userId", userProfile);
					session.setAttribute("role", "admin");
					session.setMaxInactiveInterval(30 * 60);

//					---------------------\
					Cookie userCookie = new Cookie("hello", username);
					userCookie.setMaxAge(30 * 60);
					response.addCookie(userCookie);
//					---------------------
					response.sendRedirect(request.getContextPath() + ApplicationConstant.ADMIN_PRODUCT_SERVLET);

				} else {
					request.setAttribute("loginError", "Invalid username or password");
					request.getRequestDispatcher(ApplicationConstant.LOGIN_PAGE).forward(request, response);

				}
			} catch (SQLException | NullPointerException e) {
				request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);

			}
		}
	}

}
