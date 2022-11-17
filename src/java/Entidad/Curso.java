package Entidad;

public class Curso {
    private int cur_codigo;
    private String cur_nombre;
    private String cur_horas;
    private int gra_codigo;
    private int prof_cod;
    //private Grado gra_codigo;
    //private Porfesor prof_cod
     /**
     * Constructor
     * @param cur_nombre
     * @param cur_horas 
     * @param gra_codigo 
     * @param prof_cod
     */
    
    public Curso(String cur_nombre, String cur_horas, int gra_codigo, int prof_cod) {
        this.cur_nombre = cur_nombre;
        this.cur_horas = cur_horas ;
        this.gra_codigo = gra_codigo;
        this.prof_cod = prof_cod;
    }
    public Curso(){
        //this.gra_codigo = new Grado();
        //this.prof_cod = new Profesor();
    }
       
    public int getCur_codigo() {
        return cur_codigo;
    }

    public void setCur_codigo(int cur_codigo) {
        this.cur_codigo = cur_codigo;
    }

    public String getCur_nombre() {
        return cur_nombre;
    }

    public void setCur_nombre(String cur_nombre) {
        this.cur_nombre = cur_nombre;
    }

    public String getCur_horas() {
        return cur_horas;
    }

    public void setCur_horas(String cur_horas) {
        this.cur_horas = cur_horas;
    }

    /*public Grado getGra_codigo() {
        return gra_codigo;
    }

    public void setGra_codigo(Grado gra_codigo) {
        this.gra_codigo = gra_codigo;
    }

    public Profesor getProf_cod() {
        return prof_cod;
    }

    public void setProf_cod(Profesor prof_cod) {
        this.prof_cod = prof_cod;
    }*/

    public int getGra_codigo() {
        return gra_codigo;
    }

    public void setGra_codigo(int gra_codigo) {
        this.gra_codigo = gra_codigo;
    }

    public int getProf_cod() {
        return prof_cod;
    }

    public void setProf_cod(int prof_cod) {
        this.prof_cod = prof_cod;
    }
    
    

}
