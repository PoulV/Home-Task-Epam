<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/memorina_style.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title>Memorina: Registration page</title>
 </head>
<body>
<form:form method="POST" commandName="newcomer" action="registration-user" class="box login">

		<fieldset class="boxBody">
			<form:label path="login">Login:</form:label>
			<form:input path="login" />

			<form:label path="password">Password:</form:label>
			<form:password path="password"/>

			<form:label path="firstName">First Name:</form:label>
			<form:input path="firstName" />

            <form:label path="lastName">Last Name:</form:label>
            <form:password path="lastName"/>

		</fieldset>

		<footer>
		    ${message}<br/>
			<input type="submit" class="btnLogin" value="Register and login" tabindex="4">
		</footer>

	</form:form>

</body>
</html>
