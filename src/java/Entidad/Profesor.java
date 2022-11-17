package Entidad;

public class Profesor {
    private int prof_cod;
    private String prof_nombre;
    private String prof_apellido;
    private String prof_dni;
    private String prof_telefono;
   
      /**
   * Constructor con parametros
   * @param prof_nombre
   * @param prof_apellido
   * @param prof_dni
   * @param prof_telefono
   */
    
    public Profesor(String prof_nombre, String prof_apellido, String prof_dni, String prof_telefono) {
        this.prof_nombre = prof_nombre;
        this.prof_apellido = prof_apellido;
        this.prof_dni = prof_dni;
        this.prof_telefono = prof_telefono;
    }

    public Profesor() {
    }

    public int getProf_cod() {
        return prof_cod;
    }

    public void setProf_cod(int prof_cod) {
        this.prof_cod = prof_cod;
    }

    public String getProf_nombre() {
        return prof_nombre;
    }

    public void setProf_nombre(String prof_nombre) {
        this.prof_nombre = prof_nombre;
    }

    public String getProf_apellido() {
        return prof_apellido;
    }

    public void setProf_apellido(String prof_apellido) {
        this.prof_apellido = prof_apellido;
    }

    public String getProf_dni() {
        return prof_dni;
    }

    public void setProf_dni(String prof_dni) {
        this.prof_dni = prof_dni;
    }

    public String getProf_telefono() {
        return prof_telefono;
    }

    public void setProf_telefono(String prof_telefono) {
        this.prof_telefono = prof_telefono;
    }
    
    
}
