function agregar(){
    var nombres = document.getElementById("Nombre").value;
    var apellidos = document.getElementById("Apellido").value;

    if(nombres === "" || apellidos === ""){
        alert("Hay campos vacíos");
    }
    else{
        document.getElementById("nombres").innerHTML=nombres;
        document.getElementById("apellidos").innerHTML=apellidos;
        document.getElementById("Nombre").value = "";
        document.getElementById("Apellido").value = "";
    }
}