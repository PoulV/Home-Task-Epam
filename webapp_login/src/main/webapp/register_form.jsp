<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Form</title>
    </head>
    <body>
        
        <jsp:useBean id="user" scope="request" class="beans.User"></jsp:useBean>
 
        <form action="RegisterController" method="post">
            <center>
                <table border="1" width="15%" cellpadding="5">
                    <thead>
                        <tr>
                            <th colspan="2">Register Form</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>    
                            <td>First Name : </td>
                            <td><input type="text" name="first_name" value="<jsp:getProperty name="user" property="first_name"/>"></td>
                        </tr>
                        <tr>    
                            <td>Last Name : </td>
                            <td><input type="text" name="last_name" value="<jsp:getProperty name="user" property="last_name"/>"></td>
                        </tr>
                        <tr>    
                            <td>Username : </td>
                            <td><input type="text" name="user" value="<jsp:getProperty name="user" property="user"/>"></td>
                        </tr>
                        <tr>
                            <td>Password : </td>
                            <td><input type="password" name="pwd" value="<jsp:getProperty name="user" property="pwd"/>"></td>
                        </tr>
                        <tr>
                            <td><center><input type="reset" value="Reset" /></center></td>
                            <td><center><input type="submit" value="Register"></center></td>
                        </tr>
                    </tbody>             
                </table>
            </center>
        </form>
        
        <div style="position: relative">
            <div style="position: fixed ; bottom: 0 ; width:100% ; text-align:center ">
                <p><a href="https://www.youtube.com/user/TechWorld3g?sub_confirmation=1">CLICK HERE TO SUBSCRIBE</a></p>
            </div>
        </div>                  
                        
    </body>
</html>