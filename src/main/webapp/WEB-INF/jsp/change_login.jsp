<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Change Login</title>
</head>
<body>
<h1>Change Login</h1>
<form:form action="change" method="post" modelAttribute="user">
    <form:hidden path="id" />
    <div>
        <label for="login">Login:</label>
        <form:input path="login" />
    </div>
    <div>
        <input type="submit" value="Change Login" />
    </div>
</form:form>
</body>
</html>
