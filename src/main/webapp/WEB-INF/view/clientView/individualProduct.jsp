<%@page import="appConstant.ApplicationConstant"%>
<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../navigationBar/css_js_holder.jsp"></jsp:include>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Arial", "Poppins", sans-serif;
	scroll-behavior: smooth;
}

body {
	background-color: #B2D5D1;
}

.p-0-80 {
	padding: 0 80px;
	display: flex;
	justify-content: space-between;
}

.container {
	max-width: 1000px;
	margin: auto;
	height: 90vh;
	background: white;
	box-shadow: 5px 5px 10px 3px rgba(0, 0, 0, 0.3);
}

.container .left-column {
	width: 65%;
	position: relative;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-right: 70px;
}

.container .left-column img {
	width: auto;
	height: 70%;
}

.container .right-column {
	width: 35%;
	margin-top: 60px;
	justify-content: space-evenly;
}

.container .right-column .product-description {
	border-bottom: 1px solid #E1E8EE;
	margin-bottom: 20px;
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
}

.container .right-column .product-description span {
	font-size: 12px;
	text-decoration: none;
}

.container .right-column .product-description h1 {
	font-weight: 300;
	font-size: 52px;
	color: #43484D;
}

.container .right-column .product-description p {
	font-size: 16px;
	font-weight: 300;
	color: #86939E;
	line-height: 24px;
}

.container .right-column .product-price {
	display: flex;
	align-items: center;
	justify-content: center;
}

.container .right-column .product-price span {
	font-size: 26px;
	font-weight: 300;
	margin-right: 20px;
}

.container .right-column .product-price .cart-btn {
	background-color: white;
	border: 1px solid green;
	font-size: 16px;
	color: green;
	text-decoration: none;
	padding: 12px 30px;
	transition: all 0.5s;
	border-radius: 50px;
}

.container .right-column .product-price .cart-btn:hover {
	background-color: green;
	color: white;
	cursor: pointer;
}

.container .right-column .wrapper {
	margin-top: 20px;
	margin-bottom: 30px;
	margin-left: 30px;
	height: 50px;
	width: 200px;
	display: flex;
	align-items: center;
	justify-content: center;
	border-radius: 12px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
}

.container .right-column .wrapper span {
	width: 50%;
	text-align: center;
	font-size: 25px;
	font-weight: 600;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

.container .right-column .wrapper span .num {
	font-size: 20px;
	border-right: 2px solid rgba(0, 0, 0, 0.2);
	border-left: 2px solid rgba(0, 0, 0, 0.2);
	pointer-events: none;
}
</style>
<script type="text/javascript">
	if (window.history.replaceState) {
		window.history.replaceState(null, null, window.location.href);
	}
</script>
</head>
<body>
	<div class="navbar">
		<jsp:include page="../navigationBar/mainnavbar.jsp"></jsp:include>
		<jsp:include page="../navigationBar/midnavbar.jsp"></jsp:include>
		<jsp:include page="../navigationBar/downnavbar.jsp"></jsp:include>
	</div>

	<c:set var="product" value="${individualProduct }"></c:set>
	<section>
		<div class="container p-0-80">
			<div class="left-column">

				<img src="data:image/jpeg;base64,${product.base64ImageData }"
					alt="asus" />

			</div>
			<div class="right-column">
				<div class="product-description">
					<h1>
						<c:out value="${product.productName }"></c:out>
					</h1>
					<span style="margin-bottom: 15px;"> <c:out
							value="${product.productCategory }"></c:out>
					</span> <span style="color: #9f9f9f;">$ <c:out
							value="${product.unitPrice }"></c:out>
					</span>
					<p style="margin-top: 20px;">
						<c:out value="${product.productDescription }"></c:out>
					</p>
				</div>
				<span style="font-size: 30px;">Quantity</span>

				<div class="wrapper">
					<input type="text" name="quantity"
						value="${product.stockQuantity }" id="stock"
						style="display: none;"> <span class="minus">-</span> <span>|</span>
					<span class="num">01</span> <span>|</span> <span class="plus">+</span>
				</div>
				<div class="product-price">
					<form action="add-cart" method="get">
						<input type="text" name="modelNumber"
							value="${product.modelNumber }" style="display: none;"><input
							type="text" placeholder="quantity" name="productQuantity"
							style="display: none;" class="quantityValue"> <span>
							<input type="text" class="unitPrice"
							value="${product.unitPrice }" style="display: none;">
						</span>

						<button class="cart-btn">Add to cart</button>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- <script>
    document.addEventListener("DOMContentLoaded", function() {
        const plus = document.querySelector(".plus");
        const minus = document.querySelector(".minus");
        const quantity = document.querySelector(".quantityValue");
        const num = document.querySelector(".num");
        const stockQuantity = parseInt(document.getElementById("stock").value);

        let currentQuantity = 1;
        num.innerText = currentQuantity < 10 ? "0" + currentQuantity : currentQuantity;
        quantity.value = currentQuantity;

        plus.addEventListener("click", () => {
            if (currentQuantity < stockQuantity) {
                currentQuantity++;
                num.innerText = currentQuantity < 10 ? "0" + currentQuantity : currentQuantity;
                quantity.value = currentQuantity;
            }
        });

        minus.addEventListener("click", () => {
            if (currentQuantity > 1) {
                currentQuantity--;
                num.innerText = currentQuantity < 10 ? "0" + currentQuantity : currentQuantity;
                quantity.value = currentQuantity;
            }
        });
    });
</script>
 -->
	<script type="text/javascript">
 document.addEventListener("DOMContentLoaded", function() {
	    const plus = document.querySelector(".plus");
	    const minus = document.querySelector(".minus");
	    const quantity = document.querySelector(".quantityValue");
	    const num = document.querySelector(".num");
	    const stockQuantity = parseInt(document.getElementById("stock").value);
	    const errorMessage = document.querySelector(".error-message");

	    let currentQuantity = 1;
	    num.innerText = currentQuantity < 10 ? "0" + currentQuantity : currentQuantity;
	    quantity.value = currentQuantity;

	    plus.addEventListener("click", () => {
	        if (currentQuantity < stockQuantity) {
	            currentQuantity++;
	            num.innerText = currentQuantity < 10 ? "0" + currentQuantity : currentQuantity;
	            quantity.value = currentQuantity;
	            errorMessage.textContent = ""; // Clear any previous error message
	        } else {
	            alert("Max Quantity reached");
	        }
	    });

	    minus.addEventListener("click", () => {
	        if (currentQuantity > 1) {
	            currentQuantity--;
	            num.innerText = currentQuantity < 10 ? "0" + currentQuantity : currentQuantity;
	            quantity.value = currentQuantity;
	        } else {	            	
	            alert("Quantity less than 1 not allowed"); 
	            
	        }
	    });
	});
</script>
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