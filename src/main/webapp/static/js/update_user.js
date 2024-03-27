/**
 * 
 */
document.getElementById("update-user-info").addEventListener("click", ()=>{
	const profile = document.getElementById("photo-input");
	 const formData = new FormData();
                formData.append("file", profile.files[0]);
                fetch("/app_store/upload", {
                    method: "POST",
                    body: formData,
                }).then(response => response.json())
                    .then(data => {
						if( data.status === 200 ){
							const username = document.getElementById("username");
							const user_id = document.getElementById("user-id");
							const profile_img = profile.files[0].name;
							const update_user_data = JSON.stringify({
								user_name: username.value,
								user_id: Number(user_id.value),
								profile: profile_img
							})
							fetch("/app_store/update_user_detail?update-user-data=" + encodeURIComponent(update_user_data), {
                                method: "POST"
                            }).then(response => response.json())
                                .then(data => {
                                    if (data.status === 200) {
										window.location.href = "./profile";
                                    } else {
										alert("Fail");
                                    }
                                }).catch( error => console.error(error));
						}
					} )
                   .catch( error => console.error("Error: " + error));
})