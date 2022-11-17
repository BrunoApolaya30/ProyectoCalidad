//función de inicio Jquery
var primeraFilaCurso; //variable global
var primeraFilaGrado;
$(function(){  
 
    //almacenando la primera fila de la tabla
    primeraFilaCurso = $("#fila"); 
    primeraFilaGrado = $("#filaa");
    /**
     * Acción del evento click del botón
     * llamado btnConsultar
     */
    $("#btnConsultar").click(function(){
        if ($("#txtCur_nombre").val()!==""){
              
            consultarCurso();
        }else{
            alert("Debe ingresar el noombre del curso");
            
        }
    });
    $("#btnConsultarProfesor").click(function(){
        if ($("#txtProf_dni").val()!==""){
              
            consultarProfesor();
        }else{
            alert("Debe ingresar el noombre del curso");
            
        }
    });
    /*El botón agregar se coloca como tipo submit para hacer las
    validaciones de los controles con html5*/
    $("#btnAgregar").click(function(){        
        
        if ($("#txtCur_nombre").val()!=="" && $("#txtCur_horas").val()!=="" && $("#txtProfesor_Prof_cod").val()!=="" && $("#txtGrado_Gra_cod").val()!==""){
              agregarCurso();  
        }else{
            alert("Debe ingresar datos");
            
        }
    }); 
    $("#btnActualizar").click(function(){         
        if ($("#idCurso").val()===""){
            alert("Debe primero Consultar una curso");   
        }else{
            actualizarCurso();
        } 
    });
    /*Primero se abre la ventana modal y a partir de ella se llama
    el método que hace el ajax*/
    $("#btnEliminar").click(function(){        
        if ($("#idCurso").val()===""){
           alert("Debe primero hacer una consulta curso");
        }else{
            $("#modalEliminar").modal();
        }
    });
    //Botón si del Modal de Eliminar Alumno
    $("#btnSiModal").click(function(){
        eliminarCurso();
    });  
    
    $("#btnListar").click(function(){
        listarCurso();
    }); 
    
    $("#btnListarGrados").click(function(){
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
function agregarCurso(){   
    $("#mensaje").html("");   
    $.ajax({ 
        // la URL para la petición
        url: '../ControllerCurso', 
        // la información a enviar
        data:$("#frmCurso").serialize(),
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
                $("#mensaje").html("Curso Agregado Correctamente"); 
                limpiar();
                listarCurso();
            }else{
                $("#mensaje").html("Problemas al agregar al Curso");
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
function consultarCurso(){
    $("#mensaje").html("");
    $("#msj").show();
    var curso = $("#txtCur_nombre").val();
    limpiar();
    $("#txtCur_nombre").val(curso);
    var parametros = {
        accion:"Consultar",
        identificacion:$("#txtCur_nombre").val()
    };
    $.ajax({ 
        url: '../ControllerCurso',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (Graresultado) {
            console.log(Graresultado);
            if (Graresultado!==null){   
                $("#idCurso").val(Graresultado.cur_codigo);
                $("#txtCur_nombre").val(Graresultado.cur_nombre);
                $("#txtCur_horas").val(Graresultado.cur_horas);
                $("#txtProfesor_Prof_cod").val(Graresultado.gra_codigo);
                $("#txtGrado_Gra_cod").val(Graresultado.prof_cod);
            }else{
                $("#mensaje").html("No Existe Curso en esa aula");
            }
        },
        error: function(ex){
          console.log(ex.responseText);
        }
    });
}

function consultarProfesor(){
    $("#mensaje").html("");
    $("#msj").show();
    var dni = $("#txtProf_dni").val();
    //limpiar();
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
                $("#txtProfesor_Prof_cod").val(Proresultado.prof_cod);
                $("#txtProf_nombre ").val(Proresultado.prof_nombre);
                $("#txtProf_apellido").val(Proresultado.prof_apellido);
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
function actualizarCurso(){
    $("#accion").val("Actualizar");   
    $.ajax({       
        url: '../ControllerCurso',      
        data:$("#frmCurso").serialize(),
        dataType:'json',
        type: 'post',                     
        cache: false,
        success: function (Graresultado) {
            console.log(Graresultado);
            if (Graresultado){                
                listarCurso();
                $("#mensaje").html("Curso Actualizado Correctamente");
            }else{
                $("#mensaje").html("Problemas al actualizar el Curso");
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
function eliminarCurso(){
    var parametros={
        idCurso:$("#idCurso").val(),
        accion:"Eliminar"        
    }; 
    $.ajax({       
        url: '../ControllerCurso',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (Graresultado) {
            console.log(Graresultado);
            if (Graresultado!==null){               
                limpiar();
                listarCurso();
                 $("#mensaje").html("Curso Eliminado Correctamente");
            }else{
                $("#mensaje").html("No se pudo eliminar el Curso");
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
function listarCurso(){   
    $("#tblCurso").show();
    $("#mensaje").html("");
    //eliminar los datos de la tabla    
    $(".otraFila").remove();
    //agregar la primera fila vacía
    $("#tblCurso tbody").append(primeraFilaCurso);
    var parametros= {       
        accion:"Listar"      
    }; 
    $.ajax({       
        url: '../ControllerCurso',      
        data:parametros,
        type: 'post', 
        dataType: 'json',  
        cache: false,
        success: function (resultado) {
            console.log(resultado); 
            var curso = resultado;
            $.each(curso, function (i, acurso) {
                $("#aId").html(acurso.cur_codigo);
                $("#aNombre").html(acurso.cur_nombre);
                $("#aHoras").html(acurso.cur_horas);    
                $("#aCodProfesor").html(acurso.prof_cod);
                $("#aCodGrado").html(acurso.gra_codigo);
                $("#tblCurso tbody").append($("#fila").clone(true).attr("class","otraFila"));             
            });
            $("#tblCurso tbody tr").first().remove(); 
            $("#tblCurso").DataTable();
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
                $("#tblGrado tbody").append($("#filaa").clone(true).attr("class","otraFila")); 
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
    $("#idCurso").val("");
    $("#txtCur_nombre").val("");
    $("#txtCur_horas").val("");
    $("#txtProfesor_Prof_cod").val("");
    $("#txtGrado_Gra_cod").val("");   
    
    $("#txtProf_dni").val("");
    $("#txtProf_nombre").val("");
    $("#txtProf_apellido").val("");
    
}

