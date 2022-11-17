package Datos;

import Entidad.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatosProfesor {
    private final Connection miConexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private String mensaje;
    
    public DatosProfesor(){
        miConexion = Conexion.getConexion();
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    /**
     * Método que agrega un profesor a la base de datos
     * @param unProfesor
     * @return true o false
     */
    public boolean agregarProfesor(Profesor unProfesor){
        boolean agregado = false;
        String consulta="insert into profesor values(null,?,?,?,?)";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, unProfesor.getProf_nombre());
            ps.setString(2, unProfesor.getProf_apellido());
            ps.setString(3, unProfesor.getProf_dni());
            ps.setString(4, unProfesor.getProf_telefono());
            if(ps.executeUpdate()>0){
                agregado=true;
                mensaje="Profesor agregado correctamente";
            } 
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }   
        return agregado;
    }
    /**
     * Obtener profesor por apellido
     * @param prof_apellido 
     * @return objeto de tipo profesor
     */
    public Profesor obtenerProfesorPorDni(String identificacion){
        Profesor unProfesor=null;
        mensaje=null;
        String consulta="select * from profesor where Prof_dni=?";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, identificacion);
            //ps.setString(2, identificacion);
            rs = ps.executeQuery();
            if(rs.next()){
                unProfesor=new Profesor();
                unProfesor.setProf_cod(rs.getInt("Prof_cod"));
                unProfesor.setProf_nombre(rs.getString("Prof_nombre"));
                unProfesor.setProf_apellido(rs.getString("Prof_apellido"));
                unProfesor.setProf_dni(rs.getString("Prof_dni"));
                unProfesor.setProf_telefono(rs.getString("Prof_telefono"));
                           
            }else{
                mensaje="No existe profesor con ese DNI";
            }
            rs.close();
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return unProfesor;
    }
    /**
     * Actualiza un profesor de acuerdo a su prof_cod
     * @param unProfesor
     * @return true o false
     */
    public boolean actualizarProfesor(Profesor unProfesor){
        mensaje=null;
        boolean actualizado=false;
        String consulta="update profesor set Prof_nombre=?,Prof_apellido=?," 
                + "Prof_dni=?,Prof_telefono=? where Prof_cod=?";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, unProfesor.getProf_nombre());
            ps.setString(2, unProfesor.getProf_apellido());
            ps.setString(3, unProfesor.getProf_dni());
            ps.setString(4, unProfesor.getProf_telefono());
            ps.setInt(5, unProfesor.getProf_cod());
            if (ps.executeUpdate()>0){
                actualizado=true;                
            }            
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }  
        return actualizado;
    }
    /**
     * Elimina un profesor de la base de datos de acuerdo
     * al su codigo
     * @param idProfesor
     * @return true o false
     */
    public boolean eliminarProfesor(int idProfesor){
        mensaje=null;
        boolean eliminado=false;
        String consulta="delete from profesor where Prof_cod=?";
        try{
            ps = miConexion.prepareStatement(consulta);  
            ps.setInt(1, idProfesor);            
            if (ps.executeUpdate()>0){
                eliminado=true;                
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return eliminado;
    }
    /**
     * Obtener una lista de los profesores existentes
     * en la base de datos
     * @return ArrayList
     */
    public ArrayList<Profesor> listarProfesores(){
        ArrayList<Profesor> lista=new ArrayList<>();
        mensaje=null; 
        String consulta="select * from profesor";
        try{
            ps = miConexion.prepareStatement(consulta);           
            rs = ps.executeQuery();
            while(rs.next()){
                Profesor unProfesor=new Profesor();
                unProfesor.setProf_cod(rs.getInt("Prof_cod"));
                unProfesor.setProf_nombre(rs.getString("Prof_nombre"));
                unProfesor.setProf_apellido(rs.getString("Prof_apellido"));
                unProfesor.setProf_dni(rs.getString("Prof_dni"));
                unProfesor.setProf_telefono(rs.getString("Prof_telefono"));
                lista.add(unProfesor);
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return lista;
    }
    
    
}
