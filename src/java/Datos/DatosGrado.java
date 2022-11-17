package Datos;

import Entidad.Grado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatosGrado {
    private final Connection miConexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private String mensaje;
    
    public DatosGrado(){
        miConexion = Conexion.getConexion();
    }

    public String getMensaje() {
        return mensaje;
    }
    /**
     * Método que agrega un grado a la base de datos
     * @param unGrado
     * @return true o false
     */
    public boolean agregarGrado(Grado unGrado){
        boolean agregado = false; 
        String consulta="insert into grado values(null,?,?,?,?)";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, unGrado.getGra_grado());
            ps.setString(2, unGrado.getGra_seccion());
            ps.setString(3, unGrado.getGra_nivel());
            ps.setString(4, unGrado.getGra_naula());
            if(ps.executeUpdate()>0){
                agregado=true;
                mensaje="Grado agregado correctamente";
            }  
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }     
        return agregado;
    }
    /**
     * Obtener grado por codigo
     * @param identificacion 
     * @return objeto de tipo grado
     */
    public Grado obtenerGradoPorAula(String identificacion){
        Grado unGrado=null;
        mensaje=null;
        String consulta="select * from grado where Gra_naula=?";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, identificacion);
            rs = ps.executeQuery();
            if(rs.next()){
                unGrado=new Grado();
                unGrado.setGra_codigo(rs.getInt("Gra_codigo"));
                unGrado.setGra_grado(rs.getString("Gra_grado"));
                unGrado.setGra_seccion(rs.getString("Gra_seccion"));
                unGrado.setGra_nivel(rs.getString("Gra_nivel"));
                unGrado.setGra_naula(rs.getString("Gra_naula"));
            }else{
                mensaje="No existe grado con ese codigo";
            }
            rs.close(); 
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return unGrado;
    }
    /**
     * Actualiza un grado de acuerdo a su codigo
     * @param unGrado
     * @return true o false
     */
    public boolean actualizarGrado(Grado unGrado){
        mensaje=null;
        boolean actualizado=false;
        String consulta="update grado set Gra_grado=?,Gra_seccion=?," 
                + "Gra_nivel=?, Gra_naula=?" 
                + "where Gra_codigo=?";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, unGrado.getGra_grado());
            ps.setString(2, unGrado.getGra_seccion());
            ps.setString(3, unGrado.getGra_nivel());
            ps.setString(4, unGrado.getGra_naula());
            ps.setInt(5, unGrado.getGra_codigo());
            if (ps.executeUpdate()>0){
                actualizado=true;                
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }  
        return actualizado;
    }
    /**
     * Elimina un grado de la base de datos de acuerdo
     * al codigo
     * @param idGrado
     * @return true o false
     */
    public boolean eliminarGrado(int idGrado){
        mensaje=null;
        boolean eliminado=false;
        String consulta="delete from grado where Gra_codigo=?";
        try{
            ps = miConexion.prepareStatement(consulta);            
            ps.setInt(1, idGrado);            
            if (ps.executeUpdate()>0){
                eliminado=true;                
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }        
        return eliminado;
    }
    /**
     * Obtener una lista de los grados existentes
     * en la base de datos
     * @return ArrayList
     */
    public ArrayList<Grado> listarGrados(){
        ArrayList<Grado> lista=new ArrayList<>();
        mensaje=null;
        String consulta="select * from grado";
        try{
            ps = miConexion.prepareStatement(consulta);           
            rs = ps.executeQuery();
            while(rs.next()){
               Grado unGrado=new Grado();
               unGrado.setGra_codigo(rs.getInt("Gra_codigo"));
               unGrado.setGra_grado(rs.getString("Gra_grado"));
               unGrado.setGra_seccion(rs.getString("Gra_seccion"));
               unGrado.setGra_nivel(rs.getString("Gra_nivel"));
               unGrado.setGra_naula(rs.getString("Gra_naula"));
               lista.add(unGrado);
            }
            rs.close(); 
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return lista;
    }
    
}
