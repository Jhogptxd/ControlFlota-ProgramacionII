package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

// Bus de la cooperativa
public class Bus {

    private String placa;
    private int disco;
    private int anio;
    private int capacidad;
    private int kilometraje;
    private LocalDate fechaCaducidadMatricula;
    private LocalDate fechaCaducidadRevision;
    private boolean enTaller;
    private int kmUltimoMantenimiento; // km del ultimo mantenimiento preventivo
    private ArrayList<Mantenimiento> mantenimientos;

    public Bus(String placa, int disco, int anio, int capacidad, int kilometraje,
               LocalDate fechaCaducidadMatricula, LocalDate fechaCaducidadRevision) {
        this.placa = placa;
        this.disco = disco;
        this.anio = anio;
        this.capacidad = capacidad;
        this.kilometraje = kilometraje;
        this.fechaCaducidadMatricula = fechaCaducidadMatricula;
        this.fechaCaducidadRevision = fechaCaducidadRevision;
        this.enTaller = false;
        this.kmUltimoMantenimiento = kilometraje; // se toma como punto de partida
        this.mantenimientos = new ArrayList<>();
    }

    public void agregarMantenimiento(Mantenimiento mantenimiento) {
        mantenimientos.add(mantenimiento);
    }

    public void sumarKilometros(int km) {
        kilometraje = kilometraje + km;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getDisco() {
        return disco;
    }

    public void setDisco(int disco) {
        this.disco = disco;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public LocalDate getFechaCaducidadMatricula() {
        return fechaCaducidadMatricula;
    }

    public void setFechaCaducidadMatricula(LocalDate fechaCaducidadMatricula) {
        this.fechaCaducidadMatricula = fechaCaducidadMatricula;
    }

    public LocalDate getFechaCaducidadRevision() {
        return fechaCaducidadRevision;
    }

    public void setFechaCaducidadRevision(LocalDate fechaCaducidadRevision) {
        this.fechaCaducidadRevision = fechaCaducidadRevision;
    }

    public boolean isEnTaller() {
        return enTaller;
    }

    public void setEnTaller(boolean enTaller) {
        this.enTaller = enTaller;
    }

    public int getKmUltimoMantenimiento() {
        return kmUltimoMantenimiento;
    }

    public void setKmUltimoMantenimiento(int kmUltimoMantenimiento) {
        this.kmUltimoMantenimiento = kmUltimoMantenimiento;
    }

    public ArrayList<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    @Override
    public String toString() {
        return placa + " | Disco " + disco + " | Año " + anio + " | " + capacidad
                + " pasajeros | " + kilometraje + " km";
    }
}
