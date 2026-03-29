package model;

import exception.AsignacionInvalidaException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Departamento {
    private String codigo;
    private String nombre;
    private String jefeResponsable;
    private final List<Empleado> empleados;

    public Departamento(String codigo, String nombre) {
        this(codigo, nombre, "No asignado");
    }

    public Departamento(String codigo, String nombre, String jefeResponsable) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.jefeResponsable = jefeResponsable;
        this.empleados = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getJefeResponsable() {
        return jefeResponsable;
    }

    public void setJefeResponsable(String jefeResponsable) {
        this.jefeResponsable = jefeResponsable;
    }

    public List<Empleado> getEmpleados() {
        return Collections.unmodifiableList(empleados);
    }

    public void asignarEmpleado(Empleado empleado) throws AsignacionInvalidaException {
        if (empleado == null) {
            throw new AsignacionInvalidaException("No se puede asignar un empleado nulo.");
        }
        if (empleados.contains(empleado)) {
            throw new AsignacionInvalidaException("El empleado ya está asignado a este departamento.");
        }
        empleados.add(empleado);
        empleado.setDepartamento(this);
    }

    public void retirarEmpleado(Empleado empleado) throws AsignacionInvalidaException {
        if (empleado == null || !empleados.contains(empleado)) {
            throw new AsignacionInvalidaException("El empleado no pertenece al departamento " + nombre + ".");
        }
        empleados.remove(empleado);
        empleado.setDepartamento(null);
    }

    public String listarEmpleados() {
        if (empleados.isEmpty()) {
            return "No hay empleados asignados al departamento " + nombre + ".";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Empleados del departamento ").append(nombre).append(":\n");
        for (Empleado empleado : empleados) {
            sb.append("- ").append(empleado.getId()).append(" | ")
              .append(empleado.getNombre()).append(" | ")
              .append(empleado.getTipoEmpleado()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", jefeResponsable='" + jefeResponsable + '\'' +
                ", totalEmpleados=" + empleados.size() +
                '}';
    }
}
