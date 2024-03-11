// get search input

const search = document.getElementById("search");
const search_list = document.getElementById("searched-list");
search.addEventListener("input", ()=>{
    const filtered_arry = apps.filter( app => app.app_name.toLowerCase().includes(search.value.toLowerCase()));
    while( search_list.hasChildNodes() ){
        search_list.removeChild(search_list.firstChild);
    }
    filtered_arry.forEach( app => {
        search_list.innerHTML += `<div class="searched-item" onclick="appDetail(${app.app_id})">
        <div class="icon">
            <img src="./icons/${app.icon}" alt="">
        </div>
        <div class="name">${app.app_name}</div>
    </div>`;
    })
    if( search.value === "" ){
        while( search_list.hasChildNodes() ){
            search_list.removeChild(search_list.firstChild);
        }
    }
})