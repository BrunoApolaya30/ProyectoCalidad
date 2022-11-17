<%-- 
    Document   : pruebaConexion
    Created on : 17 oct. 2022, 00:05:54
    Author     : PERSONAL
--%>
<%@page import="Entidad.Alumno"%>
<%@page import="Datos.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
           
            String mensaje = "<br><b>Prueba de Conexión a la Base de Datos</b>";
            out.print(mensaje);
            Connection con = Conexion.getConexion();
            out.print("<br>" + Conexion.getMensaje());    
        %>
    </body>
</html>
