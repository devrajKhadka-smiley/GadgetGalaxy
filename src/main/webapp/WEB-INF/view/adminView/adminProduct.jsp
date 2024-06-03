<%@page import="appConstant.ApplicationConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Arial", sans-serif;
}

body {
	height: 100vh;
}

.admin-container {
	display: flex;
	flex-direction: row;
}

.admin-container .admin-nav {
	position: fixed;
	width: 300px;
	box-shadow: 1px 0px 10px rgb(119, 119, 119);
	height: 100vh;
}

.admin-container .admin-nav .logo {
	width: 100%;
	display: flex;
	padding: 10px 20px;
	justify-content: space-around;
	align-items: center;
	margin-bottom: 40px;
	margin-top: 20px;
}

.admin-container .admin-nav .logo img:nth-child(1) {
	width: 50px;
}

.admin-container .admin-nav .logo img:nth-child(2) {
	width: 200px;
}

.admin-container .admin-nav ul li {
	list-style-type: none;
}

.admin-container .admin-nav ul li a {
	text-decoration: none;
	display: block;
	background-color: green;
	text-align: center;
	padding: 20px;
	font-size: 150%;
	color: white;
	transition: all 0.24s ease-in;
	font-weight: bold;
}

.admin-container .admin-nav ul li a:hover {
	color: white;
	background-color: darkgreen;
}

.admin-container .admin-nav .logout-button {
	width: 100%;
	position: absolute;
	bottom: 20px;
	display: flex;
	justify-content: center;
}

.admin-container .admin-nav .logout-button button {
	padding: 12px 40px;
	border: none;
	font-size: 1.2rem;
	border-radius: 10px;
	cursor: pointer;
	color: white;
	background-color: green;
	font-weight: bold;
}

.admin-container .admin-nav .logout-button button:hover {
	background-color: darkgreen;
}

.admin-container .admin-panel {
	width: 100%;
	margin-left: 300px;
	margin-bottom: 30px;
}

.admin-container .admin-panel .admin-top {
	display: flex;
	align-items: center;
	justify-content: flex-end;
	height: 80px;
	background-color: rgb(202, 201, 201);
	font-size: 2rem;
	color: rgb(53, 53, 53);
}

.p-0-80 {
	padding: 0 100px;
} /*# sourceMappingURL=main.css.map */
.main-dashboard {
	margin-top: 50px;
}

.admin-product {
	margin-top: 50px;
}

.admin-product .view-product-section {
	font-size: 3rem;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.admin-product .view-product-section .add-product-button button {
	padding: 10px 20px;
	outline: none;
	border: 1px solid black;
	border-radius: 4px;
	cursor: pointer;
	font-size: 18px;
	transition: 0.23s ease;
	font-weight: bold;
}

.admin-product .view-product-section .add-product-button button:hover {
	background-color: green;
	color: #ffffff;
	border: 1px solid green;
}

.admin-product .view-product-section .product-search-bar {
	display: flex;
	align-items: center;
	border-radius: 5px;
	border: 1px solid rgb(53, 53, 53);
}

.admin-product .view-product-section .product-search-bar input[type=search]
	{
	background: transparent;
	padding: 10px 20px;
	margin: 0;
	width: 350px;
	font-size: 1.3rem;
	border: none;
	border: 1px solid transparent;
	border-radius: inherit;
	color: rgb(53, 53, 53);
}

.admin-product .view-product-section .product-search-bar input[type=search]:focus
	{
	outline: none;
}

.admin-product .view-product-section .product-search-bar button[type=submit]
	{
	text-indent: -999px;
	overflow: hidden;
	width: 40px;
	padding: 2px;
	margin: 0;
	padding-right: 2px;
	border: 1px solid transparent;
	border-radius: inherit;
	background: transparent
		url("<%=request.getContextPath()%>/images/magnifying-glass-solid.svg")
		no-repeat center;
	cursor: pointer;
	opacity: 0.7;
	height: 100%;
}

.admin-product .view-product-section .product-search-bar button[type=submit]:hover
	{
	opacity: 1;
}

.admin-product hr {
	margin: 40px 0;
	background-color: rgb(53, 53, 53);
	height: 3px;
}

.admin-product table.product-display {
	border: 2px solid black;
	border-collapse: collapse;
	width: 100%;
}

.admin-product table.product-display th {
	background-color: green;
	color: white;
	font-weight: bold;
}

.admin-product table.product-display th, .admin-product table.product-display td
	{
	border: 2px solid rgb(189, 189, 189);
	padding: 10px;
	text-align: center;
}

.crud-buttons {
	padding: 0px;
}

table.order td.no-padding-td {
	padding: 0px;
}

table td form {
	display: flex;
	justify-content: center;
	align-items: center;
}

table td form button {
	border: none;
	outline: none;
	font-size: 20px;
	background-color: white;
	width: 100%;
	height: 29px;
	cursor: pointer;
	font-weight: bold;
	transition: 0.12s ease;
}

table td form button:hover {
	background-color: #123121;
	color: white;
}
</style>
<title>View Product | Gadget Galaxy</title>
<script type="text/javascript">
	if (window.history.replaceState) {
		window.history.replaceState(null, null, window.location.href);
	}
</script>
</head>
<body>
	<%
	String userSession = (String) session.getAttribute("sessionUsername");
	%>
	<div class="admin-container">
		<jsp:include page="./adminNavigation.jsp"></jsp:include>

		<div class="admin-panel">
			<div class="admin-top p-0-80">
				Hello,
				<%=userSession%></div>
			<div class="admin-product p-0-80">
				<div class="view-product-section">
					<form action="add-product" class="add-product-button">
						<button type="submit">Add Product</button>
					</form>
					<form action="admin-search-product" class="product-search-bar">
						<input type="search" name="search" placeholder="Search..." />
						<button type="submit">Search</button>
					</form>
				</div>
				<hr>
				<table class="product-display">
					<thead>
						<tr>

							<th>Model No.</th>
							<th>Name</th>
							<th>Category</th>
							<th>Price</th>
							<th>Stock</th>
							<th>Distributer</th>
							<th>Released Year</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</tr>
						<!-- <th>Released Year</th> -->
					</thead>

					<tbody>
						<c:forEach var="product" items="${productList }">

							<tr>
								<td><c:out value="${product.modelNumber }"></c:out></td>
								<td><c:out value="${product.productName }"></c:out></td>
								<td><c:out value="${product.productCategory }"></c:out></td>
								<td>$<c:out value="${product.unitPrice }"></c:out>
								</td>
								<td><c:out value="${product.stockQuantity }"></c:out></td>
								<td><c:out value="${product.productDistributer }"></c:out>
								</td>
								<td><c:out value="${product.releasedYear }"></c:out></td>
								<td class="no-padding-td">
									<form action="<%=request.getContextPath()%>/edit-product"
										method="post">
										<input type="text" name="modelNumber" id="modelNumber"
											value="${product.modelNumber }" class="crud-buttons"
											style="display: none;">
										<button type="submit">Edit</button>
									</form>
								</td>
								<td class="no-padding-td">
									<form action="<%=request.getContextPath()%>/delete-product"
										method="post">
										<input type="text" name="modelNumber" id="modelNumber"
											value="${product.modelNumber }" class="crud-buttons"
											style="display: none;">
										<button type="submit">Delete</button>
									</form>
								</td>
							</tr>

						</c:forEach>

					</tbody>
				</table>
			</div>


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