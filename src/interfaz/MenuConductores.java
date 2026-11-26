package interfaz;

import modelo.Conductor;
import negocio.GestorConductores;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Menu del modulo de conductores
public class MenuConductores {

    private GestorConductores gestor;
    private Lectura lectura;

    public MenuConductores(GestorConductores gestor, Lectura lectura) {
        this.gestor = gestor;
        this.lectura = lectura;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- CONDUCTORES ---");
            System.out.println("1. Registrar conductor");
            System.out.println("2. Verificar si un conductor puede manejar");
            System.out.println("3. Listar conductores");
            System.out.println("0. Volver");
            opcion = lectura.leerEntero("Opción: ");
            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    verificar();
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

    private void registrar() {
        try {
            String cedula = lectura.leerTexto("Cédula: ");
            String nombres = lectura.leerTexto("Nombres y apellidos: ");
            String telefono = lectura.leerTexto("Teléfono: ");
            String tipo = lectura.leerTexto("Tipo de licencia: ");
            LocalDate caducidad = lectura.leerFecha("Fecha de caducidad de la licencia");
            gestor.registrarConductor(cedula, nombres, telefono, tipo, caducidad);
            System.out.println("Conductor registrado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void verificar() {
        String cedula = lectura.leerTexto("Cédula del conductor: ");
        Conductor conductor = gestor.buscarConductor(cedula);
        if (conductor == null) {
            System.out.println("Error: no existe un conductor con esa cédula.");
            return;
        }
        LocalDateTime fechaViaje = lectura.leerFechaHora("Fecha y hora del viaje");
        System.out.println("Resultado: " + gestor.verificarHabilitacion(conductor, fechaViaje));
    }

    private void listar() {
        if (gestor.getConductores().isEmpty()) {
            System.out.println("No hay conductores registrados.");
            return;
        }
        for (Conductor c : gestor.getConductores()) {
            System.out.println(c);
        }
    }
}
