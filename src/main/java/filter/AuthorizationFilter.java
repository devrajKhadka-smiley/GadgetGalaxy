package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appConstant.ApplicationConstant;

public class AuthorizationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String uri = httpRequest.getRequestURI();
//		System.out.println("Requested URI: " + uri);

		String role = (String) httpRequest.getSession().getAttribute("role");
//		System.out.println("User Role: " + role);

		if (role != null && role.equals("admin")) {
			System.out.println("Admin Action:");

			if ((role != null && role.equals("admin")) && (uri.endsWith("admin"))) {
				System.out.println("1");
				httpRequest.getRequestDispatcher(ApplicationConstant.ADMIN_SERVLET).forward(httpRequest, httpResponse);

			} else if ((role != null && role.equals("admin")) && (uri.endsWith("update")
					|| uri.endsWith("/update-product") || uri.endsWith("updateProduct.jsp"))) {
				System.out.println("2");
				httpRequest.setAttribute("validationError", httpRequest.getAttribute("validationError"));
				httpRequest.getRequestDispatcher(ApplicationConstant.ADMIN_UPDATE_SERVLET).forward(httpRequest,
						httpResponse);
			} else if ((role != null && role.equals("admin"))
					&& (uri.endsWith("view-order") || uri.endsWith("adminOrder.jsp") || uri.endsWith("admin-order"))) {
				System.out.println("3");
				httpRequest.getRequestDispatcher(ApplicationConstant.ADMIN_ORDER_SERVLET).forward(httpRequest,
						httpResponse);

			} else if ((role != null && role.equals("admin")) && (uri.endsWith("view-client")
					|| uri.endsWith("adminClient.jsp") || uri.endsWith("admin-client"))) {
				System.out.println("4");
				httpRequest.getRequestDispatcher(ApplicationConstant.ADMIN_CLIENT_SERVLET).forward(httpRequest,
						httpResponse);

			} else if ((role != null && role.equals("admin"))
					&& (uri.endsWith("admin-product") || uri.endsWith("view"))) {
				System.out.println("5");
				httpRequest.getRequestDispatcher(ApplicationConstant.ADMIN_PRODUCT_SERVLET).forward(httpRequest,
						httpResponse);

			} else if ((role != null && role.equals("admin")) && (uri.endsWith("add-product")
					|| uri.endsWith("addProduct.jsp") || uri.endsWith("/add-product"))) {
				System.out.println("6");
				httpRequest.setAttribute("validationError", httpRequest.getAttribute("validationError"));
				httpRequest.getRequestDispatcher(ApplicationConstant.ADMIN_ADD_PRODUCT_SERVLET).forward(httpRequest,
						httpResponse);

			} else if ((role != null && role.equals("admin"))
					&& (uri.endsWith("admin-search-product") || uri.endsWith("search-product"))) {
				System.out.println("7");
				httpRequest.getRequestDispatcher(ApplicationConstant.ADMIN_SEARCH_SEVLET).forward(httpRequest,
						httpResponse);

			} else if ((role != null && role.equals("admin"))
					&& (uri.endsWith("logout") || uri.endsWith("user-logout"))) {
				System.out.println("8");
				httpRequest.getRequestDispatcher(ApplicationConstant.LOG_OUT_SERVLET).forward(httpRequest,
						httpResponse);

			} else if ((role != null && role.equals("admin")) && (uri.endsWith("edit-product"))) {
				System.out.println("9");
				httpRequest.getRequestDispatcher(ApplicationConstant.EDIT_PRODUCT_SERVLET).forward(httpRequest,
						httpResponse);

			} else if ((role != null && role.equals("admin")) && (uri.endsWith("delete-product"))) {
				System.out.println("10");
				httpRequest.getRequestDispatcher(ApplicationConstant.DELETE_PRODUCT_SERVLET).forward(httpRequest,
						httpResponse);

			} else if ((role != null && role.equals("admin")) && (uri.endsWith("change-order-status"))) {
				System.out.println("11");
				chain.doFilter(httpRequest, httpResponse);

			} else {
				System.out.println("12");
				httpRequest.getRequestDispatcher(ApplicationConstant.ERROR_PAGE).forward(httpRequest, httpResponse);
			}
		} else if (role != null && role.equals("client")) {
			if ((role != null && role.equals("client")) && (uri.endsWith("about") || uri.endsWith("add-cart")
					|| uri.endsWith("client-search-product") || uri.endsWith("home")
					|| uri.endsWith("individual-product") || uri.endsWith("place-order") || uri.endsWith("purchase")
					|| uri.endsWith("remove-from-cart") || uri.endsWith("shop") || uri.endsWith("track-each-order")
					|| uri.endsWith("track-order") || uri.endsWith("update-cart") || uri.endsWith("logout")
					|| uri.endsWith("profile") || uri.endsWith("view-cart")) || (uri.endsWith("update-profile"))) {
				chain.doFilter(httpRequest, httpResponse);

			} else {
				httpRequest.getRequestDispatcher(ApplicationConstant.ERROR_PAGE).forward(httpRequest, httpResponse);
			}

		} else {

			chain.doFilter(httpRequest, httpResponse);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
