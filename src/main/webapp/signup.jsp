<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign up here</title>
        <script src="https://kit.fontawesome.com/04be286e27.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="./static/css/signup.css">
	<%@ page import="javax.servlet.http.HttpSession" %>
	
    </head>

    <body>
    <% String fail = request.getParameter("fail"); %>
     <div style="width: 100%; padding: 20px; position: absolute;">
        <a href="./"><i class="fa-solid fa-circle-arrow-left" style="font-size: 2em"></i></a>
    </div>
    	<h1> </h1>
        <div class="formAimg">
            <form action="./sign_up" method="post" onsubmit="validateAndSend(event)">
                <h1>Sign Up</h1>
                <div><h1 style="color:red;"><%=  fail == null ? "": fail %></h1></div>
                <div>
                    <input type="text" id="name" name="user_name" placeholder="Your Name">
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
                    <input type="hidden" name="profile" value="default_profile.png">
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
                <div class="already"><a href="./login.jsp">I am already member</a></div>
            </div>
        </div>
        <script>
            function validateAndSend(){
                
                if( document.getElementById("agree").checked === true ){
                    return true;
                }
                return false;
            }
        </script>
    </body>

    </html>