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
<jsp:include page="./navigationBar/css_js_holder.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() + ApplicationConstant.HOME_CSS%>">
<script type="text/javascript">
	if (window.history.replaceState) {
		window.history.replaceState(null, null, window.location.href);

	}
</script>

<style type="text/css">
body {
	background: #e4e3e3;
	color: #22100d;
}

section {
	padding: 80px 13%;
}

.home {
	position: relative; /* 
	height: 100vh; */
	width: 100%;
	background: url(../images/bg.png);
	background-size: cover;
	background-position: center;
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	gap: 15rem;
	align-items: center;
}

.home-text h1 {
	font-size: 3.8rem;
	margin: 30px 0 15px;
	color: rgb(56, 53, 53);
	/* margin-top: 20px; */
}

.home-text p {
	font-size: 1.1rem;
	color: #685f78;
	/* color: var(--green); */
	font-weight: 500;
	margin-bottom: 48px;
	font-family: Arial, Helvetica, sans-serif;
}

.home-img img {
	height: 350px;
	width: auto;
}

.btn_course {
	background-color: #5db183;
	color: #efefef;
	font-family: #22100d;
	font-size: 500px;
	display: flex;
	align-items: center;
	gap: 10px;
	max-width: max-content;
	height: 50px;
	padding: 10px 20px;
	border-radius: 5px;
	font-family: "Poppins", sans-serif;
	transition: all .40s ease;
}

.btn_course span:hover {
	/* background-color: black; */
	color: white;
	padding-left: 20px;
	transition: all .40s ease;
}

.ccourse span {
	margin-left: 10px;
	width: 0;
	overflow: hidden;
	transition: 0.5s;
}

.ccourse:hover span {
	width: 30px;
}

.cut {
	width: fit-content;
	/* margin: 150px auto; */
	margin-left: -50px;
	margin-top: -20px;
}

.but {
	width: 200px;
	height: 60px;
	color: #fff;
	background: #ff5001;
	font-size: 18px;
	text-decoration: none;
	margin: 50px;
	display: flex;
	align-items: center;
	justify-content: center;
	border-radius: 10px;
}
</style>
<title>Home | Gadget Galaxy</title>
</head>
<body>

	<div class="navbar">
		<jsp:include page="./navigationBar/mainnavbar.jsp"></jsp:include>
		<jsp:include page="./navigationBar/midnavbar.jsp"></jsp:include>
		<div class="downNavbar" id="mainSticky">
			<nav class="downNavbarCenter">
				<a
					href="<%=request.getContextPath() + ApplicationConstant.HOME_SERVLET%>"
					class="active">Home</a> <a
					href="<%=request.getContextPath() + ApplicationConstant.SHOP_SERVLET%>">Shop</a>
				<a
					href="<%=request.getContextPath() + ApplicationConstant.ABOUT_SERVLET%>">About
					Us</a>
			</nav>
		</div>
	</div>

	<div class="main-product-container">

		<section class="home" id="home">
			<div class="home-text">
				<h1>
					Unleash Your<br> <span class="diff">Game</span>
				</h1>
				<p>This new year with new Phone. | Samsung Galaxy S24 Ultra. A
					super high flagship phone with 200MP Camera. Buy and Start Playing!</p>
				<div class="cut">
					<a href="shop" class="but ccourse"><b>Start Shopping</b><span>&nbsp;&#10230</span></a>
				</div>
			</div>

			<div class="home-img">
				<img src="<%=request.getContextPath()%>/images/phone.png"
					alt="phone hanging" style="margin-top: 20px;">
			</div>
		</section>
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