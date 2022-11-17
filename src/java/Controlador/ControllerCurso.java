package Controlador;

import Datos.DatosCurso;
import Entidad.Curso;
import Entidad.Grado;
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

/**
 *
 * @author PERSONAL
 */
@WebServlet(name = "ControllerCurso", urlPatterns = {"/ControllerCurso"})
public class ControllerCurso extends HttpServlet {

    //Crear un objeto de tipo DatosCurso
    private DatosCurso dCurso = new DatosCurso();
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
            out.println("<title>Servlet ControllerCurso</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerCurso at " + request.getContextPath() + "</h1>");
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
     * Método que recibe de la vista los datos del curso en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        String cur_nombre = request.getParameter("txtCur_nombre");
        String cur_horas = request.getParameter("txtCur_horas");
        int gra_codigo = Integer.parseInt(request.getParameter("txtGrado_Gra_cod"));
        int prof_cod = Integer.parseInt(request.getParameter("txtProfesor_Prof_cod"));
        Grado unGrado = new Grado();
        unGrado.setGra_codigo(gra_codigo);
        Profesor unProfesor = new Profesor();
        unProfesor.setProf_cod(prof_cod);
        //se crea el objeto curso
        Curso unCurso = new Curso(cur_nombre, cur_horas, gra_codigo, prof_cod);
        //se agrega el curso utilizando el objeto dCurso
        boolean agregado = dCurso.agregarCurso(unCurso);
        //se crea una variable mensaje para enviar a la vista
        String mensaje = dCurso.getMensaje();
        //se redirecciona a la vista con una variable mensaje pasada por la URL
        response.sendRedirect(request.getContextPath()+"/Vista/frmCurso.jsp?mensaje=" + mensaje);           
    }
    /**
     * Método que recibe de la vista los datos del curso en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar2(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        String cur_nombre = request.getParameter("txtCur_nombre");
        String cur_horas = request.getParameter("txtCur_horas");
        int gra_codigo = Integer.parseInt(request.getParameter("txtGrado_Gra_cod"));
        int prof_cod = Integer.parseInt(request.getParameter("txtProfesor_Prof_cod"));
        Grado unGrado = new Grado();
        unGrado.setGra_codigo(gra_codigo);
        Profesor unProfesor = new Profesor();
        unProfesor.setProf_cod(prof_cod);
        Curso unCurso = new Curso(cur_nombre, cur_horas, gra_codigo, prof_cod);
        boolean agregado = dCurso.agregarCurso(unCurso);
        PrintWriter out = response.getWriter();
        String json = new Gson().toJson(agregado);
        out.print(json);       
    }
    /**
      * Método que recibe una petición de la vista para consultar un curso
      * dado su nombre
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException 
      */
    private void consultar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String cur_nombre = request.getParameter("identificacion");
        Curso unCurso = dCurso.obtenerCursoPorNombre(cur_nombre);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(unCurso);   
        out.print(json);
    }
    /**
     * Actualizar un curso.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int cur_codigo = Integer.parseInt(request.getParameter("idCurso"));
        String cur_nombre = request.getParameter("txtCur_nombre");
        String cur_horas = request.getParameter("txtCur_horas");
        int gra_codigo = Integer.parseInt(request.getParameter("txtGrado_Gra_cod"));
        int prof_cod = Integer.parseInt(request.getParameter("txtProfesor_Prof_cod"));
        Grado unGrado = new Grado();
        unGrado.setGra_codigo(gra_codigo);
        Profesor unProfesor = new Profesor();
        unProfesor.setProf_cod(prof_cod);
        Curso unCurso = new Curso(cur_nombre, cur_horas, gra_codigo, prof_cod);
        unCurso.setCur_codigo(cur_codigo);
        boolean actualizado = dCurso.actualizarCurso(unCurso);
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(actualizado);   
        out.print(json); 
    }
    /**
     * Elimina un curso dado su cur_codigo
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int cur_codigo = Integer.parseInt(request.getParameter("idCurso"));
        boolean eliminado = dCurso.eliminarCurso(cur_codigo);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(eliminado);   
        out.print(json);
    }
    /**
     * Método que recibe la petición de la vista para listar los cursos
     * que se encuentren en la base de datos. 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void listar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        ArrayList<Curso> lista = dCurso.listarCursos();
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
