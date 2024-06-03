<%@page import="appConstant.ApplicationConstant"%>
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
<title>Track your Order | <%=sessionName%></title>
<jsp:include page="../navigationBar/css_js_holder.jsp"></jsp:include>
<style>
@charset "ISO-8859-1";

.main-order-track {
	margin-top: 50px;
	margin-bottom: 50px;
}

.order {
	border: 3px solid black;
	border-collapse: collapse;
	width: 100%;
}

table.order th {
	background-color: rgb(128, 0, 32);
	color: white;
	font-weight: bold;
}

table.order th, table.order td {
	border: 3px solid #28231D;
	padding: 10px;
}

table.order td#no-padding-td {
	padding: 0px;
}

table.order td form {
	display: flex;
	justify-content: center;
	align-items: center;
}

table.order td form button {
	border: none;
	outline: none;
	font-size: 20px;
	background-color: white;
	width: 100%;
	height: 39px;
	cursor: pointer;
	font-weight: bold;
	transition: 0.12s ease;
}

table.order td form button:hover {
	background-color: rgb(128, 0, 32);
	color: white;
}
</style>
</head>
<body>
	<div class="navbar">
		<jsp:include page="../navigationBar/mainnavbar.jsp"></jsp:include>
		<jsp:include page="../navigationBar/midnavbar.jsp"></jsp:include>
		<jsp:include page="../navigationBar/downnavbar.jsp"></jsp:include>
	</div>


	<div class="main-order-track p-0-80">

		<table class="order">
			<thead>
				<tr>
					<th>Order Id</th>
					<th>Order Date</th>
					<th>Total Amount</th>
					<th>Order Status</th>
					<th style="background: grey;">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${clientOrderList }">

					<tr>
						<td><c:out value="${order.orderId }"></c:out></td>
						<td><c:out value="${order.orderDate}"></c:out></td>
						<td>$ <c:out value="${order.totalAmount }"></c:out></td>
						<td><c:out value="${order.orderStatus }"></c:out></td>
						<td id="no-padding-td">
							<form action="track-each-order" method="get">
								<input type="text" name="orderId" value="${order.orderId }"
									style="display: none;">
								<button type="submit">View</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

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