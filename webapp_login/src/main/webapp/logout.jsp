<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <title>Logout Page</title>
    </head>
    <body>

        <% session.invalidate(); %>
        <br><br>
        <br><br>
        <br><br>
        <br><br>
        <center><b>You have been successfully logout!</b></center>
        <br>
        <center><a href="login_form.jsp">Log in</a></center>
        
    </body>
</html>