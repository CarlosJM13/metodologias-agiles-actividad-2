// Guarda los asientos de la sala y se encarga de generarlos y buscarlos.
public class Sala {

    private final int filas;
    private final int columnas;
    private int numeroSala;
    private final Asiento[][] asientos;

    public Sala(int numeroSala, int filas, int columnas) {
        this.numeroSala = numeroSala;
        this.filas = filas;
        this.columnas = columnas;
        this.asientos = new Asiento[filas][columnas];
        generarAsientos();
    }

    // Crea los asientos automáticamente (A1, A2, ... B1, B2, ...), todos libres
    private void generarAsientos() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                asientos[i][j] = new Asiento((char) ('A' + i), j + 1);
            }
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    // Busca un asiento por posición de fila (0 = A, 1 = B...) y número de asiento (1, 2, 3...)
    public Asiento buscarAsiento(int fila, int numero) {
        return asientos[fila][numero - 1];
    }

    // Revisa si ya no queda ningún asiento libre
    public boolean estaLlena() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!asientos[i][j].isOcupado()) {
                    return false;
                }
            }
        }
        return true;
    }
    //JPC 28
    public int cantidadAsientosLibres() {
        int disponibles = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!asientos[i][j].isOcupado()) {
                    disponibles++;
                }
            }
        }
        return disponibles;
    }
    public int getNumeroSala() {
        return numeroSala;
    }
}
