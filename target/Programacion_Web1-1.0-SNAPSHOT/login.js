$.ajax({
    async: false,
    type: "GET",
    dataType: "json",
    url: "RevisarSesion"
    }).done(function(data){
        console.log(data);
        if(data.resultado){
            
            alert("Hay una sesion activa");
            window.location.href = "inicio.html"
        }
    }).fail(function(qXHR, textEstado){
        console.log("La solicitud regreso con un error: " + textEstado);
    });
    
$(document).ready(function(){
     $("#loginForm").submit(function(event){
      event.preventDefault();
      alert("Envio el formulario");
      $.ajax({
          data: $(this).serialize(),
          type: "POST",
          dataType: "json",
          url: "Login"
      }).done(function(data){
          console.log(data);
          if(data.respuesta){
              window.location.href = "inicio.html";
          }else{
              alert("Error en el usuario o contraseña.");
          }
      }).fail(function(qXHR, textEstado){
          console.log("La solicitud regreso con un error: " + textEstado);
      });
      
  });
});