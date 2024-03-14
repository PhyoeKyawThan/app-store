<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>App Submission Form</title>
        <style>
            * {
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                padding: 20px;
                margin: 0;
            }

            .container {
                max-width: 600px;
                margin: auto;
            }

            form {
                border: 1px solid #ddd;
                padding: 20px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                background-color: #fff;
            }

            .form-group {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 5px;
            }

            label {
                flex: 1;
                margin-right: 10px;
            }

            input[type="text"],
            input[type="file"],
            input[type="date"],
            textarea {
                flex: 2;
                padding: 5px;
                margin-bottom: 5px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            input[type="button"] {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type="button"]:hover {
                background-color: #45a049;
            }
            
#message:empty{
	display: block;
}
#message{
    background-color: red;
    text-align: center;
    font-size: 2.5em;
    color: white;
}

#back a{
	color: black;
	font-weight: bold;
	font-size: 1.5em;
	font-family: monospace;
	text-decoration: underline;

}
        </style>
    </head>

    <body>
    <div id="back"><a href="../admin">HOME</a> | <a href="../admin/manage">MANAGE</a></div>
        <div class="container">
        
            <form>
                <div id="message"></div>
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
                    <textarea name="features" id="features" cols="20" rows="5"></textarea>
                </div>
                <div class="form-group">
                    <label for="require">System Requirement</label>
                    <input type="text" name="system-require" id="system-require">
                </div>
                <div class="form-group">
                    <label for="date">Release Date</label>
                    <input type="date" name="date" id="release-date">
                </div>
                <div class="form-group">
                    <input type="button" value="Add App" id="add-app">
                </div>
            </form>
        </div>
        <script>
        // form validate for lack of info sending
         document.addEventListener("DOMContentLoaded", function() {

    document.querySelector('input[type="button"]').addEventListener('click', function() {
      var valid = true;
      var feedbackMessage = '';

      // Validate App Name
      var appName = document.getElementById('app-name').value;
      if (!appName) {
        feedbackMessage += 'Please enter an app name.\n';
        valid = false;
      }

      // Validate Icon
      var icon = document.getElementById('icon').files;
      if (!icon.length) {
        feedbackMessage += 'Please select an icon file.\n';
        valid = false;
      }

      // Validate Category
      var category = document.getElementById('category').value;
      if (!category) {
        feedbackMessage += 'Please enter a category.\n';
        valid = false;
      }

      // Validate Download Link
      var link = document.getElementById('download-link').value;
      var urlPattern = new RegExp('^(https?|ftp)://' + // protocol
  '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|' + // domain name
  '((\\d{1,3}\\.){3}\\d{1,3}))' + // OR ip (v4) address
  '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*' + // port and path
  '(\\?[;&a-z\\d%_.~+=-]*)?' + // query string
  '(\\#[-a-z\\d_]*)?$', 'i'); // fragment locator
      if (!urlPattern.test(link)) {
        feedbackMessage += 'Please enter a valid download link.\n';
        valid = false;
      }

      // Validate Description
      var description = document.getElementById('description').value;
      if (!description) {
        feedbackMessage += 'Please enter a description.\n';
        valid = false;
      }

      // Validate Feature
      var feature = document.getElementById('features').value;
      if (!feature) {
        feedbackMessage += 'Please enter at least one feature.\n';
        valid = false;
      }

      // Validate System Requirement
      var requirement = document.getElementById('system-require').value;
      if (!requirement) {
        feedbackMessage += 'Please enter system requirements.\n';
        valid = false;
      }

      // Validate Release Date
      var releaseDate = document.getElementById('release-date').value;
      var currentDate = new Date().toISOString().split('T')[0];
      if (!releaseDate || releaseDate > currentDate) {
        feedbackMessage += 'Please enter a valid release date.\n';
        valid = false;
      }
      
      if(!valid){
    	  alert(feedbackMessage);
      }
		//if valid 
		if( valid ){
			 // getting data and send to server 
            const app_name = document.getElementById("app-name");
            const icon = document.getElementById("icon");
            const category = document.getElementById("category");
            const download_link = document.getElementById("download-link");
            const description = document.getElementById("description");
            const features = document.getElementById("features");
            const system_require = document.getElementById("system-require");
            const release_date = document.getElementById("release-date");

                const formData = new FormData();
                formData.append("file", icon.files[0]);
                fetch("/app_store/upload", {
                    method: "POST",
                    body: formData,
                }).then(response => response.json())
                    .then(data => {
                        if (data.status === 200) {
                            const new_app = JSON.stringify({
                                app_name: app_name.value,
                                icon: icon.files[0].name,
                                category: category.value,
                                download_link: download_link.value,
                                description: description.value,
                                features: features.value,
                                system_require: system_require.value,
                                release_date: release_date.value
                            });
                            fetch("/app_store/app_detail_upload?new-app=" + encodeURIComponent(new_app), {
                                method: "POST"
                            }).then(response => response.json())
                                .then(data => {
                                    const message = document.getElementById("message");
                                    if (data.status === 200) {
                                        message.style.color = "green";
                                        message.innerText = "Success";
                                    } else {
                                        message.style.color = "red";
                                        message.innerText = "Fail";
                                    }
                                }).catch( error => console.error(error));
                            }
                        }).catch(error => console.error("Error"))

		}
      return valid;
    });

  });

        
       
        </script>
    </body>

    </html>