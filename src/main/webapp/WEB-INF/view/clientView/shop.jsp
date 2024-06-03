<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page import="appConstant.ApplicationConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="../navigationBar/css_js_holder.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() + ApplicationConstant.HOME_CSS%>">
<script type="text/javascript">
	if (window.history.replaceState) {
		window.history.replaceState(null, null, window.location.href);

	}
</script>


<title>Shop | Gadget Galaxy</title>
</head>
<body>

	<div class="navbar">
		<jsp:include page="../navigationBar/mainnavbar.jsp"></jsp:include>
		<jsp:include page="../navigationBar/midnavbar.jsp"></jsp:include>
		<%@page import="appConstant.ApplicationConstant"%>
		<div class="downNavbar" id="mainSticky">
			<nav class="downNavbarCenter">
				<a
					href="<%=request.getContextPath() + ApplicationConstant.HOME_SERVLET%>">Home</a>
				<a
					href="<%=request.getContextPath() + ApplicationConstant.SHOP_SERVLET%>"
					class="active">Shop</a> <a
					href="<%=request.getContextPath() + ApplicationConstant.ABOUT_SERVLET%>">About
					Us</a>
			</nav>
		</div>
	</div>

	<div class="main-product-container p-0-80" style="margin-top: 50px;">

		<div class="product-container-row">
			<%
			List<Product> productListNew = (List<Product>) request.getAttribute("productList");
			String noProductMessage = (String) request.getAttribute("noProductFound");

			if (productListNew.size() > 0) {
			%>
			<c:forEach var="product" items="${productList}">
				<c:if test="${product.stockQuantity > 0}">
					<form action="individual-product" method="get"
						style="margin-bottom: 20px;">
						<input type="text" name="productId"
							value="${product.modelNumber }" style="display: none;">
						<button class="product-container">
							<div class="product-image-container">
								<img src="data:image/jpeg;base64,${product.base64ImageData }"
									alt="asus" />
							</div>
							<div class="product-details-container">
								<div class="phone-category-distributer">
									<div class="phone-category">
										<c:out value="${product.productCategory }"></c:out>
									</div>
									<div class="phone-distributer">
										<c:out value="${product.productDistributer }"></c:out>
									</div>
								</div>
								<div class="phone-name">
									<c:out value="${product.productName }"></c:out>
								</div>
								<div class="phone-price">
									$
									<c:out value="${product.unitPrice }"></c:out>
								</div>
							</div>
						</button>
					</form>
				</c:if>
			</c:forEach>
			<%
			} else {
			%>
			<div style="margin-top: 100px; font-size: 72px; color: gray;"><%=noProductMessage%></div>
			<%
			}
			%>
		</div>
		
	</div>
	<script type="text/javascript">
		let inactivityTimer;

		function reloadPage() {
			location.reload();
		}

		function resetTimer() {
			clearTimeout(inactivityTimer);
			inactivityTimer = setTimeout(reloadPage, 1800000);
		}

		document.addEventListener('mousemove', resetTimer);
		document.addEventListener('keypress', resetTimer);
		document.addEventListener('touchstart', resetTimer);

		resetTimer();
		console.log(inactivityTimer)

		document.addEventListener("DOMContentLoaded", function(event) {
			var scrollpos = sessionStorage.getItem('scrollpos');
			if (scrollpos)
				window.scrollTo(0, scrollpos);
		});

		window.onbeforeunload = function(e) {
			sessionStorage.setItem('scrollpos', window.scrollY);
		};
	</script>
</body>
</html>