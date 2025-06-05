package utilidades;

import entidades.ComputadorPortatil;
import entidades.EstudianteDiseño;
import entidades.EstudianteIngenieria;
import entidades.TabletaGrafica;
import java.util.Iterator;

public class Metodos {
    private Validaciones validaciones;

    public Metodos(Validaciones validaciones) {
        this.validaciones = validaciones;
    }

    public void registrarPrestamoIngeniero() {
        System.out.println("\n   REGISTRO DE PRESTAMO - INGENIERIA");
        String cedula = validaciones.leerCedula("Ingrese la cédula del estudiante: ");

        for (EstudianteIngenieria e : BaseDatos.ingenieros) {
            if (e.getCedula().equals(cedula)) {
                System.out.println("Error: Este estudiante ya tiene un computador prestado.");
                return;
            }
        }

        String nombre = validaciones.leerNombreApellido("Nombre del estudiante: ");
        String apellido = validaciones.leerNombreApellido("Apellido del estudiante: ");
        String telefono = validaciones.leerTelefono("Teléfono del estudiante: ");
        int numeroSemestre = validaciones.leerEntero("Número de semestre (1-10): ", 1, 10);
        float promedioAcumulado = validaciones.leerPromedioAcumulado("Promedio acumulado (0.0-5.0): ");

        System.out.println("\nDatos del Computador Portátil:");
        String serialComputador = validaciones.leerSerialEquipo("Serial del computador: ");
        for (ComputadorPortatil cp : BaseDatos.portatiles) {
            if (cp.getSerial().equalsIgnoreCase(serialComputador)) {
                System.out.println("Error: Un computador con el serial '" + serialComputador + "' ya está registrado como prestado.");
                for (EstudianteIngenieria ei : BaseDatos.ingenieros) {
                    if (ei.getSerialEquipoPrestado().equalsIgnoreCase(serialComputador)) {
                        System.out.println("Asignado al estudiante de ingeniería con cédula: " + ei.getCedula());
                        return;
                    }
                }
                for (EstudianteDiseño ed : BaseDatos.diseñadores) {
                    if (ed.getSerialEquipoPrestado().equalsIgnoreCase(serialComputador)) {
                        System.out.println("Error: Asignado al estudiante de diseño con cédula: " + ed.getCedula());
                        return;
                    }
                }
                System.out.println("El equipo existe pero no parece estar asignado, esto podría ser un error de datos.");
                return;
            }
        }

        String marca = validaciones.leerMarca("Marca del computador: ");
        float tamaño = validaciones.leerTamañoEquipo("Tamaño del computador (pulgadas): ");
        float precio = validaciones.leerPrecioEquipo("Precio del computador: ");

        String sistemaOperativo = ComputadorPortatil.seleccionarSistemaOperativo(validaciones);
        String procesador = ComputadorPortatil.seleccionarProcesador(validaciones);

        try {
            ComputadorPortatil computador = new ComputadorPortatil(serialComputador, marca, tamaño, precio, sistemaOperativo, procesador);
            EstudianteIngenieria estudiante = new EstudianteIngenieria(cedula, nombre, apellido, telefono, serialComputador, numeroSemestre, promedioAcumulado);
            
            BaseDatos.portatiles.add(computador);
            BaseDatos.ingenieros.add(estudiante);

            System.out.println("Préstamo de computador registrado exitosamente para el estudiante " + nombre + " " + apellido + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al registrar el préstamo: " + e.getMessage());
        }
    }

    public void modificarPrestamoIngeniero() {
        System.out.println("\n MODIFICAR PRESTAMO - INGENIERIA");
        String input = validaciones.leerTextoNoVacio("Ingrese la cédula del estudiante O el serial del computador a modificar: ");

        EstudianteIngenieria estudianteAModificar = null;
        ComputadorPortatil computadorAsociado = null;

        for (EstudianteIngenieria estudiante : BaseDatos.ingenieros) {
            if (estudiante.getCedula().equalsIgnoreCase(input) || estudiante.getSerialEquipoPrestado().equalsIgnoreCase(input)) {
                estudianteAModificar = estudiante;
                break;
            }
        }

        if (estudianteAModificar == null) {
            System.out.println("No se encontró ningún préstamo con la cédula o serial proporcionado.");
            return;
        }

        for (ComputadorPortatil cp : BaseDatos.portatiles) {
            if (cp.getSerial().equalsIgnoreCase(estudianteAModificar.getSerialEquipoPrestado())) {
                computadorAsociado = cp;
                break;
            }
        }
        
        System.out.println("Registro encontrado. Cédula: " + estudianteAModificar.getCedula() + ", Serial Equipo: " + estudianteAModificar.getSerialEquipoPrestado());
        System.out.println("Recuerde: La cédula y el serial del equipo NO PUEDEN ser modificados.");

        try {
            System.out.println("--- Modificar datos del Estudiante ---");
            estudianteAModificar.setNombre(validaciones.leerNombreApellido("Nuevo nombre (" + estudianteAModificar.getNombre() + "): "));
            estudianteAModificar.setApellido(validaciones.leerNombreApellido("Nuevo apellido (" + estudianteAModificar.getApellido() + "): "));
            estudianteAModificar.setTelefono(validaciones.leerTelefono("Nuevo teléfono (" + estudianteAModificar.getTelefono() + "): "));
            estudianteAModificar.setNumeroSemestre(validaciones.leerEntero("Nuevo número de semestre (" + estudianteAModificar.getNumeroSemestre() + "): ", 1, 10));
            float nuevoPromedio = validaciones.leerPromedioAcumulado("Nuevo promedio acumulado (" + estudianteAModificar.getPromedioAcumulado() + "): ");
            estudianteAModificar.setPromedioAcumulado(nuevoPromedio);

            if (computadorAsociado != null) {
                System.out.println("--- Modificar datos del Computador Portátil (Serial: "+ computadorAsociado.getSerial() + ") ---");
                computadorAsociado.setMarca(validaciones.leerMarca("Nueva marca (" + computadorAsociado.getMarca() + "): "));
                computadorAsociado.setTamaño(validaciones.leerTamañoEquipo("Nuevo tamaño (pulgadas) (" + computadorAsociado.getTamaño() + "): "));
                computadorAsociado.setPrecio(validaciones.leerPrecioEquipo("Nuevo precio (" + computadorAsociado.getPrecio() + "): "));
                computadorAsociado.setSistemaOperativo(ComputadorPortatil.seleccionarSistemaOperativo(validaciones));
                computadorAsociado.setProcesador(ComputadorPortatil.seleccionarProcesador(validaciones));
            } else {
                System.out.println("Advertencia: No se encontró el computador asociado al estudiante con serial " + estudianteAModificar.getSerialEquipoPrestado() + ". Solo se modificarán los datos del estudiante.");
            }
            System.out.println("Datos del préstamo modificados exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al modificar los datos: " + e.getMessage());
        }
    }

    public void devolverEquipoIngeniero() {
        System.out.println("\n DEVOLUCIÓN DE EQUIPO - INGENIERIA");
        String input = validaciones.leerTextoNoVacio("Ingrese la cédula del estudiante O el serial del computador a devolver: ");

        Iterator<EstudianteIngenieria> estudianteIterator = BaseDatos.ingenieros.iterator();
        boolean encontrado = false;
        while (estudianteIterator.hasNext()) {
            EstudianteIngenieria estudiante = estudianteIterator.next();
            if (estudiante.getCedula().equalsIgnoreCase(input) || estudiante.getSerialEquipoPrestado().equalsIgnoreCase(input)) {
                String serialEquipoDevuelto = estudiante.getSerialEquipoPrestado();
                estudianteIterator.remove();

                Iterator<ComputadorPortatil> portatilIterator = BaseDatos.portatiles.iterator();
                while (portatilIterator.hasNext()) {
                    ComputadorPortatil cp = portatilIterator.next();
                    if (cp.getSerial().equalsIgnoreCase(serialEquipoDevuelto)) {
                        portatilIterator.remove();
                        break;
                    }
                }
                System.out.println("Equipo devuelto y registro eliminado exitosamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún préstamo activo con la cédula o serial proporcionado.");
        }
    }

    public void buscarEquipoIngeniero() {
        System.out.println("\n BUSCAR EQUIPO - INGENIERIA");
        String input = validaciones.leerTextoNoVacio("Ingrese la cédula del estudiante O el serial del computador a buscar: ");

        EstudianteIngenieria estudianteEncontrado = null;
        ComputadorPortatil computadorAsociado = null;

        for (EstudianteIngenieria estudiante : BaseDatos.ingenieros) {
            if (estudiante.getCedula().equalsIgnoreCase(input) || estudiante.getSerialEquipoPrestado().equalsIgnoreCase(input)) {
                estudianteEncontrado = estudiante;
                for (ComputadorPortatil cp : BaseDatos.portatiles) {
                    if (cp.getSerial().equalsIgnoreCase(estudiante.getSerialEquipoPrestado())) {
                        computadorAsociado = cp;
                        break;
                    }
                }
                break;
            }
        }

        if (estudianteEncontrado != null) {
            System.out.println("=== Préstamo Encontrado ===");
            System.out.println("--- Datos del Estudiante ---");
            System.out.println(estudianteEncontrado.toString());
            if (computadorAsociado != null) {
                System.out.println("--- Datos del Computador Portátil ---");
                System.out.println(computadorAsociado.toString());
            } else {
                System.out.println("Error: No se encontró el computador asociado al estudiante con serial " + estudianteEncontrado.getSerialEquipoPrestado() + ". La base de datos podría estar inconsistente.");
            }
        } else {
            System.out.println("No se encontró ningún préstamo con la cédula o serial proporcionado.");
        }
    }

    public void registrarPrestamoDiseño() {
        System.out.println("\n   REGISTRO DE PRESTAMO - DISEÑO");
        String cedula = validaciones.leerCedula("Ingrese la cédula del estudiante: ");

        for (EstudianteDiseño e : BaseDatos.diseñadores) {
            if (e.getCedula().equals(cedula)) {
                System.out.println("Error: Este estudiante ya tiene una tableta prestada.");
                return;
            }
        }

        String nombre = validaciones.leerNombreApellido("Nombre del estudiante: ");
        String apellido = validaciones.leerNombreApellido("Apellido del estudiante: ");
        String telefono = validaciones.leerTelefono("Teléfono del estudiante: ");
        String modalidad = validaciones.leerModalidadEstudio("Modalidad de estudio (virtual/presencial): ");
        int cantidadAsignaturas = validaciones.leerEntero("Cantidad de asignaturas: ", 0, 20);

        System.out.println("\nDatos de la Tableta Gráfica:");
        String serialTableta = validaciones.leerSerialEquipo("Serial de la tableta: ");

        for (TabletaGrafica tg : BaseDatos.tabletasGraficas) {
            if (tg.getSerial().equalsIgnoreCase(serialTableta)) {
                System.out.println("Error: Una tableta con el serial '" + serialTableta + "' ya está registrada como prestada.");
                for (EstudianteDiseño ed : BaseDatos.diseñadores) {
                    if (ed.getSerialEquipoPrestado().equalsIgnoreCase(serialTableta)) {
                        System.out.println("Asignada al estudiante de diseño con cédula: " + ed.getCedula());
                        return;
                    }
                }
                System.out.println("La tableta existe pero no parece estar asignada, esto podría ser un error de datos.");
                return;
            }
        }

        String marca = validaciones.leerMarca("Marca de la tableta: ");
        float tamano = validaciones.leerTamañoEquipo("Tamaño de la tableta (pulgadas): ");
        float precio = validaciones.leerPrecioEquipo("Precio de la tableta: ");
        String almacenamiento = TabletaGrafica.seleccionarAlmacenamiento(validaciones);
        float peso = validaciones.leerFloatPositivo("Peso de la tableta (kg): ");

        try {
            TabletaGrafica tableta = new TabletaGrafica(serialTableta, marca, tamano, precio, almacenamiento, peso);
            EstudianteDiseño estudiante = new EstudianteDiseño(cedula, nombre, apellido, telefono, serialTableta, modalidad, cantidadAsignaturas);
            
            BaseDatos.tabletasGraficas.add(tableta);
            BaseDatos.diseñadores.add(estudiante);

            System.out.println("Préstamo de tableta gráfica registrado exitosamente para el estudiante " + nombre + " " + apellido + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al registrar el préstamo: " + e.getMessage());
        }
    }

    public void modificarPrestamoDiseño() {
        System.out.println("\n MODIFICAR PRESTAMO - DISEÑO");
        String input = validaciones.leerTextoNoVacio("Ingrese la cédula del estudiante O el serial de la tableta a modificar: ");

        EstudianteDiseño estudianteAModificar = null;
        TabletaGrafica tabletaAsociada = null;

        for (EstudianteDiseño estudiante : BaseDatos.diseñadores) {
            if (estudiante.getCedula().equalsIgnoreCase(input) || estudiante.getSerialEquipoPrestado().equalsIgnoreCase(input)) {
                estudianteAModificar = estudiante;
                break;
            }
        }

        if (estudianteAModificar == null) {
            System.out.println("No se encontró ningún préstamo con la cédula o serial proporcionado.");
            return;
        }

        for (TabletaGrafica tg : BaseDatos.tabletasGraficas) {
            if (tg.getSerial().equalsIgnoreCase(estudianteAModificar.getSerialEquipoPrestado())) {
                tabletaAsociada = tg;
                break;
            }
        }

        System.out.println("Registro encontrado. Cédula: " + estudianteAModificar.getCedula() + ", Serial Equipo: " + estudianteAModificar.getSerialEquipoPrestado());
        System.out.println("Recuerde: La cédula y el serial del equipo NO PUEDEN ser modificados.");

        try {
            System.out.println("--- Modificar datos del Estudiante ---");
            estudianteAModificar.setNombre(validaciones.leerNombreApellido("Nuevo nombre (" + estudianteAModificar.getNombre() + "): "));
            estudianteAModificar.setApellido(validaciones.leerNombreApellido("Nuevo apellido (" + estudianteAModificar.getApellido() + "): "));
            estudianteAModificar.setTelefono(validaciones.leerTelefono("Nuevo teléfono (" + estudianteAModificar.getTelefono() + "): "));
            estudianteAModificar.setModalidadEstudio(validaciones.leerModalidadEstudio("Nueva modalidad (virtual/presencial) (" + estudianteAModificar.getModalidadEstudio() + "): "));
            estudianteAModificar.setCantidadAsignaturas(validaciones.leerEntero("Nueva cantidad de asignaturas (" + estudianteAModificar.getCantidadAsignaturas() + "): ", 0, 20));

            if (tabletaAsociada != null) {
                System.out.println("--- Modificar datos de la Tableta Gráfica (Serial: "+ tabletaAsociada.getSerial() + ") ---");
                tabletaAsociada.setMarca(validaciones.leerMarca("Nueva marca (" + tabletaAsociada.getMarca() + "): "));
                tabletaAsociada.setTamaño(validaciones.leerTamañoEquipo("Nuevo tamaño (pulgadas) (" + tabletaAsociada.getTamaño() + "): "));
                tabletaAsociada.setPrecio(validaciones.leerPrecioEquipo("Nuevo precio (" + tabletaAsociada.getPrecio() + "): "));
                tabletaAsociada.setAlmacenamiento(TabletaGrafica.seleccionarAlmacenamiento(validaciones));
                tabletaAsociada.setPeso(validaciones.leerFloatPositivo("Nuevo peso (kg) (" + tabletaAsociada.getPeso() + "): "));
            } else {
                 System.out.println("Advertencia: No se encontró la tableta asociada al estudiante con serial " + estudianteAModificar.getSerialEquipoPrestado() + ". Solo se modificarán los datos del estudiante.");
            }
            System.out.println("Datos del préstamo modificados exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al modificar los datos: " + e.getMessage());
        }
    }

    public void devolverEquipoDiseño() {
        System.out.println("\n DEVOLUCIÓN DE EQUIPO - DISEÑO");
        String input = validaciones.leerTextoNoVacio("Ingrese la cédula del estudiante O el serial de la tableta a devolver: ");

        Iterator<EstudianteDiseño> estudianteIterator = BaseDatos.diseñadores.iterator();
        boolean encontrado = false;
        while (estudianteIterator.hasNext()) {
            EstudianteDiseño estudiante = estudianteIterator.next();
            if (estudiante.getCedula().equalsIgnoreCase(input) || estudiante.getSerialEquipoPrestado().equalsIgnoreCase(input)) {
                String serialEquipoDevuelto = estudiante.getSerialEquipoPrestado();
                estudianteIterator.remove();

                Iterator<TabletaGrafica> tabletaIterator = BaseDatos.tabletasGraficas.iterator();
                while (tabletaIterator.hasNext()) {
                    TabletaGrafica tg = tabletaIterator.next();
                    if (tg.getSerial().equalsIgnoreCase(serialEquipoDevuelto)) {
                        tabletaIterator.remove();
                        break; 
                    }
                }
                System.out.println("Equipo devuelto y registro eliminado exitosamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún préstamo activo con la cédula o serial proporcionado.");
        }
    }

    public void buscarEquipoDiseño() {
        System.out.println("\n BUSCAR EQUIPO - DISEÑO");
        String input = validaciones.leerTextoNoVacio("Ingrese la cédula del estudiante O el serial de la tableta a buscar: ");

        EstudianteDiseño estudianteEncontrado = null;
        TabletaGrafica tabletaAsociada = null;

        for (EstudianteDiseño estudiante : BaseDatos.diseñadores) {
            if (estudiante.getCedula().equalsIgnoreCase(input) || estudiante.getSerialEquipoPrestado().equalsIgnoreCase(input)) {
                estudianteEncontrado = estudiante;
                for (TabletaGrafica tg : BaseDatos.tabletasGraficas) {
                    if (tg.getSerial().equalsIgnoreCase(estudiante.getSerialEquipoPrestado())) {
                        tabletaAsociada = tg;
                        break;
                    }
                }
                break;
            }
        }

        if (estudianteEncontrado != null) {
            System.out.println("=== Préstamo Encontrado ===");
            System.out.println("--- Datos del Estudiante ---");
            System.out.println(estudianteEncontrado.toString());
            if (tabletaAsociada != null) {
                System.out.println("--- Datos de la Tableta Gráfica ---");
                System.out.println(tabletaAsociada.toString());
            } else {
                System.out.println("Error: No se encontró la tableta asociada al estudiante con serial " + estudianteEncontrado.getSerialEquipoPrestado() + ". La base de datos podría estar inconsistente.");
            }
        } else {
            System.out.println("No se encontró ningún préstamo con la cédula o serial proporcionado.");
        }
    }

    public void imprimirInventarioTotal() {
        System.out.println("\n======= INVENTARIO TOTAL DE PRÉSTAMOS ACTIVOS =======");

        System.out.println("\n--- PRÉSTAMOS A ESTUDIANTES DE INGENIERÍA ---");
        if (BaseDatos.ingenieros.isEmpty()) {
            System.out.println("No hay préstamos registrados para Ingeniería.");
        } else {
            for (EstudianteIngenieria estudiante : BaseDatos.ingenieros) {
                System.out.println("Estudiante:");
                System.out.println("  Cédula: " + estudiante.getCedula());
                System.out.println("  Nombre: " + estudiante.getNombre() + " " + estudiante.getApellido());
                System.out.println("  Teléfono: " + estudiante.getTelefono());
                System.out.println("  Semestre: " + estudiante.getNumeroSemestre());
                System.out.println("  Promedio: " + estudiante.getPromedioAcumulado());
                System.out.println("  Serial Equipo Prestado: " + estudiante.getSerialEquipoPrestado());
                
                ComputadorPortatil equipoAsociado = null;
                for (ComputadorPortatil portatil : BaseDatos.portatiles) {
                    if (portatil.getSerial().equalsIgnoreCase(estudiante.getSerialEquipoPrestado())) {
                        equipoAsociado = portatil;
                        break;
                    }
                }
                if (equipoAsociado != null) {
                    System.out.println("  Datos del Equipo (Computador Portátil):");
                    System.out.println("    Serial: " + equipoAsociado.getSerial());
                    System.out.println("    Marca: " + equipoAsociado.getMarca());
                    System.out.println("    Tamaño: " + equipoAsociado.getTamaño() + " pulgadas");
                    System.out.println("    Precio: $" + String.format("%.2f", equipoAsociado.getPrecio()) + " COP");
                    System.out.println("    Sistema Operativo: " + equipoAsociado.getSistemaOperativo());
                    System.out.println("    Procesador: " + equipoAsociado.getProcesador());
                } else {
                    System.out.println("  Error: No se encontró el Computador Portátil asociado con serial " + estudiante.getSerialEquipoPrestado());
                }
                System.out.println("-------------------------------------------");
            }
        }

        System.out.println("\n--- PRÉSTAMOS A ESTUDIANTES DE DISEÑO ---");
        if (BaseDatos.diseñadores.isEmpty()) {
            System.out.println("No hay préstamos registrados para Diseño.");
        } else {
            for (EstudianteDiseño estudiante : BaseDatos.diseñadores) {
                System.out.println("Estudiante:");
                System.out.println("  Cédula: " + estudiante.getCedula());
                System.out.println("  Nombre: " + estudiante.getNombre() + " " + estudiante.getApellido());
                System.out.println("  Teléfono: " + estudiante.getTelefono());
                System.out.println("  Modalidad: " + estudiante.getModalidadEstudio());
                System.out.println("  Asignaturas: " + estudiante.getCantidadAsignaturas());
                System.out.println("  Serial Equipo Prestado: " + estudiante.getSerialEquipoPrestado());

                TabletaGrafica equipoAsociado = null;
                for (TabletaGrafica tableta : BaseDatos.tabletasGraficas) {
                    if (tableta.getSerial().equalsIgnoreCase(estudiante.getSerialEquipoPrestado())) {
                        equipoAsociado = tableta;
                        break;
                    }
                }
                 if (equipoAsociado != null) {
                    System.out.println("  Datos del Equipo (Tableta Gráfica):");
                    System.out.println("    Serial: " + equipoAsociado.getSerial());
                    System.out.println("    Marca: " + equipoAsociado.getMarca());
                    System.out.println("    Tamaño: " + equipoAsociado.getTamaño() + " pulgadas");
                    System.out.println("    Precio: $" + String.format("%.2f", equipoAsociado.getPrecio()) + " COP");
                    System.out.println("    Almacenamiento: " + equipoAsociado.getAlmacenamiento());
                    System.out.println("    Peso: " + equipoAsociado.getPeso() + " kg");
                } else {
                    System.out.println("  Error: No se encontró la Tableta Gráfica asociada con serial " + estudiante.getSerialEquipoPrestado());
                }
                System.out.println("-------------------------------------------");
            }
        }
    }
}