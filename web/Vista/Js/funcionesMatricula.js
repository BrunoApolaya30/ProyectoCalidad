//función de inicio Jquery
var primeraFilaMatricula; //variable global
var primeraFilaGrado;
$(function(){  
 
    //almacenando la primera fila de la tabla
    primeraFilaMatricula = $("#fila"); 
    primeraFilaGrado = $("#fila1");
    /**
     * Acción del evento click del botón
     * llamado btnConsultar
     */
    $("#btnConsultar").click(function(){
        if ($("#txtAlu_dni").val()!==""){
              
            consultarMatricula();
        }else{
            alert("Debe ingresar el dni del alumno matriculado");
            
        }
    });
    
    $("#btnNuevo").click(function(){
        limpiar();
    });
    
    $("#btnConsultarAlumno").click(function(){
        if ($("#txtAlu_dni").val()!==""){
              
            consultarAlumno();
        }else{
            alert("Debe ingresar el dni del Alumno");
            
        }
    });
    /*El botón agregar se coloca como tipo submit para hacer las
    validaciones de los controles con html5*/
    $("#btnAgregar").click(function(){        
        
        if ($("#txtMat_nombre").val()!=="" && $("#txtMat_Apellido").val()!=="" && $("#txtGrado_Gra_codigo").val()!=="" && $("#txtAlumno_Alu_codigo").val()!==""){
              agregarMatricula();  
        }else{
            alert("Debe ingresar datos");
            
        }
    }); 
    $("#btnActualizar").click(function(){         
        if ($("#idMatricula").val()===""){
            alert("Debe primero Consultar a un Alumno");   
        }else{
            actualizarMatricula();
        } 
    });
    /*Primero se abre la ventana modal y a partir de ella se llama
    el método que hace el ajax*/
    $("#btnEliminar").click(function(){        
        if ($("#idMatricula").val()===""){
           alert("Debe primero hacer una consulta el dni de un alumno matricula");
        }else{
            $("#modalEliminar").modal();
        }
    });
    //Botón si del Modal de Eliminar Alumno
    $("#btnSiModal").click(function(){
        eliminarMatricula();
    });  
    
    $("#btnListar").click(function(){
        listarMatricula();
    });  
    
    $("#btnListarGrados").click(function(){
        listarGrado();
    }); 
    
    $("#txtAlu_dni").change(function(){
        existeMatricula();
    });
    
});
/**
 * Agrega un alumno. Envía todo el formulario
 * @returns true o false
 */
function agregarMatricula(){   
    $("#mensaje").html("");   
    $.ajax({ 
        // la URL para la petición
        url: '../ControllerMatricula', 
        // la información a enviar
        data:$("#frmMatricula").serialize(),
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
                $("#mensaje").html("Alumno Matricula Correctamente"); 
                limpiar();
                listarMatricula();
            }else{
                $("#mensaje").html("Problemas al matricular al Alumno");
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
function consultarMatricula(){
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
        url: '../ControllerMatricula',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado);
            if (resultado!==null){   
                $("#idMatricula").val(resultado.mat_codigo);
                $("#txtMat_nombre").val(resultado.mat_nombre);
                $("#txtMat_Apellido ").val(resultado.mat_apellido);
                $("#txtMat_fecha").val(resultado.mat_fecha);
                $("#txtGrado_Gra_codigo").val(resultado.gra_codigo);
                $("#txtAlumno_Alu_codigo ").val(resultado.alu_codigo);
            }else{
                $("#mensaje").html("No Existe Alumno con ese dni");
            }
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}
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
                $("#idMatricula").val(resultado.alu_codigo);
                $("#txtAlumno_Alu_codigo").val(resultado.alu_codigo);
                $("#txtMat_nombre").val(resultado.alu_nombre);
                $("#txtMat_Apellido").val(resultado.alu_apellidos);
            }else{
                $("#mensaje").html("No Existe Alumno con ese dni");
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
function actualizarMatricula(){
    $("#accion").val("Actualizar");   
    $.ajax({       
        url: '../ControllerMatricula',      
        data:$("#frmMatricula").serialize(),
        dataType:'json',
        type: 'post',                     
        cache: false,
        success: function (resultado) {
            console.log(resultado);
            if (resultado){                
                listarMatricula();
                $("#mensaje").html("Matricula Actualizado Correctamente");
            }else{
                $("#mensaje").html("Problemas al actualizar la matricula");
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
function eliminarMatricula(){
    var parametros={
        idMatricula:$("#idMatricula").val(),
        accion:"Eliminar"        
    }; 
    $.ajax({       
        url: '../ControllerMatricula',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado);
            if (resultado!==null){               
                limpiar();
                listarMatricula();
                 $("#mensaje").html("Matricula Eliminado Correctamente");
            }else{
                $("#mensaje").html("No se pudo eliminar la Matricula");
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
function listarMatricula(){   
    $("#tblMatricula").show();
    $("#mensaje").html("");
    //eliminar los datos de la tabla    
    $(".otraFila").remove();
    //agregar la primera fila vacía
    $("#tblMatricula tbody").append(primeraFilaMatricula);
    var parametros= {       
        accion:"Listar"      
    }; 
    $.ajax({       
        url: '../ControllerMatricula',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado); 
            var Matricula = resultado;
            $.each(Matricula, function (i, amatricula) {
                $("#aId").html(amatricula.mat_codigo);
                $("#aDni").html(amatricula.mat_dni);
                $("#aNombre").html(amatricula.mat_nombre);
                $("#aApellido").html(amatricula.mat_apellido); 
                $("#aFecha").html(amatricula.mat_fecha);
                $("#aCodigoAlumno").html(amatricula.alu_codigo);
                $("#aCodigoGrado").html(amatricula.gra_codigo);
                $("#tblMatricula tbody").append($("#fila").clone(true).attr("class","otraFila"));
            });
            $("#tblMatricula tbody tr").first().remove(); 
            $("#tblMatricula").DataTable();
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}

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
                $("#aCod").html(agrado.gra_codigo);
                $("#aAula").html(agrado.Gra_naula);
                $("#aGrado").html(agrado.gra_grado);    
                $("#aSeccion").html(agrado.gra_seccion);
                $("#aNivel").html(agrado.gra_nivel);
                $("#tblGrado tbody").append($("#fila1").clone(true).attr("class","otraFila")); 
            });
            $("#tblGrado tbody tr").first().remove(); 
            $("#tblGrado").DataTable();
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}
function existeMatricula(){
    $("#mensaje").html("");
    var parametros={
        accion:"Existe",
        identificacion: $("#txtAlu_dni").val()
    }; 
    $.ajax({       
        url: '../ControllerMatricula',      
        data:parametros,
        type: 'get', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado);
            if (resultado!==null){
                //var nombreAlumno = resultado.nombres + " " + resultado.apellidos;
              $("#mensaje").html("El alumno ya esta matriculado");
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
    
    $("#idMatricula").val("");
    $("#txtAlu_dni").val("");
    $("#txtMat_nombre").val("");
    $("#txtMat_Apellido").val("");
    $("#txtGrado_Gra_codigo").val("");    
    $("#txtAlumno_Alu_codigo").val("");
    $("#txtMat_fecha").val("");
}

