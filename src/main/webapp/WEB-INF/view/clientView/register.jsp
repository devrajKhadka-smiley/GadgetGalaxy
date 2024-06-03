<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="appConstant.ApplicationConstant"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="../navigationBar/css_js_holder.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() + ApplicationConstant.REGISTER_CSS%>">

<link rel="stylesheet"
	href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />


<title>Register</title>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		let eyeicon = document.getElementById("eyeicon");
		let password = document.getElementById("password");

		let retype = document.getElementById("retypePassword");
		let reeyeicon = document.getElementById("eyeicon1");

		eyeicon.onclick = function() {
			if (password.type == "password") {
				password.type = "text";
				eyeicon.src = "./images/eye-open.png"
			} else {
				password.type = "password";
				eyeicon.src = "./images/eye-close.png"
			}
		}

		reeyeicon.onclick = function() {
			if (retype.type === "password") {
				retype.type = "text";
				reeyeicon.src = "./images/eye-open.png";
			} else {
				retype.type = "password";
				reeyeicon.src = "./images/eye-close.png";
			}
		}

	});
</script>
</head>
<body>

	<div class="navbar">
		<jsp:include page="../navigationBar/mainnavbar.jsp"></jsp:include>
	</div>

	<div class="signupbody p-0-80">
		<div class="video-wrapper">
			<video autoplay muted loop>
				<source src="./images/ip15.mp4" type="video/mp4">
			</video>
		</div>
		<jsp:include page="../navigationBar/downnavbar.jsp"></jsp:include>

		<div class="signup-container-wrapper">

			<div class="loginContainer">
				<div class="signup">

					<%
					if (request.getAttribute("alreadyUserError") != null) {
					%>
					<p style="color: red;"><%=request.getAttribute("alreadyUserError")%></p>
					<%
					}
					%>
					<form action="<%=request.getContextPath()%>/register" method="post"
						class="signupForm" enctype="multipart/form-data">
						<h1 class="welcome">Connect with GG</h1>

						<div class="formField">
							<input type="text" placeholder="User Name" name="userName"
								id="userName" autocomplete="off" value="${userName}" required>
						</div>
						<div class="formField">
							<input type="text" placeholder="Full Name" name="fullName"
								id="fullName" autocomplete="off" value="${fullName}" required>
						</div>
						<div class="formField">
							<input type="text" placeholder="Phone Number" name="phoneNumber"
								id="phoneNumber" autocomplete="off" value="${phoneNumber}"
								required>
						</div>
						<div class="formField">
							<input type="text" placeholder="Email" name="email" id="email"
								autocomplete="off" value="${email}" required>
						</div>
						<div class="formField">
							<input type="text" placeholder="Address" name="address"
								id="address" autocomplete="off" value="${address}" required>
						</div>
						<div class="formField">
							<input type="password" placeholder="Password" name="password"
								id="password" required> <img
								src="./images/eye-close.png" id="eyeicon"
								style="height: 12px; cursor: pointer; color: white;">
						</div>
						<div class="formField">
							<input type="file" name="image" id="image" accept="image/*"
								style="display: none;">
						</div>
						<div class="formField">
							<input type="password" placeholder="Re-Type Password"
								name="retypePassword" id="retypePassword" required> <img
								src="./images/eye-close.png" id="eyeicon1"
								style="height: 12px; cursor: pointer; color: white;">



						</div>

						<button type="submit">
							<b>Register</b>
						</button>
					</form>
				</div>
				<div class="loginImage">
					<%
					if (request.getAttribute("validationError") != null) {
					%>
					<p style="color: red;"><%=request.getAttribute("validationError")%></p>
					<%
					}
					%>
					<%
					if (request.getAttribute("error") != null) {
					%>
					<p style="color: red;"><%=request.getAttribute("error")%></p>
					<%
					}
					%>
					<img src="images/signup.png" width="500px" height="660px"
						alt="LoginScreen">
					<div class="already-user">
						<p class="old">Already a User?</p>
						<p class="separator">|</p>
						<a href="<%=request.getContextPath()%>/login" class="login">Login</a>
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