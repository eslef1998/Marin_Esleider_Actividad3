package model;

public class EmpleadoTemporal extends Empleado {
    private int mesesContrato;
    private double valorHoraExtra;
    private int horasExtra;

    public EmpleadoTemporal(int id, String nombre, double salarioBase, int mesesContrato, double valorHoraExtra) {
        super(id, nombre, salarioBase);
        this.mesesContrato = mesesContrato;
        this.valorHoraExtra = valorHoraExtra;
    }

    public EmpleadoTemporal(int id, String nombre, String correo, String telefono,
                            double salarioBase, int mesesContrato, double valorHoraExtra, int horasExtra) {
        super(id, nombre, correo, telefono, salarioBase);
        this.mesesContrato = mesesContrato;
        this.valorHoraExtra = valorHoraExtra;
        this.horasExtra = horasExtra;
    }

    public int getMesesContrato() {
        return mesesContrato;
    }

    public void setMesesContrato(int mesesContrato) {
        this.mesesContrato = mesesContrato;
    }

    public double getValorHoraExtra() {
        return valorHoraExtra;
    }

    public void setValorHoraExtra(double valorHoraExtra) {
        this.valorHoraExtra = valorHoraExtra;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }

    @Override
    public String getTipoEmpleado() {
        return "Temporal";
    }

    @Override
    public double calcularPagoMensual() {
        return getSalarioBase() + (horasExtra * valorHoraExtra);
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + " -> Temporal{mesesContrato=" + mesesContrato +
                ", valorHoraExtra=" + valorHoraExtra +
                ", horasExtra=" + horasExtra + "}";
    }
}
