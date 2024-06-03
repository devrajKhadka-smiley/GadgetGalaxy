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
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/place-order" })
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao orderDao;
	private ClientDao clientDao;
	private SaleDao saleDao;
	private ProductDao productDao;

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlaceOrderServlet() {
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

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date date = new Date();

			if (sessionUsername != null) {
				int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
				int clientId = clientDao.getClientByUsername(sessionUsername);
				@SuppressWarnings("unchecked")
				ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
				ArrayList<Cart> emptyCart = new ArrayList<Cart>();

				if (cartList != null) {

					Orders order = new Orders();
					order.setOrderDate(formatter.format(date));
					order.setTotalAmount(totalAmount);
					order.setOrderStatus("pending");

					try {
						boolean orderResult = orderDao.insertOrder(order);

						if (orderResult) {
							int orderId = orderDao.getOrderId();
							if (cartList.size() > 0) {

								for (Cart cart : cartList) {
									Product product = productDao.getProductById(cart.getModelNumber());
									productDao.updateProductQuantity(cart.getModelNumber(), cart.getQuantity());
									product.setStockQuantity(product.getStockQuantity() - cart.getQuantity());

									Sale sale = new Sale();
									sale.setOrderId(orderId);
									sale.setClientId(clientId);
									sale.setModelNumber(cart.getModelNumber());
									sale.setOrderQuantity(cart.getQuantity());

									saleDao.insertSale(sale);

								}
								session.setAttribute("cart-list", emptyCart);
								session.setAttribute("cartMessage", "Thank you for placing the order!!");
								response.sendRedirect(request.getContextPath() + ApplicationConstant.CART_SERVLET);
							}
						} else {
							// Custom error
							request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request,
									response);
						}
					} catch (SQLException | NullPointerException e) {
						// TODO Auto-generated catch block
						request.getRequestDispatcher(ApplicationConstant.INTERNAL_ERROR_PAGE).forward(request,
								response);
					}
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
