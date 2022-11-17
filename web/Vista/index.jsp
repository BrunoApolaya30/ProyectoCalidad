<%-- 
    Document   : index
    Created on : 17 oct. 2022, 12:56:22
    Author     : PERSONAL
--%>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="estilos.css">
    <script src="https://kit.fontawesome.com/296bc1c218.js" crossorigin="anonymous"></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String pagina = request.getParameter("page");    
    if (pagina==null){
        pagina="contenido.jsp";
    }else{
        pagina+=".jsp";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel='stylesheet' href='https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css'>
        <link rel='stylesheet' href='https://cdn.datatables.net/buttons/1.5.2/css/buttons.bootstrap4.min.css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        
        <script src='https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js'></script>
    <script src='https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js'></script>
    <script src='https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js'></script>
    <script src='https://cdn.datatables.net/buttons/1.5.2/js/buttons.bootstrap4.min.js'></script>
        
        <script src="Recursos/Librerias/jquery/jquery-ui.js" type="text/javascript"></script>
        <link href="Recursos/Librerias/jquery/jquery-ui.css" rel="stylesheet" type="text/css"/>
       
        
    <title>Sistema de Matricula</title>
    </head>
    <!-- Aquí todo el cuerpo del formulario -->
    <header><jsp:include page="menu.jsp"/></header>
    <body>
        <div class="container">
            <section><jsp:include page="<%=pagina%>"/></section>
        </div>
    </body>
    <footer> <jsp:include page="piePagina.jsp"/></footer>
</html>
