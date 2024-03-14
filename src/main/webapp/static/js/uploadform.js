document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('uploadLink').addEventListener('click', function(event) {
        event.preventDefault(); // Prevent default button behavior
        var uploadForm = document.getElementById('uploadForm');
        if (uploadForm.style.display === 'none' || uploadForm.style.display === '') {
            uploadForm.style.display = 'block';
        } else {
            uploadForm.style.display = 'none';
        }
    });            
    document.getElementById('upload-app').addEventListener('click', function() {
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
      // Display feedback message
      if (valid) {
		  	const message = document.getElementById("message");
		  	const developer_id = document.getElementById("user-id");
            const app_name = document.getElementById("app-name");
            const icon = document.getElementById("icon");
            const category = document.getElementById("category");
            const download_link = document.getElementById("download-link");
            const description = document.getElementById("description");
            const features = document.getElementById("features");
            const system_require = document.getElementById("system-require");
            const release_date = document.getElementById("release-date");
            const repo = document.getElementById("repo");
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
                                developer_id: Number(developer_id.value),
                                category: category.value,
                                download_link: download_link.value,
                                description: description.value,
                                features: features.value,
                                system_require: system_require.value,
                                release_date: release_date.value,
                                repo: repo.value
                            });
                            fetch("/app_store/user_app_upload?new-app-by-user=" + encodeURIComponent(new_app), {
                                method: "POST"
                            }).then(response => response.json())
                                .then(data => {
                                    const message = document.getElementById("message");
                                    if (data.status === 200) {
										message.style.color = "green";
                                        message.innerText = "Uploaded";
                                    } else {
										message.style.color = "red";
                                        message.innerText = "Fail";
                                    }
                                }).catch( error => console.error(error));
                            }
                        }).catch(error => console.error("Error: " + erro))

      }
      

      return valid;
    });

  });
