//función de inicio Jquery
var primeraFilaGrado; //variable global
$(function(){  
 
    //almacenando la primera fila de la tabla
    primeraFilaGrado = $("#fila"); 
    /**
     * Acción del evento click del botón
     * llamado btnConsultar
     */
    $("#btnConsultar").click(function(){
        if ($("#txtGra_naula").val()!==""){
              
            consultarGrado();
        }else{
            alert("Debe ingresar el aula del grado");
            
        }
    });
    /*El botón agregar se coloca como tipo submit para hacer las
    validaciones de los controles con html5*/
    $("#btnAgregar").click(function(){        
        
        if ($("#txtGra_naula").val()!=="" && $("#cbGra_grado").val()!=="" && $("#cbGra_seccion").val()!=="" && $("#cbGra_nivel").val()!==""){
              agregarGrado();  
        }else{
            alert("Debe ingresar datos");
            
        }
    }); 
    $("#btnActualizar").click(function(){         
        if ($("#idGrado").val()===""){
            alert("Debe primero Consultar una aula");   
        }else{
            actualizarGrado();
        } 
    });
    /*Primero se abre la ventana modal y a partir de ella se llama
    el método que hace el ajax*/
    $("#btnEliminar").click(function(){        
        if ($("#idGrado").val()===""){
           alert("Debe primero hacer una consulta de una aula");
        }else{
            $("#modalEliminar").modal();
        }
    });
    //Botón si del Modal de Eliminar Alumno
    $("#btnSiModal").click(function(){
        eliminarGrado();
    });  
    
    $("#btnListar").click(function(){
        listarGrado();
    });  
    
    $("#btnLimpiar").click(function(){
        limpiar();
    });
    
    /*$("#txtIdentificacion").change(function(){
        existeAprendiz();
    });*/
    
});
/**
 * Agrega un alumno. Envía todo el formulario
 * @returns true o false
 */
function agregarGrado(){   
    $("#mensaje").html("");   
    $.ajax({ 
        // la URL para la petición
        url: '../ControllerGrado', 
        // la información a enviar
        data:$("#frmGrado").serialize(),
        // el tipo de información que se espera de respuesta
        dataType:'json',
        // especifica si será una petición POST o GET
        type: 'post',  
        /*Un valor booleano que indica si el navegador 
        *debe almacenar en caché las páginas solicitadas. 
        *El valor predeterminado es verdadero*/
        cache: false,
        /* código a ejecutar si la petición es satisfactoria;
         * la respuesta es pasada como argumento a la función */
        success: function (Graresultado) {
            console.log(Graresultado);
            if (Graresultado){
                $("#mensaje").html("Grado Agregado Correctamente"); 
                limpiar();
                listarGrado();
            }else{
                $("#mensaje").html("Problemas al agregar al Grado");
            }     
        },
        /*Código a ejecutar si la petición
         * falla */
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}
/**
 * Método que consulta un alumno dada la identificación
 * realiza un llamado mediante Ajax.
 * @returns Objeto Aprendiz en formato Json
 */
function consultarGrado(){
    $("#mensaje").html("");
    $("#mensaje").show();
    var aula = $("#txtGra_naula").val();
    limpiar();
    $("#txtGra_naula").val(aula);
    var parametros = {
        accion:"Consultar",
        identificacion:$("#txtGra_naula").val()
    };
    $.ajax({ 
        url: '../ControllerGrado',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (Graresultado) {
            console.log(Graresultado);
            if (Graresultado!==null){   
                $("#idGrado").val(Graresultado.gra_codigo);
                $("#txtGra_naula").val(Graresultado.Gra_naula);
                $("#cbGra_grado ").val(Graresultado.gra_grado);
                $("#cbGra_seccion").val(Graresultado.gra_seccion);
                $("#cbGra_nivel").val(Graresultado.gra_nivel);
            }else{
                $("#mensaje").html("No Existe Grado en esa aula");
            }
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}
/**
 * Método que actualiza un alumno de acuerdo al
 * codigo del alumno. Se envía los datos mediante Ajax.
 * @returns true o false
 */
function actualizarGrado(){
    $("#accion").val("Actualizar");   
    $.ajax({       
        url: '../ControllerGrado',      
        data:$("#frmGrado").serialize(),
        dataType:'json',
        type: 'post',                     
        cache: false,
        success: function (Graresultado) {
            console.log(Graresultado);
            if (Graresultado){                
                listarGrado();
                $("#mensaje").html("Grado Actualizado Correctamente");
            }else{
                $("#mensaje").html("Problemas al actualizar el Grado");
            }  
        },
        error: function(ex){
          console.log(ex.responseText);
        }  
    });
}
/**
 * Método que limpia las cajas de texto
 * @returns
 */
function eliminarGrado(){
    var parametros={
        idGrado:$("#idGrado").val(),
        accion:"Eliminar"        
    }; 
    $.ajax({       
        url: '../ControllerGrado',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (Graresultado) {
            console.log(Graresultado);
            if (Graresultado!==null){               
                limpiar();
                listarGrado();
                 $("#mensaje").html("Grado Eliminadio Correctamente");
            }else{
                $("#mensaje").html("No se pudo eliminar el Grado");
            }
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}
/**
 * Obtener listado de alumnos mediante
 * ajax.
 * @returns lista de aprendices objeto Json
 */
function listarGrado(){   
    $("#tblGrado").show();
    $("#mensaje").html("");
    //eliminar los datos de la tabla    
    $(".otraFila").remove();
    //agregar la primera fila vacía
    $("#tblGrado tbody").append(primeraFilaGrado);
    var parametros= {       
        accion:"Listar"      
    }; 
    $.ajax({       
        url: '../ControllerGrado',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado); 
            var grado = resultado;
            $.each(grado, function (i, agrado) {
                $("#aId").html(agrado.gra_codigo);
                $("#aAula").html(agrado.Gra_naula);
                $("#aGrado").html(agrado.gra_grado);    
                $("#aSeccion").html(agrado.gra_seccion);
                $("#aNivel").html(agrado.gra_nivel);
                $("#tblGrado tbody").append($("#fila").clone(true).attr("class","otraFila")); 
            });
            $("#tblGrado tbody tr").first().remove(); 
            $("#tblGrado").DataTable();
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}
/*function existeAlumno(){
    $("#mensaje").html("");
    var parametros={
        accion:"Existe",
        apellido: $("#txtAlu_apellidos").val()
    }; 
    $.ajax({       
        url: '../ControllerAlumno',      
        data:parametros,
        type: 'get', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado);
            if (resultado!==null){
                var nombreAlumno = resultado.nombres + " " + resultado.apellidos;
              $("#msjIdentifica").html("Ya existe aprendiz con la identificación: \n \n\
                su nombre es: " + nombreAprendiz);
            }else{
                $("#msjIdentifica").html("");
            }
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });  
}*/
/**
 * Método que limpia las cajas de texto
 * @returns {undefined}
 */
function limpiar(){
    
    $("#idGrado").val("");
    $("#txtGra_naula").val("");
    $("#cbGra_grado").val("");
    $("#cbGra_seccion").val("");
    $("#cbGra_nivel").val("");    
}

