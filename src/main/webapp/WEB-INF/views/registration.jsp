<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Create an account</title>
<script src="<c:url value="/static/js/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
<link rel="stylesheet"
	href="<c:url value="/static/css/bootstrap.min.css"  />" />
<link rel="stylesheet" href="<c:url value="/static/css/common.css" />" />

</head>

<body>
	<h2 class="header" align="center">Welcome to Article Management
		System.</h2>
	<table>
		<tr>
			<td><input type="button" onclick="history.back();"
				class="button" value="Back" style="margin-left: 30px;"></td>
		</tr>
	</table>
	<div class="container">

		<form:form class="form-signin" method="POST" modelAttribute="userForm">
			<h2 class="form-signin-heading">Create your account</h2>
			<c:if test="${message != null}">
				<div class="alert alert-success" align="center">
					<p>${message}
						Click <a href="<c:url value="/login" />"><font size="3"
							style="font: bold;">here</font></a> to log in.
					</p>
				</div>
			</c:if>
			<c:if test="${errorMsg != null}">
				<div class="alert alert-success">
					<p>${errorMsg}</p>
				</div>
			</c:if>
			<div>
				<form:input type="text" path="username" class="form-control"
					placeholder="Username" required="true" />
				<form:errors path="username"
					class="form-group has-error has-feedback" />
			</div>
			<div>
				<form:input type="password" path="password" class="form-control"
					placeholder="Password" required="true" />
				<form:errors path="password"
					class="form-group has-error has-feedback" />
			</div>


			<div>
				<form:input type="password" path="passwordConfirm"
					class="form-control" placeholder="Confirm your password"
					required="true" />
				<form:errors path="passwordConfirm"
					class="form-group has-error has-feedback" />
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>

	</div>
	<h2 class="footer" align="center"></h2>
</body>
</html>
