import java.util.Scanner;

// Se encarga de leer y validar los datos que escribe el usuario en la consola
public class LectorConsola {

    private Scanner scanner;

    // metodo para validar que el usuario ingrese un numero dentro de las opciones permitidas
    public int leerEnteroEnRango(String mensaje, int min, int max) {
        int valor = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            // Comprueba si lo que se ingreso es un número entero
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                // Comprueba si el numero esta dentro de las opciones
                if (valor >= min && valor <= max) {
                    valido = true;
                } else {
                    System.out.println("Error: Opcion invalida ingresa un numero entre " + min + " y " + max + ".");
                }
            } else {
                // Si el usuario metio letras o simbolos
                System.out.println("Error: Entrada invalida por favor, ingresa solo numeros.");
                scanner.next();
            }
        }
        return valor;
    }

    private final Scanner sc = new Scanner(System.in);
    // Pide un entero entre 1 y max. Si es inválido, muestra el error y vuelve a pedir
    public int pedirNumero(String mensaje, int max, String error) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();

            try {
                int valor = Integer.parseInt(entrada);
                if (valor > 0 && valor <= max) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // Las letras o vacío o decimales caen aquí
            }
            System.out.println(error);
        }
    }

    // Pide una letra de fila (A, B, C...) y devuelve su posición (0, 1, 2...)
    public int pedirFila(int filas) {
        char ultima = (char) ('A' + filas - 1);
        while (true) {
            System.out.print("¿En qué fila desea estar? (A-" + ultima + "): ");
            String entrada = sc.nextLine().trim().toUpperCase();

            if (entrada.length() == 1 && entrada.charAt(0) >= 'A' && entrada.charAt(0) <= ultima) {
                return entrada.charAt(0) - 'A';
            }
            System.out.println("Fila inválida. Ingrese una letra entre A y " + ultima);
        }
    }
    public int pedirNumeroCancelable(String mensaje, int max, String error) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor == 0) {
                    return 0;
                }
                if (valor > max) {
                    System.out.println("Solo hay " + max + " disponibles. Ingrese una cantidad menor.");
                } else if (valor > 0) {
                    return valor;
                } else {
                    System.out.println(error);
                }

            } catch (NumberFormatException e) {
                // La entrada no es un número válido.
            }
            System.out.println("Escriba 0 para cancelar.");
        }
    }
    public int pedirFilaCancelable(int filas) {
        char ultima = (char) ('A' + filas - 1);
        while (true) {
            System.out.print("¿En qué fila desea estar? (A-" + ultima + ", 0 para cancelar): ");
            String entrada = sc.nextLine().trim().toUpperCase();
            if (entrada.equals("0")) {
                return -1;
            }

            if (entrada.length() == 1 && entrada.charAt(0) >= 'A' && entrada.charAt(0) <= ultima) {
                return entrada.charAt(0) - 'A';
            }

            System.out.println("Fila inválida. Ingrese una letra entre A y " + ultima + ", o 0 para cancelar.");
        }
    }
}
