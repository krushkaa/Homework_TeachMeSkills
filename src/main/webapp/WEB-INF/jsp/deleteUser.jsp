<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<h2>Delete User</h2>
<form action="delete" method="post">
    <label for="id">User ID:</label>
    <input type="text" id="id" name="id" required>
    <br><br>
    <input type="submit" value="Delete User">
</form>
</body>
</html>
