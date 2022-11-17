<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylessheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script >
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="Js/funcionesProfesor.js" type="text/javascript"></script>
    </head>
    <body>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <div class="container">
            <br>
            <form id="frmProfesor">
                <input type="hidden" name="accion" id="accion" value="Agregar" />  
                <input type="hidden" name="idProfesor" id="idProfesor" value="" /> 
                <table style="width: 50%" border="0" align="center" class="table table-bordered">
                    <thead>
                        <tr class="bg-dark text-white">
                            <th  colspan="2" style="text-align: center"> LISTA DE PROFESORES </th>                      
                        </tr>
                    </thead>
                    <tbody>
                        <tr align="center">
                            <td colspan="2">
                                <input type="button" value="Cargar" id="btnListar" name="btnListar" class="btn btn-success" />
                            </td>                        
                        </tr>    
                    </tbody>
                </table>   
            </form>
            <br><!-- comment -->
            <div id="mensaje" style="text-align: center">
                <%
                    String mensaje = request.getParameter("mensaje");
                    if(mensaje!=null){
                        out.print(request.getParameter("mensaje"));
                    }
                %>
            </div>
        </div>
    </body>
</html>



<div id="msj" class="alert alert-info alert-dismissible" style="display: none">
    <button type="button" class="close" data-dismiss="alert">&times;</button> 
    <strong><div id="mensaje"></div></strong>
</div>

<br>
<table border="1" id="tblProfesor" align="center" class="table table-bordered" style="width: 80%; display: none">
    <thead>
        <tr class="bg-dark text-white" style="text-align: center">
            <th>Id</th>
            <th>DNI</th>
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>Celular</th>
         </tr>
    </thead>
    <tbody>                    
        <tr id="fila" class="primeraFilaProfesor">
            <td id="aId"></td>
            <td id="aDni"></td>
            <td id="aNombre"></td>
            <td id="aApellido"></td>
            <td id="aCelular"></td>
        </tr>
    </tbody>
</table>