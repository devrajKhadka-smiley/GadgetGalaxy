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
import model.Product;
import service.ProductDao;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/update-product" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,

		maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			productDao = new ProductDao();
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
		request.getRequestDispatcher(ApplicationConstant.ERROR_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPut(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			int modelNumber = Integer.parseInt(request.getParameter("modelNumber"));
			String productName = request.getParameter("productName");
			int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
			String productCategory = request.getParameter("productCategory");
			String productDistributer = request.getParameter("productDistributer");
			String productDescription = request.getParameter("productDescription");
			int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
			int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));

			Product product = new Product();

			product.setUnitPrice(unitPrice);

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

				product.setModelNumber(modelNumber);
				product.setProductName(productName);
				product.setReleasedYear(releaseYear);
				product.setProductCategory(productCategory);
				product.setProductDistributer(productDistributer);
				product.setProductDescription(productDescription);
				product.setStockQuantity(stockQuantity);
				product.setImage_data(imageData);
				product.setImage_name(image_name);
				try {
					int row = productDao.updateProduct(product);
					if (row > 0) {
						System.out.println("Product Updated");
						response.sendRedirect(request.getContextPath() + "/admin-product");
					} else {
						System.out.println("Product Not Updated");
						request.setAttribute("validationError", ApplicationConstant.ERROR_UPDATE_PRODUCT_MESSAGE);
						request.getRequestDispatcher(ApplicationConstant.UPDATE_PRODUCT_PAGE).forward(request,
								response);
					}
				} catch (SQLException | NullPointerException e) {
					// TODO Auto-generated catch block
					request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
				}
			} else {
				Product dbProduct = productDao.getProductById(modelNumber);

				product.setModelNumber(modelNumber);
				product.setProductName(productName);
				product.setReleasedYear(releaseYear);
				product.setProductCategory(productCategory);
				product.setProductDistributer(productDistributer);
				product.setProductDescription(productDescription);
				product.setStockQuantity(stockQuantity);
				product.setUnitPrice(unitPrice);
				product.setImage_data(dbProduct.getImage_data());
				product.setImage_name(dbProduct.getImage_name());
				String base64ImageData = Base64.getEncoder().encodeToString(dbProduct.getImage_data());
				product.setBase64ImageData(base64ImageData);

				try {
					int row = productDao.updateProduct(product);
					if (row > 0) {
						System.out.println("Product Updated");
						response.sendRedirect(request.getContextPath() + "/admin-product");
					} else {
						System.out.println("Product Not Updated");
						request.setAttribute("validationError", ApplicationConstant.ERROR_UPDATE_PRODUCT_MESSAGE);
						request.getRequestDispatcher(ApplicationConstant.UPDATE_PRODUCT_PAGE).forward(request,
								response);
					}
				} catch (SQLException | NullPointerException e) {
					// TODO Auto-generated catch block
					request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
				}
			}
		} catch (Exception ex) {

		}

	}

}
