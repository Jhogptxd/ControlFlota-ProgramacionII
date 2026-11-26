package interfaz;

import modelo.Bus;
import modelo.Mantenimiento;
import negocio.GestorBuses;
import negocio.GestorMantenimientos;

import java.time.LocalDate;

// Menu del modulo de mantenimientos
public class MenuMantenimientos {

    private GestorMantenimientos gestor;
    private GestorBuses gestorBuses;
    private Lectura lectura;

    public MenuMantenimientos(GestorMantenimientos gestor, GestorBuses gestorBuses, Lectura lectura) {
        this.gestor = gestor;
        this.gestorBuses = gestorBuses;
        this.lectura = lectura;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- MANTENIMIENTOS ---");
            System.out.println("1. Registrar mantenimiento");
            System.out.println("2. Calcular próximo mantenimiento");
            System.out.println("3. Ver historial de un bus");
            System.out.println("0. Volver");
            opcion = lectura.leerEntero("Opción: ");
            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    calcularProximo();
                    break;
                case 3:
                    verHistorial();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrar() {
        try {
            String placa = lectura.leerTexto("Placa del bus: ");
            LocalDate fecha = lectura.leerFecha("Fecha del mantenimiento");
            int km = lectura.leerEntero("Kilometraje: ");
            String tipo = lectura.leerTexto("Tipo (Preventivo/Correctivo): ");
            double costo = lectura.leerDecimal("Costo: $");
            gestor.registrarMantenimiento(placa, fecha, km, tipo, costo);
            System.out.println("Mantenimiento registrado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void calcularProximo() {
        try {
            System.out.println(gestor.generarAviso(lectura.leerTexto("Placa del bus: ")));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void verHistorial() {
        Bus bus = gestorBuses.buscarBus(lectura.leerTexto("Placa del bus: "));
        if (bus == null) {
            System.out.println("Error: no existe un bus con esa placa.");
            return;
        }
        if (bus.getMantenimientos().isEmpty()) {
            System.out.println("El bus no tiene mantenimientos registrados.");
            return;
        }
        for (Mantenimiento m : bus.getMantenimientos()) {
            System.out.println(m);
        }
    }
}
