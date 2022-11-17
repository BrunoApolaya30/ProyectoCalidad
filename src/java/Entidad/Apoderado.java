
package Entidad;


public class Apoderado {
    private int apo_codigo;
    private String apo_nombre;
    private String apo_apellido;
    private String apo_celular;
    private String apo_dni;
    private String apo_parentesco;
    
     /**
     * Constructor
     * @param apo_nombre
     * @param apo_apellido 
     * @param apo_celular
     * @param apo_dni
     * @param apo_parentesco 
     */
    
    public Apoderado(String apo_nombre, String apo_apellido, String apo_celular,
    String apo_dni, String apo_parentesco) {
        this.apo_nombre = apo_nombre;
        this.apo_apellido = apo_apellido;
        this.apo_celular = apo_celular;
        this.apo_dni = apo_dni;
        this.apo_parentesco = apo_parentesco;
       
    }
    
    public Apoderado(){
            
    }

    public int getApo_codigo() {
        return apo_codigo;
    }

    public void setApo_codigo(int apo_codigo) {
        this.apo_codigo = apo_codigo;
    }

    public String getApo_nombre() {
        return apo_nombre;
    }

    public void setApo_nombre(String apo_nombre) {
        this.apo_nombre = apo_nombre;
    }

    public String getApo_apellido() {
        return apo_apellido;
    }

    public void setApo_apellido(String apo_apellido) {
        this.apo_apellido = apo_apellido;
    }

    public String getApo_celular() {
        return apo_celular;
    }

    public void setApo_celular(String apo_celular) {
        this.apo_celular = apo_celular;
    }

    public String getApo_dni() {
        return apo_dni;
    }

    public void setApo_dni(String apo_dni) {
        this.apo_dni = apo_dni;
    }

    public String getApo_parentesco() {
        return apo_parentesco;
    }

    public void setApo_parentesco(String apo_parentesco) {
        this.apo_parentesco = apo_parentesco;
    }

    
    
}
