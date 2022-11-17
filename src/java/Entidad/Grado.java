package Entidad;


public class Grado {
    private int gra_codigo;
    private String gra_grado;
    private String gra_seccion;
    private String gra_nivel;
    private String Gra_naula;
    
    /**
     * Constructor
     * @param gra_grado
     * @param gra_seccion
     * @param gra_nivel
     * @param gra_naula
     */
    
    public Grado(String gra_grado, String gra_seccion, String gra_nivel, String gra_naula) {
        this.gra_grado = gra_grado;
        this.gra_seccion = gra_seccion;
        this.gra_nivel = gra_nivel;
        this.Gra_naula = gra_naula;
    }
    
    public Grado(){
            
    }

    public int getGra_codigo() {
        return gra_codigo;
    }

    public void setGra_codigo(int gra_codigo) {
        this.gra_codigo = gra_codigo;
    }

    public String getGra_grado() {
        return gra_grado;
    }

    public void setGra_grado(String gra_grado) {
        this.gra_grado = gra_grado;
    }

    public String getGra_seccion() {
        return gra_seccion;
    }

    public void setGra_seccion(String gra_seccion) {
        this.gra_seccion = gra_seccion;
    }

    public String getGra_nivel() {
        return gra_nivel;
    }

    public void setGra_nivel(String gra_nivel) {
        this.gra_nivel = gra_nivel;
    }

    public String getGra_naula() {
        return Gra_naula;
    }

    public void setGra_naula(String Gra_naula) {
        this.Gra_naula = Gra_naula;
    }
    
    
}
