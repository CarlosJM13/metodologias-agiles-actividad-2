import java.util.UUID;

public class Ticket {
    private String idCompra;
    private String nombreSala;
    private char fila;
    private String asientos;

    public Ticket(String nombreSala, char fila, String asientos) {
        // Genera un ID unico y toma los primeros 8 caracteres para que sea visible en consola
        this.idCompra = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.nombreSala = nombreSala;
        this.fila = fila;
        this.asientos = asientos;
    }

    // Getters para acceder a la informacion
    public String getIdCompra() { return idCompra; }
    public String getNombreSala() { return nombreSala; }
    public char getFila() { return fila; }
    public String getAsientos() { return asientos; }
}