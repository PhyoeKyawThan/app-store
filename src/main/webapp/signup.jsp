<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign up here</title>
        <link rel="stylesheet" href="./static/css/signup.css">
	<%@ page import="javax.servlet.http.HttpSession" %>
	
    </head>

    <body>
    	<% 
        if ( session != null && session.getAttribute("current-user") != null) { 
    %>
        <script>
            window.location.href = "./index.html"; // Redirect if user is already logged in
        </script>
    <% } %>
        <div class="formAimg">
            <form method="post" onsubmit="validateAndSend(event)">
                <h1>Sign Up</h1>
                <div>
                    <input type="text" id="name" name="name" placeholder="Your Name">
                </div>
                <div>

                    <input type="email" id="email" name="email" placeholder="Your Email">
                </div>
                <div>
                    <input type="password" id="password" name="password" placeholder="Password">
                </div>
                <div>
                    <input type="password" id="rpassword" name="rpassword" placeholder="Repeat Your Password">
                </div>
                <div class="agree">
                    <input type="checkbox" id="agree">
                    <label for="agree">I agree all statements in <a href="">Terms and service</a></label>
                </div>
                <div>
                    <input type="submit" value="Register" class="button">
                </div>
            </form>
            <div class="img">
                <img src="./static/images/signup.jpg" width="100%" alt="signup" height="100%">
                <div class="already"><a href="">I am already member</a></div>
            </div>
        </div>
        <script>
            function validateAndSend(){
                event.preventDefault();
                if( document.getElementById("agree").checked === true ){
                    return true;
                }
                return false;
            }
        </script>
    </body>

    </html>