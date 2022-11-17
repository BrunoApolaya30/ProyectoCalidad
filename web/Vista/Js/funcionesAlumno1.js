//función de inicio Jquery
var primeraFilaAlumno; //variable global
$(function(){  
    //almacenando la primera fila de la tabla
    primeraFilaAlumno = $("#fila"); 
    /**
     * Acción del evento click del botón
     * llamado btnConsultar
     */
    $("#btnConsultar").click(function(){
        if ($("#txtAlu_dni").val()!==""){
              
            consultarAlumno();
        }else{
            alert("Debe ingresar el dni del Alumno");
            
        }
    });
    $("#btnNuevo").click(function(){
        limpiar();
    });
    
    $("#btnConsultarApoderado").click(function(){
        if ($("#txtApo_dni").val()!==""){
              
            consultarApoderado();
        }else{
            alert("Debe ingresar el dni del Apoderado");
            
        }
    });
    /*El botón agregar se coloca como tipo submit para hacer las
    validaciones de los controles con html5*/
    $("#btnAgregar").click(function(){        
        
        if ($("#txtAlu_dni").val()!=="" && $("#txtAlu_apellidos").val()!=="" && $("#txtAlu_nombre").val()!=="" && $("#txtAlu_direccion").val()!=="" && $("#txtAlu_telefono").val()!=="" && $("#txtAlu_fechaadmi").val()!=="" && $("#cbAlu_sexo").val()!=="" && $("#txtApo_id").val()!==""){
              agregarAlumno();  
        }else{
            alert("Debe ingresar datos");
            
        }
    }); 
    $("#btnActualizar").click(function(){         
        if ($("#idAlumno").val()===""){
            alert("Debe primero Consultar a un alumno");   
        }else{
            actualizarAlumno();
        } 
    });
    /*Primero se abre la ventana modal y a partir de ella se llama
    el método que hace el ajax*/
    $("#btnEliminar").click(function(){        
        if ($("#idAlumno").val()===""){
           alert("Debe primero hacer una consulta de un Alumno");
        }else{
            $("#modalEliminar").modal();
        }
    });
    //Botón si del Modal de Eliminar Alumno
    $("#btnSiModal").click(function(){
        eliminarAlumno();
    });  
    
    $("#btnListar").click(function(){
        listarAlumno();
    });  
    
    $("#txtAlu_dni").change(function(){
        existeAlumno();
    });
    
});
/**
 * Agrega un alumno. Envía todo el formulario
 * @returns true o false
 */
function agregarAlumno(){   
    $("#mensaje").html("");   
    $.ajax({ 
        // la URL para la petición
        url: '../ControllerAlumno', 
        // la información a enviar
        data:$("#frmAlumno").serialize(),
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
        success: function (resultado) {
            console.log(resultado);
            if (resultado){
                $("#mensaje").html("Alumno Agregado Correctamente"); 
                limpiar();
                listarAlumno();
            }else{
                $("#mensaje").html("Problemas al agregar al Alumno");
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
function consultarAlumno(){
    $("#mensaje").html("");
    $("#msj").show();
    var dni = $("#txtAlu_dni").val();
    limpiar();
    $("#txtAlu_dni").val(dni);
    var parametros = {
        accion:"Consultar",
        identificacion:$("#txtAlu_dni").val()
    };
    $.ajax({ 
        url: '../ControllerAlumno',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado);
            if (resultado!==null){   
                $("#idAlumno").val(resultado.alu_codigo);
                $("#txtAlu_apellidos").val(resultado.alu_apellidos);
                $("#txtAlu_nombre").val(resultado.alu_nombre);
                $("#txtAlu_direccion").val(resultado.alu_direccion);
                $("#txtAlu_telefono").val(resultado.alu_telefono);
                $("#txtAlu_dni").val(resultado.alu_dni);
                $("#txtAlu_fechaadmi").val(resultado.alu_fechaadmi);
                $("#cbAlu_sexo").val(resultado.alu_sexo);
                $("#txtApo_id").val(resultado.apo_codigo);
            }else{
                $("#mensaje").html("No Existe Alumno con ese DNI");
            }
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}

function consultarApoderado(){
    $("#mensaje").html("");
    $("#mensaje").show();
    var dni = $("#txtApo_dni").val();
   
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
                $("#txtApo_id").val(Aporesultado.apo_codigo);
                $("#txtApo_nombre").val(Aporesultado.apo_nombre);
                $("#txtApo_Apellido ").val(Aporesultado.apo_apellido);
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
function actualizarAlumno(){
    $("#accion").val("Actualizar");   
    $.ajax({       
        url: '../ControllerAlumno',      
        data:$("#frmAlumno").serialize(),
        dataType:'json',
        type: 'post',                     
        cache: false,
        success: function (resultado) {
            console.log(resultado);
            if (resultado){                
                listarAlumno();
                $("#mensaje").html("Alumno Actualizado Correctamente");
            }else{
                $("#mensaje").html("Problemas al actualizar el Alumno");
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
function eliminarAlumno(){
    var parametros={
        idAlumno:$("#idAlumno").val(),
        accion:"Eliminar"        
    }; 
    $.ajax({       
        url: '../ControllerAlumno',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado);
            if (resultado!==null){               
                limpiar();
                listarAlumno();
                 $("#mensaje").html("Alumno Eliminado Correctamente");
            }else{
                $("#mensaje").html("No se pudo eliminar el Alumno");
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
function listarAlumno(){   
    $("#tblAlumno").show();
    $("#mensaje").html("");
    //eliminar los datos de la tabla    
    $(".otraFila").remove();
    //agregar la primera fila vacía
    $("#tblAlumno tbody").append(primeraFilaAlumno);
    var parametros= {       
        accion:"Listar"      
    }; 
    $.ajax({       
        url: '../ControllerAlumno',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado); 
            var profesor = resultado;
            $.each(profesor, function (i, aprofesor) {
                $("#aId").html(aprofesor.alu_codigo);
                $("#aDni").html(aprofesor.alu_dni);
                $("#aNombre").html(aprofesor.alu_nombre);    
                $("#aApellido").html(aprofesor.alu_apellidos);
                $("#aDireccion").html(aprofesor.alu_direccion);
                $("#aTelefono").html(aprofesor.alu_telefono);    
                $("#aFecha").html(aprofesor.alu_fechaadmi);
                $("#aSexo").html(aprofesor.alu_sexo); 
                $("#aApoderado").html(aprofesor.apo_codigo);
                $("#tblAlumno tbody").append($("#fila").clone(true).attr("class","otraFila")); 
            });
            $("#tblAlumno tbody tr").first().remove(); 
            $("#tblAlumno").DataTable();
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}
function existeAlumno(){
    $("#mensaje").html("");
    var parametros={
        accion:"Existe",
        identificacion: $("#txtAlu_dni").val()
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
                //var nombreAlumno = resultado.nombres + " " + resultado.apellidos;
                $("#mensaje").html("El alumno ya esta registrado");
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
    $("#idAlumno").val("");
    $("#txtAlu_dni").val("");
    $("#txtAlu_apellidos").val("");
    $("#txtAlu_nombre").val("");
    $("#txtAlu_direccion").val("");    
    $("#txtAlu_telefono").val("");
    $("#txtAlu_fechaadmi").val("");    
    $("#cbAlu_sexo").val(""); 
    $("#txtApo_id").val(""); 
    
    $("#txtApo_dni").val("");    
    $("#txtApo_nombre").val(""); 
    $("#txtApo_Apellido").val("");
    $("#txtApo_parentesco").val("");
}

