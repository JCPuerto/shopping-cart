<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Products</title>
		<spring:url var="bootstrapCss" value="/resources/core/css/bootstrap.min.css" />
		<spring:url var="scCss" value="/resources/core/css/sc.css" />
		<link href="${bootstrapCss}" rel="stylesheet" />
		<link href="${scCss}" rel="stylesheet" />
	</head>

	<body>
	    <nav class="navbar navbar-inverse navbar-fixed-top">
	        <div class="container">
	            <div class="navbar-header">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand" href="/">Shopping Cart</a>
	            </div>
	            <div class="navbar-collapse collapse">
	                <ul class="nav navbar-nav">
						<spring:url var="productsUrl" value="/products">
							<spring:param name="userId" value="${user.id}" />
						</spring:url>
						<li class="active"><a href="${productsUrl}">Products</a></li>
						<spring:url var="myCartUrl" value="/cart">
							<spring:param name="userId" value="${user.id}" />
						</spring:url>
						
						<c:choose>
							<c:when test="${empty order.cartTextDisplay}">
								<c:set var="cartText" value="cart is empty" />
							</c:when>
							<c:otherwise>
								<c:set var="cartText" value="${order.cartTextDisplay}" />
							</c:otherwise>
						</c:choose>
						
						<li><a href="${myCartUrl}">My Cart: <kbd><span id="cart">${cartText}</span></kbd></a></li>
	                </ul>
	                <ul class="nav navbar-nav navbar-right">
	        			<spring:url var="dbUrl" value="h2-console" />
	        			<li><a href="${dbUrl}" target="_blank">DB Console</a></li>
	        			<li><a href="/">Log off</a></li>
	    			</ul>
	            </div>
	        </div>
	    </nav>
	    
		<div class="container body-content">
			<h2>Products</h2>
			<div class="row">
				<div class="col-md-12">
					<p>Welcome <strong>${user.firstName}</strong>, these are some products recommended for you:</p>
					<table id="tblProducts" class="table table-striped">
						<tr>
							<th>Product name</th>
							<th>Price</th>
							<th></th>
						</tr>
						<c:forEach var="p" items="${products}">
							<tr>
								<spring:url var="detailUrl" value="/products/${p.id}">
									<spring:param name="userId" value="${user.id}" />
								</spring:url>
								<td><a href="${detailUrl}">${p.name}</a></td>
								<td><fmt:formatNumber value="${p.price}" type="currency"/></td>
								<td>
									<spring:url var="addToCartUrl" value="/rest/users/${user.id}/cart" />
									<form action="${addToCartUrl}" method="post" data-sc-productId="${p.id}">
										<mark><a href="#" data-sc-addToCart>add to cart</a></mark>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		
			<hr>
			<footer>
				<p>&copy; JCPuerto.com 2016</p>
			</footer>
		</div>
	
		<spring:url var="bootstrapJs" value="/resources/core/js/bootstrap.min.js" />
		<spring:url var="shoppingCartJs" value="/resources/core/js/shopping-cart.js" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="${bootstrapJs}"></script>
		<script src="${shoppingCartJs}"></script>
	</body>
</html>