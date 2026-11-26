package interfaz;

import modelo.Bus;
import negocio.GestorBuses;

import java.time.LocalDate;

// Menu del modulo de buses
public class MenuBuses {

    private GestorBuses gestor;
    private Lectura lectura;

    public MenuBuses(GestorBuses gestor, Lectura lectura) {
        this.gestor = gestor;
        this.lectura = lectura;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- BUSES ---");
            System.out.println("1. Registrar bus");
            System.out.println("2. Ver estado de un bus");
            System.out.println("3. Listar buses");
            System.out.println("4. Ingresar bus al taller");
            System.out.println("0. Volver");
            opcion = lectura.leerEntero("Opción: ");
            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    verEstado();
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    ingresarTaller();
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
            String placa = lectura.leerTexto("Placa (ABC-1234): ");
            int disco = lectura.leerEntero("Número de disco: ");
            int anio = lectura.leerEntero("Año: ");
            int capacidad = lectura.leerEntero("Capacidad de pasajeros: ");
            int km = lectura.leerEntero("Kilometraje actual: ");
            LocalDate matricula = lectura.leerFecha("Caducidad de la matrícula");
            LocalDate revision = lectura.leerFecha("Caducidad de la revisión técnica");
            gestor.registrarBus(placa, disco, anio, capacidad, km, matricula, revision);
            System.out.println("Bus registrado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void verEstado() {
        Bus bus = gestor.buscarBus(lectura.leerTexto("Placa del bus: "));
        if (bus == null) {
            System.out.println("Error: no existe un bus con esa placa.");
            return;
        }
        String estado = gestor.determinarEstado(bus);
        System.out.println("Estado: " + estado);
        if (!estado.equals("Disponible")) {
            System.out.println("Motivo: " + gestor.motivoEstado(bus));
        }
    }

    private void listar() {
        if (gestor.getBuses().isEmpty()) {
            System.out.println("No hay buses registrados.");
            return;
        }
        for (Bus b : gestor.getBuses()) {
            System.out.println(b + " | " + gestor.determinarEstado(b));
        }
    }

    private void ingresarTaller() {
        try {
            gestor.cambiarEnTaller(lectura.leerTexto("Placa del bus: "), true);
            System.out.println("El bus quedó En mantenimiento.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
