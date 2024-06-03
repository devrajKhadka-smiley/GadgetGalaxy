<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
	box-sizing: border-box;
	font-family: "Montserrat", sans-serif;
}

body {
	background: #e4e3e3;
	color: #22100d;
	height: 100vh;
}

.container {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	padding: 80px;
}

.container img {
	width: 400px;
	height: auto;
}

.container h1 {
	font-weight: bold;
	padding: 15px;
}

.container p {
	font-size: 20px;
}

.container input {
	padding: 10px 20px 10px 20px;
	margin-top: 20px;
	background-color: lightcyan;
	font-size: 15px;
	border-radius: 5px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container">
		<div class="img">
			<img src="<%=request.getContextPath()%>/images/penguinError.png"
				alt="">
		</div>
		<div class="line">
			<p>---------------------------------------------------</p>
		</div>
		<h1>Oops!, Page Not Found</h1>
		<p>The page you are trying to redirect is not available</p>
		<form>
			<input type="button" value="Back" onclick="history.go(-1)"
				style="padding: 20px; margin-left: 110px; border-radius: 20px; margin-bottom: -50px; cursor: pointer; font-size: 20px;">
		</form>
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