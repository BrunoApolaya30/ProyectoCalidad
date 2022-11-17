package Entidad;

public class Matricula {
    private int mat_codigo;
    private String mat_dni;
    private String mat_nombre;
    private String mat_apellido;
    private String mat_fecha;
    private int alu_codigo;
    private int gra_codigo;

    /**
   * Constructor con parametros
   * @param mat_dni
   * @param mat_fecha
   * @param mat_nombre
   * @param mat_apellido
   * @param alu_codigo
   * @param gra_codigo
   */
    
    public Matricula(String mat_dni, String mat_nombre, String mat_apellido, String mat_fecha, int gra_codigo, int alu_codigo) {
        this.mat_dni = mat_dni;
        this.mat_nombre = mat_nombre;
        this.mat_apellido = mat_apellido;
        this.mat_fecha = mat_fecha;
        this.alu_codigo = alu_codigo;
        this.gra_codigo = gra_codigo;
        
    }

    public Matricula() {
        //this.alu_codigo=new Alumno();
       // this.gra_codigo=new Grado();
    }

    public String getMat_dni() {
        return mat_dni;
    }

    public void setMat_dni(String mat_dni) {
        this.mat_dni = mat_dni;
    }
    

    public int getMat_codigo() {
        return mat_codigo;
    }

    public void setMat_codigo(int mat_codigo) {
        this.mat_codigo = mat_codigo;
    }

    public String getMat_fecha() {
        return mat_fecha;
    }

    public void setMat_fecha(String mat_fecha) {
        this.mat_fecha = mat_fecha;
    }

    public String getMat_nombre() {
        return mat_nombre;
    }

    public void setMat_nombre(String mat_nombre) {
        this.mat_nombre = mat_nombre;
    }

    public String getMat_apellido() {
        return mat_apellido;
    }

    public void setMat_apellido(String mat_apellido) {
        this.mat_apellido = mat_apellido;
    }
/*
    public Alumno getAlu_codigo() {
        return alu_codigo;
    }

    public void setAlu_codigo(Alumno alu_codigo) {
        this.alu_codigo = alu_codigo;
    }

    public Grado getGra_codigo() {
        return gra_codigo;
    }

    public void setGra_codigo(Grado gra_codigo) {
        this.gra_codigo = gra_codigo;
    }
    */

    public int getAlu_codigo() {
        return alu_codigo;
    }

    public void setAlu_codigo(int alu_codigo) {
        this.alu_codigo = alu_codigo;
    }

    public int getGra_codigo() {
        return gra_codigo;
    }

    public void setGra_codigo(int gra_codigo) {
        this.gra_codigo = gra_codigo;
    }
    
    
}
