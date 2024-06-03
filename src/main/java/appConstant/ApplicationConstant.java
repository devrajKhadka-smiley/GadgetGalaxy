package appConstant;

public class ApplicationConstant {
//	Start JSP Routes
	public static final String HOME_PAGE = "/WEB-INF/view/home.jsp";
	public static final String SHOP_PAGE = "/WEB-INF/view/clientView/shop.jsp";
	public static final String LOGIN_PAGE = "/WEB-INF/view/clientView/login.jsp";
	public static final String CART_PAGE = "/WEB-INF/view/clientView/cart.jsp";
	public static final String ADMIN_PRODUCT_PAGE = "/WEB-INF/view/adminView/adminProduct.jsp";
	public static final String ADMIN_CLIENT_PAGE = "/WEB-INF/view/adminView/adminClient.jsp";
	public static final String ADMIN_ORDER_PAGE = "/WEB-INF/view/adminView/adminOrder.jsp";
	public static final String ADD_PRODUCT_PAGE = "/WEB-INF/view/adminView/addProduct.jsp";
	public static final String UPDATE_PRODUCT_PAGE = "/WEB-INF/view/adminView/updateProduct.jsp";
	public static final String REGISTER_PAGE = "/WEB-INF/view/clientView/register.jsp";
	public static final String TRACK_ORDER_PAGE = "/WEB-INF/view/clientView/trackOrder.jsp";
	public static final String TRACK_EACH_ORDER_PAGE = "/WEB-INF/view/clientView/trackEachOrder.jsp";
	public static final String PROFILE_PAGE = "/WEB-INF/view/clientView/userProfile.jsp";
	public static final String ERROR_PAGE = "/WEB-INF/view/clientView/error.jsp";
	public static final String INTERNAL_ERROR_PAGE = "/WEB-INF/view/clientView/internalError.jsp";
	public static final String ABOUT_US_PAGE = "/WEB-INF/view/clientView/aboutus.jsp";
	public static final String ADMIN_DASH = "/WEB-INF/view/adminView/adminDashboard.jsp";

//  END JSP ROUTES

//	START SERVLET ROUTES
	public static final String HOME_SERVLET = "/home";
	public static final String LOGIN_SERVLET = "/login";
	public static final String REGISTER_SERVLET = "/register";
	public static final String ADMIN_PRODUCT_SERVLET = "/admin-product";
	public static final String ADMIN_ORDER_SERVLET = "/admin-order";
	public static final String ADMIN_CLIENT_SERVLET = "/admin-client";
	public static final String CART_SERVLET = "/view-cart";
	public static final String UPDATE_CART_SERVLET = "/update-cart";
	public static final String SHOP_SERVLET = "/shop";
	public static final String PROFILE_SERVLET = "/profile";
	public static final String ABOUT_SERVLET = "/about";
	public static final String LOG_OUT_SERVLET = "/user-logout";
	public static final String ADMIN_SERVLET = "/admin";
	public static final String ADMIN_UPDATE_SERVLET = "/update-product";
	public static final String ADMIN_ADD_PRODUCT_SERVLET = "/add-product";
	public static final String ADMIN_SEARCH_SEVLET = "/admin-search-product";
	public static final String EDIT_PRODUCT_SERVLET = "/edit-product";
	public static final String DELETE_PRODUCT_SERVLET = "/delete-product";
	public static final String CHANGE_ORDER_STATUS = "/change-order-status";
	public static final String CLIENT_SEARCH_SERVLET = "/client-search-product";
	public static final String ADD_TO_CART_SERVLET = "/add-cart";
	public static final String INDIVIDUAL_PRODUCT_SERVLET = "/individual-product";
	public static final String PLACE_ORDER_SERVLET = "/place-order";
	public static final String PURCHASE_SERVLET = "/purchase";
	public static final String REMOVE_FROM_CART_SERVLET = "/remove-from-cart";
	public static final String TRACK_EACH_ORDER_SERVLET = "/track-each-order";
	public static final String TRACK_ORDER_SERVLET = "/track-order";

//	END SERVLET ROUTES

//	START JS ROUTING
	public static final String NAVBAR_JS = "/js/mainnav.js";
	public static final String STICKYNAV_JS = "/js/navsticky.js";
//	END JS ROUTING

//	START CS ROUTING
	public static final String NAVBAR_CSS = "/css/mainnav.css";
	public static final String LOGIN_CSS = "/css/login.css";
	public static final String HOME_CSS = "/css/home.css";
	public static final String PRODUCT_CSS = "/css/product.css";
	public static final String DASHBOARD_CSS = "/css/dashboard.css";
	public static final String ADMIN_PRODUCT_CSS = "/css/crud_product.css";
	public static final String ADMIN_CLIENT_CSS = "/css/adminClient.css";
	public static final String ADMIN_ORDER_CSS = "/css/adminOrder.css";
	public static final String ADD_PRODUCT_CSS = "/css/addProduct.css";
	public static final String REGISTER_CSS = "/css/register.css";
	public static final String CLIENT_PRODUCT_CSS = "/css/clientProduct.css";
	public static final String TRACK_ORDER_CSS = "/css/trackOrder.css";
	public static final String TRACK_EACH_ORDER_CSS = "/css/trackEachOrder.css";
//	END CS ROUTING

//	START MESSAGE ROUTING
	public static final String ERROR_MESSAGE_INCORRECT_DATA = "Please Use a valid Credential";
	public static final String ERROR_MESSAGE_EMAIL_DOMAIN = "Please Use ggclient.com as domain";
	public static final String ERROR_STRING_MESSAGE = "Please use text only";
	public static final String ERROR_PHONE_MESSAGE = "Please use numbers only in phone number";
	public static final String ERROR_PHONE_LONG_MESSAGE = "Please enter 10 numbers only in phone number";
	public static final String ERROR_CHARACTER_MESSAGE = "Please remove any special characters";
	public static final String ERROR_YEAR_MESSAGE = "Please enter year as 4 character only";
	public static final String ERROR_ADD_PRODUCT_NUMBER_MESSAGE = "Please make sure the value of released year, stockQuantity and unitPrice is in number";
	public static final String ERROR_STOCK_QUANTITY_MESSAGE = "Please enter quantity more than 0";
	public static final String ERROR_UNIT_PRICE_MESSAGE = "Please enter price in positive numbers";
	public static final String ERROR_PRODUCT_EXISTS_MESSAGE = "Product with that name already exists";
	public static final String ERROR_UPDATE_PRODUCT_MESSAGE = "Product could not be updated";

//	START MESSAGE ROUTING
}
