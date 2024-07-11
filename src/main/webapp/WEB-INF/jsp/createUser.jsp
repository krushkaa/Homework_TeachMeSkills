<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h2>Create User</h2>
<form action="create" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br><br>
    <label for="login">Login:</label>
    <input type="text" id="login" name="login" required>
    <br><br>
    <input type="submit" value="Create User">
</form>
</body>
</html>

