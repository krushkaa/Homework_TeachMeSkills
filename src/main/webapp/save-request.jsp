<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>User Info</title>
</head>
<body>
    <h4> Application successfully saved! </h4>
    <p>Name: <%=request.getParameter("name") %></p>
    <p>Surname: <%=request.getParameter("surname") %></p>
    <h4> Selected benefits:</h4>
    <ul>
        <%
            String[] courses = request.getParameterValues("courses");
            for(String course: courses){
                out.println("<li>" + course + "</li>");
            }
        %>
    </ul>
</body>
</html>