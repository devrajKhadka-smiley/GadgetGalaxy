<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<title>About Page | Gadget Galaxy</title>

<style type="text/css">
.GG {
	width: 100vw;
	height: 80vh;
	background-color: #f2ece5;
	display: flex;
	justify-content: space-evenly;
	align-items: center;
}

.GG .photo {
	width: 35vw;
	height: 30vw;
	background-color: #d9d9d9;
	align-items: center;
	justify-content: center;
	display: flex;
}

.GG .photo img {
	width: 80%;
	height: 80%;
}

.GG .aboutUs {
	width: 35vw;
	height: 30vw;
	background-color: #e3e1de;
	display: flex;
	justify-content: space-evenly;
	flex-direction: column;
	align-items: center;
}

.GG .aboutUs .aboutUsText {
	width: 80%;
	height: 10%;
	display: flex;
}

.GG .aboutUs .aboutUsText span {
	font-size: 30px;
}

.GG .aboutUs .aboutUsParagraph {
	width: 80%;
	height: 75%;
	display: flex;
	background-color: #f2ece5;
}

.GG .aboutUs .aboutUsParagraph span {
	font-size: 15px;
	margin: 15px 15px;
}

.contactUsContainer {
	height: 100vh;
	width: 80vw;
	margin: 0 auto;
}

.contactUsContainer .contactUsText {
	text-decoration: underline;
	height: 80px;
	background-color: white;
	font-size: 40px;
	padding: 20px 20px;
}

.contactUsContainer .contactUs {
	width: 100%;
	height: 80vh;
	background-color: #d9d9d9;
	display: flex;
	justify-content: space-evenly;
	align-items: center;
}

.contactUsContainer .contactUs .adminDetails {
	width: 40%;
	height: 60%;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: center;
}

.contactUsContainer .contactUs .verticalLine {
	height: 60%;
	border: 3px solid black;
}

.contactUsContainer .contactUs .form {
	width: 40%;
}

.contactUsContainer .contactUs .form .leaveUsMessageText {
	font-size: 30px;
	margin-bottom: 10px;
	font-style: bold;
	color: #3712a4;
}

.contactUsContainer .contactUs .form form {
	margin-top: 20px;
}

.contactUsContainer .contactUs .form form .formField {
	margin-bottom: 20px;
}

.contactUsContainer .contactUs .form form .formField input[type=text],
	.contactUsContainer .contactUs .form form .formField input[type=email],
	.contactUsContainer .contactUs .form form .formField textarea {
	width: 100%;
	padding: 10px;
	font-size: 18px;
	border: 1px solid #ccc;
	border-radius: 5px;
	transition: border-color 0.3s ease;
}

.contactUsContainer .contactUs .form form .formField input[type=text]:focus,
	.contactUsContainer .contactUs .form form .formField input[type=email]:focus,
	.contactUsContainer .contactUs .form form .formField textarea:focus {
	border-color: #3712a4;
	outline: none;
}

.contactUsContainer .contactUs .form form .formField textarea {
	height: 100px;
	resize: vertical;
}

.contactUsContainer .contactUs .form form button[type=submit] {
	padding: 10px 20px;
	font-size: 12px;
	background-color: #3712a4;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.contactUsContainer .contactUs .form form button[type=submit]:hover {
	background-color: #2d0b7d;
} /*# sourceMappingURL=aboutUs.css.map */
</style>
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
					href="<%=request.getContextPath() + ApplicationConstant.SHOP_SERVLET%>">Shop</a>
				<a
					href="<%=request.getContextPath() + ApplicationConstant.ABOUT_SERVLET%>"
					class="active">About Us</a>
			</nav>
		</div>
	</div>

	<div class="GG">
		<div class="photo">
			<img src="<%=request.getContextPath() %>/images/carpet.png" alt="Write with Us">
		</div>
		<div class="aboutUs">
			<div class="aboutUsText">
				<span>About Us</span>
			</div>
			<div class="aboutUsParagraph">
				<span> Welcome to our website! We are GadgetGalaxy, a team
					passionate about innovation and technology. Our journey began with
					a simple idea: a passionate project. Driven by our dedication to
					innovation, we strive for market leadership. With a focus on
					digital marketing solutions, our aim is to achieve global
					recognition and client satisfaction. At GadgetGalaxy, we believe in
					providing access to cutting-edge technology for all. Our commitment
					to customer satisfaction guides everything we do, ensuring that
					each interaction with us is memorable and valuable. Thank you for
					visiting us! We look forward to assisting you in finding the
					perfect gadgets, and we invite you to join us on this exciting
					journey. </span>
			</div>
		</div>
	</div>
	<div class="contactUsContainer">
		<div class="contactUsText">Contact Us!</div>
		<div class="contactUs">

			<div class="adminDetails">
				<div class="address">
					<h2 style="text-align: center;">
						<b>Address</b>
					</h2>
					<h4 style="color: #747272; text-align: center;">
						Kamalpokhari, Kathmandu <br>Nepal
					</h4>
				</div>
				<div class="phone">
					<h2 style="text-align: center;">
						<b>Phone</b>
					</h2>
					<h4 style="color: #747272; text-align: center;">
						+01234567890 <br>9803450145
					</h4>
				</div>
				<div class="email">
					<h2 style="text-align: center;">
						<b>Email</b>
					</h2>
					<h4 style="color: #747272; text-align: center;">
						gadget@galaxy.com <br>
					</h4>
				</div>
			</div>

			<div class="verticalLine"></div>

			<div class="form">
				<div class="leaveUsMessageText">Leave Us Message!</div>
				<span>Within 30 min of interval you will response to your
					query or for any kind of emergency, contact us at provided Phone
					Number</span>
				<form>
					<div class="formField">
						<input type="text" placeholder="Your Name" name="name" required>
					</div>
					<div class="formField">
						<input type="email" placeholder="Your Email" name="email" required>
					</div>
					<div class="formField">
						<textarea placeholder="Your Message" name="message" rows="4"
							required></textarea>
					</div>
					<button type="submit">Send Message</button>
				</form>
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