package entidades;

public class EstudianteIngenieria extends Estudiante {
    private int numeroSemestre;
    private float promedioAcumulado;

    // No se define un constructor público por defecto, ya que los datos deben ser validados.

    public EstudianteIngenieria(String cedula, String nombre, String apellido, String telefono, 
                                String serialEquipoPrestado, int numeroSemestre, float promedioAcumulado) {
        super(cedula, nombre, apellido, telefono, serialEquipoPrestado);

        if (numeroSemestre < 1 || numeroSemestre > 10) { // Max 10 semestres
            throw new IllegalArgumentException("Número de semestre inválido. Debe ser un entero entre 1 y 10.");
        }
        if (promedioAcumulado < 0 || promedioAcumulado > 5) { // Asumiendo un rango típico de promedio 0-5
            throw new IllegalArgumentException("Promedio acumulado inválido. Debe estar entre 0 y 5.");
        }

        this.numeroSemestre = numeroSemestre;
        this.promedioAcumulado = promedioAcumulado;
    }

    // Getters específicos de EstudianteIngenieria
    public int getNumeroSemestre() {
        return numeroSemestre;
    }

    public float getPromedioAcumulado() {
        return promedioAcumulado;
    }

    // Setters específicos con validación
    public void setNumeroSemestre(int numeroSemestre) {
        if (numeroSemestre < 1 || numeroSemestre > 10) { // Max 10 semestres
            throw new IllegalArgumentException("Número de semestre inválido. Debe ser un entero entre 1 y 10.");
        }
        this.numeroSemestre = numeroSemestre;
    }

    public void setPromedioAcumulado(float promedioAcumulado) {
        if (promedioAcumulado < 0 || promedioAcumulado > 5) { 
            throw new IllegalArgumentException("Promedio acumulado inválido. Debe estar entre 0 y 5.");
        }
        this.promedioAcumulado = promedioAcumulado;
    }

    @Override
    public String toString() {
        return super.toString() + ", Semestre: " + numeroSemestre + ", Promedio: " + promedioAcumulado;
    }
}
