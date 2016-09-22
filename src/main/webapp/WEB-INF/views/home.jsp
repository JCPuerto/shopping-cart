<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Shopping Cart</title>
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
	                <ul class="nav navbar-nav navbar-right">
	        			<spring:url var="dbUrl" value="h2-console" />
	        			<li><a href="${dbUrl}" target="_blank">DB Console</a></li>
	        			<li><a href="/">Log in</a></li>
	    			</ul>
	            </div>
	        </div>
	    </nav>

		<div class="container body-content">
			<h2>Log in</h2>
			<div class="row">
				<div class="col-md-6">
					<form:form method="post" modelAttribute="user" role="form" cssClass="form-horizontal">
						<h4>Use a local account to log in.</h4>
						<spring:url var="restApiUrl" value="/rest" />
						<mark>Use the <a href="${restApiUrl}" target="_blank">REST api</a> to create new users!</mark>
						<hr />
		                <div class="form-group">
							<label class="col-md-2 control-label" for="firstName">Name</label>
		                    <div class="col-md-10">
		                		<form:input path="firstName" type="text" class="form-control" placeHolder="'Juan', 'Francis' or 'Susana'" />
		                		<p>"Susana" has items in the cart already</p>
		                    </div>
		                </div>
		                
		                <div class="form-group">
		                    <div class="col-md-offset-2 col-md-10">
		                        <input type="submit" value="Log in" class="btn btn-default" />
		                    </div>
		                </div>
					</form:form>
				</div>
				<div class="col-md-offset-2 col-md-4">
					<p><strong><a href="${dbUrl}" target="_blank">Accessing the database</a>:</strong></p>
					<p><strong>JDBC URL:</strong> jdbc:h2:mem:testdb</p>
					<p><strong>User Name:</strong> sa</p>
					<p><strong>Password:</strong> (no password needed)</p>
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