/**
 * 
 */

 const item_container = document.getElementById("item-container");
 const all_program_count = document.getElementById("program-count");
 const nav_items = document.querySelectorAll(".nav-item");
 let apps;
//  detail and download page 
 function appDetail(app_id){
	 window.location.href = "./app_detail.html?app_id="+app_id;
 }
function allDatas(){
	fetch("./GetAllApps", {
	 method: "GET"
 }).then(response => response.json())
 .then( datas => {
	apps = datas.apps;
	const social = apps.filter( app=> app.category === "social").length;
	const game = apps.filter( app=> app.category === "game").length;
	const development = apps.filter( app => app.category === "development").length;
	document.getElementById("social-count").innerText = social;
	document.getElementById("game-count").innerText = game;
	document.getElementById("development-count").innerText = development;
    all_program_count.innerText = apps.length;
	 apps.forEach( app => {
		 item_container.innerHTML += `<div class="item" onclick="appDetail(${app.app_id})">
                    <div class="icon">
                        <img src=".//icons/${app.icon}" alt="" srcset="">
                    </div>
                    <div class="description">
                        <div class="name">${app.app_name}</div>
                        <div class="system">${app.category}</div>
                        <div class="company">
                            <div class="name">com.${(app.app_name).toLowerCase()}</div>
                            <div class="date">${app.release_date}</div>
                        </div>
                    </div>
                </div>`;
	 })
 }).catch( error => console.error(error));
}
// initial all datas
 allDatas();
 // listen to all tab and show with related category when click 
 nav_items.forEach( nav => {
	 nav.addEventListener("click", ()=>{
		 const related_data = apps.filter( app => nav.id === app.category );
		 while( item_container.hasChildNodes() ){
			 item_container.removeChild(item_container.firstChild);
		 }
		 
		 if( nav.id === "programs" ){
			 allDatas();
		 }else{
			 related_data.forEach( app => {
			 item_container.innerHTML += `<div class="item" onclick="appDetail(${app.app_id})">
                    <div class="icon">
                        <img src="./icons/${app.icon}" alt="" srcset="">
                    </div>
                    <div class="description">
                        <div class="name">${app.app_name}</div>
                        <div class="system">${app.category}</div>
                        <div class="company">
                            <div class="name">com.${app.app_name.toLowerCase()}</div>
                            <div class="date">${app.release_date}</div>
                        </div>
                    </div>
                </div>`;
		 })
		 }
	 })
 })
