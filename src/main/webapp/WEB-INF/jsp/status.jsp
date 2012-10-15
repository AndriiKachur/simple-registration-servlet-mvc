<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="util.NM" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Authentication page</title>
</head>
<body>
<c:choose>
	<c:when test="${sessionScope['authenticated']}">
		Authenticated as <c:out value="${sessionScope['username']}"/>.
		<c:url value="/customerinfo" var="url"/>
		<a href="${url}">Next</a>
	</c:when>
	<c:otherwise>
		Not authenticated.
	</c:otherwise>
</c:choose>
</body>
</html>