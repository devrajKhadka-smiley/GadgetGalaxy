<%@page import="appConstant.ApplicationConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="../navigationBar/css_js_holder.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() + ApplicationConstant.LOGIN_CSS%>">
<title>Login</title>

<script>
	document.addEventListener('DOMContentLoaded', function() {
		let eyeicon = document.getElementById("eyeicon");
		let password = document.getElementById("password");

		let retype = document.getElementById("reTypePassword");
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

	});
	


</script>
</head>
<body>
	<div class="navbar">

		<jsp:include page="../navigationBar/mainnavbar.jsp"></jsp:include>
	</div>


	<div class="loginbody p-0-80">
		<div class="video-wrapper">
			<video autoplay muted loop>
				<source src="./images/gS24 vid.mp4" type="video/mp4">
			</video>

		</div>

		<!-- <div class="downNavbar">
			<nav class="downNavbarCenter">
				<a href="#" class="active">Home</a> <a href="#">Shop</a> <a href="#">Category</a>
				<a href="#">About Us</a>
			</nav>
		</div> -->

		<jsp:include page="../navigationBar/downnavbar.jsp"></jsp:include>


		<div class="login-container-wrapper" style="margin-top: 30px">
			<div class="loginContainer">
				<div class="loginImage">
					<img src="./images/Mobile login.png" width="472px" height="602px"
						alt="LoginScreen" srcset="">
				</div>
				<form action="<%=request.getContextPath()%>/login" class="loginForm"
					method="post">
					<h1 class="welcome">Welcome Back</h1>
					<span class="signUp"> Not registered yet? <a
						href="<%=request.getContextPath()%>/register"
						style="text-decoration: none;">&nbsp;&nbsp;Sign Up</a>
					</span>

					<p style="color: red">${loginError}</p>

					<div class="formField">
						<input type="text" placeholder="Username" name="username" autocomplete="off" style="color: white;" required>
					</div>
					<div class="formField" style="color: white;">
						<input type="password" placeholder="Password" name="password"
							id="password" style="color: white;" required> <img src="./images/eye-close.png"
							id="eyeicon" style="height: 12px; cursor: pointer; color: white;">
					</div>
					<!-- <span class="forgot"> <a href="#">Forgotten your login
							Details?</a>
					</span> -->
					<div class="formField">
						<select id="userProfile" name="userProfile" required
							style="background-color: transparent; color: white; border: none; margin-left: 50px; padding: 10px 0 10px 0; font-weight: bold;">
							<option value="client" style="color: black; border: none;"
								selected>Client</option>
							<option value="admin" style="color: black; border: none;">Admin</option>
						</select>
					</div>
					<button type="submit">
						<b>Log In</b>
					</button>


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