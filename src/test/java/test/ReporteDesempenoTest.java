package test;

import exception.ReporteInvalidoException;
import model.Departamento;
import model.EmpleadoPermanente;
import model.ReporteDesempeno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteDesempenoTest {

    private ReporteDesempeno reporte;
    private EmpleadoPermanente empleado;
    private Departamento departamento;

    @BeforeEach
    public void setUp() throws Exception {
        reporte = new ReporteDesempeno("Primer trimestre 2026", "12/03/2026");
        empleado = new EmpleadoPermanente(1, "Ana Torres", 3200000, 350000, "Senior");
        departamento = new Departamento("D01", "Talento Humano", "Laura Ramírez");
        departamento.asignarEmpleado(empleado);
    }

    @Test
    public void deberiaGenerarReporteIndividual() throws Exception {
        String resultado = reporte.generarReporte(empleado, 90, 85, 95);

        assertTrue(resultado.contains("REPORTE INDIVIDUAL"));
        assertTrue(resultado.contains("Ana Torres"));
    }

    @Test
    public void deberiaGenerarReportePorDepartamentoConObservacion() throws Exception {
        String resultado = reporte.generarReporte(departamento, "Buen desempeño general.");

        assertTrue(resultado.contains("REPORTE POR DEPARTAMENTO"));
        assertTrue(resultado.contains("Buen desempeño general."));
    }

    @Test
    public void deberiaLanzarExcepcionPorMetricasInvalidas() {
        assertThrows(ReporteInvalidoException.class,
                () -> reporte.generarReporte(empleado, 120, 85, 90));
    }
}
