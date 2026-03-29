package test;

import exception.AsignacionInvalidaException;
import exception.DepartamentoNoEncontradoException;
import exception.EmpleadoNoEncontradoException;
import model.Departamento;
import model.EmpleadoPermanente;
import model.ReporteDesempeno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.SistemaGestionRRHH;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaGestionRRHHTest {

    private SistemaGestionRRHH sistema;

    @BeforeEach
    public void setUp() {
        sistema = new SistemaGestionRRHH(new ReporteDesempeno("Primer trimestre 2026", "12/03/2026"));
    }

    @Test
    public void deberiaRegistrarEmpleadoCorrectamente() throws Exception {
        EmpleadoPermanente empleado = new EmpleadoPermanente(1, "Ana Torres", "ana@compuwork.com", "3001112233",
                3200000, 350000, "Senior");

        sistema.registrarEmpleado(empleado);

        assertEquals("Ana Torres", sistema.buscarEmpleadoPorId(1).getNombre());
    }

    @Test
    public void noDeberiaPermitirRegistrarEmpleadoDuplicado() throws Exception {
        EmpleadoPermanente empleado1 = new EmpleadoPermanente(1, "Ana Torres", 3200000, 350000, "Senior");
        EmpleadoPermanente empleado2 = new EmpleadoPermanente(1, "Luis Pérez", 2800000, 250000, "Semi Senior");

        sistema.registrarEmpleado(empleado1);

        assertThrows(AsignacionInvalidaException.class, () -> sistema.registrarEmpleado(empleado2));
    }

    @Test
    public void deberiaAsignarEmpleadoADepartamento() throws Exception {
        EmpleadoPermanente empleado = new EmpleadoPermanente(1, "Ana Torres", 3200000, 350000, "Senior");
        Departamento departamento = new Departamento("D01", "Talento Humano", "Laura Ramírez");

        sistema.registrarEmpleado(empleado);
        sistema.crearDepartamento(departamento);
        sistema.asignarEmpleadoADepartamento(1, "D01");

        assertEquals("Talento Humano", sistema.buscarEmpleadoPorId(1).getDepartamento().getNombre());
    }

    @Test
    public void deberiaLanzarExcepcionSiDepartamentoNoExiste() throws Exception {
        EmpleadoPermanente empleado = new EmpleadoPermanente(1, "Ana Torres", 3200000, 350000, "Senior");
        sistema.registrarEmpleado(empleado);

        assertThrows(DepartamentoNoEncontradoException.class,
                () -> sistema.asignarEmpleadoADepartamento(1, "D99"));
    }

    @Test
    public void deberiaLanzarExcepcionSiEmpleadoNoExiste() {
        assertThrows(EmpleadoNoEncontradoException.class,
                () -> sistema.buscarEmpleadoPorId(50));
    }
}
