package model;

public class EmpleadoPermanente extends Empleado {
    private double bonoFijo;
    private String categoria;

    public EmpleadoPermanente(int id, String nombre, double salarioBase, double bonoFijo, String categoria) {
        super(id, nombre, salarioBase);
        this.bonoFijo = bonoFijo;
        this.categoria = categoria;
    }

    public EmpleadoPermanente(int id, String nombre, String correo, String telefono,
                              double salarioBase, double bonoFijo, String categoria) {
        super(id, nombre, correo, telefono, salarioBase);
        this.bonoFijo = bonoFijo;
        this.categoria = categoria;
    }

    public double getBonoFijo() {
        return bonoFijo;
    }

    public void setBonoFijo(double bonoFijo) {
        this.bonoFijo = bonoFijo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String getTipoEmpleado() {
        return "Permanente";
    }

    @Override
    public double calcularPagoMensual() {
        return getSalarioBase() + bonoFijo;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + " -> Permanencia{bonoFijo=" + bonoFijo +
                ", categoria='" + categoria + "'}";
    }
}
