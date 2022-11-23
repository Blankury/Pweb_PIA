
const valores2 = window.location.search;
const urlParams2 = new URLSearchParams(valores2);
var busqueda_ = urlParams2.get("search");

busquedadefault();

$(document).ready(function(){
    
    $("#Ordenar").click(function(){
        if($('#Combo').val() == 'Menor'){
            $("#lista_usuarios").empty();
            $(".Buscadas").empty();
            busquedadefault();
       }
       else{
            $("#lista_usuarios").empty();
            $(".Buscadas").empty();
            busquedaCara();
       }
    });
});

function busquedadefault() {
    $.ajax({
        data: {busqueda: busqueda_, action: 'searchToys'},
        type: "GET",
        url: './controlador/busqueda_ctrl.php',
        async: false,
        success: function(result){
            var data = JSON.parse(result);
            for (let i = 0; i < data['jug'].length;i++){
                $(".Buscadas").append('<div class="container-fluid separadorDoble" > <div class="row"> <div class="col-3 text-center" > <img src="' + data['jug'][i].icono +'" class="img-thumbnail" width="150px " height =150px alt=""> </div> <div class="col-9 bg-info separador" > <h4 id="Nombre producto"> ' +  data['jug'][i].nombre +'</h4> <label> MXN ' + data['jug'][i].precio +' </label> <br> <label > Venta de: </label> <label > ' +  data['jug'][i].vendedor + '</label> <div align = "right"> <button type="button" id="Ver' + data['jug'][i].ID_PRODUCTO +'" value="'+  data['jug'][i].ID_PRODUCTO + '" class="btn btn-primmary bg-warning VER" style="right: 10px;"> Ver </button></div> </div> </div> </div> <hr>');
            }
            for (let i = 0; i < data['usuarios'].length;i++){
                $("#lista_usuarios").append(' <a href="perfil_usuario.php?user='+data['usuarios'][i].usuario +'"> '+data['usuarios'][i].usuario +' </a> <br>');
            }
        },
        error: function(result){
            console.log("La solicitud regreso con un error: " + result);
        }  
    });
}

function busquedaCara() {
    $.ajax({
        data: {busqueda: busqueda_, action: 'searchToys'},
        type: "GET",
        url: './controlador/busqueda_ctrl.php',
        async: false,
        success: function(result){
            var data = JSON.parse(result);
            for (let i = 0; i < data['jugcaro'].length;i++){
                $(".Buscadas").append('<div class="container-fluid separadorDoble" > <div class="row"> <div class="col-3 text-center" > <img src="' + data['jugcaro'][i].icono +'" class="img-thumbnail" width="150px " height =150px alt=""> </div> <div class="col-9 bg-info separador" > <h4 id="Nombre producto"> ' +  data['jugcaro'][i].nombre +'</h4> <label> MXN ' + data['jugcaro'][i].precio +' </label> <br> <label > Venta de: </label> <label > ' +  data['jugcaro'][i].vendedor + '</label> <div align = "right"> <button type="button" id="Ver' + data['jugcaro'][i].ID_PRODUCTO +'" value="'+  data['jugcaro'][i].ID_PRODUCTO + '" class="btn btn-primmary bg-warning VER" style="right: 10px;"> Ver </button></div> </div> </div> </div> <hr>');
            }
            for (let i = 0; i < data['usuarios'].length;i++){
                $("#lista_usuarios").append(' <a href="perfil_usuario.php?user='+data['usuarios'][i].usuario +'"> '+data['usuarios'][i].usuario +' </a> <br>');
            }
        },
        error: function(result){
            console.log("La solicitud regreso con un error: " + result);
        }  
    });
}