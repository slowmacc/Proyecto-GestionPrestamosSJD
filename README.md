# Proyecto de Gestion de Prestamos SJD

Sistema desarrollado en Java para la gestion y control de prestamos, enfocado en el manejo de entidades academicas, equipos tecnologicos y utilidades de conexion y validacion.

## Estructura del Proyecto

El repositorio esta organizado en las siguientes carpetas y archivos principales:

* **`entidades/`**: Clases que modelan los objetos del dominio del sistema.
  * `ComputadorPortatil.java`: Representa los equipos portatiles disponibles para prestamo.
  * `EquipoElectronico.java`: Clase base o general para equipos electronicos.
  * `Estudiante.java`: Modelo base que representa a un estudiante.
  * `EstudianteDiseno.java`: Subclase o especializacion para estudiantes del area de diseno.
  * `EstudianteIngenieria.java`: Subclase o especializacion para estudiantes del area de ingenieria.
  * `TabletaGrafica.java`: Representa perifericos o herramientas especificas de dibujo y diseno.

* **`utilidades/`**: Modulos de soporte transversal.
  * `BaseDatos.java`: Gestiona la conexion o la persistencia de los datos.
  * `Metodos.java`: Funciones logicas de apoyo para las operaciones del sistema.
  * `Validaciones.java`: Reglas para la comprobacion de datos e integridad.

* **Archivos Raiz**:
  * `Main.java`: Clase principal que inicializa y ejecuta la aplicacion.
  * `Menu.java`: Gestiona la interfaz de interaccion por consola con el usuario.
  * `.gitignore`: Configuracion de archivos excluidos del control de versiones.

## Tecnologias Utilizadas

* **Lenguaje:** Java
* **Paradigma:** Programacion Orientada a Objetos (POO)

## Instrucciones de Ejecucion

1. Clona el repositorio en tu computadora local.
2. Abre el proyecto en tu entorno de desarrollo favorito compatible con Java (como IntelliJ IDEA, Eclipse o NetBeans).
3. Asegurate de compilar todas las clases dentro de los paquetes `entidades` y `utilidades`.
4. Ejecuta el archivo `Main.java` para iniciar el programa y desplegar el menu de opciones.
