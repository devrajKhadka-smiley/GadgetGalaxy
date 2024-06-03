package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.ApplicationConstant;
import model.Cart;
import model.Orders;
import model.Product;
import model.Sale;
import service.ClientDao;
import service.OrderDao;
import service.ProductDao;
import service.SaleDao;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/purchase" })
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao orderDao;
	private ClientDao clientDao;
	private SaleDao saleDao;
	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			orderDao = new OrderDao();
			clientDao = new ClientDao();
			saleDao = new SaleDao();
			productDao = new ProductDao();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PurchaseServlet() {
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

			HttpSession session = request.getSession(false);
			String sessionUsername = (String) session.getAttribute("sessionUsername");
			@SuppressWarnings("unchecked")
			ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date date = new Date();

			if (sessionUsername != null) {
				try {
					int modelNumber = Integer.parseInt(request.getParameter("modelNumber"));
					int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
					int clientId = clientDao.getClientByUsername(sessionUsername);

					int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));

					if (productQuantity <= 0) {
						productQuantity = 1;
					}

					Orders order = new Orders();
					order.setOrderDate(formatter.format(date));
					order.setTotalAmount(totalAmount);
					order.setOrderStatus("pending");

					boolean orderResult = orderDao.insertOrder(order);

					if (orderResult) {
						Product product = productDao.getProductById(modelNumber);
						productDao.updateProductQuantity(modelNumber, productQuantity);
						product.setStockQuantity(product.getStockQuantity() - productQuantity);

						int orderId = orderDao.getOrderId();
						Sale sale = new Sale();
						sale.setClientId(clientId);
						sale.setModelNumber(modelNumber);
						sale.setOrderId(orderId);
						sale.setOrderQuantity(productQuantity);

						boolean saleResult = saleDao.insertSale(sale);
						if (saleResult) {
							if (cartList != null) {
								for (Cart cart : cartList) {
									if (cart.getModelNumber() == modelNumber) {
										cartList.remove(cart);
										session.setAttribute("cart-list", cartList);
										break;
									}
								}
								response.sendRedirect(request.getContextPath() + ApplicationConstant.CART_SERVLET);
							}
						} else {
							System.out.println("Sale not saled");
						}
					} else {
						System.out.println("Order not Placed");
					}
				} catch (SQLException | NullPointerException e) {
					// TODO Auto-generated catch block
					request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request, response);
				}

			} else {
				response.sendRedirect(request.getContextPath() + ApplicationConstant.LOGIN_SERVLET);
			}
		} catch (Exception ex) {
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
