package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import appConstant.ApplicationConstant;
import model.Client;
import service.ClientDao;

@WebServlet(asyncSupported = true, urlPatterns = { "/update-profile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDao clientDao;

	public UpdateProfile() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			clientDao = new ClientDao();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Handle GET request if needed
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Delegate to doPut method
		doPut(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int clientId = Integer.parseInt(request.getParameter("clientId"));
		
		String clientUsername = request.getParameter("clientUsername");
		long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String location = request.getParameter("location");
		String password = request.getParameter("password");

		Client client = new Client();

		try {
			Part filePart = request.getPart("image");
			if (filePart != null && filePart.getSize() > 0) {
				String image_name = filePart.getSubmittedFileName();
				InputStream imageStream = filePart.getInputStream();
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();

				int byteRead;
				int sizeInBytes = 2 * 1024 * 1024;
				byte[] data = new byte[sizeInBytes];
				while ((byteRead = imageStream.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, byteRead);
				}
				byte[] imageData = buffer.toByteArray();

				client.setClientId(clientId);
				client.setClientUsername(clientUsername);
				client.setPhoneNumber(phoneNumber);
				client.setEmail(email);
				client.setFullName(fullName);
				client.setLocation(location);
				client.setPassword(password);
				client.setImage_data(imageData);
				client.setImage_name(image_name);

				try {
					int row = clientDao.updateClient(client);
					if (row > 0) {
						response.sendRedirect(request.getContextPath() + ApplicationConstant.HOME_SERVLET);
					} else {
						request.setAttribute("validationError", "Your profile was not updated");
						request.getRequestDispatcher(ApplicationConstant.PROFILE_PAGE).forward(request, response);
					}
				} catch (SQLException | NullPointerException e) {
					request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
				}

			} else {
				System.out.println(clientId);
				Client dbClient = clientDao.getClientById(clientId);
				
				client.setClientId(clientId);
				client.setClientUsername(clientUsername);
				client.setPhoneNumber(phoneNumber);
				client.setEmail(email);
				client.setFullName(fullName);
				client.setLocation(location);
				client.setPassword(password);
				client.setImage_data(dbClient.getImage_data());
				client.setImage_name(dbClient.getImage_name());
				String base64ImageData = Base64.getEncoder().encodeToString(dbClient.getImage_data());
				client.setBase64ImageData(base64ImageData);

				try {
					int row = clientDao.updateClient(client);
					if (row > 0) {
						response.sendRedirect(request.getContextPath() + ApplicationConstant.HOME_SERVLET);
					} else {
						request.getRequestDispatcher(ApplicationConstant.PROFILE_PAGE).forward(request, response);
					}
				} catch (SQLException | NullPointerException e) {
					request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
				}
			}
		} catch (Exception ex) {
			request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
		}
	}
}
