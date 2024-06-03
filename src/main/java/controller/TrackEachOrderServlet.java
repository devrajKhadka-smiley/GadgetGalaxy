package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appConstant.ApplicationConstant;
import model.Sale;
import service.SaleDao;

/**
 * Servlet implementation class TrackEachOrderServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/track-each-order" })
public class TrackEachOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SaleDao saleDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			saleDao = new SaleDao();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TrackEachOrderServlet() {
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
			String orderId = request.getParameter("orderId");
			List<Sale> listOfOrderProduct = saleDao.getProductByOrder(Integer.parseInt(orderId));
			for (Sale sale : listOfOrderProduct) {
				String base64ImageData = Base64.getEncoder().encodeToString(sale.getImage_data());
				sale.setBase64ImageData(base64ImageData);
			}

			request.setAttribute("productOrderList", listOfOrderProduct);
			request.getRequestDispatcher(ApplicationConstant.TRACK_EACH_ORDER_PAGE).forward(request, response);
		} catch (NumberFormatException | SQLException e) {
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
