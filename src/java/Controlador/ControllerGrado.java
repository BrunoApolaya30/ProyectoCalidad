package Controlador;

import Datos.DatosGrado;
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
@WebServlet(name = "ControllerGrado", urlPatterns = {"/ControllerGrado"})
public class ControllerGrado extends HttpServlet {

    //Crear un objeto de tipo DatosGrado
    private DatosGrado dGrado = new DatosGrado();
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
            out.println("<title>Servlet ControllerGrado</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerGrado at " + request.getContextPath() + "</h1>");
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
     * Método que recibe de la vista los datos del grado en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        String gra_grado = request.getParameter("cbGra_grado");
        String gra_seccion = request.getParameter("cbGra_seccion");
        String gra_nivel = request.getParameter("cbGra_nivel");
        String gra_naula = request.getParameter("txtGra_naula");
        //se crea el objeto grado
        Grado unGrado = new Grado(gra_grado, gra_seccion, gra_nivel,gra_naula);
        //se agrega el grado utilizando el objeto dGrado
        boolean agregado = dGrado.agregarGrado(unGrado);
        //se crea una variable mensaje para enviar a la vista
        String mensaje = dGrado.getMensaje();
        //se redirecciona a la vista con una variable mensaje pasada por la URL
        response.sendRedirect(request.getContextPath()+"/Vista/frmGrado.jsp?mensaje=" + mensaje);       
    }
    /**
     * Método que recibe de la vista los datos del grado en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar2(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        String gra_grado = request.getParameter("cbGra_grado");
        String gra_seccion = request.getParameter("cbGra_seccion");
        String gra_nivel = request.getParameter("cbGra_nivel");
        String gra_naula = request.getParameter("txtGra_naula");
        Grado unGrado = new Grado(gra_grado, gra_seccion, gra_nivel, gra_naula);
        boolean agregado = dGrado.agregarGrado(unGrado);
        PrintWriter out = response.getWriter();
        String json = new Gson().toJson(agregado);
        out.print(json);
    }
    /**
      * Método que recibe una petición de la vista para consultar un grado
      * dado su apellido
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException 
      */
    private void consultar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String gra_codigo =  request.getParameter("identificacion");
        Grado unGrado = dGrado.obtenerGradoPorAula(gra_codigo);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(unGrado);   
        out.print(json);   
    }
    /**
     * Actualizar un grado.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
     private void actualizar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int gra_codigo = Integer.parseInt(request.getParameter("idGrado"));
        String gra_grado = request.getParameter("cbGra_grado");
        String gra_seccion = request.getParameter("cbGra_seccion");
        String gra_nivel = request.getParameter("cbGra_nivel");
        String gra_naula = request.getParameter("txtGra_naula");
        Grado unGrado = new Grado(gra_grado, gra_seccion, gra_nivel, gra_naula);
        unGrado.setGra_codigo(gra_codigo);
        boolean actualizado = dGrado.actualizarGrado(unGrado);
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(actualizado);   
        out.print(json); 
     }
     /**
     * Elimina un grado dado su codigo
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
     private void eliminar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int gra_codigo = Integer.parseInt(request.getParameter("idGrado"));
        boolean eliminado = dGrado.eliminarGrado(gra_codigo);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(eliminado);   
        out.print(json);  
     }
     /**
     * Método que recibe la petición de la vista para listar los grados
     * que se encuentren en la base de datos. 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
     private void listar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        ArrayList<Grado> lista = dGrado.listarGrados();
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
