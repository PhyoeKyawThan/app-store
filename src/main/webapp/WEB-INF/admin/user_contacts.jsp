<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="admin.Model.Model" %>
<%@ page import="java.util.ArrayList"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Information - Admin Dashboard</title>
    <script src="https://kit.fontawesome.com/04be286e27.js" crossorigin="anonymous"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .delete{
        	background-color: red;
        	color: white;
        	padding: 10px;
        	border-radius: 5px;
        	text-decoration: none;
        	cursor: pointer;
        }
    </style>
</head>
<body>
<% Model model = new Model();
   ArrayList<JSONObject> contects = model.GetContects();
%>
<div class="back">
    <a href="/app_store/admin"><button><i class="fa-solid fa-house"></i></button></a>
     </div>
    <h1>Contact Information</h1>
    <h3 style="color: green;"><%= request.getParameter("message") == null ? "" : request.getParameter("message")  %></h3>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Message</th>
                <th>IsUser</th>
            </tr>
        </thead>
        <tbody>
        <% for(int index = 0; index < contects.size(); index++) {%>
            <tr>
                <td><%= contects.get(index).getString("user_name") %></td>
                <td><%= contects.get(index).getString("email") %></td>
                <td><%= contects.get(index).getString("message") %></td>
                <td><%= contects.get(index).getInt("user_id") > 0 ? true: false %></td>
                <td><a href="../DeleteContect?contect_id=<%= contects.get(index).getInt("contect_id") %>" class="delete">Delete</a></td>
            </tr>
           <% } %>
            <!-- Additional rows for more contacts -->
        </tbody>
    </table>
</body>
</html>
