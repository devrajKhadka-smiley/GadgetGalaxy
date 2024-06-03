<%@page import="model.Product"%>
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

.admin-panel .main-dashboard form .formField {
	margin-bottom: 10px;
}

.admin-panel .main-dashboard form .formField input[type=text] {
	padding: 10px;
	font-size: 1.1rem;
}

.admin-panel .main-dashboard form .formField input[type=file] {
	font-size: 1.1rem;
}

.admin-panel .main-dashboard form .formField input[type=file]::file-selector-button
	{
	padding: 5px 10px;
	margin-right: 10px;
}

.admin-panel .main-dashboard form .formField label {
	display: inline-block;
	width: 190px;
}

.admin-panel .main-dashboard form button {
	margin-top: 10px;
	padding: 10px;
	width: 160px;
	font-size: 1rem;
	font-weight: bold;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	background-color: green;
	color: white;
	transition: 0.23s ease;
}

.admin-panel .main-dashboard form button:hover {
	background-color: rgb(2, 86, 2);
} /*# sourceMappingURL=main.css.map */
.productName-heading {
	font-size: 3rem;
	font-weight: bold;
	color: #cfcfcf;
}
</style>

<title>Update Product | Gadget Galaxy</title>
</head>
<body>
	<%
	String userSession = (String) session.getAttribute("sessionUsername");
	String validationError = (String) request.getAttribute("validationError");
	%>
	<div class="admin-container">
		<jsp:include page="./adminNavigation.jsp"></jsp:include>

		<div class="admin-panel">
			<div class="admin-top p-0-80">
				Hello,
				<%=userSession%></div>
			<h1 class="p-0-80" style="margin-top: 30px; margin-bottom: -20px;">Update
				Product</h1>
			<div class="main-dashboard p-0-80">

				<c:set var="product" value="${product}"></c:set>
				<div style="display: flex;">
					<div>
						<form action="<%=request.getContextPath()%>/update-product"
							method="post" enctype="multipart/form-data">
							<p class="productName-heading">${product.productName }</p>
							<div class="formField">

								<input type="text" placeholder="modelNumber" name="modelNumber"
									value="${modelNumber}" style="display: none;"> <input
									type="text" placeholder="productName" name="productName"
									value="${product.productName}" style="display: none;">
							</div>



							<div class="formField">
								<label for="releasedYear">Released Year</label> <input
									type="text" placeholder="release year" name="releaseYear"
									id="releasedYear" value="${product.releasedYear }" required>
							</div>

							<div class="formField">
								<label for="productCategory">Product Category</label> <input
									type="text" placeholder="product category" id="productCategory"
									name="productCategory" value="${product.productCategory }"
									required>
							</div>

							<div class="formField">
								<label for="productDistributer">Product Distributer</label> <input
									type="text" placeholder="product distributer"
									name="productDistributer" id="productDistributer"
									value="${product.productDistributer }" required>
							</div>

							<div class="formField">
								<label for="productDescription">Product Description</label> <input
									type="text" placeholder="product Description"
									name="productDescription" id="productDescription"
									value="${product.productDescription }" required>
							</div>

							<div class="formField">
								<label for="stockQuantity">Stock Quantity</label> <input
									type="text" placeholder="Stock Quantity" name="stockQuantity"
									id="stockQuantity" value="${product.stockQuantity}" required>
							</div>
							<div class="formField">
								<label for="unitProduct">Unit Price</label> <input type="text"
									placeholder="Unit Price" name="unitPrice" id="unitProduct"
									value="${product.unitPrice }" required>
							</div>

							<div class="formField">
								<label for="image">Image</label> <input type="file" name="image"
									id="image" accept="image/*">
							</div>

							<button type="submit" value="Upload">
								<b>Update</b>
							</button>
						</form>
						<%
						if (validationError != null) {
						%>

						<div style="color: red; margin-top: 10px; font-weight: bold;"><%=validationError%></div>
						<%
						}
						%>

					</div>
					<div
						style="width: 384px; overflow: hidden; padding: 40px; border: 2px solid; display: flex; justify-content: center; align-items: center;">
						<img alt=""
							src="data:image/jpeg;base64,${product.base64ImageData }"
							style="width: 300px;">
					</div>
				</div>
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