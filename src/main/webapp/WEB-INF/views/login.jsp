<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<script src="<c:url value="/static/js/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/static/js/user-login.js" />"></script>
<link rel="stylesheet"
	href="<c:url value="/static/css/bootstrap.min.css"  />" />
<link rel="stylesheet" href="<c:url value="/static/css/common.css" />" />
</head>

<c:url value="/login" var="secureUrl" />
<c:url value="/registration" var="registrationUrl" />

<body>

	<h2 class="header" align="center">Welcome to Article Management
		System. Please Login.</h2>
	<div class="container">
		<form class="form-signin" action="${secureUrl}" method="POST">

			<c:if test="${param.error != null}">
				<div class="alert alert-danger" id="message">
					<p>Invalid username and password.</p>
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success" id="message">
					<p>You have been logged out successfully.</p>
				</div>
			</c:if>

			<input class="form-control" name="username" type="text" autofocus
				placeholder="username" required /> <input class="form-control"
				name="password" type="password" placeholder="password" required />

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<button class="btn btn-lg btn-primary btn-block" type="submit">Log
				In</button>
			<h4 class="text-center">
				<a href="${registrationUrl}">Create an account</a>
			</h4>

		</form>
	</div>
	<h2 class="footer" align="center"></h2>
</body>
</html>