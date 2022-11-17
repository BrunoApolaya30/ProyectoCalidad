package Controlador;

import Datos.DatosAlumno;
import Datos.DatosApoderado;
import Entidad.Alumno;
import Entidad.Apoderado;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerAlumno", urlPatterns = {"/ControllerAlumno"})
public class ControllerAlumno extends HttpServlet {

    //Crear un objeto de tipo DatosAlumno
    private DatosAlumno dAlumno=new DatosAlumno();     
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerAlumno</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerAlumno at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        consultar(request,response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tarea = request.getParameter("accion"); 
        switch (tarea){
            case    "Agregar":      agregar2(request, response); 
                break;
            case    "Consultar":    consultar(request, response);
                break;   
            case    "Actualizar":   actualizar(request, response);
                break; 
            case    "Eliminar":     eliminar(request, response);
                break; 
            case    "Listar":       listar(request, response);
                break;
            case    "Existe":       consultar(request, response);
                break;
               
        }        
    }
    /**
     * Método que recibe de la vista los datos del alumno en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
     private void agregar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{    
         String alu_apellidos = request.getParameter("txtAlu_apellidos");
         String alu_nombre = request.getParameter("txtAlu_nombre");
         String alu_direccion = request.getParameter("txtAlu_direccion");
         String alu_telefono = request.getParameter("txtAlu_telefono");
         String alu_dni = request.getParameter("txtAlu_dni");
         String alu_fechaadmi = request.getParameter("txtAlu_fechaadmi");
         String alu_sexo = request.getParameter("cbAlu_sexo");
         int apo_codigo = Integer.parseInt(request.getParameter("txtApo_id"));
         Apoderado unApoderado = new Apoderado();
         unApoderado.setApo_codigo(apo_codigo);
         //se crea el objeto alumno
         Alumno unAlumno = new Alumno(alu_apellidos, alu_nombre, alu_direccion, alu_telefono, alu_dni, alu_fechaadmi, alu_sexo, apo_codigo);
        //se agrega el alumno utilizando el objeto dAlumno
        boolean agregado = dAlumno.agregarAlumno(unAlumno);
        //se crea una variable mensaje para enviar a la vista
        String mensaje = dAlumno.getMensaje();
        // se redirecciona a la vista con una variable mensaje pasada por la URL
        response.sendRedirect(request.getContextPath()+"/Vista/frmAlumno.jsp?mensaje=" + mensaje);                  
     }
      /**
     * Método que recibe de la vista los datos del alumno en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar2(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{       
        String alu_apellidos = request.getParameter("txtAlu_apellidos");
        String alu_nombre = request.getParameter("txtAlu_nombre");
        String alu_direccion = request.getParameter("txtAlu_direccion");
        String alu_telefono = request.getParameter("txtAlu_telefono");
        String alu_dni = request.getParameter("txtAlu_dni");
        String alu_fechaadmi = request.getParameter("txtAlu_fechaadmi");
        String alu_sexo = request.getParameter("cbAlu_sexo");
        int apo_codigo = Integer.parseInt(request.getParameter("txtApo_id"));
        Apoderado unApoderado = new Apoderado();
        unApoderado.setApo_codigo(apo_codigo);
        Alumno unAlumno = new Alumno(alu_apellidos, alu_nombre, alu_direccion, alu_telefono, alu_dni, alu_fechaadmi, alu_sexo, apo_codigo);
        boolean agregado = dAlumno.agregarAlumno(unAlumno);
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(agregado);   
        out.print(json);  
    }
     
    /**
      * Método que recibe una petición de la vista para consultar un alumno
      * dado su apellido
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException 
      */
    
    private void consultar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String alu_dni = request.getParameter("identificacion");
        Alumno unAlumno = dAlumno.obtenerAlumnoPorDni(alu_dni);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(unAlumno);   
        out.print(json);  
    }
    
    /**
     * Actualizar un alumno.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int alu_codigo = Integer.parseInt(request.getParameter("idAlumno"));
        String alu_apellidos = request.getParameter("txtAlu_apellidos");
        String alu_nombre = request.getParameter("txtAlu_nombre");
        String alu_direccion = request.getParameter("txtAlu_direccion");
        String alu_telefono = request.getParameter("txtAlu_telefono");
        String alu_dni = request.getParameter("txtAlu_dni");
        String alu_fechaadmi = request.getParameter("txtAlu_fechaadmi");
        String alu_sexo = request.getParameter("cbAlu_sexo");
        int apo_codigo = Integer.parseInt(request.getParameter("txtApo_id"));
        Apoderado unApoderado = new Apoderado();
        unApoderado.setApo_codigo(apo_codigo);
        Alumno unAlumno = new Alumno(alu_apellidos, alu_nombre, alu_direccion, alu_telefono, alu_dni, alu_fechaadmi, alu_sexo, apo_codigo);
        unAlumno.setAlu_codigo(alu_codigo);
        boolean actualizado = dAlumno.actualizarAlumno(unAlumno);
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(actualizado);   
        out.print(json);   
    }      
    /**
     * Elimina un alumno dado su alu_codigo
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
     private void eliminar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int alu_codigo=Integer.parseInt(request.getParameter("idAlumno"));
        boolean eliminado=dAlumno.eliminarAlumno(alu_codigo);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(eliminado);   
        out.print(json);   
     }
    
    /**
     * Método que recibe la petición de la vista para listar los alumnos
     * que se encuentren en la base de datos. 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
     
      private void listar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        ArrayList<Alumno> lista = dAlumno.listarAlumno();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(lista);   
        out.print(json);          
      }            
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
