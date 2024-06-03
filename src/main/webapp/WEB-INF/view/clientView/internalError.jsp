<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Internal Error</title>
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

.container a {
	padding: 10px 20px 10px 20px;
	margin-top: 20px;
	background-color: lightcyan;
	font-size: 15px;
	border-radius: 5px;
	cursor: pointer;
} /*# sourceMappingURL=main.css.map */
</style>
<!--     <link rel="stylesheet" href="/scss/main.css"> -->
</head>

<body>
	<div class="container">
		<div class="img">
			<img src="<%=request.getContextPath()%>/images/internalpenguin.png"
				alt="Error500">
		</div>
		<div class="line">
			<p>------------------------------------------------------</p>
		</div>
		<h1>Oops!, It's Not You It's Us</h1>
		<p>Internal Server Error! Something went Wrong</p>
		<!-- <a button href="#">Home Page</a> -->
	</div>
</body>

</html>