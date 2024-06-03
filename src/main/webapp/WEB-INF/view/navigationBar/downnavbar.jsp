<%@page import="appConstant.ApplicationConstant"%>
<div class="downNavbar" id="mainSticky">
	<nav class="downNavbarCenter">
		<a
			href="<%=request.getContextPath() + ApplicationConstant.HOME_SERVLET%>"
			class="active">Home</a> <a
			href="<%=request.getContextPath() + ApplicationConstant.SHOP_SERVLET%>">Shop</a>
		<a href="<%=request.getContextPath() + ApplicationConstant.ABOUT_SERVLET%>">About Us</a>
	</nav>
</div>