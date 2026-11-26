package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Viaje que hace un bus con un conductor
public class Viaje {

    private int codigo;
    private String origen;
    private String destino;
    private LocalDateTime fechaHoraSalida;
    private LocalDateTime fechaHoraLlegada;
    private int kmRecorridos;
    private String estado; // Programado o Finalizado
    private Bus bus;
    private Conductor conductor;

    public Viaje(int codigo, String origen, String destino, LocalDateTime fechaHoraSalida,
                 Bus bus, Conductor conductor) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fechaHoraSalida = fechaHoraSalida;
        this.bus = bus;
        this.conductor = conductor;
        this.estado = "Programado";
        this.kmRecorridos = 0;
        this.fechaHoraLlegada = null;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public LocalDateTime getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public int getKmRecorridos() {
        return kmRecorridos;
    }

    public void setKmRecorridos(int kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Bus getBus() {
        return bus;
    }

    public Conductor getConductor() {
        return conductor;
    }

    @Override
    public String toString() {
        return "Viaje " + codigo + " | " + origen + " - " + destino + " | Salida: " + fechaHoraSalida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + " | Bus " + bus.getPlaca() + " | Conductor: " + conductor.getNombres()
                + " | " + estado;
    }
}
