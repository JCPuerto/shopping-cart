<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>My Cart</title>
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
						<spring:url var="productsUrl" value="products">
							<spring:param name="userId" value="${user.id}" />
						</spring:url>
						<li><a href="${productsUrl}">Products</a></li>
						<spring:url var="myCartUrl" value="cart">
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
						
						<li class="active"><a href="${myCartUrl}">My Cart: <kbd><span id="cart">${cartText}</span></kbd></a></li>
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
			<h2>My Cart</h2>
			<div class="row">
				<div class="col-md-12">
					<c:choose>
						<c:when test="${empty order}">
							<spring:url var="productsUrl" value="products">
								<spring:param name="userId" value="${user.id}" />
							</spring:url>
							<p><strong>${user.firstName}</strong>, your cart is empty. <a href="${productsUrl}">Ready to start shopping?</a></p>
						</c:when>
						<c:otherwise>
							<p><strong>${user.firstName}</strong>, review your shopping cart:</p>
							<table id="tblCart" class="table table-striped">
								<thead>
									<tr>
										<th>Product name</th>
										<th>Price</th>
										<th>Quantity</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="orderItem" items="${order.orderItems}">
										<tr id="tr${orderItem.id}" data-sc-delete="true">
											<td>${orderItem.product.name}</td>
											<td><fmt:formatNumber value="${orderItem.product.price}" type="currency"/></td>
											<td id="qty${orderItem.id}">${orderItem.qty}</td>
											<td>
												<spring:url var="deletefromCartUrl" value="/rest/users/${user.id}/cart/${orderItem.id}" />
												<form action="${deletefromCartUrl}" method="delete" data-sc-productId="${orderItem.product.id}">
													<a href="#">delete from cart</a>
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
								  <tr>
								    <td align="right"><h3>Total</h3></td>
								    <td id="totalPrice"><h3><fmt:formatNumber value="${order.total}" type="currency"/></h3></td>
								    <td id="totalQty"><h3>${order.totalQty}</h3></td>
								    <td></td>
								  </tr>
								</tfoot>
							</table>
						</c:otherwise>
					</c:choose>
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