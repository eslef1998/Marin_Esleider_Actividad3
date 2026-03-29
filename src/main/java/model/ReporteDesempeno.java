package model;

import exception.ReporteInvalidoException;
import java.util.List;

public class ReporteDesempeno {
    private String periodo;
    private String fechaGeneracion;

    public ReporteDesempeno(String periodo, String fechaGeneracion) {
        this.periodo = periodo;
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String generarReporte(Empleado empleado, double asistencia, double productividad, double evaluacion)
            throws ReporteInvalidoException {
        validarMetricas(asistencia, productividad, evaluacion);
        if (empleado == null) {
            throw new ReporteInvalidoException("No se puede generar un reporte para un empleado nulo.");
        }
        double desempeno = empleado.calcularDesempeno(asistencia, productividad, evaluacion);
        return "REPORTE INDIVIDUAL\n" +
                "Periodo: " + periodo + "\n" +
                "Fecha: " + fechaGeneracion + "\n" +
                "Empleado: " + empleado.getNombre() + "\n" +
                "Tipo: " + empleado.getTipoEmpleado() + "\n" +
                "Departamento: " + (empleado.getDepartamento() == null ? "Sin asignar" : empleado.getDepartamento().getNombre()) + "\n" +
                String.format("Desempeno final: %.2f/100\n", desempeno);
    }

    public String generarReporte(Departamento departamento) throws ReporteInvalidoException {
        if (departamento == null) {
            throw new ReporteInvalidoException("El departamento no puede ser nulo.");
        }
        List<Empleado> empleados = departamento.getEmpleados();
        if (empleados.isEmpty()) {
            throw new ReporteInvalidoException("El departamento no tiene empleados para consolidar el reporte.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE POR DEPARTAMENTO\n");
        sb.append("Periodo: ").append(periodo).append("\n");
        sb.append("Fecha: ").append(fechaGeneracion).append("\n");
        sb.append("Departamento: ").append(departamento.getNombre()).append("\n");
        sb.append("Jefe responsable: ").append(departamento.getJefeResponsable()).append("\n");
        sb.append("Total empleados: ").append(empleados.size()).append("\n");
        sb.append("Resumen de nomina mensual aproximada: ")
          .append(String.format("%.2f", calcularNominaMensual(departamento))).append("\n");
        return sb.toString();
    }

    public String generarReporte(Departamento departamento, String observacionAdicional) throws ReporteInvalidoException {
        return generarReporte(departamento) + "Observacion: " + observacionAdicional + "\n";
    }

    private void validarMetricas(double asistencia, double productividad, double evaluacion)
            throws ReporteInvalidoException {
        if (!esValida(asistencia) || !esValida(productividad) || !esValida(evaluacion)) {
            throw new ReporteInvalidoException("Las métricas deben estar en un rango de 0 a 100.");
        }
    }

    private boolean esValida(double valor) {
        return valor >= 0 && valor <= 100;
    }

    private double calcularNominaMensual(Departamento departamento) {
        double total = 0;
        for (Empleado empleado : departamento.getEmpleados()) {
            total += empleado.calcularPagoMensual();
        }
        return total;
    }
}
