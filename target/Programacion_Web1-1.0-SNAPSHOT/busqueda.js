const valores = window.location.search;

const urlParams = new URLSearchParams(valores);
var desc = urlParams.get("descrip");

var indiceposicion = 0;
var pages = 0;
var publiaeliminar;
var publiaeditar;

$.ajax({
    async: false,
    type: "GET",
    dataType: "json",
    url: "RevisarSesion"
    }).done(function(data){
        console.log(data);
        if(data.resultado){
            $("#icon_").attr("src",data.usuario.imagen);
            $("#nombre_ini").html(data.usuario.nombre);
            document.getElementById("id_user").value = data.usuario.id_usuario;
        }
    }).fail(function(qXHR, textEstado){
        console.log("La solicitud regreso con un error: " + textEstado);
});
    
$.ajax({
    async: false,
    type: "GET",
    dataType: "json",
    url: "Total_publicaciones"
}).done(function(datas){
    console.log(datas);
    if(datas.Respuesta){

        var total = datas.Cantidad;
        var str = '<li class ="page-item" id="Previous"> <a class = "page-link"> Anterior </a></li>';
        pages = Math.ceil(total/10);

        for (let i = 1; i <= pages; i++)
        {
            str += '<li class ="page-item indice"  indice="' + (i-1) + '"> <a class = "page-link">' + i + '</a></li>                                ';
        }
        str+= '<li class ="page-item" id = "Next"> <a class = "page-link"> Siguiente </a></li>';
        
        $("#paginas").html(str);
        cargarpubs(0,10);
    }
    else{
        alert("ehhh");

    }
}).fail(function(qXHR, textEstado){              

   console.log("La solicitud regreso con un error: " + textEstado);
});
                                  
$(document).ready(function(){
    $(".indice").click(function(){
        $(this).addClass("active");
        indiceposicion = $(this).attr("indice");
        cargarpubs((indiceposicion *2), 10);
    });
    $("#Previous").click(function(){    
        if (indiceposicion>0){
            $(".indice").removeClass("active");
            indiceposicion--;
            $(".indice:nth-child(" + (indiceposicion + 2) + ")").addClass("active");
            cargarpubs((indiceposicion *2), 10);

        }
    });
    $("#Next").click(function(){    
        if (indiceposicion<pages){
            $(".indice").removeClass("active");
            indiceposicion++;
            $(".indice:nth-child(" + (indiceposicion + 2) + ")").addClass("active");
            cargarpubs((indiceposicion *2), 10);
        }
    });
    $(".eliminarpublicacion").click(function(){
       publiaeliminar = $(this).attr("value");
       document.getElementById("temporal").value = publiaeliminar;
    });
    $(".editararpublicacion").click(function(){
       publiaeditar = $(this).attr("value");
       document.getElementById("temporal").value = publiaeditar;
       $("#Modal_Editar").modal();
         
       $.ajax({
          data: {id_: publiaeditar},
          type: "POST",
          dataType: "json",
          url: "IdentificarPubli"
      }).done(function(data){
           document.getElementById("recipient-name").value = data.post.titulo;
           document.getElementById("message-text").value = data.post.descripcion;
           for(let i = 0; i < data.categorias.length; i++){
           $("#combo_ed").append(' <option value="'+ (i+1) +'">'+ data.categorias[i].nombre +'</option>');
           }
           $("#combo_ed option:contains("+data.post.id_categoria_publicacion+")").attr('selected', true);
           document.getElementById("temporal").value = publiaeditar;
            
            
       
          if(!(data.respuesta)){
              alert("No pude traer la informacion");
          }
      }).fail(function(qXHR, textEstado){
          console.log("La solicitud regreso con un error: " + textEstado);
      });
      

       
    });
    $("#Eliminarp").click(function(){
      $.ajax({
          data: {id: publiaeliminar},
          type: "POST",
          dataType: "json",
          url: "EliminarPublicacion"
      }).done(function(data){
          console.log(data);
          if(data.resultado){
              alert("Publicacion eliminada.");
              $("publicacions").html("");
              cargarpubs(0,10);
              
          }else{
              alert("No pude eliminar la publicacion.");
          }
      }).fail(function(qXHR, textEstado){
          console.log("La solicitud regreso con un error: " + textEstado);
      });
    });

    $("#Editarp").click(function(){
        
      $.ajax({
          data: {id_: publiaeditar, combo: $("#combo_ed").val(), descripcion: $("#message-text").val(), titulo: $("#recipient-name").val() },
          type: "POST",
          dataType: "json",
          url: "EditarPublicacion"
      }).done(function(data){
          console.log(data);
          if(data.respuesta){
              alert("Publicacion actualizada.");
              cargarpubs(0,10);
          }else{
              alert("No pude editar la publicacion.");
          }
      }).fail(function(qXHR, textEstado){
          console.log("La solicitud regreso con un error: " + textEstado);
      });
      
      
    });

    $("#CrearpostForm").submit(function(event){
      event.preventDefault();
      $.ajax({
          
          data: $(this).serialize(),
          type: "POST",
          dataType: "json",
          url: "CrearPublicacion",
      }).done(function(data){
          console.log(data);
          if(data.resultado){
            $("#publicacions").append('<div  style="color: rgb(51, 6, 9);"> <br> <fieldset style="background-color: rgb(141, 127, 177);  border-radius: 15px;"> <div style="margin: 8px;"> <h5>' + data.publicacion.titulo + '</h5> <a href="" >'+ data.publicacion.id_categoria_publicacion + '</a> <p> from: '+  data.publicacion.id_usuario_publicacion + '</p> <p>'+data.publicacion.descripcion + '</p> <p>'+ data.publicacion.fecha  +'</p> <button type="button" class="botones2 text-end" data-toggle="modal" data-target="#Modal_Editar">Editar publicación</button> <button type="button" class="botones2 text-end" data-toggle="modal" data-target="#Modal_Eliminar">Eliminar publicación</button> <div class="modal fade" id="Modal_Eliminar" tabindex="-1" role="dialog" aria-labelledby="Modal_EliminarLabel" aria-hidden="true"> <div class="modal-dialog" role="document"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title" id="Modal_EliminarLabel"> ¿Quieres eliminar esta publicación? </h5> <button type="button" class=" botones2 close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button> </div> <div class="modal-body"> <p>Esta acción no se puede revertir. </p> </div> <div class="modal-footer"> <button type="button" class="botones2" data-dismiss="modal"> Cancelar </button> <button type="button" class="botones2"> Eliminar </button> </div>  </div> </div> </div> <div class="modal fade" id="Modal_Editar" tabindex="-1" role="dialog" aria-labelledby="Modal_EditarLabel" aria-hidden="true"> <div class="modal-dialog" role="document"> <div class="modal-content"> <div class="modal-body"> <form> <div class="form-group"> <label for="recipient-name" class="col-form-label">Titulo:</label> <input type="text" class="form-control" id="recipient-name"> </div> <div class="form-group"> <label for="message-text" class="col-form-label">Contenido:</label> <textarea class="form-control" id="message-text"></textarea> <br> <select name="select">  <option value="value1"> Música</option> <option value="value2"> Tecnología </option> <option value="value3"> Gaming</option> <option value="value4"> Entretenimiento</option> <option value="value5"> Noticias</option>  </select> </div>  </form> </div> <div class="modal-footer"> <button type="button" class="botones2" data-dismiss="modal"> Cancelar </button> <button type="submit" class="botones2"> Actualizar </button> </div> </div> </div>  </div>  </div> </fieldset> </div>');
            $("#DescPublicacion").val("");
            $("#tit").val("");
            $("#combo").val(0).change();

          }else{
              alert("No pude insertar la publicacion.");
          }
      }).fail(function(qXHR, textEstado){
          console.log("La solicitud regreso con un error: " + textEstado);
      });
    });

});


 function cargarpubs(_indice, _cantidad){
    
    $.ajax({
    async: false,
    data: {indice: _indice, cantidad: _cantidad, descripcion: desc},
    type: "GET",
    dataType: "json",
    url: "MostrarPublicacionesBuscadas"
    }).done(function(data){
        console.log(data);
        if (data.publicacion.length > 0){
            $("#publicacions").html("");
            for(let i = 0; i<data.publicacion.length; i++){
               
                $("#publicacions").append('  <div  style="color: rgb(51, 6, 9);"> <br> <fieldset style="background-color: rgb(141, 127, 177);  border-radius: 15px;"> <div style="margin: 8px;">  <h5>' + data.publicacion[i].titulo + '</h5>  <a href="">'+ data.publicacion[i].id_categoria_publicacion + '</a>  <p>  from:'+  data.publicacion[i].id_usuario_publicacion + '</p> <p>'+data.publicacion[i].descripcion + '</p> <p>'+ data.publicacion[i].fecha  +'</p> <button type="button" value ="' + data.publicacion[i].id_publicacion +'" class="botones2 editararpublicacion text-end" data-toggle="modal" data-target="#Modal_Editar">Editar publicación</button> <button type="button" value ="' + data.publicacion[i].id_publicacion +'"  class="eliminarpublicacion botones2 text-end" data-toggle="modal" data-target="#Modal_Eliminar">Eliminar publicación</button> </div> </fieldset> </div>');
            }
        }
        else{
               $("#publicacions").append('<div  style="color: rgb(51, 6, 9);">  NO HAY PUBLICACIONES </div>');
        }
    
   
    }).fail(function(qXHR, textEstado){
        console.log("La solicitud regreso con un error: " + textEstado);
    });
}



