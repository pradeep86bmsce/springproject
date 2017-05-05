<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}" />
<title>ArticlePage</title>

<link rel="stylesheet"
	href="<c:url value="/static/css/form-style.css" />" />
<script src="<c:url value="/static/js/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/static/js/jquery.validate.js" />"></script>
<script src="<c:url value="/static/js/additional-methods.js" />"></script>
<script src="<c:url value="/static/js/form-validation.js" />"></script>

</head>
<c:url value="/logout" var="logoutUrl" />

<c:url value="/admin/manageArticles/" var="adminUrl" />

<body>

	<c:url value="/upload/" var="articleUrl" />

	<h1 class="header" align="center">
		Welcome <font class="capitalize">${userName}</font>
	</h1>
	<p align="right" style="margin-right: 40px;">
		<a href="${adminUrl}" style="width: 100px" class="btn">Manage
			Articles</a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="${logoutUrl}"
			style="width: 100px" class="btn">logout</a>
	</p>

	<c:if test="${not empty accessDenied}">
		<script>
   	 window.addEventListener("load",function(){
         alert("${accessDenied}");
    });
    </script>
	</c:if>

	<form:form id="myform" autocomplete="off" action="${articleUrl}"
		method="post" commandName="article" modelAttribute="article"
		enctype="multipart/form-data">

		<c:if test="${ mode ne 'manage'}">

			<c:choose>
				<c:when test="${article.articleId != 0}">
					<input type="hidden" name="mode" id="mode" value="edit" />
					<h3 align="left" class="subheader">Edit Article below,</h3>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="mode" id="mode" value="add" />
					<h3 align="left" class="subheader">Add Article below,</h3>
				</c:otherwise>
			</c:choose>

			<table class="selector">
				<tr>
					<form:hidden path="articleId" />
					<td><form:label path="title">
							<spring:message text="Title" />
						</form:label></td>
					<td><form:input path="title"
							readonly="${(not empty article.title) ? 'true' : 'false'}" />
						&nbsp; <form:errors path="title" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="author">
							<spring:message text="Author" />
						</form:label></td>
					<td><form:input path="author" /> &nbsp; <form:errors
							path="author" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="status">
							<spring:message text="Status" />
						</form:label></td>
					<td><form:select path="status">
							<form:options items="${aticleStatusList}" />
						</form:select> &nbsp; <form:errors path="status" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="comment">
							<spring:message text="Comment" />
						</form:label></td>
					<td><form:textarea cols="30" rows="8" path="comment"
							readonly="${(not empty article.comment) ? 'true' : 'false'}"></form:textarea>
						&nbsp; <form:errors path="comment" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Upload File:</td>
					<td><c:choose>
							<c:when test="${ empty article.contentName}">
								<input type="file" name="file" id="file">
							</c:when>
							<c:otherwise>
								<c:set var="fileName"
									value="${fn:substring(article.contentName,0, fn:indexOf(article.contentName,\".\") - 2)}${\".\"}${fn:substringAfter(article.contentName,\".\")}" />
								<input class="upload" name="uploadFile" id="uploadFile"
									value="${fileName}" />
								<input type="file" id="editFile" name="editFile" />
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>Upload Image:</td>
					<td><c:choose>
							<c:when test="${empty article.imageName}">
								<input type="file" name="image" id="image">
							</c:when>
							<c:otherwise>
								<c:set var="imageName"
									value="${fn:substring(article.imageName,0, fn:indexOf(article.imageName,\".\") - 2)}${\".\"}${fn:substringAfter(article.imageName,\".\")}" />
								<input class="upload" name="uploadImage" id="uploadImage"
									value="${imageName}" />
								<input type="file" id="editImage" name="editImage" />
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>

				<tr>
					<td colspan="1"><input type="submit" id="submit"
						value="<spring:message text="Save"/>" class="button" /> <c:choose>
							<c:when test="${article.articleId == 0}">
								<input type="button" value="<spring:message text="Clear" />"
									class="button" onclick="$('#myform')[0].reset();" />
							</c:when>
							<c:otherwise>
								<input type="button" value="<spring:message text="Back" />"
									class="button" onclick="history.back();" />
							</c:otherwise>
						</c:choose> &nbsp;&nbsp;</td>
					<td align="left"><div id="progress">Please wait...</div>
						<div class="success" id="message">${message}</div>
						<div class="error" id="errorMsg">${errorMsg}</div></td>
				</tr>
			</table>

			<form:hidden path="contentName" value="${article.contentName }" />

			<form:hidden path="imageName" value="${article.imageName }" />
		</c:if>
	</form:form>
	<input type="hidden" value="<c:url value="/article/deleteArticle/"/>"
		id="deleteArticleUrl" />
	<input type="hidden" value="<c:url value="/article/articleDisplay/"/>"
		id="getArticleUrl" />
	<input type="hidden"
		value="<c:url value="/article/validateTitleUnique/"/>"
		id="getTitleUrl" />
	<c:if test="${ mode eq 'manage'}">
		<table>
			<tr>
				<td><input type="button" onclick="history.back();"
					class="button" value="Back"></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${not empty articles}">
		<c:choose>
			<c:when test="${mode eq 'manage'}">
				<h3 align="left" class="subheader">Article of all the users,</h3>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="mode" id="mode" value="add" />
				<h3 align="left" class="subheader">Articles added,</h3>
			</c:otherwise>
		</c:choose>
		<table class="display">
			<tr>
				<th>TITLE</th>
				<th>IMAGE</th>
				<th>AUTHOR</th>
				<th>STATUS</th>
				<th>CONTENT</th>
				<th>UPDATED ON</th>
				<th>CONTENT</th>
				<c:if test="${ mode eq 'manage'}">
					<th>USER</th>
				</c:if>
				<th>OPERATIONS</th>
			</tr>
			<c:forEach items="${articles}" var="article">
				<tr>
					<td>${article.title}</td>
					<td><div>
							<img src="<c:url value="/tmpFiles/${article.imageName}"/>"
								width="15%" height="15%" alt="image" />
						</div></td>
					<td>${article.author}</td>
					<td><c:out value="${aticleStatusList[article.status]}" /></td>
					<td style="word-break: break-word">${article.comment}</td>
					<td><fmt:formatDate value="${article.lastUpdatedDate}"
							pattern="dd/MM/yyyy" /></td>
					<td><c:out
							value="${fn:substring(article.contentName,0, fn:indexOf(article.contentName,\".\") - 2)}${\".\"}${fn:substringAfter(article.contentName,\".\")}" />&nbsp;&nbsp;<a
						href="<c:url value="/download/${article.contentName}" />">Download</a></td>
					<c:set var="idNum" value='${article.articleId}' />
					<c:if test="${ mode eq 'manage'}">
						<td class="capitalize">${article.user.username}</td>
					</c:if>
					<td align="center"><c:if test="${ mode ne 'manage'}">
							<a
								href="<c:url value="/article/editArticle/${article.articleId}"/>"
								id="edit">Edit</a>
		  | </c:if><span onclick="deleteArticle(${article.articleId})" id="delete">Delete</span></td>
				</tr>

			</c:forEach>

		</table>

	</c:if>
	<h2 class="footer" align="center"></h2>
</body>
</html>