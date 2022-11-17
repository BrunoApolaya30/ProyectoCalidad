package Controlador;

import Datos.DatosApoderado;
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

/**
 *
 * @author PERSONAL
 */
@WebServlet(name = "ControllerApoderado", urlPatterns = {"/ControllerApoderado"})
public class ControllerApoderado extends HttpServlet {
    //Crear un objeto de tipo DatosApoderado
    private DatosApoderado dApoderado = new DatosApoderado();
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
            out.println("<title>Servlet ControllerApoderado</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerApoderado at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        String tarea = request.getParameter("accion");
        switch(tarea){
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
     * Método que recibe de la vista los datos del apoderado en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{    
        String apo_nombre = request.getParameter("txtApo_nombre");
        String apo_apellido = request.getParameter("txtApo_Apellido");
        String apo_celular = request.getParameter("txtApo_celular");
        String apo_dni = request.getParameter("txtApo_dni");
        String apo_parentesco = request.getParameter("txtApo_parentesco");
        //se crea el objeto apoderado
        Apoderado unApoderado = new Apoderado(apo_nombre, apo_apellido, apo_celular, apo_dni, apo_parentesco);
        //se agrega al apoderado utilizando el objeto dApoderado
        boolean agregado = dApoderado.agregarApoderado(unApoderado);
        //se crea una variable mensaje para enviar a la vista
        String mensaje = dApoderado.getMensaje();
        //se redirecciona a la vista con una variable mensaje pasada por la URL
        response.sendRedirect(request.getContextPath()+"/Vista/frmApoderado.jsp?mensaje=" + mensaje);
        }
    
    /**
     * Método que recibe de la vista los datos del apoderado en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar2(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        String apo_nombre = request.getParameter("txtApo_nombre");
        String apo_apellido = request.getParameter("txtApo_Apellido");
        String apo_celular = request.getParameter("txtApo_celular");
        String apo_dni = request.getParameter("txtApo_dni");
        String apo_parentesco = request.getParameter("txtApo_parentesco");
        //se crea el objeto apoderado
        Apoderado unApoderado = new Apoderado(apo_nombre, apo_apellido, apo_celular, apo_dni, apo_parentesco);
        boolean agregado = dApoderado.agregarApoderado(unApoderado);
        PrintWriter out = response.getWriter();
        String json = new Gson().toJson(agregado);
        out.print(json);      
    }
    /**
      * Método que recibe una petición de la vista para consultar un apoderado
      * dada su apellido
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException 
      */
    private void consultar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String apo_dni = request.getParameter("identificacion");
        Apoderado unApoderado = dApoderado.obtenerApoderadoPorDni(apo_dni);
        //response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(unApoderado);   
        out.print(json);  
    }
    /**
     * Actualizar un apoderado.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int apo_codigo = Integer.parseInt(request.getParameter("idApoderado"));
        String apo_nombre = request.getParameter("txtApo_nombre");
        String apo_apellido = request.getParameter("txtApo_Apellido");
        String apo_celular = request.getParameter("txtApo_celular");
        String apo_dni = request.getParameter("txtApo_dni");
        String apo_parentesco = request.getParameter("txtApo_parentesco");
        Apoderado unApoderado = new Apoderado(apo_nombre, apo_apellido, apo_celular, apo_dni, apo_parentesco);
        unApoderado.setApo_codigo(apo_codigo);
        boolean actualizado = dApoderado.actualizarApoderado(unApoderado);
        PrintWriter out = response.getWriter();
        String json = new Gson().toJson(actualizado);
        out.print(json);
    }
    /**
     * Elimina un aprendiz dado su codigo
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int apo_codigo = Integer.parseInt(request.getParameter("idApoderado"));
        boolean eliminado = dApoderado.eliminarApoderado(apo_codigo);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(eliminado);   
        out.print(json);    
    }
    /**
     * Método que recibe la petición de la vista para listar los apoderados
     * que se encuentren en la base de datos. 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void listar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        ArrayList<Apoderado> lista = dApoderado.listarApoderado();
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
