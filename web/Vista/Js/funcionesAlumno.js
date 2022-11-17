//función de inicio Jquery
var primeraFilaEstudiantes; //variable global
$(function(){  
    $("#txtAlu_fechaadmi").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat:"yy/mm/dd",       
        maxDate:  "0D"
    });
    //almacenando la primera fila de la tabla
    primeraFilaEstudiantes = $("#fila"); 
    /**
     * Acción del evento click del botón
     * llamado btnConsultar
     */
    $("#btnApoderadoConsultar").click(function(){
        if ($("#txtApo_dni").val()!==""){
            consultarApoderado();
        }else{
            alert("Debe ingresar el dni del Apoderado");
            $("#txtApo_dni").focus();
        }
    });
    $("#btnConsultar").click(function(){
        if ($("#txtAlu_dni").val()!==""){          
            consultarAlumno();
        }else{
            alert("Debe ingresar el apellido del alumno");
            $("#txtAlu_dni").focus();
        }
    });
    /*El botón agregar se coloca como tipo submit para hacer las
    validaciones de los controles con html5*/
    $("#btnAgregar").click(function(){        
        agregarAlumno();  
    }); 
    $("#btnActualizar").click(function(){         
        if ($("#Alu_codigo").val()===""){
            alert("Debe primero Consultar un Alumno");   
            $("#txtAlu_apellidos").focus();
        }else{
            actualizarAlumno();
        } 
    });
    /*Primero se abre la ventana modal y a partir de ella se llama
    el método que hace el ajax*/
    $("#btnEliminar").click(function(){        
        if ($("#Alu_codigo").val()===""){
           alert("Debe primero hacer una consulta de un Alumno");
           $("#txtAlu_apellidos").focus();
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
    
    /*$("#txtIdentificacion").change(function(){
        existeAprendiz();
    });*/
    
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
                $("#mensaje").html("Problemas al agregar el alumno");
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
                $("#idApoderado").val(resultado.apo_codigo);
            }else{
                $("#mensaje").html("No Existe Alumno con ese apellido");
            }
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}


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
                $("#txtApo_Apellido").val(Aporesultado.apo_apellido);
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
                $("#mensaje").html("Problemas al actualizar el alumno");
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
        idAprendiz:$("#Alu_codigo").val(),
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
                 $("#mensaje").html("Alumno Eliminadio Correctamente");
            }else{
                $("#mensaje").html("No se pudo eliminar el alumno");
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
    $("#tblAlumno tbody").append(primeraFilaEstudiantes);
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
            var alumno = resultado;
            $.each(alumno, function (i, alumno) {
                $("#aApellidos").html(alumno.Alu_apellidos);
                $("#aNombre").html(alumno.Alu_nombre);
                $("#aDireccion").html(alumno.Alu_direccion);
                $("#aTelefono").html(alumno.Alu_telefono);
                $("#aDni").html(alumno.Alu_dni);
             
                $("#aFechaAdmision").html(alumno.Alu_fechaadmi);
                $("#aSexo").html(alumno.Alu_sexo);
            });
            $("#tblAlumno tbody tr").first().remove(); 
          //  $("#tblAprendices").DataTable();
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
    $("#txtAlu_apellidos").val("");
    $("#txtAlu_nombre").val("");
    $("#txtAlu_direccion").val("");
    $("#txtAlu_telefono").val("");    
    $("#txtAlu_dni").val(""); 
    $("#txtAlu_fechanaci").val(""); 
    $("#cbAlu_sexo").val(""); 
}

