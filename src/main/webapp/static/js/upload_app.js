/**
 * 
 */

 document.getElementById("upload-app").addEventListener("click", ()=>{
	 const datas = {
		 app_name: document.getElementById("app-name").value,
		 icon: document.getElementById("icon").files[0].name,
		 developer_id: user_id,
		 category: document.getElementById("category").value,
		 download_link: document.getElementById("download-link").value,
		 description: document.getElementById("description").value,
		 features: document.getElementById("features").value,
		 system_require: document.getElementById("system-require").value,
		 release_date: document.getElementById("release-date").value,
		 repo: document.getElementById("repo").value
	 }
	 
	 fetch("")
 })