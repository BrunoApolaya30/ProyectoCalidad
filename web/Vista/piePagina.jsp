<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    footer{
     width: 100%;
     background: #1C8C2D;
     color: #fff;
     font-family: 'Roboto Condensed', sans-serif;
 }
 
 .container-footer-all{
     width: 100%;
     max-width: 1200px;
     margin: auto;
     padding: 40px;
 }
 
 .container-body{
     display: flex;
     justify-content: space-between;
 }
 
 .colum1{
     max-width: 400px;
 }
 
 .colum1 img{
     width: 80%;
     height: auto;
 }
 
 .colum2{
     max-width: 400px;
 }
 
 .colum2 h1{
 
     font-size: 22px;
 }
 
 .row{
     margin-top: 3px;
     display: flex;
 }
 
 .row i{
     font-size: 25px;
     width: 30px;
     height: 15px;
 }
 
 .row label{
     margin-top: 10px;
     margin-left: 20px;
     color: #fff;
 }
 
 .colum3{
     max-width: 400px;
 }
 
 .colum3 h1{
     font-size: 22px;
 }
 
 .row2{
     margin-top: 15px;
     display: flex;
 }
 
 .row2 i{
     font-size: 25px;
     width: 40px;
     height: 30px;
 }
 
 .row2 img{
     width: 36px;
     height: 36px;
 }
 
 .row2 label{
     margin-top: 10px;
     margin-left: 20px;
     max-width: 140px;
 }
 
 .container-footer{
     width: 100%;  
     background: #1A7328;
 }
 
 .footer{
     max-width: 1200px;
     margin: auto;
     display: flex;
     justify-content: space-between;  
     padding: 30px;
 }
 
 .copyright{
     color: #fff;
 }
 
 .copyright a{
     text-decoration: none;
     color: white;
     font-weight: bold;
 }
 
 .information a{
     text-decoration: none;
     color: #fff;
 }
 
 
 @media screen and (max-width: 1100px){
     
     .container-body{
         flex-wrap: wrap;
     }
     
     .colum1{
         max-width: 100%;
     }
     
     .colum2,
     .colum3{
         margin-top: 40px;
     }
 }
 
     .pie a:-webkit-any-link {
     color: #fff;
     cursor: pointer;
     text-decoration: none;
    }
    
    .pie a:hover{
        color: #fff;
    }
</style>
    <footer class="pie">   
       <div class="container-footer-all">
        
            <div class="container-body">

                <div class="colum1">
                    <img src="https://i.ibb.co/4m3dBt2/Mi-proyecto.png">
                </div>

                <div class="colum2">

                    <h1>Nuestros Servicios</h1>

                    <div class="row">
                        <label>
                            <a href="index.jsp">Inicio</a>
                        </label>
                    </div>

                    <div class="row">
                        <label>
                            <a href="#">Gestionar Matricula</a>
                        </label>
                    </div>
                    
                    <div class="row">                        
                        <label>
                            <a href="#">Gestionar Alumnos</a>
                        </label>                        
                    </div>

                    <div class="row">
                        <label>
                            <a href="#">Gestionar Docentes</a>
                        </label>
                    </div>
                </div>

                <div class="colum3">

                    <h1>Informacion de Contactos</h1>

                    <div class="row2">
                        <a href="https://www.google.com/maps/place/Institución+Educativa+Divino+Maestro+de+Pro/@-11.9341967,-77.079248,17.96z/data=!4m5!3m4!1s0x9105d1c0253c972b:0x5f0086f0a30452c5!8m2!3d-11.9331507!4d-77.0788524?hl=es" target="_blank">
                        <i class="fa-solid fa-location-dot"></i></a>
                        <a href="https://www.google.com/maps/place/Institución+Educativa+Divino+Maestro+de+Pro/@-11.9341967,-77.079248,17.96z/data=!4m5!3m4!1s0x9105d1c0253c972b:0x5f0086f0a30452c5!8m2!3d-11.9331507!4d-77.0788524?hl=es" target="_blank">
                            Calle Sinceridad S/N Mz. R5 Lt. 5, 20, 21, 22 Urb., Los Olivos</a>  
                    </div>

                    <div class="row2">
                       <i class="fa-sharp fa-solid fa-phone"></i>
                            (01) 6375755      
                    </div>
                    
                    <div class="row2">
                        <a href="https://wa.me/51918258421?text=Quisiera+saber+sobre+el+proceso+de+matricula"><i class="fa-brands fa-whatsapp"></i></a>                        
                        <a href="https://wa.me/51918258421?text=Quisiera+saber+sobre+el+proceso+de+matricula">+51 918258421</a>
                    </div>
                    
                    <div class="row2">
                            <a href="https://www.facebook.com/DivinoMaestrodePro" target="_blank"><i class="fa-brands fa-square-facebook jgg"></i></a>
                            <a href="https://www.facebook.com/DivinoMaestrodePro" target="_blank">I.E.P. Divino Maestro de Pro</a>
                    </div>

                </div>

            </div>
        
        </div>
        
        <div class="container-footer">
            
               <div class="footer">
                    <div class="copyright">
                        © 2022 Todos los Derechos Reservados | Divino Maestro de Pro
                    </div>    
                </div>
            </div>       
</footer>  

