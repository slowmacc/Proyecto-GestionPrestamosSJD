package entidades;

public class EstudianteDiseño extends Estudiante {
    private String modalidadEstudio; // "virtual" o "presencial"
    private int cantidadAsignaturas;
    // El serial del equipo ya está en la clase base Estudiante como serialEquipoPrestado (String)

    public EstudianteDiseño(String cedula, String nombre, String apellido, String telefono,
                              String serialEquipoPrestado, String modalidadEstudio, int cantidadAsignaturas) {
        super(cedula, nombre, apellido, telefono, serialEquipoPrestado);

        if (!validarModalidad(modalidadEstudio)) {
            throw new IllegalArgumentException("Modalidad de estudio inválida. Debe ser 'virtual' o 'presencial'.");
        }
        if (cantidadAsignaturas < 1 || cantidadAsignaturas > 10) { // Rango 1-10
            throw new IllegalArgumentException("Cantidad de asignaturas inválida. Debe ser un entero entre 1 y 10.");
        }

        this.modalidadEstudio = modalidadEstudio.toLowerCase();
        this.cantidadAsignaturas = cantidadAsignaturas;
    }

    // Getters específicos
    public String getModalidadEstudio() {
        return modalidadEstudio;
    }

    public int getCantidadAsignaturas() {
        return cantidadAsignaturas;
    }

    // Setters específicos con validación
    public void setModalidadEstudio(String modalidadEstudio) {
        if (!validarModalidad(modalidadEstudio)) {
            throw new IllegalArgumentException("Modalidad de estudio inválida. Debe ser 'virtual' o 'presencial'.");
        }
        this.modalidadEstudio = modalidadEstudio.toLowerCase();
    }

    public void setCantidadAsignaturas(int cantidadAsignaturas) {
        if (cantidadAsignaturas < 1 || cantidadAsignaturas > 10) { // Rango 1-10
            throw new IllegalArgumentException("Cantidad de asignaturas inválida. Debe ser un entero entre 1 y 10.");
        }
        this.cantidadAsignaturas = cantidadAsignaturas;
    }

    // Método de validación interno para modalidad
    private static boolean validarModalidad(String modalidad) {
        if (modalidad == null) return false;
        String mod = modalidad.trim().toLowerCase();
        return mod.equals("virtual") || mod.equals("presencial");
    }

    @Override
    public String toString() {
        return super.toString() + ", Modalidad: " + modalidadEstudio + ", Asignaturas: " + cantidadAsignaturas;
    }
}