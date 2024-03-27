<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    <script src="https://kit.fontawesome.com/04be286e27.js" crossorigin="anonymous"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }
        .contact-form {
            width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .contact-form label {
            font-weight: bold;
        }
        .contact-form input[type="text"],
        .contact-form textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
        .contact-form textarea {
            height: 100px;
        }
        .contact-form button {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        .contact-form button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="contact-form">
    <div class="back" id="back">
        <i class="fa-solid fa-circle-arrow-left"></i>
    </div>
        <h2>Contact Us</h2>
        <h3 style="color: green;"><%= request.getParameter("message") == null ? "" : request.getParameter("message")  %></h3>
        <form action="./contect_us" method="post">
            <label for="name">Your Name:</label>
            <input type="text" id="name" name="user_name" required>

            <label for="email">Your Email:</label>
            <input type="text" id="email" name="email" required>

            <label for="message">Message:</label>
            <textarea id="message" name="message" required></textarea>

            <button type="submit">Submit</button>
        </form>
    </div>
    <script>
    document.getElementById("back").addEventListener("click", ()=>{
    	window.location.href = "/app_store";
    })
    </script>
</body>
</html>
