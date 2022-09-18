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
    
var enviarRegistro = false;

$(document).ready(function(){
  //use keyup event on email
  $("#email").keyup(function(){
    if (validateEmail()){
      $("#email").css("border", "2px solid blue");
    }
    else{
      $("#email").css("border", "2px solid red");
    }
    
  });
  //use keyup event on password
  $("#c1").keyup(function(){
        if (validatePassword()){
          $("#c1").css("border", "2px solid blue");
        }
        else{
          $("#c1").css("border", "2px solid red");
        }
  });
  //use keyup event on passwords
  
  $("#c2").keyup(function(){
      if (coinciden(c1, c2)){
        $("#c2").css("border", "2px solid blue");
      }
      else{
        $("#c2").css("border", "2px solid red");
    }
  });
  
  $("#btn_submit").click(function(){
    if (validatePassword()){
      if (validateEmail()){
        if(coinciden(c1, c2)){
          if (sonLetras(Nm, Ap)){
            if (Hoy()){
              alert("Fecha no válida.");
              event.preventDefault();
            }
          }
          else{
            alert("El nombre solo acepta letras.");
            event.preventDefault();
          }
        }
        else{
          alert("Las contraseñas no coinciden.");
          event.preventDefault();
        }
      }
      else{
        alert("Correo no válido.");
        event.preventDefault();
      }
    }
    else{
      alert("La contraseña debe tener mínimo 8 caracteres, una mayuscula, una minúscula, un número y un símbolo.");
      event.preventDefault();
    }
  });
  
  $("#RegisterForm").submit(function(event){
      event.preventDefault();
      $.ajax({
          data: new FormData(this),
          type: "POST",
          dataType: "json",
          url: "InsertarUsuario",
          cache: false,
          contentType: false,/*se envia el formato tal cual está*/
          processData: false/*acepta el formato
           * sea cual sea en el que esté hecho*/
      }).done(function(data){
          console.log(data);
          if(data.resultado){
              alert("Te registrarste en Retroblog");
              window.location.href = "inicio.html"
          }else{
              alert("No pude insertar el usuario");
          }
      }).fail(function(qXHR, textEstado){
          console.log("La solicitud regreso con un error: " + textEstado);
      });
      
  });
});

function validateEmail(){
   //obtiene el valor del input de email
   var email=$("#email").val();
   //uso de la expresión regular REGEX
   let reg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
   if (reg.test(email)){
     return true;
   }
   else{
     return false;
   }
 }

function validatePassword(){
  //get input password value
  var pass=$("#c1").val();
  //uso de la expresión regular REGEX
  let regpas = /^(?=.*\d)(?=.*[a-z]).*[A-Z].*[&%$#"!/()=]/;
  if (regpas.test(pass)){
    return true;
  }
 else{
   return false;
 }
}

function coinciden(c1, c2){
  var constraseña1=$("#c1").val();
  var constraseña2=$("#c2").val();
  if (constraseña1 != constraseña2) {
      return false;
  } else
  {
      return true; 
  }
}

function sonLetras(Nm, Ap){
  var txt1=$("#Nm").val();
  var txt2=$("#Ap").val();
  let regtxt = /^[a-zA-Z ]+$/;
  if (regtxt.test(txt1)){
    if (regtxt.test(txt2)){
      return true;
    }
    else
    {
      return false;
    }
  }
 else{
   return false;
 }
}

function Hoy(){
  var fechaNac = document.getElementById("fechaNacimiento").value;

  var d = new Date();
  var month = d.getMonth()+1;
  var day = d.getDate();
  var fechaHoy = d.getFullYear() + '/' + (month<10 ? '0' : '') + month + '/' + (day<10 ? '0' : '') + day;

  if(Date.parse(fechaNac) < Date.parse(fechaHoy)){
    return false;
  }
  else{
    return true;
  }
}
