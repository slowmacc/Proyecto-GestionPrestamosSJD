package entidades;

import utilidades.Validaciones; // Necesario para los métodos de selección

public class ComputadorPortatil extends EquipoElectronico {

    private String sistemaOperativo;
    private String procesador;

    // Lista de SO y Procesadores permitidos
    private static final String[] SISTEMAS_OPERATIVOS_PERMITIDOS = {"Windows 7", "Windows 10", "Windows 11"};
    private static final String[] PROCESADORES_PERMITIDOS = {"AMD Ryzen", "Intel Core i5"}; // Ajustado según el ejemplo "Intel® Core™ i5"

    public ComputadorPortatil(String serial, String marca, float tamaño, float precio, String sistemaOperativo,
            String procesador) {
        super(serial, marca, tamaño, precio);
        if (!validarSO(sistemaOperativo))
            throw new IllegalArgumentException("Sistema operativo no válido o no permitido.");
        if (!validarProcesador(procesador))
            throw new IllegalArgumentException("Procesador no válido o no permitido.");

        this.sistemaOperativo = sistemaOperativo;
        this.procesador = procesador;
    }

    // Getters
    public String getSistemaOperativo() { return sistemaOperativo; }
    public String getProcesador() { return procesador; }

    // Setters con validación (Serial no se puede cambiar según requisitos)
    public void setSistemaOperativo(String sistemaOperativo) {
        if (!validarSO(sistemaOperativo))
            throw new IllegalArgumentException("Sistema operativo no válido o no permitido.");
        this.sistemaOperativo = sistemaOperativo;
    }

    public void setProcesador(String procesador) {
        if (!validarProcesador(procesador))
            throw new IllegalArgumentException("Procesador no válido o no permitido.");
        this.procesador = procesador;
    }

    // Métodos de validación específicos
    private static boolean validarSO(String so) {
        if (so == null) return false;
        for (String permitido : SISTEMAS_OPERATIVOS_PERMITIDOS) {
            if (permitido.equalsIgnoreCase(so.trim())) return true;
        }
        return false;
    }

    private static boolean validarProcesador(String proc) {
        if (proc == null) return false;
        for (String permitido : PROCESADORES_PERMITIDOS) {
             // Se hace un equalsIgnoreCase para flexibilidad, aunque los menús darán el valor exacto.
            if (permitido.equalsIgnoreCase(proc.trim())) return true;
        }
        return false;
    }

    // Métodos de selección usando la clase Validaciones
    public static String seleccionarSistemaOperativo(Validaciones val) {
        System.out.println("Seleccione el sistema operativo:");
        for (int i = 0; i < SISTEMAS_OPERATIVOS_PERMITIDOS.length; i++) {
            System.out.println((i + 1) + ". " + SISTEMAS_OPERATIVOS_PERMITIDOS[i]);
        }
        int opcion = val.leerEntero("Opción: ", 1, SISTEMAS_OPERATIVOS_PERMITIDOS.length);
        return SISTEMAS_OPERATIVOS_PERMITIDOS[opcion - 1];
    }

    public static String seleccionarProcesador(Validaciones val) {
        System.out.println("Seleccione el procesador:");
        for (int i = 0; i < PROCESADORES_PERMITIDOS.length; i++) {
            // Mostrar "Intel® Core™ i5" en el menú si el valor funcional es "Intel Core i5"
            String displayName = PROCESADORES_PERMITIDOS[i];
            if (displayName.equals("Intel Core i5")) {
                displayName = "Intel® Core™ i5";
            }
            System.out.println((i + 1) + ". " + displayName);
        }
        int opcion = val.leerEntero("Opción: ", 1, PROCESADORES_PERMITIDOS.length);
        return PROCESADORES_PERMITIDOS[opcion - 1]; // Devuelve el valor del array
    }

    @Override
    public String toString() {
        return super.toString() + ", SO=" + sistemaOperativo + ", Procesador=" + procesador;
    }
}
