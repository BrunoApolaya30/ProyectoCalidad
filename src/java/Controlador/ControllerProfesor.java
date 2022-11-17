package Controlador;

import Datos.DatosProfesor;
import Entidad.Profesor;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerProfesor", urlPatterns = {"/ControllerProfesor"})
public class ControllerProfesor extends HttpServlet {
    
    //Crear un objeto de tipo DatosProfesor
    private DatosProfesor dProfesor = new DatosProfesor();
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
            out.println("<title>Servlet ControllerProfesor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerProfesor at " + request.getContextPath() + "</h1>");
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
        consultar(request, response);
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
     * Método que recibe de la vista los datos del profesor en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        String prof_nombre = request.getParameter("txtProf_nombre");
        String prof_apellido = request.getParameter("txtProf_apellido");
        String prof_dni = request.getParameter("txtProf_dni");
        String prof_telefono = request.getParameter("txtProf_telefono");
        //se crea el objeto profesor
        Profesor unProfesor = new Profesor(prof_nombre, prof_apellido, prof_dni, prof_telefono);
        //se agrega el profesor utilizando el objeto dProfesor
        boolean agregado = dProfesor.agregarProfesor(unProfesor);
        //se crea una variable mensaje para enviar a la vista
        String mensaje = dProfesor.getMensaje();
        //se redirecciona a la vista con una variable mensaje pasada por la URL
        response.sendRedirect(request.getContextPath()+"/Vista/frmProfesor.jsp?mensaje=" + mensaje);                               
    }
    /**
     * Método que recibe de la vista los datos del profesor en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar2(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        String prof_nombre = request.getParameter("txtProf_nombre");
        String prof_apellido = request.getParameter("txtProf_apellido");
        String prof_dni = request.getParameter("txtProf_dni");
        String prof_telefono = request.getParameter("txtProf_telefono");
        Profesor unProfesor = new Profesor(prof_nombre, prof_apellido, prof_dni, prof_telefono);
        boolean agregado = dProfesor.agregarProfesor(unProfesor);
        PrintWriter out = response.getWriter();
        String json = new Gson().toJson(agregado);
        out.print(json);
    }
    /**
      * Método que recibe una petición de la vista para consultar un profesor
      * dado su apellido
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException 
      */
    private void consultar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String prof_dni = request.getParameter("identificacion");
        Profesor unProfesor = dProfesor.obtenerProfesorPorDni(prof_dni);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(unProfesor);   
        out.print(json);   
    }
    /**
     * Actualizar un profesor.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int prof_cod = Integer.parseInt(request.getParameter("idProfesor"));
        String prof_nombre = request.getParameter("txtProf_nombre");
        String prof_apellido = request.getParameter("txtProf_apellido");
        String prof_dni = request.getParameter("txtProf_dni");
        String prof_telefono = request.getParameter("txtProf_telefono");
        Profesor unProfesor = new Profesor(prof_nombre, prof_apellido, prof_dni, prof_telefono);
        unProfesor.setProf_cod(prof_cod);
        boolean actualizado = dProfesor.actualizarProfesor(unProfesor);
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(actualizado);   
        out.print(json);  
    }
    /**
     * Elimina un profesor dado su codigo
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int prof_cod = Integer.parseInt(request.getParameter("idProfesor"));
        boolean eliminado = dProfesor.eliminarProfesor(prof_cod);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(eliminado);   
        out.print(json);       
    }
    /**
     * Método que recibe la petición de la vista para listar los profesores
     * que se encuentren en la base de datos. 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void listar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        ArrayList<Profesor> lista = dProfesor.listarProfesores();
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
