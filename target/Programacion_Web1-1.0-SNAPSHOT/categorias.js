
$(document).ready(function(){

$.ajax({
    type: "GET",
    dataType: "json",
    url: "MostrarCategorias"
    }).done(function(data){
        console.log(data);
    for(let i = 0; i < data.categorias.length; i++){
        $("#categs").append('<ul style="list-style:none;"> <li> <a href="Porcategoria.html?id_cat=' + data.categorias[i].id_categoria + '" > '+ data.categorias[i].nombre +'</a>  </li>  </ul>');
        
        
        $("#combo").append(' <option value="'+ (i+1) +'">'+ data.categorias[i].nombre +'</option>');

    }
    }).fail(function(qXHR, textEstado){
        console.log("La solicitud regreso con un error: " + textEstado);
    });
    
    $("#icon2").click(function(){
       window.location.href = "inicio.html";
    });
    
    $("#icon1").click(function(){
       window.location.href = "index.html";
    });
});




