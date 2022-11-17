<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylessheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="Js/funcionesCurso.js" type="text/javascript"></script>
    </head>
    <body>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <div class="container">
            <br><!-- comment -->
                <div class="alert alert-danger" role="alert">
                    <div id="mensaje" style="text-align: center">
                        <%
                            String mensaje = request.getParameter("mensaje");
                            if (mensaje != null) {
                                out.print(request.getParameter("mensaje"));
                            }
                        %>
                    </div>
                </div> 
            <form id="frmCurso">
                <input type="hidden" name="accion" id="accion" value="Agregar" />  
                <input type="hidden" name="idCurso" id="idCurso" value="" /> 
                <table style="width: 50%" border="0" align="center" class="table table-bordered">
                    <thead>
                        <tr class="bg-dark text-white">
                            <th  colspan="2" style="text-align: center"> REGISTRAR CURSOS </th>                      
                        </tr>
                    </thead>
                    <tbody>
                         <tr>
                            <td>Nombre del curso:</td>
                            <td>
                                <div class="form-group">
                                    <input type="text" name="txtCur_nombre" id="txtCur_nombre" class="form-control" required />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Cantidad de horas:</td>
                            <td>
                                <div class="form-group">
                                    <input type="text" name="txtCur_horas" id="txtCur_horas" class="form-control" required />
                                </div>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>Dni del profesor:</td>
                            <td>
                                <div class="form-group">
                                    <input type="text" name="txtProf_dni" id="txtProf_dni" class="form-control" required />
                                </div>
                            </td>
                        </tr>
                        <tr align="center">
                            <td colspan="2">
                                <input type="button" value="Buscar" id="btnConsultarProfesor" name="btnConsultarProfesor" class="btn btn-success" />
                            </td>                        
                        </tr> 
                        
                        <tr>
                            <td>Id Profesor:</td>
                            <td>
                                <div class="form-group">
                                    <input type="text" name="txtProfesor_Prof_cod" id="txtProfesor_Prof_cod" class="form-control" required readonly/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Nombre del Profesor:</td>
                            <td>
                                <div class="form-group">
                                    <input type="text" name="txtProf_nombre" id="txtProf_nombre" class="form-control" required readonly/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Apellido del Profesor:</td>
                            <td>
                                <div class="form-group">
                                    <input type="text" name="txtProf_apellido" id="txtProf_apellido" class="form-control" required readonly/>
                                </div>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>Id Grado:</td>
                            <td>
                                <div class="form-group">
                                    <input type="text" name="txtGrado_Gra_cod" id="txtGrado_Gra_cod" class="form-control" required />
                                 </div>
                            </td>
                        </tr>
                        <tr align="center">
                            <td colspan="2">
                                <input type="button" value="Listar Grados" id="btnListarGrados" name="btnListarGrados" class="btn btn-success" />
                            </td>                        
                        </tr> 
                          
                        <tr align="center">
                            <td colspan="2">
                                <input type="button" value="Limpiar" id="btnLimpiar" name="btnLimpiar" class="btn btn-success" />
                                <input type="button" value="Agregar" name="btnAgregar" id="btnAgregar" class="btn btn-success" />
                                <input type="button" value="Consultar" id="btnConsultar" name="btnConsultar" class="btn btn-success" />
                                <input type="button" value="Actualizar" name="btnActualizar" id="btnActualizar" class="btn btn-success" />
                            </td>                        
                        </tr> 
                        <tr align="center">
                            <td colspan="2">
                                <input type="button" value="Eliminar" id="btnEliminar" name="btnEliminar" class="btn btn-success" />
                                <input type="button" value="Listar" id="btnListar" name="btnListar" class="btn btn-success" />
                            </td>                        
                        </tr> 
                    </tbody>
                </table>   
            </form>
            
        </div>
    </body>
</html>

<!--  Ventana modal cuando se va a eliminar -->   
<!-- The Modal -->
<div class="modal" id="modalEliminar">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Encabezado -->
            <div class="modal-header bg-info text-white">
              <h4 class="modal-title">Eliminar Curso</h4>
              <button type="button" class="text-white close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal Contenido -->
            <div class="modal-body">
              ¿Está seguro de eliminar Curso?
            </div>
            <!-- Modal Pie Página -->
            <div class="modal-footer">
              <button type="button" class="btn btn-success" data-dismiss="modal" id="btnSiModal">Si</button>
              <button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>

<div id="msj" class="alert alert-info alert-dismissible" style="display: none">
    <button type="button" class="close" data-dismiss="alert">&times;</button> 
    <strong><div id="mensaje"></div></strong>
</div>

<br>
<table border="1" id="tblCurso" align="center" class="table table-bordered" style="width: 80%; display: none">
    <thead>
        <tr class="bg-dark text-white" style="text-align: center">
            <th>Id</th>
            <th>Nombre</th>
            <th>Horas</th>
            <th>Codigo Profesor</th>
            <th>Codigo Grado</th>
         </tr>
    </thead>
    <tbody>                    
        <tr id="fila" class="primeraFilaCurso">
            <td id="aId"></td>
            <td id="aNombre"></td>
            <td id="aHoras"></td>
            <td id="aCodProfesor"></td>
            <td id="aCodGrado"></td>
        </tr>
    </tbody>
</table>

<br>
<table border="1" id="tblGrado" align="center" class="table table-bordered" style="width: 80%; display: none">
    <thead>
        <tr class="bg-dark text-white" style="text-align: center">
            <th>Id</th>
            <th>Numero de aula</th>
            <th>Grado</th>
            <th>Seccion</th>
            <th>Nivel</th>
         </tr>
    </thead>
    <tbody>                    
        <tr id="filaa" class="primeraFilaGrado">
            <td id="aCod"></td>
            <td id="aAula"></td>
            <td id="aGrado"></td>
            <td id="aSeccion"></td>
            <td id="aNivel"></td>
        </tr>
    </tbody>
</table>
