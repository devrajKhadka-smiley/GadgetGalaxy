<div class="admin-nav">
	<div class="logo">
		<img src="<%=request.getContextPath()%>/images/penguin.png" alt="">
	</div>
	<ul>
		<li><a href="admin-product">Products</a></li>
		<li><a href="admin-client">Clients</a></li>
		<li><a href="admin-order">Orders</a></li>
	</ul>
	<form action="user-logout" class="logout-button" method="post">
		<button>Log Out</button>
	</form>
</div>