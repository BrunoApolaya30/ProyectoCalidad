package Datos;

import Entidad.Matricula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatosMatricula {
    private final Connection miConexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private String mensaje;
    /**
     * Constructor de la Clase DatosAprendiz
     * Instancia el objeto miConexion
     */
    public DatosMatricula(){
        miConexion = Conexion.getConexion();
    }

    public String getMensaje() {
        return mensaje;
    }
    /**
     * Método que agrega una matricula a la base de datos
     * @param unaMatricula
     * @return true o false
     */
    public boolean agregarMatricula(Matricula unaMatricula){
        boolean agregado = false;        
        String consulta="insert into matricula values(null,?,?,?,?,?,?)";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, unaMatricula.getMat_dni());
            ps.setString(2, unaMatricula.getMat_nombre());
            ps.setString(3, unaMatricula.getMat_apellido());
            ps.setString(4, unaMatricula.getMat_fecha());
            ps.setInt(5, unaMatricula.getGra_codigo());
            ps.setInt(6, unaMatricula.getAlu_codigo());
            if(ps.executeUpdate()>0){
                agregado=true;
                mensaje="Matricula agregada correctamente";
            }   
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }        
        return agregado;
    }
    
    /**
     * Obtener matricula por codigo
     * @param mat_codigo 
     * @return objeto de tipo Matricula
     */
    public Matricula obtenerMatriculaPorDni(String identificacion){
        Matricula unaMatricula=null;
        mensaje=null;
        String consulta="select * from matricula where Mat_dni=?";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, identificacion);
            rs = ps.executeQuery();
            if(rs.next()){
                unaMatricula=new Matricula();
                unaMatricula.setMat_codigo(rs.getInt("Mat_codigo"));
                unaMatricula.setMat_dni(rs.getString("Mat_dni"));
                unaMatricula.setMat_nombre(rs.getString("Mat_nombre"));
                unaMatricula.setMat_apellido(rs.getString("Mat_apellido"));
                unaMatricula.setMat_fecha(rs.getString("Mat_fecha"));
                unaMatricula.setGra_codigo(rs.getInt("grado_Gra_codigo"));  
                unaMatricula.setAlu_codigo(rs.getInt("alumno_Alu_codigo"));
                           
            }else{
                mensaje="No existe matricula con ese codigo";
            }
            rs.close();
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return unaMatricula;
    }
    
    /**
     * Actualiza una matricula de acuerdo a su codigo
     * @param unaMatricula
     * @return true o false
     */
    public boolean actualizarMatricula(Matricula unaMatricula){
        mensaje=null;
        boolean actualizado=false;
        String consulta="update matricula set Mat_dni=?,Mat_nombre=?,Mat_apellido=?,Mat_fecha=?,grado_Gra_codigo=?,alumno_Alu_codigo=? " 
                + " where Mat_codigo=?";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, unaMatricula.getMat_dni());
            ps.setString(2, unaMatricula.getMat_nombre());
            ps.setString(3, unaMatricula.getMat_apellido());
            ps.setString(4, unaMatricula.getMat_fecha());
            ps.setInt(5, unaMatricula.getGra_codigo());
            ps.setInt(6, unaMatricula.getAlu_codigo());
            ps.setInt(7, unaMatricula.getMat_codigo());
            if (ps.executeUpdate()>0){
                actualizado=true;                
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }        
        return actualizado;
    }
    /**
     * Elimina una matricula de la base de datos de acuerdo
     * al codigo
     * @param idMatricula
     * @return true o false
     */
    public boolean eliminarMatricula(int idMatricula){
        mensaje=null;
        boolean eliminado=false;
        String consulta="delete from matricula where Mat_codigo=?";
        try{
            ps = miConexion.prepareStatement(consulta);            
            ps.setInt(1, idMatricula);            
            if (ps.executeUpdate()>0){
                eliminado=true;                
            }
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }        
        return eliminado;
    }
    
    /**
     * Obtener una lista de las matriculas existentes
     * en la base de datos
     * @return ArrayList
     */
    public ArrayList<Matricula> listarMatriculas(){
        ArrayList<Matricula> lista=new ArrayList<>();
        mensaje=null; 
        String consulta="select * from matricula";
        try{
            ps = miConexion.prepareStatement(consulta);           
            rs = ps.executeQuery();
            while(rs.next()){
                Matricula unaMatricula=new Matricula();
                unaMatricula.setMat_codigo(rs.getInt("Mat_codigo"));
                unaMatricula.setMat_dni(rs.getString("Mat_dni"));
                unaMatricula.setMat_nombre(rs.getString("Mat_nombre"));
                unaMatricula.setMat_apellido(rs.getString("Mat_apellido"));
                unaMatricula.setMat_fecha(rs.getString("Mat_fecha"));
                unaMatricula.setGra_codigo(rs.getInt("grado_Gra_codigo"));
                unaMatricula.setAlu_codigo(rs.getInt("alumno_Alu_codigo"));
                lista.add(unaMatricula);
            }
            rs.close();  
        }catch(SQLException ex){
            mensaje=ex.getMessage();
        }
        return lista;
    }
    
}
