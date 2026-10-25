import java.util.ArrayList;
import java.util.List;

public class ConfiguracionInicialDeLaSala {

    private final LectorConsola lector = new LectorConsola();
    private final VistaSala vista = new VistaSala();

    private List<Sala> salas = new ArrayList<>();

    // Lista para los tickets generados en el sistema
    private List<Ticket> registroTickets = new ArrayList<>();

    // Metodo que se llama desde el main
    public void iniciar() {
        System.out.println("=== CONFIGURACIÓN INICIAL DEL CINE ===");

        //Preguntar cuantas salas se van a administrar
        int totalSalas = lector.pedirNumero("¿Cuántas salas administrarás hoy? (máximo 10): ", 10,
                "Cantidad inválida. Ingrese un valor entre 1 y 10.");

        // ciclo para pedir dimensiones y crear cada sala por separado
        for (int i = 1; i <= totalSalas; i++) {
            System.out.println("\n--- Definiendo dimensiones para la Sala " + i + " ---");
            int filas = lector.pedirNumero("¿Cuántas filas tendrá la sala? (máximo 26): ", 26,
                    "Dimensiones inválidas. Ingrese números enteros mayores a cero (máximo 26 filas).");
            int columnas = lector.pedirNumero("¿Cuántos asientos por fila tendrá la sala?: ", Integer.MAX_VALUE,
                    "Dimensiones inválidas. Ingrese números enteros mayores a cero.");

            // constructor para las salas y listas
            Sala nuevaSala = new Sala(i, filas, columnas);
            salas.add(nuevaSala); // Guardamos la sala en nuestra lista
        }

        int opcion = 0;
        while (opcion != 3) {
            vista.mostrarMenu();
            opcion = lector.pedirNumero("Elija una opción: ", 3, "Opción inválida. Elija 1, 2 o 3.");

            if (opcion == 3) {
                break;
            }

            // menu para seleccionar la sala antes de continuar
            System.out.println("\n-- SALAS DISPONIBLES --");
            for (Sala s : salas) {
                System.out.println("Sala " + s.getNumeroSala());
            }
            int idSalaElegida = lector.pedirNumero("Selecciona el número de la sala: ", totalSalas, "Sala inválida. Elija una sala existente.");

            Sala salaActual = salas.get(idSalaElegida - 1);

            if (opcion == 1) {
                // Le pasamos la sala actual a la compra
                seleccionarAsiento(salaActual);
            } else if (opcion == 2) {
                // pasamos la sala actual a la vista
                vista.mostrarSala(salaActual);
            }
        }
        System.out.println("¡Vuelve pronto a JoPo's Juan cinema!");
    }

    private void seleccionarAsiento(Sala sala) {
        if (sala.estaLlena()) {
            System.out.println("La sala está llena, no quedan asientos disponibles.");
            return;
        }
        int disponibles = sala.cantidadAsientosLibres();

        vista.mostrarSala(sala);
        System.out.println("Asientos disponibles: " + disponibles);
        System.out.println("Escriba 0 para cancelar.");

        int cantidad = lector.pedirNumeroCancelable("¿Cuántos asientos desea comprar?: ", disponibles, "Cantidad inválida. Ingrese una cantidad entre 1 y " + disponibles + ".");
        if (cantidad == 0) {
            System.out.println("Compra cancelada.");
            return;
        }

        List<Asiento> seleccionados = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            while (true) {
                System.out.println("\nSeleccione el asiento " + (i + 1) + " de " + cantidad);
                int fila = lector.pedirFilaCancelable(sala.getFilas());
                if (fila == -1) {
                    System.out.println("Compra cancelada. No se reservó ningún asiento.");
                    return;
                }

                int numero = lector.pedirNumeroCancelable("Número de asiento (0 para cancelar): ", sala.getColumnas(), "Número inválido. Ingrese un número entre 1 y " + sala.getColumnas() + ".");
                if (numero == 0) {
                    System.out.println("Compra cancelada. No se reservó ningún asiento.");
                    return;
                }

                Asiento asiento = sala.buscarAsiento(fila, numero);
                if (asiento.isOcupado()) {
                    System.out.println("El asiento " + asiento.getId() + " ya está ocupado. Elija otro.");
                } else if (seleccionados.contains(asiento)) {
                    System.out.println("El asiento " + asiento.getId() + " ya fue seleccionado. Elija otro.");
                } else {
                    seleccionados.add(asiento);
                    System.out.println("Asiento " + asiento.getId() + " seleccionado.");
                    break;
                }
            }
        }

        // Marcar los asientos seleccionados como ocupados y generar el ticket
        for (Asiento asiento : seleccionados) {
            asiento.setOcupado(true);
            System.out.println("Asiento reservado: " + asiento.getId() + " en Sala " + sala.getNumeroSala());
            Ticket nuevoTicket = new Ticket("Sala " + sala.getNumeroSala(), asiento.getFila(), String.valueOf(asiento.getNumero()));
            registroTickets.add(nuevoTicket);
            vista.imprimirTicket(nuevoTicket);
        }
        vista.mostrarSala(sala);
    }
}