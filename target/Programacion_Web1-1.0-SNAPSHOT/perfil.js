$(document).ready(function(){
    $.ajax({
    type: "GET",
    dataType: "json",
    url: "RevisarSesion"
    }).done(function(data){
        console.log(data);
        if(data.resultado){
            $("#nom").html(data.usuario.nombre + " " + data.usuario.apellido);
            $("#nac").html(data.usuario.nacimiento);
            $("#email").html(data.usuario.correo);
            $("#username").html(data.usuario.usuario);
            $("#foto").attr("src",data.usuario.imagen);
            $("#icon_").attr("src",data.usuario.imagen);
            $("#nombre_ini").html(data.usuario.nombre);
        }
    }).fail(function(qXHR, textEstado){
        console.log("La solicitud regreso con un error: " + textEstado);
    });
    
    $("#icon2").click(function(){
       window.location.href = "inicio.html";
    });
    
    $("#Editarr").click(function(){     
        window.location.href = "perfil_editar.html";
    });
});


