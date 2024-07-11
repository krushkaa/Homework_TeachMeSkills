<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>
    <title>User Information</title>
</head>
<body>
<h2>User Information</h2>
<c:if test="${user != null}">
    <p>ID: ${user.id}</p>
    <p>Login: ${user.login}</p>
</c:if>
<c:if test="${user == null}">
    <p>User not found.</p>
</c:if>
<a href="/users">Back to users</a>
</body>
</html>
