package model;

public abstract class Empleado {
    private int id;
    private String nombre;
    private String correo;
    private String telefono;
    private double salarioBase;
    private Departamento departamento;

    public Empleado() {
    }

    public Empleado(int id, String nombre, double salarioBase) {
        this(id, nombre, "", "", salarioBase);
    }

    public Empleado(int id, String nombre, String correo, String telefono, double salarioBase) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.salarioBase = salarioBase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public abstract String getTipoEmpleado();

    public abstract double calcularPagoMensual();

    public double calcularDesempeno(double asistencia, double productividad, double evaluacion) {
        return (asistencia * 0.30) + (productividad * 0.40) + (evaluacion * 0.30);
    }

    public String mostrarInformacion() {
        String nombreDepartamento = (departamento == null) ? "Sin departamento" : departamento.getNombre();
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + getTipoEmpleado() + '\'' +
                ", salarioBase=" + salarioBase +
                ", departamento='" + nombreDepartamento + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return mostrarInformacion();
    }
}
