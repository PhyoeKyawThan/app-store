<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign up here</title>
    <script src="https://kit.fontawesome.com/04be286e27.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./static/css/signup.css">
    <%@ page import="javax.servlet.http.HttpSession" %>

    <style>
        /* Additional CSS for input validation */
        .error-message {
            color: red;
            font-size: 0.8em;
            font-family: monospace;
        }

        .error {
            border: 1px solid red;
        }
    </style>
</head>

<body>
    <% String fail = request.getParameter("fail"); %>
    <div style="width: 100%; padding: 20px; position: absolute;">
        <a href="./"><i class="fa-solid fa-circle-arrow-left" style="font-size: 2em"></i></a>
    </div>
    <h1> </h1>
    <div class="formAimg">
        <form action="./sign_up" method="post" onsubmit="return validateAndSend(event)">
            <h1>Sign Up</h1>
            <div><h1 style="color:red;"><%= fail == null ? "" : fail %></h1></div>
            <div>
                <input type="text" id="name" name="user_name" placeholder="Your Name" required>
            </div>
            <div>
                <input type="email" id="email" name="email" placeholder="Your Email" required>
            </div>
            <div>
                <input type="password" id="password" name="password" placeholder="Password" required>
            </div>
            <div>
                <input type="password" id="rpassword" name="rpassword" placeholder="Repeat Your Password" required>
            </div>
            <input type="hidden" name="profile" value="default_profile.png">
            <div class="agree">
                <div><small id="message" class="error-message"></small></div>
                <input type="checkbox" id="agree" required>
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
        function validateAndSend(event) {
            const nameInput = document.getElementById("name");
            const emailInput = document.getElementById("email");
            const passwordInput = document.getElementById("password");
            const repeatPasswordInput = document.getElementById("rpassword");
            const agreeCheckbox = document.getElementById("agree");
            const errorMessage = document.getElementById("message");

            // Reset error styles
            nameInput.classList.remove("error");
            emailInput.classList.remove("error");
            passwordInput.classList.remove("error");
            repeatPasswordInput.classList.remove("error");
            errorMessage.innerText = "";

            // Check if agree checkbox is checked
            if (!agreeCheckbox.checked) {
                errorMessage.innerText = "You must agree to our terms and conditions";
                return false;
            }

            // Validate passwords match
            if (passwordInput.value !== repeatPasswordInput.value) {
                errorMessage.innerText = "Passwords do not match";
                passwordInput.classList.add("error");
                repeatPasswordInput.classList.add("error");
                return false;
            }

            // You can add additional validation rules here

            // If all validations pass, allow form submission
            return true;
        }
    </script>
</body>

</html>
