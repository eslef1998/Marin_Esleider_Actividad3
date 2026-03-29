# CompuWork - Actividad 3

Proyecto académico desarrollado en Java para la materia **Programación II: Orientada a Objetos Avanzada**.

## Descripción
El sistema CompuWork fue realizado con el fin de gestionar empleados, departamentos y reportes de desempeño dentro de una empresa.  
Esta versión corresponde a la **Actividad 3: Integración y Validación**, por lo tanto se trabajó sobre la base construida en las actividades anteriores, integrando las clases del sistema en una aplicación funcional y validando su funcionamiento con pruebas unitarias.

El proyecto permite:

- Registrar empleados permanentes y temporales.
- Crear y organizar departamentos.
- Asignar empleados a departamentos.
- Listar empleados y departamentos registrados.
- Generar reportes de desempeño individuales y por departamento.
- Validar el funcionamiento del sistema mediante pruebas unitarias con JUnit.

## Objetivo
Integrar las clases desarrolladas previamente en una aplicación funcional en Java, aplicando Programación Orientada a Objetos y validando el correcto funcionamiento del sistema con pruebas unitarias.

## Estructura del proyecto
El proyecto se encuentra organizado de la siguiente manera:

- `src/main/java/app` → contiene la clase principal `Main`, desde donde se ejecuta el menú del sistema.
- `src/main/java/model` → contiene las clases del dominio: `Empleado`, `EmpleadoPermanente`, `EmpleadoTemporal`, `Departamento` y `ReporteDesempeno`.
- `src/main/java/service` → contiene la clase `SistemaGestionRRHH`, que concentra la lógica principal del sistema.
- `src/main/java/exception` → contiene las excepciones personalizadas utilizadas para controlar errores.
- `src/test/java/test` → contiene las pruebas unitarias desarrolladas con JUnit.

## Funcionalidades principales
Dentro del sistema se implementaron las siguientes funcionalidades:

- Creación de departamentos.
- Registro de empleados permanentes.
- Registro de empleados temporales.
- Asignación de empleados a departamentos.
- Consulta de empleados y departamentos.
- Generación de reportes de desempeño.
- Manejo de errores mediante excepciones personalizadas.

## Requisitos
Para ejecutar este proyecto se necesita:

- Java 17 o superior.
- Maven instalado en el sistema.
- Un entorno de desarrollo como Visual Studio Code, IntelliJ IDEA o NetBeans.

## Ejecución del proyecto

### Opción 1: Compilar y ejecutar con Maven y Java
Primero se compila el proyecto con Maven:

```bash
mvn compile     

java -cp target/classes app.Main

