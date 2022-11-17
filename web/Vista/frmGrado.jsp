<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylessheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="Js/funcionesGrado.js" type="text/javascript"></script>
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
            <br>
            <form id="frmGrado">
                <input type="hidden" name="accion" id="accion" value="Agregar" />  
                <input type="hidden" name="idGrado" id="idGrado" value="" /> 
                <table style="width: 50%" border="0" align="center" class="table table-bordered">
                    <thead>
                        <tr class="bg-dark text-white">
                            <th  colspan="2" style="text-align: center"> REGISTRAR GRADOS DISPONIBLES </th>                      
                        </tr>
                    </thead>
                    <tbody>
                        
                         <tr>
                            <td>Numero de aula:</td>
                            <td>
                                <div class="form-group">
                                    <input type="text" name="txtGra_naula" id="txtGra_naula" class="form-control" required />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Grado :</td>
                            <td>
                                <div class="form-group">
                                    <select name="cbGra_grado" id="cbGra_grado" class="form-control custom-select" required>
                                        <option value="Primero">Primero</option>
                                        <option value="Segundo">Segundo</option>
                                        <option value="Tercero">Tercero</option>
                                        <option value="Cuarto">Cuarto</option>
                                        <option value="Quinto">Quinto</option>
                                        <option value="Sexto">Sexto</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Seccion :</td>
                            <td>
                                <div class="form-group">
                                    <select name="cbGra_seccion" id="cbGra_seccion" class="form-control custom-select" required>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="D">D</option>
                                        <option value="E">E</option>
                                        <option value="F">F</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Nivel :</td>
                            <td>
                                <div class="form-group">
                                    <select name="cbGra_nivel" id="cbGra_nivel" class="form-control custom-select" required>
                                        <option value="Primaria">Primaria</option>
                                        <option value="Secundaria">Secundaria</option>
                                    </select>
                                </div>
                            </td>
                        </tr>            
                        <tr align="center">
                            <td colspan="2">
                                <input type="button" value="Nuevo" id="btnLimpiar" name="btnLimpiar" class="btn btn-success" />
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
              <h4 class="modal-title">Eliminar Grado</h4>
              <button type="button" class="text-white close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal Contenido -->
            <div class="modal-body">
              ¿Está seguro de eliminar el Grado?
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
        <tr id="fila" class="primeraFilaGrado">
            <td id="aId"></td>
            <td id="aAula"></td>
            <td id="aGrado"></td>
            <td id="aSeccion"></td>
            <td id="aNivel"></td>
        </tr>
    </tbody>
</table>