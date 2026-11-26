package interfaz;

import modelo.Viaje;
import negocio.GestorViajes;

import java.time.LocalDateTime;

// Menu del modulo de viajes
public class MenuViajes {

    private GestorViajes gestor;
    private Lectura lectura;

    public MenuViajes(GestorViajes gestor, Lectura lectura) {
        this.gestor = gestor;
        this.lectura = lectura;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- VIAJES ---");
            System.out.println("1. Programar viaje");
            System.out.println("2. Finalizar viaje");
            System.out.println("3. Listar viajes");
            System.out.println("0. Volver");
            opcion = lectura.leerEntero("Opción: ");
            switch (opcion) {
                case 1:
                    programar();
                    break;
                case 2:
                    finalizar();
                    break;
                case 3:
                    listar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void programar() {
        try {
            String origen = lectura.leerTexto("Origen: ");
            String destino = lectura.leerTexto("Destino: ");
            LocalDateTime salida = lectura.leerFechaHora("Fecha y hora de salida");
            String placa = lectura.leerTexto("Placa del bus: ");
            String cedula = lectura.leerTexto("Cédula del conductor: ");
            Viaje viaje = gestor.programarViaje(origen, destino, salida, placa, cedula);
            System.out.println("Viaje programado con el código " + viaje.getCodigo() + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void finalizar() {
        try {
            int codigo = lectura.leerEntero("Código del viaje: ");
            LocalDateTime llegada = lectura.leerFechaHora("Fecha y hora de llegada");
            int km = lectura.leerEntero("Kilómetros recorridos: ");
            gestor.finalizarViaje(codigo, llegada, km);
            System.out.println("Viaje finalizado. Se sumaron " + km + " km al bus.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listar() {
        if (gestor.getViajes().isEmpty()) {
            System.out.println("No hay viajes registrados.");
            return;
        }
        for (Viaje v : gestor.getViajes()) {
            System.out.println(v);
        }
    }
}
