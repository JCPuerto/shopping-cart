<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Products</title>
		<spring:url var="bootstrapCss" value="/resources/core/css/bootstrap.min.css" />
		<link href="${bootstrapCss}" rel="stylesheet" />
	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Products</a>
					<a class="navbar-brand" href="#">My Cart</a>
				</div>
			</div>
		</nav>
	
		<div class="jumbotron">
			<div class="container">
				<h1>Products</h1>
			</div>
		</div>
	
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					Hey ${person.firstName} !
					<br />
					<table class="table table-striped">
						<tr>
							<th>Header</th>
							<th>Price</th>
							<th></th>
						</tr>
						<c:forEach var="product" items="${products}">
							<tr>
								<td>${product.name}</td>
								<td><fmt:formatNumber value="${product.price}" type="currency"/></td>
								<td>
									<a href="#" data-pf-url="Marce">
										Add
									</a>
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