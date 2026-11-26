package negocio;

import modelo.Bus;
import modelo.Conductor;
import modelo.Viaje;

import java.time.LocalDateTime;
import java.util.ArrayList;

// Modulo 4: Viajes
public class GestorViajes {

    private ArrayList<Viaje> viajes;
    private int siguienteCodigo;
    private GestorBuses gestorBuses;
    private GestorConductores gestorConductores;

    public GestorViajes(GestorBuses gestorBuses, GestorConductores gestorConductores) {
        this.viajes = new ArrayList<>();
        this.siguienteCodigo = 1;
        this.gestorBuses = gestorBuses;
        this.gestorConductores = gestorConductores;
    }

    // RF-4.1 Programar viaje
    public Viaje programarViaje(String origen, String destino, LocalDateTime fechaHoraSalida,
                                String placa, String cedula) {
        if (origen == null || origen.trim().isEmpty() || destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El origen y el destino son obligatorios.");
        }
        if (origen.trim().equalsIgnoreCase(destino.trim())) {
            throw new IllegalArgumentException("El origen y el destino no pueden ser iguales.");
        }
        if (fechaHoraSalida == null || fechaHoraSalida.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de salida no puede ser una fecha pasada.");
        }
        Bus bus = gestorBuses.buscarBus(placa);
        if (bus == null) {
            throw new IllegalArgumentException("No existe un bus con esa placa.");
        }
        Conductor conductor = gestorConductores.buscarConductor(cedula);
        if (conductor == null) {
            throw new IllegalArgumentException("No existe un conductor con esa cédula.");
        }
        String estadoBus = gestorBuses.determinarEstado(bus);
        if (!estadoBus.equals("Disponible")) {
            throw new IllegalArgumentException("El bus no está disponible (" + estadoBus + "): "
                    + gestorBuses.motivoEstado(bus));
        }
        String habilitacion = gestorConductores.verificarHabilitacion(conductor, fechaHoraSalida);
        if (!habilitacion.equals("Habilitado")) {
            throw new IllegalArgumentException("El conductor no puede manejar. " + habilitacion);
        }
        Viaje viaje = new Viaje(siguienteCodigo, origen.trim(), destino.trim(), fechaHoraSalida, bus, conductor);
        siguienteCodigo++;
        viajes.add(viaje);
        return viaje;
    }

    // RF-4.2 Finalizar viaje
    public void finalizarViaje(int codigo, LocalDateTime fechaHoraLlegada, int kmRecorridos) {
        Viaje viaje = buscarViaje(codigo);
        if (viaje == null) {
            throw new IllegalArgumentException("No existe un viaje con ese código.");
        }
        if (!viaje.getEstado().equals("Programado")) {
            throw new IllegalArgumentException("El viaje ya está finalizado.");
        }
        if (fechaHoraLlegada == null || !fechaHoraLlegada.isAfter(viaje.getFechaHoraSalida())) {
            throw new IllegalArgumentException("La llegada debe ser después de la salida.");
        }
        if (kmRecorridos <= 0) {
            throw new IllegalArgumentException("Los kilómetros recorridos deben ser mayores a cero.");
        }
        viaje.setFechaHoraLlegada(fechaHoraLlegada);
        viaje.setKmRecorridos(kmRecorridos);
        viaje.setEstado("Finalizado");
        viaje.getBus().sumarKilometros(kmRecorridos);
        viaje.getConductor().setUltimaLlegada(fechaHoraLlegada);
    }

    public Viaje buscarViaje(int codigo) {
        for (Viaje v : viajes) {
            if (v.getCodigo() == codigo) {
                return v;
            }
        }
        return null;
    }

    public ArrayList<Viaje> getViajes() {
        return viajes;
    }
}
