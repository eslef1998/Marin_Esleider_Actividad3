package service;

import exception.AsignacionInvalidaException;
import exception.DepartamentoNoEncontradoException;
import exception.EmpleadoNoEncontradoException;
import exception.ReporteInvalidoException;
import model.Departamento;
import model.Empleado;
import model.ReporteDesempeno;

import java.util.ArrayList;
import java.util.List;

public class SistemaGestionRRHH {
    private final List<Empleado> empleados;
    private final List<Departamento> departamentos;
    private final ReporteDesempeno generadorReportes;

    public SistemaGestionRRHH(ReporteDesempeno generadorReportes) {
        this.empleados = new ArrayList<>();
        this.departamentos = new ArrayList<>();
        this.generadorReportes = generadorReportes;
    }

    public void registrarEmpleado(Empleado empleado) throws AsignacionInvalidaException {
        validarEmpleado(empleado);
        if (buscarEmpleadoPorIdInterno(empleado.getId()) != null) {
            throw new AsignacionInvalidaException("Ya existe un empleado con el ID " + empleado.getId() + ".");
        }
        empleados.add(empleado);
    }

    public void crearDepartamento(Departamento departamento) throws AsignacionInvalidaException {
        if (departamento == null) {
            throw new AsignacionInvalidaException("El departamento no puede ser nulo.");
        }
        if (buscarDepartamentoPorCodigoInterno(departamento.getCodigo()) != null) {
            throw new AsignacionInvalidaException("Ya existe un departamento con el código " + departamento.getCodigo() + ".");
        }
        departamentos.add(departamento);
    }

    public void actualizarEmpleado(int id, String nuevoNombre, double nuevoSalario)
            throws EmpleadoNoEncontradoException, AsignacionInvalidaException {
        Empleado empleado = buscarEmpleadoPorId(id);
        if (nuevoNombre == null || nuevoNombre.isBlank() || nuevoSalario <= 0) {
            throw new AsignacionInvalidaException("Los datos de actualización del empleado son inválidos.");
        }
        empleado.setNombre(nuevoNombre);
        empleado.setSalarioBase(nuevoSalario);
    }

    public void eliminarEmpleado(int id) throws EmpleadoNoEncontradoException, AsignacionInvalidaException {
        Empleado empleado = buscarEmpleadoPorId(id);
        if (empleado.getDepartamento() != null) {
            empleado.getDepartamento().retirarEmpleado(empleado);
        }
        empleados.remove(empleado);
    }

    public void actualizarDepartamento(String codigo, String nuevoNombre, String nuevoJefe)
            throws DepartamentoNoEncontradoException, AsignacionInvalidaException {
        Departamento departamento = buscarDepartamentoPorCodigo(codigo);
        if (nuevoNombre == null || nuevoNombre.isBlank() || nuevoJefe == null || nuevoJefe.isBlank()) {
            throw new AsignacionInvalidaException("Los datos del departamento son inválidos.");
        }
        departamento.setNombre(nuevoNombre);
        departamento.setJefeResponsable(nuevoJefe);
    }

    public void eliminarDepartamento(String codigo)
            throws DepartamentoNoEncontradoException, AsignacionInvalidaException {
        Departamento departamento = buscarDepartamentoPorCodigo(codigo);
        if (!departamento.getEmpleados().isEmpty()) {
            throw new AsignacionInvalidaException("No se puede eliminar el departamento porque tiene empleados asignados.");
        }
        departamentos.remove(departamento);
    }

    public void asignarEmpleadoADepartamento(int idEmpleado, String codigoDepartamento)
            throws EmpleadoNoEncontradoException, DepartamentoNoEncontradoException, AsignacionInvalidaException {
        Empleado empleado = buscarEmpleadoPorId(idEmpleado);
        Departamento nuevoDepartamento = buscarDepartamentoPorCodigo(codigoDepartamento);

        if (empleado.getDepartamento() != null) {
            empleado.getDepartamento().retirarEmpleado(empleado);
        }
        nuevoDepartamento.asignarEmpleado(empleado);
    }

    public Empleado buscarEmpleadoPorId(int id) throws EmpleadoNoEncontradoException {
        Empleado empleado = buscarEmpleadoPorIdInterno(id);
        if (empleado == null) {
            throw new EmpleadoNoEncontradoException("No existe un empleado con el ID " + id + ".");
        }
        return empleado;
    }

    public Departamento buscarDepartamentoPorCodigo(String codigo) throws DepartamentoNoEncontradoException {
        Departamento departamento = buscarDepartamentoPorCodigoInterno(codigo);
        if (departamento == null) {
            throw new DepartamentoNoEncontradoException("No existe un departamento con el código " + codigo + ".");
        }
        return departamento;
    }

    public String generarReporteEmpleado(int idEmpleado, double asistencia, double productividad, double evaluacion)
            throws EmpleadoNoEncontradoException, ReporteInvalidoException {
        Empleado empleado = buscarEmpleadoPorId(idEmpleado);
        return generadorReportes.generarReporte(empleado, asistencia, productividad, evaluacion);
    }

    public String generarReporteDepartamento(String codigoDepartamento)
            throws DepartamentoNoEncontradoException, ReporteInvalidoException {
        Departamento departamento = buscarDepartamentoPorCodigo(codigoDepartamento);
        return generadorReportes.generarReporte(departamento);
    }

    public String generarReporteDepartamento(String codigoDepartamento, String observacion)
            throws DepartamentoNoEncontradoException, ReporteInvalidoException {
        Departamento departamento = buscarDepartamentoPorCodigo(codigoDepartamento);
        return generadorReportes.generarReporte(departamento, observacion);
    }

    public String listarEmpleados() {
        if (empleados.isEmpty()) {
            return "No hay empleados registrados.";
        }
        StringBuilder sb = new StringBuilder("LISTADO GENERAL DE EMPLEADOS\n");
        for (Empleado empleado : empleados) {
            sb.append(empleado.mostrarInformacion()).append("\n");
        }
        return sb.toString();
    }

    public String listarDepartamentos() {
        if (departamentos.isEmpty()) {
            return "No hay departamentos registrados.";
        }
        StringBuilder sb = new StringBuilder("LISTADO DE DEPARTAMENTOS\n");
        for (Departamento departamento : departamentos) {
            sb.append(departamento).append("\n");
        }
        return sb.toString();
    }

    private void validarEmpleado(Empleado empleado) throws AsignacionInvalidaException {
        if (empleado == null) {
            throw new AsignacionInvalidaException("El empleado no puede ser nulo.");
        }
        if (empleado.getNombre() == null || empleado.getNombre().isBlank() || empleado.getSalarioBase() <= 0) {
            throw new AsignacionInvalidaException("Los datos básicos del empleado son inválidos.");
        }
    }

    private Empleado buscarEmpleadoPorIdInterno(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }

    private Departamento buscarDepartamentoPorCodigoInterno(String codigo) {
        for (Departamento departamento : departamentos) {
            if (departamento.getCodigo().equalsIgnoreCase(codigo)) {
                return departamento;
            }
        }
        return null;
    }
}
