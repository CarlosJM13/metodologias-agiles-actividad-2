// Se encarga de mostrar en consola el menú y la sala
public class VistaSala {

    // Colores para la consola
    private static final String ROJO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public void mostrarMenu() {
        System.out.println("=========== Menú de JoPo Cine ===========");
        System.out.println("1. Seleccionar asientos");
        System.out.println("2. Sala");
        System.out.println("3. Salir de JoPo Cine");
    }

    // Dibuja la sala: pantalla arriba, filas con letra y asientos numerados
    public void mostrarSala(Sala sala) {
        int filas = sala.getFilas();
        int columnas = sala.getColumnas();

        int ancho = columnas * 5 + 8;
        String titulo = "P A N T A L L A";
        int espacios = Math.max(0, (ancho - titulo.length()) / 2);

        System.out.println();
        System.out.println(" ".repeat(espacios) + titulo);
        System.out.println("=".repeat(ancho));
        System.out.println();

        for (int i = 0; i < filas; i++) {
            char letra = (char) ('A' + i);
            System.out.print(letra + "   ");

            // El asiento 1 queda a la derecha
            for (int numero = columnas; numero >= 1; numero--) {
                boolean estaOcupado = sala.buscarAsiento(i, numero).isOcupado();
                String color = estaOcupado ? ROJO : VERDE;

                // Si el asiento está ocupado, ponemos X, de lo contrario, ponemos el número
                String textoAsiento = estaOcupado ? "[ X]" : String.format("[%2d]", numero);

                System.out.print(color + textoAsiento + RESET + " ");
            }
            System.out.println("  " + letra);
        }

        System.out.println("\n" + VERDE + "Verde = libre" + RESET + "   " + ROJO + "Rojo = ocupado (X)" + RESET);
    }

    public void imprimirTicket(Ticket ticket) {
        System.out.println("\n=======================================");
        System.out.println("           TICKET DE COMPRA            ");
        System.out.println("=======================================");
        System.out.println("ID de Compra : " + ticket.getIdCompra());
        System.out.println("Sala         : " + ticket.getNombreSala());
        System.out.println("Fila         : " + ticket.getFila());
        System.out.println("Asiento(s)   : " + ticket.getAsientos());
        System.out.println("=======================================\n");
    }
}
