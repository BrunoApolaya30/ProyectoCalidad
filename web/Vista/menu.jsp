<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .menu{
        font-family: 'Roboto Condensed', sans-serif;
        list-style: none;
        padding: 0;
        background: #f2e205;
        margin: auto;
    }
    
    .menu li a{
        text-decoration: none;
        color: black;
        padding: 25px;
        display: block;
    }
    
    .menu li{
        display: inline-block;
        text-align: center;
    }
    
    .tpg{
        width: 350px;
        margin: 2px 50px;
    }
</style>
<header class="menu">
    <nav>
        
        <a href="index.jsp"><img class="tpg" src="https://i.ibb.co/SR3NxMj/tipografia.png"></a>
        
        <li>
            <a href="#" data-toggle="dropdown">Profesor</a>
            <div class="dropdown-menu">
                    <a class="dropdown-item" href="index.jsp?page=frmProfesor">Registrar Docente</a>                                
            </div>
        </li>
        <li>
            <a href="#" data-toggle="dropdown">Grado</a>
            <div class="dropdown-menu">
                    <a class="dropdown-item" href="index.jsp?page=frmGrado">Registrar Grado</a>                                
            </div>
        </li>
        <li>
            <a href="#" data-toggle="dropdown">Curso</a>
            <div class="dropdown-menu">
                    <a class="dropdown-item" href="index.jsp?page=frmCurso">Registar Curso</a>                                
            </div>
        </li>
        <li>
            <a href="#" data-toggle="dropdown">Apoderados</a>
            <div class="dropdown-menu">
                    <a class="dropdown-item" href="index.jsp?page=frmApoderado">Registrar Apoderado</a>                                
            </div>
        </li>
        <li>
            <a href="#" data-toggle="dropdown">Alumnos</a>
            <div class="dropdown-menu">
                    <a class="dropdown-item" href="index.jsp?page=frmAlumno">Registrar Alumno</a>                                
            </div>
        </li>
        <li class="nav-item dropdown">
            <a href="#" data-toggle="dropdown">Matricula</a>
            <div class="dropdown-menu">
                    <a class="dropdown-item" href="index.jsp?page=frmMatricula">Registrar Matricula</a>                                
            </div>
        </li>
    </nav>
</header>
