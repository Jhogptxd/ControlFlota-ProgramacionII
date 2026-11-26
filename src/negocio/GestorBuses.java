package negocio;

import modelo.Bus;

import java.time.LocalDate;
import java.util.ArrayList;

// Modulo 2: Buses
public class GestorBuses {

    private ArrayList<Bus> buses;

    public GestorBuses() {
        buses = new ArrayList<>();
    }

    // RF-2.1 Registrar bus
    public void registrarBus(String placa, int disco, int anio, int capacidad, int kilometraje,
                             LocalDate fechaCaducidadMatricula, LocalDate fechaCaducidadRevision) {
        if (placa == null || !placa.toUpperCase().matches("[A-Z]{3}-\\d{3,4}")) {
            throw new IllegalArgumentException("La placa debe tener el formato ABC-1234.");
        }
        placa = placa.toUpperCase();
        if (buscarBus(placa) != null) {
            throw new IllegalArgumentException("Ya existe un bus con esa placa.");
        }
        if (disco <= 0) {
            throw new IllegalArgumentException("El número de disco debe ser mayor a cero.");
        }
        int anioActual = LocalDate.now().getYear();
        if (anio < 1990 || anio > anioActual + 1) {
            throw new IllegalArgumentException("El año debe estar entre 1990 y " + (anioActual + 1) + ".");
        }
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a cero.");
        }
        if (kilometraje < 0) {
            throw new IllegalArgumentException("El kilometraje no puede ser negativo.");
        }
        if (fechaCaducidadMatricula == null || fechaCaducidadRevision == null) {
            throw new IllegalArgumentException("Las fechas de matrícula y revisión técnica son obligatorias.");
        }
        Bus bus = new Bus(placa, disco, anio, capacidad, kilometraje,
                fechaCaducidadMatricula, fechaCaducidadRevision);
        buses.add(bus);
    }

    // RF-2.2 Determinar estado del bus
    public String determinarEstado(Bus bus) {
        if (bus.isEnTaller()) {
            return "En mantenimiento";
        }
        LocalDate hoy = LocalDate.now();
        if (bus.getFechaCaducidadMatricula().isBefore(hoy)
                || bus.getFechaCaducidadRevision().isBefore(hoy)
                || GestorMantenimientos.mantenimientoVencido(bus)) {
            return "No apto";
        }
        return "Disponible";
    }

    // Explica por que el bus no esta disponible
    public String motivoEstado(Bus bus) {
        String motivo = "";
        LocalDate hoy = LocalDate.now();
        if (bus.isEnTaller()) {
            motivo = motivo + "Está en el taller. ";
        }
        if (bus.getFechaCaducidadMatricula().isBefore(hoy)) {
            motivo = motivo + "Matrícula caducada. ";
        }
        if (bus.getFechaCaducidadRevision().isBefore(hoy)) {
            motivo = motivo + "Revisión técnica caducada. ";
        }
        if (GestorMantenimientos.mantenimientoVencido(bus)) {
            motivo = motivo + "Mantenimiento vencido. ";
        }
        return motivo.trim();
    }

    public void cambiarEnTaller(String placa, boolean enTaller) {
        Bus bus = buscarBus(placa);
        if (bus == null) {
            throw new IllegalArgumentException("No existe un bus con esa placa.");
        }
        bus.setEnTaller(enTaller);
    }

    public Bus buscarBus(String placa) {
        for (Bus b : buses) {
            if (b.getPlaca().equalsIgnoreCase(placa)) {
                return b;
            }
        }
        return null;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }
}
