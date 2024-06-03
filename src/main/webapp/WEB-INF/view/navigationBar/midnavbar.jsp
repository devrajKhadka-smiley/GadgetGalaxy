
<%@page import="model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%
String sessionName = (String) session.getAttribute("sessionUsername");
ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
%>
<nav class="midNavbar p-0-80">
	<div class="midNavbarLeft">
		<img src="./images/penguin.png" alt="Logo" class="navbarLogo" />
	</div>
	<div class="midNavbarCenter">
		<form action="client-search-product" class="product-search-bar" method="post">
			<input type="search" name="search" placeholder="Search product" />
			<button type="submit">Search</button>
		</form>
	</div>
	<div class="midNavbarRight">
		<div class="midNavbarRight-container">
			<div class="system-in">
				<%
				if (sessionName != null) {
				%>
				<a href="profile"><%=sessionName%></a> | <a href="user-logout">Logout</a>
				<%
				} else {
				%>
				<a href="login">Login</a> | <a href="register">Sign up</a>

				<%
				}
				%>



			</div>
			<div class="cart-area">
				<a href="view-cart" style="margin-left: 10px"> <i
					class="fa fa-shopping-cart"></i>
				</a>
				<div class="cart-count" style="">
					<%
					if (cartList != null) {
						out.print(cartList.size());
					} else {
						out.print(0);
					}
					%>
				</div>
			</div>
		</div>
	</div>
</nav>