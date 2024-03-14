<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <script src="https://kit.fontawesome.com/04be286e27.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./static/css/signIn.css" type="text/css">
</head>
<body>
<% String fail = request.getParameter("fail"); %>
<% String success = request.getParameter("success"); %>
 <div style="width: 100%; padding: 20px; position: absolute;">
        <a href="./"><i class="fa-solid fa-circle-arrow-left" style="font-size: 2em"></i></a>
    </div>
    <div class="imgAform">
    <div class="img">
    <img src="./static/images/login_.jpg"height="100%"width="100%">
    </div>
    	
            <form action="./Login" method="post">
                <h1>Sign In</h1>
                <div><h3 style="color: red"><%= fail == null ? "": fail %></h3></div>
                <div><h4 style="color: green"><%= success == null ? "": success %></h4></div>
                <div>
                <input type="email" id="email" name="email" placeholder="Your Email">
                </div>
                <div>
                    <input type="password" id="password" name="password" placeholder="Password">
                </div>
                <div style="
                	font-size: 0.7em;
                	margin: -10px auto;
                ">
                	<a href="./signup.jsp" style="font-size: 0.9em">Create Account?</a>
                </div>
                <div>
                    <input type="submit" value="Log in" class="button">
                </div>
            </form>
    </div>
</body>
</html>