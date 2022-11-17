package Datos;

import Entidad.Alumno;
import Entidad.Apoderado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatosAlumno {
    private final Connection miConexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private String mensaje;
    
    /**
     * Constructor de la Clase DatosAlumno
     * Instancia el objeto miConexion
     */
    
     public DatosAlumno(){
        miConexion = Conexion.getConexion();
    }
     
    public String getMensaje() {
        return mensaje;
    }
    
    /**
     * Método que agrega un alumno a la base de datos
     * @param unAlumno
     * @return true o false
     */
    
     public boolean agregarAlumno(Alumno unAlumno){
        boolean agregado = false;
        String consulta="insert into alumno values(null,?,?,?,?,?,?,?,?)";
        try{
             ps = miConexion.prepareStatement(consulta);
             ps.setString(1, unAlumno.getAlu_dni());
             ps.setString(2, unAlumno.getAlu_apellidos());
             ps.setString(3, unAlumno.getAlu_nombre());
             ps.setString(4, unAlumno.getAlu_direccion());
             ps.setString(5, unAlumno.getAlu_telefono());
             ps.setString(6, unAlumno.getAlu_fechaadmi());
             ps.setString(7, unAlumno.getAlu_sexo());
             ps.setInt(8, unAlumno.getApo_codigo());
             if(ps.executeUpdate()>0){
                agregado=true;
                mensaje="Alumno agregado correctamente";
            }  
             
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
         return agregado;
    }
     
    /**
     * Obtener alumno por apellido
     * @param alu_dni 
     * @return objeto de tipo Alumno
     */
     
     /**String consulta="select Alu_codigo,Alu_apellidos,Alu_nombre,Alu_direccion,Alu_telefono,Alu_dni," 
                 + "Alu_fechaadmi,Alu_sexo,Apoderado_Apo_codigo,Apo_nombre,Apo_Apellido" 
                 + "from alumno" 
                 + "inner join apoderado" 
                 + "on alumno.Apoderado_Apo_codigo=apoderado.Apo_codigo" 
                 + "where alumno.Alu_apellidos=?";**/
    
     
    public Alumno obtenerAlumnoPorDni(String alu_dni){
         Alumno unAlumno=null;
         mensaje=null;
        
         String consulta="select * from alumno where Alu_dni=?";
         try{
             ps = miConexion.prepareStatement(consulta);
             ps.setString(1, alu_dni);
             rs = ps.executeQuery();
              if(rs.next()){
                  unAlumno= new Alumno();
                  unAlumno.setAlu_codigo(rs.getInt("Alu_codigo"));
                  unAlumno.setAlu_dni(rs.getString("Alu_dni"));
                  unAlumno.setAlu_apellidos(rs.getString("Alu_apellidos"));
                  unAlumno.setAlu_nombre(rs.getString("Alu_nombre"));
                  unAlumno.setAlu_direccion(rs.getString("Alu_direccion"));
                  unAlumno.setAlu_telefono(rs.getString("Alu_telefono"));
                  unAlumno.setAlu_fechaadmi(rs.getString("Alu_fechaadmi"));
                  unAlumno.setAlu_sexo(rs.getString("Alu_sexo"));
                  unAlumno.setApo_codigo(rs.getInt("apoderado_Apo_codigo"));
                  
              }else{
                mensaje="No existe alumno con ese Apellido";
            }
            rs.close();   
         }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
         return unAlumno;
     }
    
    

     
    /**
     * Actualiza un alumnio de acuerdo a su codigo
     * @param unAlumno
     * @return true o false
     */ 
     
    public boolean  actualizarAlumno(Alumno unAlumno){
        mensaje=null;
        boolean actualizado=false;
        String consulta=" update alumno set Alu_dni=?,Alu_apellidos=?,Alu_nombre=?,Alu_direccion=?,Alu_telefono=?,Alu_fechaadmi=?,Alu_sexo=?,apoderado_Apo_codigo=? where Alu_codigo=?";
        try{
             ps = miConexion.prepareStatement(consulta);
             ps.setString(1, unAlumno.getAlu_dni());
             ps.setString(2,unAlumno.getAlu_apellidos());
             ps.setString(3,unAlumno.getAlu_nombre());
             ps.setString(4, unAlumno.getAlu_direccion());
             ps.setString(5, unAlumno.getAlu_telefono());
             ps.setString(6, unAlumno.getAlu_fechaadmi());
             ps.setString(7, unAlumno.getAlu_sexo());
             ps.setInt(8, unAlumno.getApo_codigo());
             ps.setInt(9, unAlumno.getAlu_codigo());
             if (ps.executeUpdate()>0){
                actualizado=true;                
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }        
        return actualizado;            
     }
     
    /**
     * Elimina un alumno de la base de datos de acuerdo
     * al codigo
     * @param idAlumno
     * @return true o false
     */
     
     public boolean eliminarAlumno(int idAlumno){
        mensaje=null;
        boolean eliminado=false;
        String consulta="delete from alumno where Alu_codigo=?";
        try{
            ps = miConexion.prepareStatement(consulta);            
            ps.setInt(1, idAlumno);            
            if (ps.executeUpdate()>0){
                eliminado=true;                
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }        
        return eliminado;
    }
     
    /**
     * Obtener una lista de los alumnos existentes
     * en la base de datos
     * @return ArrayList
     */ 
     
    public ArrayList<Alumno> listarAlumno(){
        ArrayList<Alumno> lista=new ArrayList<>();
        mensaje=null;  
        String consulta="select * from alumno";
        try{
             ps = miConexion.prepareStatement(consulta);  
             rs = ps.executeQuery();
              while(rs.next()){
                  Alumno unAlumno=new Alumno();
                  unAlumno.setAlu_codigo(rs.getInt("Alu_codigo"));
                  unAlumno.setAlu_apellidos(rs.getString("Alu_apellidos"));
                  unAlumno.setAlu_nombre(rs.getString("Alu_nombre"));
                  unAlumno.setAlu_direccion(rs.getString("Alu_direccion"));
                  unAlumno.setAlu_telefono(rs.getString("Alu_telefono"));
                  unAlumno.setAlu_dni(rs.getString("Alu_dni"));
                  unAlumno.setAlu_fechaadmi(rs.getString("Alu_fechaadmi"));
                  unAlumno.setAlu_sexo(rs.getString("Alu_sexo"));
                  unAlumno.setApo_codigo(rs.getInt("apoderado_Apo_codigo"));
                  lista.add(unAlumno);                                                                   
              }
              rs.close(); 
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return lista;
     }
         
    
}
