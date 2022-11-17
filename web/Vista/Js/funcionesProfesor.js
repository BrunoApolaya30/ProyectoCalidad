//función de inicio Jquery
var primeraFilaProfesor; //variable global
$(function(){  
 
    //almacenando la primera fila de la tabla
    primeraFilaProfesor = $("#fila"); 
    /**
     * Acción del evento click del botón
     * llamado btnConsultar
     */
    $("#btnConsultar").click(function(){
        if ($("#txtProf_dni").val()!==""){
              
            consultarProfesor();
        }else{
            alert("Debe ingresar el dni del Apoderado");
            
        }
    });
    
    $("#btnLimpiar").click(function(){
        limpiar();
    });
    /*El botón agregar se coloca como tipo submit para hacer las
    validaciones de los controles con html5*/
    $("#btnAgregar").click(function(){        
        
        if ($("#txtProf_dni").val()!=="" && $("#txtProf_nombre").val()!=="" && $("#txtProf_apellido").val()!=="" && $("#txtProf_telefono").val()!==""){
              agregarProfesor();  
        }else{
            alert("Debe ingresar datos");
            
        }
    }); 
    $("#btnActualizar").click(function(){         
        if ($("#idProfesor").val()===""){
            alert("Debe primero Consultar a un Profesor");   
        }else{
            actualizarProfesor();
        } 
    });
    /*Primero se abre la ventana modal y a partir de ella se llama
    el método que hace el ajax*/
    $("#btnEliminar").click(function(){        
        if ($("#idProfesor").val()===""){
           alert("Debe primero hacer una consulta de un Profesor");
        }else{
            $("#modalEliminar").modal();
        }
    });
    //Botón si del Modal de Eliminar Alumno
    $("#btnSiModal").click(function(){
        eliminarProfesor();
    });  
    
    $("#btnListar").click(function(){
        listarProfesor();
    });  
    
    $("#txtProf_dni").change(function(){
        existeProfesor();
    });
    
});
/**
 * Agrega un alumno. Envía todo el formulario
 * @returns true o false
 */
function agregarProfesor(){   
    $("#mensaje").html("");   
    $.ajax({ 
        // la URL para la petición
        url: '../ControllerProfesor', 
        // la información a enviar
        data:$("#frmProfesor").serialize(),
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
        success: function (Proresultado) {
            console.log(Proresultado);
            if (Proresultado){
                $("#mensaje").html("Profesor Agregado Correctamente"); 
                limpiar();
                listarProfesor();
            }else{
                $("#mensaje").html("Problemas al agregar al Profesor");
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
function consultarProfesor(){
    $("#mensaje").html("");
    $("#mensaje").show();
    var dni = $("#txtProf_dni").val();
    limpiar();
    $("#txtProf_dni").val(dni);
    var parametros = {
        accion:"Consultar",
        identificacion:$("#txtProf_dni").val()
    };
    $.ajax({ 
        url: '../ControllerProfesor',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (Proresultado) {
            console.log(Proresultado);
            if (Proresultado!==null){   
                $("#idProfesor").val(Proresultado.prof_cod);
                $("#txtProf_dni").val(Proresultado.prof_dni);
                $("#txtProf_nombre ").val(Proresultado.prof_nombre);
                $("#txtProf_apellido").val(Proresultado.prof_apellido);
                $("#txtProf_telefono").val(Proresultado.prof_telefono);
            }else{
                $("#mensaje").html("No Existe Profesor con ese dni");
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
function actualizarProfesor(){
    $("#accion").val("Actualizar");   
    $.ajax({       
        url: '../ControllerProfesor',      
        data:$("#frmProfesor").serialize(),
        dataType:'json',
        type: 'post',                     
        cache: false,
        success: function (Proresultado) {
            console.log(Proresultado);
            if (Proresultado){                
                listarProfesor();
                limpiar();
                $("#mensaje").html("Profesor Actualizado Correctamente");
            }else{
                $("#mensaje").html("Problemas al actualizar el Profesor");
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
function eliminarProfesor(){
    var parametros={
        idProfesor:$("#idProfesor").val(),
        accion:"Eliminar"        
    }; 
    $.ajax({       
        url: '../ControllerProfesor',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (Proresultado) {
            console.log(Proresultado);
            if (Proresultado!==null){               
                limpiar();
                listarProfesor();
                 $("#mensaje").html("Profesor Eliminado Correctamente");
            }else{
                $("#mensaje").html("No se pudo eliminar el Profesor");
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
function listarProfesor(){   
    $("#tblProfesor").show();
    $("#mensaje").html("");
    //eliminar los datos de la tabla    
    $(".otraFila").remove();
    //agregar la primera fila vacía
    $("#tblProfesor tbody").append(primeraFilaProfesor);
    var parametros= {       
        accion:"Listar"      
    }; 
    $.ajax({       
        url: '../ControllerProfesor',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (Proresultado) {
            console.log(Proresultado); 
            var profesor = Proresultado;
            $.each(profesor, function (i, aprofesor) {
                $("#aId").html(aprofesor.prof_cod);
                $("#aNombre").html(aprofesor.prof_nombre);
                $("#aApellido").html(aprofesor.prof_apellido);    
                $("#aCelular").html(aprofesor.prof_telefono);
                $("#aDni").html(aprofesor.prof_dni);   
                $("#tblProfesor tbody").append($("#fila").clone(true).attr("class","otraFila")); 
            });
            $("#tblProfesor tbody tr").first().remove(); 
            $("#tblProfesor").DataTable();
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}
function existeProfesor(){
    $("#mensaje").html("");
    var parametros={
        accion:"Existe",
        identificacion: $("#txtProf_dni").val()
    }; 
    $.ajax({       
        url: '../ControllerProfesor',      
        data:parametros,
        type: 'get', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado);
            if (resultado!==null){
                //var nombreAlumno = resultado.nombres + " " + resultado.apellidos;
              $("#mensaje").html("El profesor ya esta registrado");
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
    $("#idProfesor").val("");
    $("#txtProf_dni").val("");
    $("#txtProf_nombre").val("");
    $("#txtProf_apellido").val("");
    $("#txtProf_telefono").val("");     
}

