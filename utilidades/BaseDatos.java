package utilidades;
import java.io.*;
import java.util.ArrayList;

import entidades.ComputadorPortatil;
import entidades.EstudianteDiseño;
import entidades.EstudianteIngenieria;
import entidades.TabletaGrafica;

public class BaseDatos {
    public static ArrayList<EstudianteIngenieria> ingenieros = new ArrayList<>();
    public static ArrayList<EstudianteDiseño> diseñadores = new ArrayList<>();
    public static ArrayList<ComputadorPortatil> portatiles = new ArrayList<>();
    public static ArrayList<TabletaGrafica> tabletasGraficas = new ArrayList<>();

    private static final String ARCHIVO_DATOS = "prestamos.dat";

    // Método para verificar si una cédula ya existe
    public static boolean existeCedula(String cedula) {
        for (EstudianteIngenieria e : ingenieros) {
            if (e.getCedula().equals(cedula)) return true;
        }
        for (EstudianteDiseño e : diseñadores) {
            if (e.getCedula().equals(cedula)) return true;
        }
        return false;
    }

    // Método para verificar si un serial ya existe
    public static boolean existeSerial(String serial) {
        for (ComputadorPortatil cp : portatiles) {
            if (cp.getSerial().equalsIgnoreCase(serial)) return true;
        }
        for (TabletaGrafica tg : tabletasGraficas) {
            if (tg.getSerial().equalsIgnoreCase(serial)) return true;
        }
        return false;
    }

    // Guardar datos en archivo
    public static void guardarDatos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            out.writeObject(ingenieros);
            out.writeObject(diseñadores);
            out.writeObject(portatiles);
            out.writeObject(tabletasGraficas);
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    // Cargar datos desde archivo
    @SuppressWarnings("unchecked")
    public static void cargarDatos() {
        File archivo = new File(ARCHIVO_DATOS);
        if (!archivo.exists()) {
            System.out.println("No hay datos previos guardados.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            ingenieros = (ArrayList<EstudianteIngenieria>) in.readObject();
            diseñadores = (ArrayList<EstudianteDiseño>) in.readObject();
            portatiles = (ArrayList<ComputadorPortatil>) in.readObject();
            tabletasGraficas = (ArrayList<TabletaGrafica>) in.readObject();
            System.out.println("Datos cargados exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            // Inicializar listas vacías en caso de error
            ingenieros = new ArrayList<>();
            diseñadores = new ArrayList<>();
            portatiles = new ArrayList<>();
            tabletasGraficas = new ArrayList<>();
        }
    }

    // Limpiar todos los datos
    public static void limpiarDatos() {
        ingenieros.clear();
        diseñadores.clear();
        portatiles.clear();
        tabletasGraficas.clear();
        File archivo = new File(ARCHIVO_DATOS);
        if (archivo.exists()) {
            archivo.delete();
        }
        System.out.println("Todos los datos han sido eliminados.");
    }
}
