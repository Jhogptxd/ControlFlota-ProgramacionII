package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Mantenimiento que se le hace a un bus
public class Mantenimiento {

    private LocalDate fecha;
    private int kilometraje;
    private String tipo; // Preventivo o Correctivo
    private double costo;

    public Mantenimiento(LocalDate fecha, int kilometraje, String tipo, double costo) {
        this.fecha = fecha;
        this.kilometraje = kilometraje;
        this.tipo = tipo;
        this.costo = costo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | " + kilometraje + " km | " + tipo + " | $" + String.format("%.2f", costo);
    }
}
