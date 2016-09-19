<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Shopping Cart</title>
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
				<h1>Shopping Cart</h1>
			</div>
		</div>
	
		<div class="container">
		
			<div class="row">
				Products
			</div>
		
		
			<hr>
			<footer>
				<p>&copy; JCPuerto.com 2016</p>
			</footer>
		</div>
	
		<spring:url var="bootstrapJs" value="/resources/core/css/bootstrap.min.js" />
		
		<script src="${bootstrapJs}"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	</body>
</html>