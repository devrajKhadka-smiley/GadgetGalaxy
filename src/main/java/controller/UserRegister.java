package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

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
import utils.PasswordHashing;
import utils.RegisterValidation;

@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDao dao;

	public UserRegister() {
		super();
	}

	@Override
	public void init() throws ServletException {
		try {
			dao = new ClientDao();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(ApplicationConstant.REGISTER_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String fullName = request.getParameter("fullName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		String retype = request.getParameter("retypePassword");
		try {

			if (!RegisterValidation.isTextOnly(userName) || !RegisterValidation.isTextOnly(fullName)) {
				request.setAttribute("validationError", ApplicationConstant.ERROR_STRING_MESSAGE);
				request.getRequestDispatcher(ApplicationConstant.REGISTER_PAGE).forward(request, response);
			}

			if (phoneNumber.length() != 10) {
				request.setAttribute("validationError", ApplicationConstant.ERROR_PHONE_LONG_MESSAGE);
				request.getRequestDispatcher(ApplicationConstant.REGISTER_PAGE).forward(request, response);
			}

			if (!RegisterValidation.isLongOnly(phoneNumber)) {
				request.setAttribute("validationError", ApplicationConstant.ERROR_PHONE_MESSAGE);
				request.getRequestDispatcher(ApplicationConstant.REGISTER_PAGE).forward(request, response);
			}

			if (!RegisterValidation.isEmail(email)) {
				request.setAttribute("validationError", ApplicationConstant.ERROR_MESSAGE_EMAIL_DOMAIN);
				request.getRequestDispatcher(ApplicationConstant.REGISTER_PAGE).forward(request, response);
			}

			if (!RegisterValidation.isAlphanumberic(address) || !RegisterValidation.isAlphanumberic(password)
					|| !RegisterValidation.isAlphanumberic(retype)) {
				request.setAttribute("validationError", ApplicationConstant.ERROR_CHARACTER_MESSAGE);
				request.getRequestDispatcher(ApplicationConstant.REGISTER_PAGE).forward(request, response);
			}

			Part filePart = request.getPart("image");
			String image_name;
			byte[] imageData;
			int sizeInBytes = 2 * 1024 * 1024;

			if (filePart != null && filePart.getSize() > 0) {
				image_name = filePart.getSubmittedFileName();
				InputStream imageStream = filePart.getInputStream();
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();

				int byteRead;
				byte[] data = new byte[sizeInBytes];
				while ((byteRead = imageStream.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, byteRead);
				}
				imageData = buffer.toByteArray();
			} else {
				image_name = "profile.png";
				InputStream placeholderStream = getServletContext().getResourceAsStream("/images/profile.png");
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				if (placeholderStream != null) {
					int byteRead;
					byte[] data = new byte[sizeInBytes];
					while ((byteRead = placeholderStream.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, byteRead);
					}
					imageData = buffer.toByteArray();
				} else {
					throw new IOException("Placeholder image not found");
				}
			}

			if (!password.equals(retype)) {
				request.setAttribute("userName", userName);
				request.setAttribute("fullName", fullName);
				request.setAttribute("phoneNumber", phoneNumber);
				request.setAttribute("email", email);
				request.setAttribute("address", address);
				request.setAttribute("error", "Password Doesn't Match");
				request.getRequestDispatcher(ApplicationConstant.REGISTER_PAGE).forward(request, response);
				return;
			}

			Client client = new Client();
			client.setClientUsername(userName);
			client.setFullName(fullName);
			client.setPhoneNumber(Long.parseLong(phoneNumber));
			client.setEmail(email);
			client.setLocation(address);
			client.setImage_data(imageData);
			client.setImage_name(image_name);
			client.setPassword(PasswordHashing.passwordHash(password));

			boolean isSuccess = dao.saveClient(client);
			if (isSuccess) {
				request.getRequestDispatcher(ApplicationConstant.LOGIN_PAGE).forward(request, response);
			} else {
				request.setAttribute("alreadyUserError", "User Name or Email or Phone Number Already Taken");
				request.getRequestDispatcher(ApplicationConstant.REGISTER_PAGE).forward(request, response);
			}
		} catch (NumberFormatException ex) {
			request.setAttribute("validationError", "Please enter correct details only");
			request.getRequestDispatcher(ApplicationConstant.REGISTER_PAGE).forward(request, response);
		} catch (NullPointerException | SQLException ex) {
			request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
		}
	}
}
