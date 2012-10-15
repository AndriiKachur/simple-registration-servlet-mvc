<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="util.NM"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer info page</title>
</head>
<body>
	<form method="post" action="<c:url value="/customerinfo"/>">
		<div>
			<c:set value="${requestScope.customer.name}" var="variable" />
			<label for="name">Name:</label> <input type="text" size="30"
				name="name" id="name" value="${variable}">
		</div>
		<div>
			<c:set value="${requestScope.customer.surname}" var="variable" />
			<label for="surname">Surname:</label> <input type="text" size="30"
				name="surname" id="surname" value="${variable}">
		</div>
		<div>
			<c:set value="${requestScope.customer.sex}" var="variable" />
			<label for="sex">Sex:</label> <select name="sex" id="sex">
				<option value="m"
					<c:if test="${'m' eq variable}">
					selected="selected"</c:if>>Male
				</option>
				<option value="f"
					<c:if test="${'f' eq variable}">
					selected="selected"</c:if>>Female
				</option>
			</select>
		</div>
		<div>
			<button type="submit" name="submit">Submit</button>
		</div>
	</form>
	<div>
		<a href="<c:url value="/logout/"/>">Log out</a>
	</div>
</body>
</html>