package entidades;

import utilidades.Validaciones;

public class TabletaGrafica extends EquipoElectronico {
    
    private String almacenamiento;
    private float peso; // en kg

    private static final String[] ALMACENAMIENTOS_PERMITIDOS = {"256 GB", "512 GB", "1 TB"};

    public TabletaGrafica(String serial, String marca, float tamaño, float precio, String almacenamiento, float peso) {
        super(serial, marca, tamaño, precio);
        
        if (!validarAlmacenamiento(almacenamiento)) 
            throw new IllegalArgumentException("Almacenamiento no válido o no permitido.");
        if (peso <= 0) 
            throw new IllegalArgumentException("Peso debe ser un número positivo.");
        
        this.almacenamiento = almacenamiento;
        this.peso = peso;
    }

    // Getters
    public String getAlmacenamiento() { return almacenamiento; }
    public float getPeso() { return peso; }

    // Setters con validación
    public void setAlmacenamiento(String almacenamiento) {
        if (!validarAlmacenamiento(almacenamiento)) 
            throw new IllegalArgumentException("Almacenamiento no válido o no permitido.");
        this.almacenamiento = almacenamiento;
    }

    public void setPeso(float peso) {
        if (peso <= 0) 
            throw new IllegalArgumentException("Peso debe ser un número positivo.");
        this.peso = peso;
    }

    // Método de validación específico
    private static boolean validarAlmacenamiento(String alm) {
        if (alm == null) return false;
        for (String permitido : ALMACENAMIENTOS_PERMITIDOS) {
            if (permitido.equalsIgnoreCase(alm.trim())) return true;
        }
        return false;
    }

    // Método de selección usando la clase Validaciones
    public static String seleccionarAlmacenamiento(Validaciones val) {
        System.out.println("Seleccione almacenamiento:");
        for (int i = 0; i < ALMACENAMIENTOS_PERMITIDOS.length; i++) {
            System.out.println((i + 1) + ". " + ALMACENAMIENTOS_PERMITIDOS[i]);
        }
        int opcion = val.leerEntero("Opción: ", 1, ALMACENAMIENTOS_PERMITIDOS.length);
        return ALMACENAMIENTOS_PERMITIDOS[opcion - 1];
    }

    @Override
    public String toString() {
        return super.toString() + ", Almacenamiento=" + almacenamiento + ", Peso=" + peso + " kg";
    }
}