<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="Model.UserModel" %>
<%@ page import="java.util.ArrayList"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <script src="https://kit.fontawesome.com/04be286e27.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./static/css/userprofile.css">
    <style>
    	  
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
    }

    .edit-container{
      margin: 50px auto;
      padding: 20px;
      height: 300px;
      position: absolute;
      top: 10px;
      background-color: #f4f4f4;
      border-radius: 5px;
      z-index: 1;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .user-info {
      margin-bottom: 20px;
    }

    .user-photo {
      margin-right: 20px;
    }

    .user-photo img {
      display: block;
      width: 100px;
      height: 100px;
      border-radius: 50%;
      object-fit: cover;
      border: 2px solid #ccc;
    }

    .user-details input[type="text"] {
      width: 100%;
      padding: 8px;
      width: 90%;
      display: block;
      font-size: 16px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    button {
      padding: 10px 20px;
      font-size: 16px;
      cursor: pointer;
      background-color: #007bff;
      color: #fff;
      border: none;
      border-radius: 5px;
    }

    button:hover {
      background-color: #0056b3;
    }

    .name-section {
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-bottom: 10px;
    }

    #nameInput {
      flex: 1;
      padding: 8px;
      font-size: 16px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .edit-btn {
      padding: 8px 16px;
      font-size: 16px;
      cursor: pointer;
      background-color: #007bff;
      color: #fff;
      border: none;
      border-radius: 5px;
      margin-left: 10px;
    }

    .edit-btn:hover {
      background-color: #0056b3;
    }

    #displayName {
      font-size: 18px;
      font-weight: bold;
    }
  
  </style>
   
</head>
<body>

<% Object user_data =  request.getSession().getAttribute("current-user");
	JSONObject current_user = (JSONObject) user_data;
	String username = current_user.getString("user_name");
	String profile = current_user.getString("profile");
	String email = current_user.getString("email");
	String protocol = request.getScheme();
	String serverName = request.getServerName();
	int portNumber = request.getServerPort();
	String baseURL = protocol + "://" + serverName + ":" + portNumber;
	UserModel model = new UserModel();
	ArrayList<JSONObject> user_uploaded_apps = model.GetUserUploadedApps(current_user.getInt("user_id"));
%>

<div class="edit-container" id="edit-container">
	<div class="back" id="close-edit-form">
        <i class="fa-solid fa-circle-arrow-left"></i>
    </div>
    <h1>User Profile Edit</h1>
    <div class="user-info">
      <div class="user-photo">
        <img id="user-photo" src="/app_store/icons/<%= profile %>" alt="User Photo">
        <input type="file" id="photo-input" accept="image/*" onchange="previewPhoto(event)">
      </div>
      <div class="user-details">
        <div class="name-section">
          <input type="text" id="username" placeholder="Enter your name" value="<%= username %>"/>
          <button class="edit-btn" id="update-user-info">Update</button>
        </div>
      </div>
    </div>
    <p id="displayName"></p>
  </div>

<input type="hidden" value="<%= current_user.getInt("user_id") %>" id="user-id">
    <div class="back">
        <a href="./"><i class="fa-solid fa-circle-arrow-left"></i></a>
    </div>
    <div class="container upload-container"> <!-- Add class "upload-container" -->
        <div class="profilebody">
            <div class="profile">
                <img src="<%= baseURL %>/app_store/icons/<%= profile %>" alt="">
            </div>
            <div class="name">
                <h2><%= username %></h2>
                <h4><%= email %></h4>
            </div>
            <div class="btn">
                <input type="button" id="edit" value="Edit">
            </div>
        </div>
        
        <div class="app">
        <div class="title">
                <h1>Uploaded App</h1>
            </div>
        	<div class="btn">
                <input type="button" id="uploadLink" value="Upload">
            </div>
            
            <div class="appitem">
                <h2>Icon</h2>
                <h2>Name</h2>
                <h2>Date</h2>
            </div>
            <% for(int index = 0; index < user_uploaded_apps.size(); index++) { %>
                <div class="appitem">
                    <div class="img"><img src="<%= baseURL + "/app_store/icons/" + user_uploaded_apps.get(index).getString("icon") %>" alt=""></div>
                    <h3><%= user_uploaded_apps.get(index).getString("app_name") %></h3>
                    <h3><%= user_uploaded_apps.get(index).getString("release_date") %></h3>
                </div>
            <% } %>
        </div>
    </div>
    
    <!-- Upload form as a dropdown -->
    <div class="dropdown" id="uploadForm">
        <div class="back">
            <a href="./profile"><i class="fa-solid fa-circle-arrow-left"></i></a>
        </div>
        <div class="containerr">
            <form method="post" id="upload-form">
            <div class="form-group">
                    <h3 id="message" style="margin: 10px auto; width: fit-content"></h3>
                  </div>
                <div class="form-group">
                    <label for="name">App Name</label>
                    <input type="text" name="name" id="app-name">
                  </div>
                  <div class="form-group">
                    <label for="icon">Icon</label>
                    <input type="file" name="icon" id="icon">
                  </div>
                  <div class="form-group">
                    <label for="category">Category</label>
                    <input type="text" name="category" id="category">
                  </div>
                  <div class="form-group">
                    <label for="link">Download Link</label>
                    <input type="text" name="link" id="download-link">
                  </div>
                  <div class="form-group">
                    <label for="description">Description</label>
                    <textarea name="description" id="description" cols="20" rows="5"></textarea>
                  </div>
                  <div class="form-group">
                    <label for="feature">Feature</label>
                    <textarea name="feature" id="features" cols="20" rows="5"></textarea>
                  </div>
                  <div class="form-group">
                    <label for="require">System Requirement</label>
                    <input type="text" name="require" id="system-require">
                  </div>
                  <div class="form-group">
                    <label for="date">Release Date</label>
                    <input type="date" name="date" id="release-date">
                  </div>
                  <div class="form-group">
                    <label for="repo">Project Repo </label>
                    <input type="text" name="repo" id="repo">
                  </div>
                  <div class="form-group">
                    <label for="submit">Upload</label>
                    <input type="button" value="Submit" id="upload-app">
                  </div>
            </form>
        </div>
    </div>
    <script>
    function previewPhoto(event) {
      const reader = new FileReader();
      reader.onload = function() {
        const preview = document.getElementById('user-photo');
        preview.src = reader.result;
      }
      reader.readAsDataURL(event.target.files[0]);
    }

    function updateName() {
      var name = document.getElementById('nameInput').value;
      document.getElementById('displayName').textContent = name;
    }
    
    document.getElementById("edit").addEventListener("click", ()=>{
    	document.getElementById("edit-container").style.display = "block";
    })
    
    document.getElementById("close-edit-form").addEventListener("click", ()=>{
    	document.getElementById("edit-container").style.display = "none";
    })
 </script>
    <script src="./static/js/uploadform.js"></script>
    <script src="./static/js/update_user.js"></script>
</body>
</html>
