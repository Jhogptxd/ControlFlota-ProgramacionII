package negocio;

import modelo.Bus;
import modelo.Mantenimiento;

import java.time.LocalDate;

// Modulo 3: Mantenimientos
public class GestorMantenimientos {

    public static final int KM_ENTRE_MANTENIMIENTOS = 10000;
    public static final int KM_AVISO = 1000;

    private GestorBuses gestorBuses;

    public GestorMantenimientos(GestorBuses gestorBuses) {
        this.gestorBuses = gestorBuses;
    }

    // RF-3.1 Registrar mantenimiento
    public void registrarMantenimiento(String placa, LocalDate fecha, int kilometraje,
                                       String tipo, double costo) {
        Bus bus = gestorBuses.buscarBus(placa);
        if (bus == null) {
            throw new IllegalArgumentException("No existe un bus con esa placa.");
        }
        if (fecha == null || fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura.");
        }
        if (kilometraje > bus.getKilometraje()) {
            throw new IllegalArgumentException("El kilometraje no puede ser mayor al actual del bus ("
                    + bus.getKilometraje() + " km).");
        }
        if (!bus.getMantenimientos().isEmpty()) {
            Mantenimiento ultimo = bus.getMantenimientos().get(bus.getMantenimientos().size() - 1);
            if (kilometraje < ultimo.getKilometraje()) {
                throw new IllegalArgumentException("El kilometraje no puede ser menor al del último mantenimiento ("
                        + ultimo.getKilometraje() + " km).");
            }
        }
        if (tipo == null || !(tipo.equalsIgnoreCase("Preventivo") || tipo.equalsIgnoreCase("Correctivo"))) {
            throw new IllegalArgumentException("El tipo debe ser Preventivo o Correctivo.");
        }
        if (costo <= 0) {
            throw new IllegalArgumentException("El costo debe ser mayor a cero.");
        }
        String tipoFinal = tipo.equalsIgnoreCase("Preventivo") ? "Preventivo" : "Correctivo";
        bus.agregarMantenimiento(new Mantenimiento(fecha, kilometraje, tipoFinal, costo));
        if (tipoFinal.equals("Preventivo")) {
            bus.setKmUltimoMantenimiento(kilometraje);
        }
        bus.setEnTaller(false); // al registrar el mantenimiento el bus sale del taller
    }

    // RF-3.2 Calcular proximo mantenimiento
    public static int calcularProximoMantenimiento(Bus bus) {
        return bus.getKmUltimoMantenimiento() + KM_ENTRE_MANTENIMIENTOS;
    }

    public static boolean mantenimientoVencido(Bus bus) {
        return bus.getKilometraje() >= calcularProximoMantenimiento(bus);
    }

    public String generarAviso(String placa) {
        Bus bus = gestorBuses.buscarBus(placa);
        if (bus == null) {
            throw new IllegalArgumentException("No existe un bus con esa placa.");
        }
        int proximo = calcularProximoMantenimiento(bus);
        int faltan = proximo - bus.getKilometraje();
        if (faltan <= 0) {
            return "VENCIDO: tocaba a los " + proximo + " km y el bus tiene " + bus.getKilometraje()
                    + " km. El bus queda No apto.";
        }
        if (faltan <= KM_AVISO) {
            return "PRÓXIMO: el mantenimiento toca a los " + proximo + " km. Faltan " + faltan + " km.";
        }
        return "Al día: el próximo mantenimiento es a los " + proximo + " km. Faltan " + faltan + " km.";
    }
}
