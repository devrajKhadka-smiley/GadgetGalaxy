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
import javax.servlet.http.HttpSession;

import appConstant.ApplicationConstant;

public class AuthFilter implements Filter {

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

		HttpSession session = httpRequest.getSession(false);

		String role = (String) httpRequest.getSession().getAttribute("role");

		if (uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".svg")
				|| uri.endsWith(".mp4") || uri.endsWith(".js")) {
			chain.doFilter(request, response);
			return;
		} else if (uri.endsWith("/") || uri.endsWith("home") || uri.endsWith("gg") || uri.endsWith("gadgetgalaxy")
				|| uri.endsWith("index.jsp")) {
			if (role == "admin") {
				httpRequest.getRequestDispatcher(ApplicationConstant.ERROR_PAGE).forward(httpRequest, httpResponse);

			} else {
				httpRequest.getRequestDispatcher(ApplicationConstant.HOME_PAGE).forward(request, response);
				
			}
			return;
		} else if (uri.endsWith("login") || uri.endsWith("register") || uri.endsWith("about") || uri.endsWith("shop")
				|| uri.endsWith("client-search-product") || uri.endsWith("individual-product")) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		} else if (session == null || session.getAttribute("sessionUsername") == null) {
			if (uri.endsWith("admin") || uri.endsWith("profile") || uri.endsWith("track-order")
					|| uri.endsWith("view-cart") || uri.endsWith("add-cart")) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + ApplicationConstant.LOGIN_SERVLET);

			} else {
				httpRequest.getRequestDispatcher(ApplicationConstant.ERROR_PAGE).forward(httpRequest, httpResponse);
				return;
			}
			return;
		} else {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
