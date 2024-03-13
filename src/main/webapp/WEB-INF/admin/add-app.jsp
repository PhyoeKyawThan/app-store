<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add-New-App</title>
    <style>
        *{
            width: 100%;
            margin: auto;
        }

        input, select{
            display: block;
            width: 70%;
            padding: 10px;
            margin: 10px auto;
        }
    </style>
</head>
<body>

    <form enctype="multipart/form-data">
        <input type="text" name="icon" id="app-name" placeholder="app-nmae">
        <input type="file" name="file" id="icon">
        <input type="text" name="category" id="category" placeholder="Catego">
        <input type="text" name="download-link" id="download-link" placeholder="Download">
        <input type="text" name="description" id="description" placeholder="des">
        <input type="text" name="features" id="features" placeholder="features">
        <label for="system-require">System Requirement</label>
        <select name="system-require" id="system-require">
            <option value="Linux">Linux</option>
            <option value="Windows">Windows</option>
            <option value="Macs">Macs</option>
        </select>
        <input type="date" name="release-date" id="release-date">
        <input type="button" value="Add APP" id="add-app">
    </form>
    <script>
        const app_name = document.getElementById("app-name");
        const icon = document.getElementById("icon");
        const category = document.getElementById("category");
        const download_link = document.getElementById("download-link");
        const description = document.getElementById("description");
        const features = document.getElementById("features");
        const system_require = document.getElementById("system-require");
        const release_date = document.getElementById("release-date");
        
        document.getElementById("add-app").addEventListener("click", ()=>{
            const formData = new FormData();
            formData.append("file", icon.files[0]);
            fetch("/app_store/upload",{
                method: "POST",
                body: formData,
            }).then( response => response.json() )
            .then( data => {
                if( data.status === 200 ){
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
                    	method: "POST"}).then( response => response.json() )
                    	.then( data => {
                    		console.log(data);
                    	}).catch( error => console.error(error));
                }else{
                    alert("Not OK");
                }
            }).catch( error => console.error("Error"))
        })
           
    </script>
</body>
</html>