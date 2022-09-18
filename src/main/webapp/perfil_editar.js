 $.ajax({
    type: "GET",
    dataType: "json",
    url: "RevisarSesion"
    }).done(function(data){
        console.log(data);
        if(data.resultado){
            $("#nom").val(data.usuario.nombre);
            $("#ape").val(data.usuario.apellido);
            $("#nac").val(data.usuario.nacimiento);
            $("#email").val(data.usuario.correo);
            $("#username").val(data.usuario.usuario);
            $("#foto").attr("src", data.usuario.imagen);
            $("#fototemp").val(data.usuario.imagen);
            $("#icon_").attr("src", data.usuario.imagen);
            $("#nombre_ini").html(data.usuario.nombre);
            document.getElementById("id_user").value = data.usuario.id_usuario;
        }
    }).fail(function(qXHR, textEstado){
        console.log("La solicitud regreso con un error: " + textEstado);
    });



$(document).ready(function(){
 $("#icon2").click(function(){
       window.location.href = "inicio.html";
    });
    
    
 $("#actualizar").submit(function(event){
      event.preventDefault();
     $.ajax({
          data: new FormData(this),
          type: "POST",
          dataType: "json",
          url: "EditarUsuario",
          cache: false,
          contentType: false,/*se envia el formato tal cual está*/
          processData: false/*acepta el formato
           * sea cual sea en el que esté hecho*/
      }).done(function(data){
          console.log(data);
          if(data.resultado){
              alert("Se actualizó el perfil");
              window.location.href = "inicio.html"
          }else{
              alert("No pude actualizar la información del usuario");
          }
      }).fail(function(qXHR, textEstado){
          console.log("La solicitud regreso con un error: " + textEstado);
      });
 });
});



