`<%@page import="appConstant.ApplicationConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String sessionName = (String) session.getAttribute("sessionUsername");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products in your order</title>
<jsp:include page="../navigationBar/css_js_holder.jsp"></jsp:include>

<style type="text/css">
.each-container {
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
	justify-content: space-between;
}

.each-product-container {
	margin: 20px 0;
	border: 2px solid;
	padding: 20px;
	width: 440px;
	display: flex;
	align-items: center;
}

.each-product-container .each-image {
	width: 160px;
	height: 170px;
	overflow: hidden;
}

.each-product-container .each-description {
	display: flex;
	flex-direction: column;
	margin-left: 30px;
	height: 120px;
	justify-content: space-around;
	font-size: 16px;
}

.each-product-container .each-description .each-product-name {
	font-size: 24px;
	font-weight: bold;
}

img {
	width: 170px;
}
</style>
</head>
<body>
	<div class="navbar">
		<jsp:include page="../navigationBar/mainnavbar.jsp"></jsp:include>
		<jsp:include page="../navigationBar/midnavbar.jsp"></jsp:include>
		<jsp:include page="../navigationBar/downnavbar.jsp"></jsp:include>
	</div>

	<div class="each-container p-0-80">

		<c:forEach var="sale" items="${productOrderList}">
			<div class="each-product-container">
				<div class="each-image">
					<img src="data:image/jpeg;base64,${sale.base64ImageData }"
						alt="asus" />
				</div>
				<div class="each-description">

					<div class="each-product-name">
						<c:out value="${sale.productName }"></c:out>
					</div>
					<div class="each-product-distributer">
						<c:out value="${sale.productDistributer }"></c:out>
					</div>
					<div>
						Quantity:
						<c:out value="${sale.orderQuantity }"></c:out>
					</div>
				</div>
			</div>
		</c:forEach>
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