package entidades;

import java.io.Serializable;

public abstract class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String cedula;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String serialEquipoPrestado; // Serial del equipo asignado

    public Estudiante(String cedula, String nombre, String apellido, String telefono, String serialEquipoPrestado) {
        if (!validarCedulaGeneral(cedula)) throw new IllegalArgumentException("Cédula inválida: debe ser alfanumérica.");
        if (!validarNombreApellidoGeneral(nombre)) throw new IllegalArgumentException("Nombre inválido: solo letras y espacios.");
        if (!validarNombreApellidoGeneral(apellido)) throw new IllegalArgumentException("Apellido inválido: solo letras y espacios.");
        if (!validarTelefonoGeneral(telefono)) throw new IllegalArgumentException("Teléfono inválido: solo números.");
        // La validación del serial del equipo es genérica aquí, puede ser más específica en subclases si es necesario
        if (!validarSerialEquipoGeneral(serialEquipoPrestado)) throw new IllegalArgumentException("Serial de equipo inválido: debe ser alfanumérico.");

        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.serialEquipoPrestado = serialEquipoPrestado;
    }

    // Métodos de validación generales 
    // Estas son para validar los datos ya obtenidos antes de asignarlos.
    protected static boolean validarCedulaGeneral(String cedula) {
        return cedula != null && cedula.matches("[a-zA-Z0-9]+");
    }

    protected static boolean validarNombreApellidoGeneral(String texto) {
        return texto != null && texto.matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ ]+");
    }

    protected static boolean validarTelefonoGeneral(String telefono) {
        // Acepta formato internacional: +XX... seguido de números
        // Ejemplos válidos: +573123456789, +14155552671
        return telefono != null && telefono.matches("\\+\\d{1,4}\\d{6,15}");
    }

    protected static boolean validarSerialEquipoGeneral(String serial) {
        // Asumimos que los seriales de equipo también son alfanuméricos
        return serial != null && !serial.trim().isEmpty() && serial.matches("[a-zA-Z0-9]+");
    }

    // Getters (Cédula y SerialEquipoPrestado no deben tener setters según requisitos)
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getSerialEquipoPrestado() { return serialEquipoPrestado; }

    // Setters para campos modificables
    public void setNombre(String nombre) {
        if (!validarNombreApellidoGeneral(nombre)) throw new IllegalArgumentException("Nombre inválido: solo letras y espacios.");
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        if (!validarNombreApellidoGeneral(apellido)) throw new IllegalArgumentException("Apellido inválido: solo letras y espacios.");
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        if (!validarTelefonoGeneral(telefono)) throw new IllegalArgumentException("Teléfono inválido: solo números.");
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cédula: " + cedula + ", Nombre: " + nombre + " " + apellido + 
               ", Teléfono: " + telefono + ", Serial Equipo Prestado: " + serialEquipoPrestado;
    }
} 