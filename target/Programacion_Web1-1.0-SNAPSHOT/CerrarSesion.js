$.ajax({
    async: false,
    type: "GET",
    dataType: "json",
    url: "RevisarSesion"
    }).done(function(data){
        console.log(data);
        if(!data.resultado){
            alert("No hay una sesion activa");
            window.location.href = "login.html"
        }
    }).fail(function(qXHR, textEstado){
        console.log("La solicitud regreso con un error: " + textEstado);
    });
    
$(document).ready(function(){
     $("#cerrarsesion").click(function(){
      alert("Cerrando sesión");
      $.ajax({
          data: $(this).serialize(),
          type: "GET",
          dataType: "json",
          url: "CerrarSesion"
      }).done(function(data){
          console.log(data);
          if(data.respuesta){
              window.location.href = "login.html";
          }else{
              alert("no cerré correctamente la sesión.");
              window.location.href = "login.html";
          }
      }).fail(function(qXHR, textEstado){
          console.log("La solicitud regreso con un error: " + textEstado);
      });
      
  });
});


