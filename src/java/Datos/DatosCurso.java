package Datos;

import Entidad.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class DatosCurso {
    private final Connection miConexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private String mensaje;
    
    /**
     * Constructor de la Clase DatosAlumno
     * Instancia el objeto miConexion
     */
    
    public DatosCurso(){
        miConexion = Conexion.getConexion();
    }
     
    public String getMensaje() {
        return mensaje;
    }
    
    /**
     * Agregar Curso a la Base de Datos
     * @param unCurso
     * @return  true o false
     */
    public boolean agregarCurso(Curso unCurso){
        boolean agregado = false;
        String consulta="insert into curso values(null,?,?,?,?)";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, unCurso.getCur_nombre());
            ps.setString(2, unCurso.getCur_horas());
            ps.setInt(3, unCurso.getProf_cod());
            ps.setInt(4,unCurso.getGra_codigo());
            if(ps.executeUpdate()>0){
                agregado=true;
                mensaje="Curso agregado correctamente";
            }  
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return agregado;
    }     
    /**
     * Obtener curso por nombre
     * @param cur_nombre
     * @return objeto de tipo Curso
     */
    public Curso obtenerCursoPorNombre(String cur_nombre){
        Curso unCurso=null;
        mensaje=null;
        String consulta="select * from curso where Cur_nombre=?";
        try{
             ps = miConexion.prepareStatement(consulta);
             ps.setString(1, cur_nombre);
             rs = ps.executeQuery();
             if(rs.next()){
                 unCurso=new Curso();
                 unCurso.setCur_codigo(rs.getInt("Cur_codigo"));
                 unCurso.setCur_nombre(rs.getString("Cur_nombre"));
                 unCurso.setCur_horas(rs.getString("Cur_horas"));
                 unCurso.setGra_codigo(rs.getInt("grado_Gra_codigo"));
                 unCurso.setProf_cod(rs.getInt("profesor_Prof_cod"));            
             }else{
                mensaje="No existe curso con ese nombre";
            }
            rs.close();   
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return unCurso;
    }    
    /**
     * Actualiza un curso de acuerdo a su codigo
     * @param unCurso
     * @return true o false
     */ 
    public boolean actualizarCurso(Curso unCurso){
        mensaje=null;
        boolean actualizado=false;
        String consulta="update curso set Cur_nombre=?,Cur_horas=?,grado_Gra_codigo=?,profesor_Prof_cod=? where Cur_codigo=?";
        try{
            ps = miConexion.prepareStatement(consulta);
             ps.setString(1, unCurso.getCur_nombre());
            ps.setString(2, unCurso.getCur_horas());
            ps.setInt(3, unCurso.getGra_codigo());
            ps.setInt(4, unCurso.getProf_cod());
            ps.setInt(5, unCurso.getCur_codigo());
            if (ps.executeUpdate()>0){
                actualizado=true;                
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        } 
        return actualizado; 
    }
    
    /**
     * Elimina un curso de la base de datos de acuerdo
     * al codigo
     * @param idCurso
     * @return true o false
     */
    public boolean eliminarCurso(int idCurso){
        mensaje=null;
        boolean eliminado=false;
        String consulta="delete from curso where Cur_codigo=?";
        try{
            ps = miConexion.prepareStatement(consulta); 
            ps.setInt(1, idCurso);            
            if (ps.executeUpdate()>0){
                eliminado=true;                
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }  
        return eliminado;
    }   
     /**
     * Obtener un listado de Cursos
     * @return Lista de objetos de tipo Curso
     */
     public ArrayList<Curso> listarCursos(){
        ArrayList<Curso> lista=new ArrayList<>();
        mensaje=null;  
        String consulta="select * from curso";
        try{
            ps = miConexion.prepareStatement(consulta);           
            rs = ps.executeQuery();
            while(rs.next()){
                Curso unCurso=new Curso();
                unCurso.setCur_codigo(rs.getInt("Cur_codigo"));
                unCurso.setCur_nombre(rs.getString("Cur_nombre"));
                unCurso.setCur_horas(rs.getString("Cur_horas"));
                unCurso.setGra_codigo(rs.getInt("grado_Gra_codigo"));
                unCurso.setProf_cod(rs.getInt("profesor_Prof_cod"));
                lista.add(unCurso);
            }
            rs.close();
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }   
        return lista;
     }
    
    
}
