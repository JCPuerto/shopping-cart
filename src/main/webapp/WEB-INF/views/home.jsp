<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
				<div class="col-md-12">
					<form:form method="post" modelAttribute="person" role="form" cssClass="form-horizontal">
						<h4>Login please</h4>
						<p>(Use "Juan", "Francis", "Susana" or create one using the REST api)</p>
						<hr />
		                <div class="form-group">
							<label class="col-md-2 control-label" for="firstName">Name</label>
		                    <div class="col-md-10">
		                		<form:input path="firstName" type="text" class="form-control" />        
		                    </div>
		                </div>
		                
		                <div class="form-group">
		                    <div class="col-md-offset-2 col-md-10">
		                        <input type="submit" value="Log in" class="btn btn-default" />
		                    </div>
		                </div>
					</form:form>
				</div>
			</div>
		
			<hr>
			<footer>
				<p>&copy; JCPuerto.com 2016</p>
			</footer>
		</div>
	
		<spring:url var="bootstrapJs" value="/resources/core/js/bootstrap.min.js" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="${bootstrapJs}"></script>
	</body>
</html>