package utilidades;

import java.util.Scanner;

public class Validaciones {

    private Scanner sc;

    public Validaciones(Scanner sc) {
        this.sc = sc;
    }

    // Método recursivo para leer un entero
    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese un número entero válido.");
            return leerEntero(mensaje); // Llamada recursiva
        }
    }

    // Método recursivo para leer un entero dentro de un rango
    public int leerEntero(String mensaje, int min, int max) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        try {
            int valor = Integer.parseInt(entrada);
            if (valor >= min && valor <= max) {
                return valor;
            } else {
                System.out.println("Entrada inválida. Ingrese un número entre " + min + " y " + max + ".");
                return leerEntero(mensaje, min, max); // Llamada recursiva
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese un número entero válido.");
            return leerEntero(mensaje, min, max); // Llamada recursiva
        }
    }

    // Método recursivo para leer un float
    public float leerFloat(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        try {
            return Float.parseFloat(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese un número decimal válido.");
            return leerFloat(mensaje); // Llamada recursiva
        }
    }
    
    // Método recursivo para leer un float positivo
    public float leerFloatPositivo(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        try {
            float valor = Float.parseFloat(entrada);
            if (valor > 0) {
                return valor;
            } else {
                System.out.println("Entrada inválida. El valor debe ser positivo.");
                return leerFloatPositivo(mensaje);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese un número decimal válido.");
            return leerFloatPositivo(mensaje); // Llamada recursiva
        }
    }

    // Método recursivo para leer texto no vacío
    public String leerTextoNoVacio(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        if (!entrada.isEmpty()) {
            return entrada;
        } else {
            System.out.println("Este campo no puede estar vacío.");
            return leerTextoNoVacio(mensaje); // Llamada recursiva
        }
    }

    // Método recursivo para leer cédula (máximo 10 dígitos numéricos)
    public String leerCedula(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        if (entrada.matches("\\d{1,10}")) {
            return entrada;
        } else {
            System.out.println("Cédula inválida. Debe contener máximo 10 dígitos numéricos.");
            return leerCedula(mensaje);
        }
    }

    // Método recursivo para leer nombre/apellido (solo letras y espacios)
    public String leerNombreApellido(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        if (entrada.matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ ]+") && !entrada.isEmpty()) {
            return entrada;
        } else {
            System.out.println("Entrada inválida. Solo se permiten letras y espacios.");
            return leerNombreApellido(mensaje);
        }
    }

    // Método recursivo para leer teléfono (exactamente 11 dígitos)
    public String leerTelefono(String mensaje) {
        String tipoEstudiante = leerTipoEstudiante("¿El estudiante es regional o extranjero?: ");
        if (tipoEstudiante.equals("regional")) {
            return leerTelefonoRegional("Ingrese el número de teléfono (debe comenzar con 3): ");
        } else {
            String indicativo = leerIndicativoTelefono("Ingrese el indicativo del país (ejemplo: +1, +44): ");
            return indicativo + leerTelefonoExtranjero("Ingrese el número de teléfono: ");
        }
    }

    // Método recursivo para leer serial de equipo (alfanumérico, sin caracteres especiales, permite guiones, 5-20 caracteres)
    public String leerSerialEquipo(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        if (entrada.matches("[a-zA-Z0-9\\-]{5,20}")) {
            return entrada;
        } else {
            System.out.println("Serial inválido. Debe contener letras, números o guiones, longitud entre 5 y 20 caracteres.");
            return leerSerialEquipo(mensaje);
        }
    }
    
    // Método recursivo para leer marca (letras, números, espacios y .,®™-)
    public String leerMarca(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        if (entrada.matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ ]+") && !entrada.isEmpty()) {
            return entrada;
        } else {
            System.out.println("Marca inválida. Solo se permiten letras y espacios.");
            return leerMarca(mensaje);
        }
    }

    // Método recursivo para leer modalidad de estudio (virtual o presencial)
    public String leerModalidadEstudio(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim().toLowerCase();
        if (entrada.equals("virtual") || entrada.equals("presencial")) {
            return entrada;
        } else {
            System.out.println("Modalidad inválida. Debe ser 'virtual' o 'presencial'.");
            return leerModalidadEstudio(mensaje);
        }
    }

    // Método recursivo para leer promedio acumulado (0.0-5.0)
    public float leerPromedioAcumulado(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        try {
            float valor = Float.parseFloat(entrada);
            if (valor >= 0.0f && valor <= 5.0f) {
                return valor;
            } else {
                System.out.println("Promedio inválido. Debe estar entre 0.0 y 5.0.");
                return leerPromedioAcumulado(mensaje);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese un número decimal válido.");
            return leerPromedioAcumulado(mensaje);
        }
    }

    // Método recursivo para leer tipo de estudiante (regional/extranjero)
    public String leerTipoEstudiante(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim().toLowerCase();
        if (entrada.equals("regional") || entrada.equals("extranjero")) {
            return entrada;
        } else {
            System.out.println("Tipo inválido. Debe ser 'regional' o 'extranjero'.");
            return leerTipoEstudiante(mensaje);
        }
    }

    // Método recursivo para leer indicativo telefónico internacional
    public String leerIndicativoTelefono(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        if (entrada.matches("\\+\\d{1,4}")) {
            return entrada;
        } else {
            System.out.println("Indicativo inválido. Debe comenzar con '+' seguido de 1 a 4 dígitos (ejemplo: +57, +1, +44).");
            return leerIndicativoTelefono(mensaje);
        }
    }

    // Método recursivo para leer teléfono regional (Colombia)
    public String leerTelefonoRegional(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        if (entrada.matches("3\\d{9}")) {
            return "+57" + entrada;
        } else {
            System.out.println("Teléfono inválido. Para Colombia debe comenzar con 3 y tener 10 dígitos en total.");
            return leerTelefonoRegional(mensaje);
        }
    }

    // Método recursivo para leer teléfono extranjero
    public String leerTelefonoExtranjero(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        if (entrada.matches("\\d{6,15}")) {
            return entrada;
        } else {
            System.out.println("Teléfono inválido. Debe contener entre 6 y 15 dígitos.");
            return leerTelefonoExtranjero(mensaje);
        }
    }

    // Método recursivo para leer tamaño de equipo (11.0 - 32.0 pulgadas)
    public float leerTamañoEquipo(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        try {
            float valor = Float.parseFloat(entrada);
            if (valor >= 11.0f && valor <= 32.0f) {
                return valor;
            } else {
                System.out.println("Tamaño inválido. Debe estar entre 11.0 y 32.0 pulgadas.");
                return leerTamañoEquipo(mensaje);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese un número decimal válido.");
            return leerTamañoEquipo(mensaje);
        }
    }

    // Método recursivo para leer precio de equipo (100000 - 15000000 COP)
    public float leerPrecioEquipo(String mensaje) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        try {
            float valor = Float.parseFloat(entrada);
            if (valor >= 100000.0f && valor <= 15000000.0f) {
                return valor;
            } else {
                System.out.println("Precio inválido. Debe estar entre $100,000 y $15,000,000 COP.");
                return leerPrecioEquipo(mensaje);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese un número decimal válido.");
            return leerPrecioEquipo(mensaje);
        }
    }
}