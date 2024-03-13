<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login here</title>
</head>
<body>
	<form action="/app_store/AdminLogin" method="post">
	<% String error = request.getParameter("error"); %>
	<h1><%= error == null ? "": "Username or Password Wrong" %></h1>
	<input type="text" name="username">
	<input type="password" name="password">
	<input type="submit" value="Login">
	</form>
</body>
</html>