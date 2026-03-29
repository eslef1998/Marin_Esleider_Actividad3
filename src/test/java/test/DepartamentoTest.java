package test;

import exception.AsignacionInvalidaException;
import model.Departamento;
import model.EmpleadoPermanente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoTest {

    @Test
    public void deberiaAsignarEmpleadoCorrectamente() throws Exception {
        Departamento departamento = new Departamento("D01", "Talento Humano", "Laura Ramírez");
        EmpleadoPermanente empleado = new EmpleadoPermanente(1, "Ana Torres", 3200000, 350000, "Senior");

        departamento.asignarEmpleado(empleado);

        assertEquals(1, departamento.getEmpleados().size());
        assertEquals("Talento Humano", empleado.getDepartamento().getNombre());
    }

    @Test
    public void noDeberiaPermitirAsignarEmpleadoDuplicado() throws Exception {
        Departamento departamento = new Departamento("D01", "Talento Humano", "Laura Ramírez");
        EmpleadoPermanente empleado = new EmpleadoPermanente(1, "Ana Torres", 3200000, 350000, "Senior");

        departamento.asignarEmpleado(empleado);

        assertThrows(AsignacionInvalidaException.class, () -> departamento.asignarEmpleado(empleado));
    }

    @Test
    public void deberiaRetirarEmpleadoCorrectamente() throws Exception {
        Departamento departamento = new Departamento("D01", "Talento Humano", "Laura Ramírez");
        EmpleadoPermanente empleado = new EmpleadoPermanente(1, "Ana Torres", 3200000, 350000, "Senior");

        departamento.asignarEmpleado(empleado);
        departamento.retirarEmpleado(empleado);

        assertTrue(departamento.getEmpleados().isEmpty());
        assertNull(empleado.getDepartamento());
    }
}
