<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cart"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String sessionName = (String) session.getAttribute("sessionUsername");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../navigationBar/css_js_holder.jsp"></jsp:include>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Arial", sans-serif;
}

.m-0-80 {
	margin: 0 80px;
}

.main-section {
	display: flex;
}

.main-section .cart-section {
	width: 75%;
	display: flex;
	flex-direction: column;
	padding: 10px;
}

.main-section .cart-section .actual-cart {
	border: 2px solid black;
	margin-bottom: 20px;
	padding: 20px;
}

.main-section .cart-section .cart-section-1 .cart-product {
	width: 100%;
	height: 200px;
	display: flex;
	flex-direction: row;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-heading
	{
	font-weight: bold;
	font-size: 22px;
	padding: 20px;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-1 .cart-image-holder
	{
	display: flex;
	align-items: center;
	justify-content: center;
	width: 200px;
	height: 200px;
	overflow: hidden;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-1 .cart-image-holder img
	{
	width: 180px;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-2
	{
	display: flex;
	flex-direction: column;
	row-gap: 10px;
	width: 30%;
	padding: 12px 5px;
	font-size: 14px;
	justify-content: center;
	color: grey;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-3
	{
	width: 17%;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-3 .cart-product-unit-price
	{
	font-size: 32px;
	font-weight: bold;
	color: gray;
	margin-left: 20px;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-4
	{
	width: 18%;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-4 .cart-product-quantity
	{
	margin-left: 20px;
	height: 50px;
	width: 55%;
	padding: 10px;
	display: flex;
	align-items: center;
	justify-content: center;
	border: 2px solid;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-4 .cart-product-quantity span
	{
	width: 50%;
	text-align: center;
	font-size: 25px;
	font-weight: 600;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-4 .cart-product-quantity .num
	{
	font-size: 20px;
	cursor: default;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-5
	{
	width: 15%;
}

.main-section .cart-section .cart-section-1 .cart-product .cart-product-5 .cart-product-total
	{
	font-size: 32px;
	font-weight: bold;
	color: gray;
	margin-left: 20px;
}

.main-section .cart-section .cart-section-2 {
	display: flex;
	justify-content: space-between;
	margin-left: 15px;
	align-items: center;
}

.main-section .cart-section .cart-section-2 .cart-product-name {
	font-weight: bold;
	font-size: 32px;
	color: rgb(73, 73, 73);
}

.main-section .cart-section .cart-section-2 .cart-buttons {
	width: 220px;
	display: flex;
	justify-content: space-around;
}

.main-section .cart-section .cart-section-2 .cart-buttons button {
	padding: 8px 20px;
	border: none;
	outline: none;
	color: white;
	font-weight: bold;
	border-radius: 8px;
}

.main-section .cart-section .cart-section-2 .cart-buttons .purchase {
	background-color: blue;
	transition: 0.12s ease;
}

.main-section .cart-section .cart-section-2 .cart-buttons .purchase:hover
	{
	background-color: #00308F;
}

.main-section .cart-section .cart-section-2 .cart-buttons .remove {
	background-color: red;
	transition: 0.12s ease;
}

.main-section .cart-section .cart-section-2 .cart-buttons .remove:hover
	{
	background-color: #BA0021;
}

.main-section .place-order-section {
	width: 25%;
	border: 2px solid;
	height: 350px;
	margin-left: 20px;
	padding: 0px 10px;
	margin-top: 10px;
} /*# sourceMappingURL=cart.css.map */
.cartEmpty {
	font-size: 48px;
	margin: 100px;
}

.inc-sub {
	text-decoration: none;
	color: black;
}

.minus {
	margin-right: 8px;
	margin-left: 5px;
}

.plus {
	margin-left: 8px;
	margin-right: 5px;
}

table, table td {
	border: 2px solid;
	border-collapse: collapse;
	margin: auto;
	margin-top: 50px;
}

table td {
	padding: 10px;
}

.totalPrice {
	margin: auto;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: center;
	height: 80px;
	margin-top: 30px;
}

.totalPrice p {
	font-size: 30px;
	font-weight: bold;
}

.totalPrice form button {
	border: none;
	outline: none;
	background: green;
	padding: 10px 50px;
	color: white;
	font-weight: bold;
	border-radius: 8px;
	transition: 0.12s ease;
	font-size: 16px;
}

.totalPrice form button:hover {
	background-color: #006400;
}

button {
	cursor: pointer;
}
</style>
<title>Cart | <%=sessionName%></title>

</head>
<body>

	<div class="navbar">
		<jsp:include page="../navigationBar/mainnavbar.jsp"></jsp:include>
		<jsp:include page="../navigationBar/midnavbar.jsp"></jsp:include>
		<jsp:include page="../navigationBar/downnavbar.jsp"></jsp:include>
	</div>
	<%
	ArrayList<Cart> cartList = (ArrayList<Cart>) request.getAttribute("cartList");
	int totalPrice = (Integer) request.getAttribute("totalPrice");
	String cartEmptyMessage = (String) request.getAttribute("cartEmptyMessage");
	if (cartEmptyMessage == null || cartEmptyMessage.equals("")) {
	%>

	<section class="main-section m-0-80">
		<div class="cart-section">
			<%-- <c:forEach var="cartProduct" items="${cartList }"> --%>
			<%
			if (cartList.size() > 0) {

				for (Cart cartProduct : cartList) {
			%>

			<div class="actual-cart">
				<div class="cart-section-1">
					<div class="cart-product">
						<div class="cart-product-1">
							<div class="cart-image-holder">
								<img
									src="data:image/jpeg;base64,<%=cartProduct.getBase64ImageData()%>"
									alt="asus" />
							</div>
						</div>
						<div class="cart-product-2">
							<div class="cart-product-distributer">
								Distributer:
								<c:out value="<%=cartProduct.getProductDistributer()%>"></c:out>
							</div>
							<div class="cart-product-year">
								Released:
								<c:out value="<%=cartProduct.getReleasedYear()%>"></c:out>
							</div>
							<div class="cart-stock-quantity">
								Stock left:
								<c:out value="<%=cartProduct.getStockQuantity()%>"></c:out>
							</div>
							<div class="cart-product-description">
								<c:out value="<%=cartProduct.getProductDescription()%>"></c:out>
							</div>
						</div>
						<div class="cart-product-3">
							<div class="cart-product-heading">Unit Price</div>
							<div class="cart-product-unit-price">
								$
								<c:out value="<%=cartProduct.getUnitPrice()%>"></c:out>
							</div>
						</div>
						<div class="cart-product-4">
							<div class="cart-product-heading">Quantity</div>
							<!-- Put the plus minus here as well -->

							<form action="" method="get">
								<input type="text" name="modelNumber"
									value="<%=cartProduct.getModelNumber()%>"
									style="display: none;">
								<div class="cart-product-quantity">

									<a
										href="update-cart?action=sub&modelNumber=<%=cartProduct.getModelNumber()%>"
										class="inc-sub" type="button"><span class="minus">-</span></a>
									<input
										style="width: 30px; outline: none; border: none; text-align: center;"
										type="text" value="<%=cartProduct.getQuantity()%>" class="num">
									<a
										href="update-cart?action=inc&modelNumber=<%=cartProduct.getModelNumber()%>"
										class="inc-sub" type="button"><span class="plus">+</span></a>
								</div>
							</form>

						</div>
						<div class="cart-product-5">
							<div class="cart-product-heading">Total</div>
							<div class="cart-product-total">
								$
								<c:out value="<%=cartProduct.getPrice()%>"></c:out>
							</div>
						</div>
					</div>
				</div>
				<div class="cart-section-2">
					<div class="cart-product-name">
						<c:out value="<%=cartProduct.getProductName()%>"></c:out>
					</div>
					<!-- remove and purchase part -->
					<div class="cart-buttons">
						<form action="purchase">
							<input type="text" name="modelNumber"
								value="<%=cartProduct.getModelNumber()%>" style="display: none" />
							<input type="text" name="productQuantity"
								value="<%=cartProduct.getQuantity()%>" style="display: none;"
								class="quantityValue" /> <input type="text" name="totalAmount"
								value="<%=cartProduct.getQuantity() * cartProduct.getUnitPrice()%>"
								style="display: none;" />

							<button class="purchase">Purchase</button>
						</form>
						<form action="remove-from-cart" method="get">
							<input type="text" name="modelNumber"
								value="<%=cartProduct.getModelNumber()%>" style="display: none" />
							<button class="remove">Remove</button>
						</form>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
		<div class="place-order-section">
			<table>
				<thead>
					<tr>
						<td>Name</td>
						<td>Qty.</td>
						<td>Price</td>
					</tr>
				</thead>
				<tbody>

					<%
					for (Cart cartProduct : cartList) {
					%>
					<tr>
						<td><%=cartProduct.getProductName()%></td>
						<td><%=cartProduct.getQuantity()%></td>
						<td>$<%=cartProduct.getQuantity() * cartProduct.getUnitPrice()%></td>
					</tr>

					<%
					}
					%>
				</tbody>
			</table>
			<div class="totalPrice">

				<p>
					Total Price:
					<%=totalPrice%></p>
				<form action="place-order" method="get">
					<input type="text" name="totalAmount" value="<%=totalPrice%>"
						style="display: none;">

					<button>Place Order</button>
				</form>
			</div>
		</div>
	</section>
	<%
	} else {
	%>
	<div class="cartEmpty">Your cart is now empty. To track your
		order, please click the link at the top!!</div>
	<%
	}
	%>

	<%-- </c:forEach> --%>

	<%
	} else {
	%>
	<div class="cartEmpty">
		<%=cartEmptyMessage%>
	</div>

	<%
	}
	%>

	</div> --%>
	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded", function(event) {
			var scrollpos = sessionStorage.getItem('scrollpos');
			if (scrollpos)
				window.scrollTo(0, scrollpos);
		});

		window.onbeforeunload = function(e) {
			sessionStorage.setItem('scrollpos', window.scrollY);
		};
	</script>
	<!-- <script>
    // Select all plus and minus buttons
    const buttons = document.querySelectorAll(".plus, .minus");

    // Attach click event listeners to each button
    buttons.forEach(button => {
        button.addEventListener("click", () => {
            // Find the parent container of the clicked button (i.e., the product container)
            const productContainer = button.closest(".actual-cart");
            
            // Find the quantity input field and the display element within the product container
            const quantityInput = productContainer.querySelector(".quantityValue");
            const displayNum = productContainer.querySelector(".num");
            
            // Get the current quantity from the input field
            let quantity = parseInt(quantityInput.value);
            
            // Increment or decrement the quantity based on the button clicked
            if (button.classList.contains("plus")) {
                quantity++;
            } else if (button.classList.contains("minus") && quantity > 1) {
                quantity--;
            }
            
            // Update the input field and display with the new quantity
            quantityInput.value = quantity;
            displayNum.value = quantity;
        });
    }); 
</script>-->
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