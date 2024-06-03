package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

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
import utils.RegisterValidation;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/add-product" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,

		maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)

public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProduct() {
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
		request.getRequestDispatcher(ApplicationConstant.ADD_PRODUCT_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part filePart = request.getPart("image");

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

//			int modelNumber = Integer.parseInt(request.getParameter("modelNumber"));
		String productName = request.getParameter("productName");
		String releaseYear = request.getParameter("releaseYear");
		String productCategory = request.getParameter("productCategory");
		String productDistributer = request.getParameter("productDistributer");
		String productDescription = request.getParameter("productDescription");
		String stockQuantity = request.getParameter("stockQuantity");
		String unitPrice = request.getParameter("unitPrice");

		try {
			if (!RegisterValidation.isNumbersOnly(releaseYear) || !RegisterValidation.isNumbersOnly(unitPrice)
					|| !RegisterValidation.isNumbersOnly(stockQuantity)) {
				request.setAttribute("validationError", ApplicationConstant.ERROR_ADD_PRODUCT_NUMBER_MESSAGE);
				request.getRequestDispatcher(ApplicationConstant.ADD_PRODUCT_PAGE).forward(request, response);
			} else if (releaseYear.length() != 4) {
				request.setAttribute("validationError", ApplicationConstant.ERROR_YEAR_MESSAGE);
				request.getRequestDispatcher(ApplicationConstant.ADD_PRODUCT_PAGE).forward(request, response);

			} else if (Integer.parseInt(stockQuantity) < 1) {
				request.setAttribute("validationError", ApplicationConstant.ERROR_STOCK_QUANTITY_MESSAGE);
				request.getRequestDispatcher(ApplicationConstant.ADD_PRODUCT_PAGE).forward(request, response);
			} else if (Integer.parseInt(unitPrice) < 0) {
				request.setAttribute("validationError", ApplicationConstant.ERROR_UNIT_PRICE_MESSAGE);
				request.getRequestDispatcher(ApplicationConstant.ADD_PRODUCT_PAGE).forward(request, response);
			} else {

				Product product = new Product();

				product.setProductName(productName);
				product.setReleasedYear(Integer.parseInt(releaseYear));
				product.setProductCategory(productCategory);
				product.setProductDistributer(productDistributer);
				product.setProductDescription(productDescription);
				product.setStockQuantity(Integer.parseInt(stockQuantity));
				product.setUnitPrice(Integer.parseInt(unitPrice));
				product.setImage_data(imageData);
				product.setImage_name(image_name);

				try {
					boolean isSuccess = productDao.addProduct(product);
					if (isSuccess) {
						System.out.println("Product Added");
						try {
							ProductDao productDao = new ProductDao();
							List<Product> listOfProduct;
							listOfProduct = productDao.getListOfProduct();
							for (Product newProduct : listOfProduct) {

								String base64ImageData = Base64.getEncoder().encodeToString(newProduct.getImage_data());
								newProduct.setBase64ImageData(base64ImageData);
							}

							request.setAttribute("productList", listOfProduct);

							request.getRequestDispatcher(ApplicationConstant.ADMIN_PRODUCT_PAGE).forward(request,
									response);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("Product Not Added");
						request.setAttribute("validationError", ApplicationConstant.ERROR_PRODUCT_EXISTS_MESSAGE);
						request.getRequestDispatcher(ApplicationConstant.ADD_PRODUCT_PAGE).forward(request, response);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception ex) {
		}
	}

}
