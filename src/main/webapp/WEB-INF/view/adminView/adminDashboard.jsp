<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
</style>
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
		</div>
	</div>
	
	<div style="margin-left: 400px; margin-top: 100px; font-size: 36px;">
		Navigate the panel to your right to access the detailed view!!
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