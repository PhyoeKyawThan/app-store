<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login here</title>
<style>
*{
  width: 100%;
  margin: auto;
}

html, body{
  height: 100%;
  display: flex;
}

form{
  width: 60%;
}

form input{
  display: block;
  padding: 10px;
  border: none;
  margin: 10px auto;
  background-color: #a3b4a471;
  color: black;
  font-weight: bold;
}

form input[type=submit]{
  width: fit-content;
  padding: 10px 20px 10px 20px;
  font-weight: bold;
  border-radius: 10px;
}

</style>
</head>
<body>
	<form action="/app_store/AdminLogin" method="post">
	<h1>Admin Auth</h1>
	<% String error = request.getParameter("error");
	%>
	<h2 style="color: red"><%= request.getParameter("error") == null ? "": "Wrong auth info to Login! Try again!" %></h2>
	<input type="text" name="username" placeholder="Username*">
	<input type="password" name="password" placeholder="Password*">
	<input type="submit" value="Login">
	</form>
</body>
</html>