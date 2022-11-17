package Datos;

import Entidad.Apoderado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatosApoderado {
    private final Connection miConexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private String mensaje;
    
    /**
     * Constructor de la Clase DatosApoderado
     * Instancia el objeto miConexion
     */
    
     public DatosApoderado(){
        miConexion = Conexion.getConexion();
    }
     
    public String getMensaje() {
        return mensaje;
    }
    
    /**
     * Método que agrega un apoderado a la base de datos
     * @param unApoderado
     * @return true o false
     */
    
    public boolean agregarApoderado(Apoderado unApoderado){
        boolean agregado = false;
        String consulta="insert into apoderado values(null,?,?,?,?,?)";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1,unApoderado.getApo_nombre());
            ps.setString(2,unApoderado.getApo_apellido());
            ps.setString(3,unApoderado.getApo_celular());
            ps.setString(4,unApoderado.getApo_dni());
            ps.setString(5,unApoderado.getApo_parentesco());
            if(ps.executeUpdate()>0){
                agregado=true;
                mensaje="Apoderado agregado correctamente";
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return agregado;
    }
    
    /**
     * Obtener apoderado por apellido
     * @param apo_apellido
     * @return objeto de tipo Apoderado
     */
    
    public Apoderado obtenerApoderadoPorDni(String identificacion){
        Apoderado unApoderado=null;
        mensaje=null;
        String consulta="select * from apoderado where Apo_dni=?";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1,identificacion);
            //ps.setString(2,apo_apellido);
            rs = ps.executeQuery();
            if(rs.next()){
                unApoderado=new Apoderado();
                unApoderado.setApo_codigo(rs.getInt("Apo_codigo"));
                unApoderado.setApo_nombre(rs.getString("Apo_nombre"));
                unApoderado.setApo_apellido(rs.getString("Apo_Apellido"));
                unApoderado.setApo_celular(rs.getString("Apo_celular"));
                unApoderado.setApo_dni(rs.getString("Apo_dni"));
                unApoderado.setApo_parentesco(rs.getString("Apo_parentesco"));
            }else{
                mensaje="No existe apoderado con ese dni";
            }
            rs.close();   
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return unApoderado;
    }
    
    /**
     * Actualiza un apoderado de acuerdo a su codigo
     * @param unApoderado
     * @return true o false
     */ 
    
    public boolean actualizarApoderado(Apoderado unApoderado){
      mensaje=null;
      boolean actualizado=false;  
      String consulta=" update apoderado set Apo_nombre=?,Apo_Apellido=?," 
              + "Apo_celular=?,Apo_dni=?,Apo_parentesco=?" 
              + " where Apo_codigo=?";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, unApoderado.getApo_nombre());
            ps.setString(2, unApoderado.getApo_apellido());
            ps.setString(3, unApoderado.getApo_celular());
            ps.setString(4, unApoderado.getApo_dni());
            ps.setString(5, unApoderado.getApo_parentesco());
            ps.setInt(6, unApoderado.getApo_codigo());
            if (ps.executeUpdate()>0){
                actualizado=true;                
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        } 
         return actualizado;
    }
    
    /**
     * Elimina un apoderado de la base de datos de acuerdo
     * al codigo
     * @param idApoderado
     * @return true o false
     */
    
    public boolean eliminarApoderado(int idApoderado){
        mensaje=null;
        boolean eliminado=false;
        String consulta="delete from apoderado where Apo_codigo=?";
        try{
            ps = miConexion.prepareStatement(consulta); 
            ps.setInt(1, idApoderado);
            if (ps.executeUpdate()>0){
                eliminado=true;                
            }           
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        } 
        return eliminado;
    }
    
    /**
     * Obtener una lista de los apoderados existentes
     * en la base de datos
     * @return ArrayList
     */ 
    
    public ArrayList<Apoderado> listarApoderado(){
        ArrayList<Apoderado> lista=new ArrayList<>();
        mensaje=null;  
        String consulta="select * from apoderado";
        try{
            ps = miConexion.prepareStatement(consulta);  
            rs = ps.executeQuery();
            while(rs.next()){
                Apoderado unApoderado=new Apoderado();
                unApoderado.setApo_codigo(rs.getInt("Apo_codigo"));
                unApoderado.setApo_nombre(rs.getString("Apo_nombre"));
                unApoderado.setApo_apellido(rs.getString("Apo_Apellido"));
                unApoderado.setApo_celular(rs.getString("Apo_celular"));
                unApoderado.setApo_dni(rs.getString("Apo_dni"));
                unApoderado.setApo_parentesco(rs.getString("Apo_parentesco"));
                lista.add(unApoderado);
            }
            rs.close(); 
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return lista;
    }
    
}
