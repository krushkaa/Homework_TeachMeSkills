<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Login</title>
</head>
<body>
<h2>Change Login</h2>
<form action="change-login" method="post">
    <label for="id">User ID:</label>
    <input type="text" id="id" name="id" required>
    <br><br>
    <label for="login">New Login:</label>
    <input type="text" id="login" name="login" required>
    <br><br>
    <input type="submit" value="Change Login">
</form>
</body>
</html>

