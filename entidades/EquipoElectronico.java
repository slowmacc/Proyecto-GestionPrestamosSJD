package entidades;

import java.io.Serializable;

public abstract class EquipoElectronico implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String serial;
    protected String marca;
    protected float tamaño; // en pulgadas
    protected float precio;

    // Constantes para validación
    private static final float TAMAÑO_MIN = 11.0f; // Tamaño mínimo en pulgadas
    private static final float TAMAÑO_MAX = 32.0f; // Tamaño máximo en pulgadas
    private static final float PRECIO_MIN = 100000.0f; // Precio mínimo en COP
    private static final float PRECIO_MAX = 15000000.0f; // Precio máximo en COP

    public EquipoElectronico(String serial, String marca, float tamaño, float precio) {
        if (!validarSerialGeneral(serial)) 
            throw new IllegalArgumentException("Serial de equipo inválido: debe contener letras, números o guiones, longitud entre 5 y 20 caracteres.");
        if (!validarMarcaGeneral(marca)) 
            throw new IllegalArgumentException("Marca inválida: solo letras, números, espacios y algunos caracteres especiales como ® ™.");
        if (!validarTamaño(tamaño)) 
            throw new IllegalArgumentException(String.format("Tamaño debe estar entre %.1f y %.1f pulgadas.", TAMAÑO_MIN, TAMAÑO_MAX));
        if (!validarPrecio(precio)) 
            throw new IllegalArgumentException(String.format("Precio debe estar entre $%.2f y $%.2f COP.", PRECIO_MIN, PRECIO_MAX));

        this.serial = serial;
        this.marca = marca;
        this.tamaño = tamaño;
        this.precio = precio;
    }

    // Validaciones generales mejoradas
    protected static boolean validarSerialGeneral(String serial) {
        return serial != null && serial.matches("[a-zA-Z0-9\\-]{5,20}"); // Entre 5 y 20 caracteres alfanuméricos o guiones
    }

    protected static boolean validarMarcaGeneral(String marca) {
        return marca != null && marca.matches("[a-zA-Z0-9ÁÉÍÓÚáéíóúñÑ\\s\\-.,®™]{2,50}"); // Entre 2 y 50 caracteres
    }

    protected static boolean validarTamaño(float tamaño) {
        return tamaño >= TAMAÑO_MIN && tamaño <= TAMAÑO_MAX;
    }

    protected static boolean validarPrecio(float precio) {
        return precio >= PRECIO_MIN && precio <= PRECIO_MAX;
    }

    // Getters (Serial no debe tener setter según requisitos)
    public String getSerial() { return serial; }
    public String getMarca() { return marca; }
    public float getTamaño() { return tamaño; }
    public float getPrecio() { return precio; }

    // Setters para campos modificables con validaciones
    public void setMarca(String marca) {
        if (!validarMarcaGeneral(marca)) 
            throw new IllegalArgumentException("Marca inválida: solo letras, números, espacios y algunos caracteres especiales como ® ™.");
        this.marca = marca;
    }

    public void setTamaño(float tamaño) {
        if (!validarTamaño(tamaño))
            throw new IllegalArgumentException(String.format("Tamaño debe estar entre %.1f y %.1f pulgadas.", TAMAÑO_MIN, TAMAÑO_MAX));
        this.tamaño = tamaño;
    }

    public void setPrecio(float precio) {
        if (!validarPrecio(precio))
            throw new IllegalArgumentException(String.format("Precio debe estar entre $%.2f y $%.2f COP.", PRECIO_MIN, PRECIO_MAX));
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Serial: " + serial + ", Marca: " + marca + ", Tamaño: " + String.format("%.1f", tamaño) + " pulgadas, Precio: $" + String.format("%,.2f", precio) + " COP";
    }
} 