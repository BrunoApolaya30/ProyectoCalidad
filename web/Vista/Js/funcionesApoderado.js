//función de inicio Jquery
var primeraFilaApoderado; //variable global
$(function(){  
 
    //almacenando la primera fila de la tabla
    primeraFilaApoderado = $("#fila"); 
    /**
     * Acción del evento click del botón
     * llamado btnConsultar
     */
    $("#btnConsultar").click(function(){
        if ($("#txtApo_dni").val()!==""){
              
            consultarApoderado();
        }else{
            alert("Debe ingresar el dni del Apoderado");
            
        }
    });
    
    $("#btnNuevo").click(function(){
        limpiar();
    });
    /*El botón agregar se coloca como tipo submit para hacer las
    validaciones de los controles con html5*/
    $("#btnAgregar").click(function(){        
        
        if ($("#txtApo_dni").val()!=="" && $("#txtApo_nombre").val()!=="" && $("#txtApo_Apellido").val()!=="" && $("#txtApo_celular").val()!=="" && $("#txtApo_parentesco").val()!==""){
              agregarApoderado();  
        }else{
            alert("Debe ingresar datos");
            
        }
    }); 
    $("#btnActualizar").click(function(){         
        if ($("#idApoderado").val()===""){
            alert("Debe primero Consultar a un apoderado");   
        }else{
            actualizarApoderado();
        } 
    });
    /*Primero se abre la ventana modal y a partir de ella se llama
    el método que hace el ajax*/
    $("#btnEliminar").click(function(){        
        if ($("#idApoderado").val()===""){
           alert("Debe primero hacer una consulta de un Apoderado");
        }else{
            $("#modalEliminar").modal();
        }
    });
    //Botón si del Modal de Eliminar Alumno
    $("#btnSiModal").click(function(){
        eliminarApoderado();
    });  
    
    $("#btnListar").click(function(){
        listarApoderado();
    });  
    
    $("#txtApo_dni").change(function(){
        existeApoderado();
    });
    
});
/**
 * Agrega un alumno. Envía todo el formulario
 * @returns true o false
 */
function agregarApoderado(){   
    $("#mensaje").html("");   
    $.ajax({ 
        // la URL para la petición
        url: '../ControllerApoderado', 
        // la información a enviar
        data:$("#frmApoderado").serialize(),
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
        success: function (Aporesultado) {
            console.log(Aporesultado);
            if (Aporesultado){
                $("#mensaje").html("Apoderado Agregado Correctamente"); 
                limpiar();
                listarApoderado();
            }else{
                $("#mensaje").html("Problemas al agregar al Apoderado");
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
function consultarApoderado(){
    $("#mensaje").html("");
    $("#msj").show();
    var dni = $("#txtApo_dni").val();
    limpiar();
    $("#txtApo_dni").val(dni);
    var parametros = {
        accion:"Consultar",
        identificacion:$("#txtApo_dni").val()
    };
    $.ajax({ 
        url: '../ControllerApoderado',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (Aporesultado) {
            console.log(Aporesultado);
            if (Aporesultado!==null){   
                $("#idApoderado").val(Aporesultado.apo_codigo);
                $("#txtApo_nombre").val(Aporesultado.apo_nombre);
                $("#txtApo_Apellido ").val(Aporesultado.apo_apellido);
                $("#txtApo_celular").val(Aporesultado.apo_celular);
                $("#txtApo_parentesco").val(Aporesultado.apo_parentesco);
            }else{
                $("#mensaje").html("No Existe Apoderado con ese dni");
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
function actualizarApoderado(){
    $("#accion").val("Actualizar");   
    $.ajax({       
        url: '../ControllerApoderado',      
        data:$("#frmApoderado").serialize(),
        dataType:'json',
        type: 'post',                     
        cache: false,
        success: function (Aporesultado) {
            console.log(Aporesultado);
            if (Aporesultado){                
                listarApoderado();
                $("#mensaje").html("Apoderado Actualizado Correctamente");
            }else{
                $("#mensaje").html("Problemas al actualizar el Apoderado");
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
function eliminarApoderado(){
    var parametros={
        idApoderado:$("#idApoderado").val(),
        accion:"Eliminar"        
    }; 
    $.ajax({       
        url: '../ControllerApoderado',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (Aporesultado) {
            console.log(Aporesultado);
            if (Aporesultado!==null){               
                limpiar();
                listarApoderado();
                 $("#mensaje").html("Apoderado Eliminado Correctamente");
            }else{
                $("#mensaje").html("No se pudo eliminar el Apoderado");
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
function listarApoderado(){   
    $("#tblApoderado").show();
    $("#mensaje").html("");
    //eliminar los datos de la tabla    
    $(".otraFila").remove();
    //agregar la primera fila vacía
    $("#tblApoderado tbody").append(primeraFilaApoderado);
    var parametros= {       
        accion:"Listar"      
    }; 
    $.ajax({       
        url: '../ControllerApoderado',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado); 
            var Apoderado = resultado;
            $.each(Apoderado, function (i, aapoderado) {
                $("#aId").html(aapoderado.apo_codigo);
                $("#aNombre").html(aapoderado.apo_nombre);
                $("#aApellido").html(aapoderado.apo_apellido);    
                $("#aCelular").html(aapoderado.apo_celular);
                $("#aDni").html(aapoderado.apo_dni);
                $("#aParentesco").html(aapoderado.apo_parentesco);   
                $("#tblApoderado tbody").append($("#fila").clone(true).attr("class","otraFila")); 
            });
            $("#tblApoderado tbody tr").first().remove(); 
            $("#tblApoderado").DataTable();
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}
function existeApoderado(){
    $("#mensaje").html("");
    var parametros={
        accion:"Existe",
        identificacion: $("#txtApo_dni").val()
    }; 
    $.ajax({       
        url: '../ControllerApoderado',      
        data:parametros,
        type: 'get', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado);
            if (resultado!==null){
                //var dniApoderado = resultado. + " " + resultado.apellidos;
              $("#mensaje").html("El apoderado ya esta registrado");
            }else{
               $("#mensaje").html("");
            }
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });  
}
/**
 * Método que limpia las cajas de texto
 * @returns {undefined}
 */
function limpiar(){
    $("#idApoderado").val("");
    $("#txtApo_nombre").val("");
    $("#txtApo_Apellido").val("");
    $("#txtApo_celular").val("");
    $("#txtApo_dni").val("");    
    $("#txtApo_parentesco").val("");  
}

