<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h2>Users List</h2>
<form action="/create" method="post">
    <input type="text" name="login" placeholder="New User Login" required />
    <button type="submit">Create User</button>
</form>
<ul>
    <c:forEach var="user" items="${users}">
        <li>${user.id}: ${user.login}
            <a href="/get?id=${user.id}">View</a>
            <a href="/delete?id=${user.id}">Delete</a>
            <form action="/change-login" method="get" style="display:inline;">
                <input type="hidden" name="id" value="${user.id}" />
                <input type="text" name="login" placeholder="New Login" required />
                <button type="submit">Change Login</button>
            </form>
        </li>
    </c:forEach>
</ul>
</body>
</html>
