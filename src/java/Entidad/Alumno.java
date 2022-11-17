
package Entidad;

public class Alumno {
   private int alu_codigo;
   private String alu_apellidos;
   private String alu_nombre;
   private String alu_direccion;
   private String alu_telefono;
   private String alu_dni;
   private String alu_fechaadmi;
   private String alu_sexo;
   private int apo_codigo;
   
          /**
     * Constructor
     * @param alu_apellidos
     * @param alu_nombre 
     * @param alu_direccion
     * @param alu_telefono
     * @param alu_dni
     * @param alu_fechaadmi
     * @param alu_sexo
     * @param apo_codigo
     */
   
    public Alumno(String alu_apellidos, String alu_nombre, String alu_direccion, String alu_telefono, String alu_dni, String alu_fechaadmi, String alu_sexo, int apo_codigo) {
        this.alu_apellidos = alu_apellidos;
        this.alu_nombre = alu_nombre;
        this.alu_direccion = alu_direccion;
        this.alu_telefono = alu_telefono;
        this.alu_dni = alu_dni;
        this.alu_fechaadmi = alu_fechaadmi;
        this.alu_sexo = alu_sexo;
        this.apo_codigo = apo_codigo;
    }

    public Alumno() {
        //this.apo_codigo = new Apoderado();
    }

    public int getAlu_codigo() {
        return alu_codigo;
    }

    public void setAlu_codigo(int alu_codigo) {
        this.alu_codigo = alu_codigo;
    }

    public String getAlu_apellidos() {
        return alu_apellidos;
    }

    public void setAlu_apellidos(String alu_apellidos) {
        this.alu_apellidos = alu_apellidos;
    }

    public String getAlu_nombre() {
        return alu_nombre;
    }

    public void setAlu_nombre(String alu_nombre) {
        this.alu_nombre = alu_nombre;
    }

    public String getAlu_direccion() {
        return alu_direccion;
    }

    public void setAlu_direccion(String alu_direccion) {
        this.alu_direccion = alu_direccion;
    }

    public String getAlu_telefono() {
        return alu_telefono;
    }

    public void setAlu_telefono(String alu_telefono) {
        this.alu_telefono = alu_telefono;
    }

    public String getAlu_dni() {
        return alu_dni;
    }

    public void setAlu_dni(String alu_dni) {
        this.alu_dni = alu_dni;
    }

    public String getAlu_fechaadmi() {
        return alu_fechaadmi;
    }

    public void setAlu_fechaadmi(String alu_fechaadmi) {
        this.alu_fechaadmi = alu_fechaadmi;
    }

    public String getAlu_sexo() {
        return alu_sexo;
    }

    public void setAlu_sexo(String alu_sexo) {
        this.alu_sexo = alu_sexo;
    }

   // public Apoderado getApo_codigo() {
   //     return apo_codigo;
   // }

   // public void setApo_codigo(Apoderado apo_codigo) {
   //     this.apo_codigo = apo_codigo;
   // }

    public int getApo_codigo() {
        return apo_codigo;
    }

    public void setApo_codigo(int apo_codigo) {
        this.apo_codigo = apo_codigo;
    }
    
    
    
    
   
   
}
