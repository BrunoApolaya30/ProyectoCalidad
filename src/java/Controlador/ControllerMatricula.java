
package Controlador;

import Datos.DatosMatricula;
import Entidad.Matricula;
import Entidad.Alumno;
import Entidad.Grado;
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
@WebServlet(name = "ControllerMatricula", urlPatterns = {"/ControllerMatricula"})
public class ControllerMatricula extends HttpServlet {

    //Crear un objeto de tipo de DatosMatricula
    private DatosMatricula dMatricula = new DatosMatricula();
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
            out.println("<title>Servlet ControllerMatricula</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerMatricula at " + request.getContextPath() + "</h1>");
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
     * Método que recibe de la vista los datos de la matricula en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        String mat_dni = request.getParameter("txtMat_dni");
        String mat_fecha = request.getParameter("txtMat_fecha");
        String mat_nombre = request.getParameter("txtMat_nombre");
        String mat_Apellido = request.getParameter("txtMat_Apellido");
        int alu_codigo = Integer.parseInt(request.getParameter("txtAlumno_Alu_codigo"));
        int gra_codigo = Integer.parseInt(request.getParameter("txtGrado_Gra_codigo"));
        Alumno unAlumno = new Alumno();
        unAlumno.setAlu_codigo(alu_codigo);
        Grado unGrado = new Grado();
        unGrado.setGra_codigo(gra_codigo);
        //se crea el objeto alumno
        Matricula unaMatricula = new Matricula(mat_dni, mat_fecha, mat_nombre, mat_Apellido, gra_codigo, alu_codigo);
        //se agrega la matricula utilizando el objeto dMatricula
        boolean agregado = dMatricula.agregarMatricula(unaMatricula);
        //se crea una variable mensaje para enviar a la vista
        String mensaje = dMatricula.getMensaje();
        //se redirecciona a la vista con una variable mensaje pasada por la URL
        response.sendRedirect(request.getContextPath()+"/Vista/frmMatricula.jsp?mensaje=" + mensaje);                  

    }
    /**
     * Método que recibe de la vista los datos de la matricula en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar2(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        String mat_dni = request.getParameter("txtAlu_dni");
        String mat_nombre = request.getParameter("txtMat_nombre");
        String mat_apellido = request.getParameter("txtMat_Apellido");
        String mat_fecha = request.getParameter("txtMat_fecha");
        int alu_codigo = Integer.parseInt(request.getParameter("txtAlumno_Alu_codigo"));
        int gra_codigo = Integer.parseInt(request.getParameter("txtGrado_Gra_codigo"));
        Alumno unAlumno = new Alumno();
        unAlumno.setAlu_codigo(alu_codigo);
        Grado unGrado = new Grado();
        unGrado.setGra_codigo(gra_codigo);
        Matricula unaMatricula = new Matricula(mat_dni, mat_nombre, mat_apellido, mat_fecha, gra_codigo, alu_codigo);
        boolean agregado = dMatricula.agregarMatricula(unaMatricula);
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(agregado);   
        out.print(json);
    }
    /**
      * Método que recibe una petición de la vista para consultar una matricula
      * dado su codigo
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException 
      */
    private void consultar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String mat_dni = request.getParameter("identificacion");
        Matricula unaMatricula = dMatricula.obtenerMatriculaPorDni(mat_dni);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(unaMatricula);   
        out.print(json); 
    }
    /**
     * Actualizar una matricula.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int mat_codigo = Integer.parseInt(request.getParameter("idMatricula"));
        String mat_dni = request.getParameter("txtAlu_dni");
        String mat_nombre = request.getParameter("txtMat_nombre");
        String mat_apellido = request.getParameter("txtMat_Apellido");
        String mat_fecha = request.getParameter("txtMat_fecha");
        int alu_codigo = Integer.parseInt(request.getParameter("txtAlumno_Alu_codigo"));
        int gra_codigo = Integer.parseInt(request.getParameter("txtGrado_Gra_codigo"));
        Alumno unAlumno = new Alumno();
        unAlumno.setAlu_codigo(alu_codigo);
        Grado unGrado = new Grado();
        unGrado.setGra_codigo(gra_codigo);
        Matricula unaMatricula = new Matricula(mat_dni, mat_nombre, mat_apellido,mat_fecha, gra_codigo, alu_codigo);
        unaMatricula.setMat_codigo(mat_codigo);
        boolean actualizado = dMatricula.actualizarMatricula(unaMatricula);
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(actualizado);   
        out.print(json); 
    }
    /**
     * Elimina una matricula de acuerdo a su codigo
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int mat_codigo = Integer.parseInt(request.getParameter("idMatricula"));
        boolean eliminado = dMatricula.eliminarMatricula(mat_codigo);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(eliminado);   
        out.print(json);          
    }
    /**
     * Método que recibe la petición de la vista para listar las matriculas
     * que se encuentren en la base de datos. 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void listar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        ArrayList<Matricula> lista = dMatricula.listarMatriculas();
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
