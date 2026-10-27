document.addEventListener("DOMContentLoaded", function(){
    var alerts = document.querySelectorAll(".alert");
    alerts.forEach(function(item){
        setTimeout(function(){
            item.style.display = "none";
        }, 3500);
    });

    var dni = document.querySelector(".dni-input");
    if(dni){
        dni.focus();
        dni.addEventListener("input", function(){
            this.value = this.value.replace(/[^0-9]/g, "");
        });
    }
});