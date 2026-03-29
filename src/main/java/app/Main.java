package app;

import java.util.Scanner;

import model.Departamento;
import model.EmpleadoPermanente;
import model.EmpleadoTemporal;
import model.ReporteDesempeno;
import service.SistemaGestionRRHH;

/**
 * Aplicación de consola para integrar los componentes del sistema CompuWork.
 * Se mantiene un diseño sencillo y apropiado para una entrega académica de 4º semestre.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ReporteDesempeno reporte = new ReporteDesempeno("Primer trimestre 2026", "12/03/2026");
        SistemaGestionRRHH sistema = new SistemaGestionRRHH(reporte);

        inicializarDatosBasicos(sistema);
        ejecutarMenu(sistema);
    }

    private static void inicializarDatosBasicos(SistemaGestionRRHH sistema) {
        try {
            sistema.crearDepartamento(new Departamento("D01", "Talento Humano", "Laura Ramirez"));
            sistema.crearDepartamento(new Departamento("D02", "Tecnologia", "Carlos Quintero"));
        } catch (Exception e) {
            System.out.println("No fue posible realizar la inicialización: " + e.getMessage());
        }
    }

    private static void ejecutarMenu(SistemaGestionRRHH sistema) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opcion: ");

            try {
                switch (opcion) {
                    case 1 -> crearDepartamento(sistema);
                    case 2 -> registrarEmpleadoPermanente(sistema);
                    case 3 -> registrarEmpleadoTemporal(sistema);
                    case 4 -> asignarEmpleado(sistema);
                    case 5 -> System.out.println("\n" + sistema.listarDepartamentos());
                    case 6 -> System.out.println("\n" + sistema.listarEmpleados());
                    case 7 -> generarReporteEmpleado(sistema);
                    case 8 -> generarReporteDepartamento(sistema);
                    case 9 -> {
                        continuar = false;
                        System.out.println("Saliendo del sistema...");
                    }
                    default -> System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error controlado por el sistema: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n======================================");
        System.out.println("   SISTEMA COMPUWORK - MENU PRINCIPAL");
        System.out.println("======================================");
        System.out.println("1. Crear departamento");
        System.out.println("2. Registrar empleado permanente");
        System.out.println("3. Registrar empleado temporal");
        System.out.println("4. Asignar empleado a departamento");
        System.out.println("5. Listar departamentos");
        System.out.println("6. Listar empleados");
        System.out.println("7. Generar reporte de empleado");
        System.out.println("8. Generar reporte de departamento");
        System.out.println("9. Salir");
    }

    private static void crearDepartamento(SistemaGestionRRHH sistema) throws Exception {
        String codigo = leerTexto("Codigo del departamento: ");
        String nombre = leerTexto("Nombre del departamento: ");
        String jefe = leerTexto("Jefe responsable: ");

        sistema.crearDepartamento(new Departamento(codigo, nombre, jefe));
        System.out.println("Departamento creado correctamente.");
    }

    private static void registrarEmpleadoPermanente(SistemaGestionRRHH sistema) throws Exception {
        int id = leerEntero("ID del empleado: ");
        String nombre = leerTexto("Nombre: ");
        String correo = leerTexto("Correo: ");
        String telefono = leerTexto("Telefono: ");
        double salario = leerDouble("Salario base: ");
        double bono = leerDouble("Bono fijo: ");
        String categoria = leerTexto("Categoria: ");

        EmpleadoPermanente empleado = new EmpleadoPermanente(id, nombre, correo, telefono, salario, bono, categoria);
        sistema.registrarEmpleado(empleado);
        System.out.println("Empleado permanente registrado correctamente.");
    }

    private static void registrarEmpleadoTemporal(SistemaGestionRRHH sistema) throws Exception {
        int id = leerEntero("ID del empleado: ");
        String nombre = leerTexto("Nombre: ");
        String correo = leerTexto("Correo: ");
        String telefono = leerTexto("Telefono: ");
        double salario = leerDouble("Salario base: ");
        int mesesContrato = leerEntero("Meses de contrato: ");
        double valorHoraExtra = leerDouble("Valor por hora extra: ");
        int horasExtra = leerEntero("Horas extra: ");

        EmpleadoTemporal empleado = new EmpleadoTemporal(
                id, nombre, correo, telefono, salario, mesesContrato, valorHoraExtra, horasExtra
        );

        sistema.registrarEmpleado(empleado);
        System.out.println("Empleado temporal registrado correctamente.");
    }

    private static void asignarEmpleado(SistemaGestionRRHH sistema) throws Exception {
        int idEmpleado = leerEntero("ID del empleado: ");
        String codigoDepartamento = leerTexto("Codigo del departamento: ");

        sistema.asignarEmpleadoADepartamento(idEmpleado, codigoDepartamento);
        System.out.println("Empleado asignado correctamente.");
    }

    private static void generarReporteEmpleado(SistemaGestionRRHH sistema) throws Exception {
        int id = leerEntero("ID del empleado: ");
        double asistencia = leerDouble("Puntuacion de asistencia: ");
        double productividad = leerDouble("Puntuacion de productividad: ");
        double evaluacion = leerDouble("Puntuacion de evaluación: ");

        String reporte = sistema.generarReporteEmpleado(id, asistencia, productividad, evaluacion);
        System.out.println("\n" + reporte);
    }

    private static void generarReporteDepartamento(SistemaGestionRRHH sistema) throws Exception {
        String codigo = leerTexto("Codigo del departamento: ");
        String observacion = leerTexto("Observacion adicional: ");

        String reporte = sistema.generarReporteDepartamento(codigo, observacion);
        System.out.println("\n" + reporte);
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }
}
