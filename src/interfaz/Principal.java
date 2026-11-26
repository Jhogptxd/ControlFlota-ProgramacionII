package interfaz;

import negocio.GestorBuses;
import negocio.GestorConductores;
import negocio.GestorMantenimientos;
import negocio.GestorViajes;

// Clase principal: arranca el programa y muestra el menu general
public class Principal {

    public static void main(String[] args) {
        Lectura lectura = new Lectura();
        GestorConductores gestorConductores = new GestorConductores();
        GestorBuses gestorBuses = new GestorBuses();
        GestorMantenimientos gestorMantenimientos = new GestorMantenimientos(gestorBuses);
        GestorViajes gestorViajes = new GestorViajes(gestorBuses, gestorConductores);

        MenuConductores menuConductores = new MenuConductores(gestorConductores, lectura);
        MenuBuses menuBuses = new MenuBuses(gestorBuses, lectura);
        MenuMantenimientos menuMantenimientos = new MenuMantenimientos(gestorMantenimientos, gestorBuses, lectura);
        MenuViajes menuViajes = new MenuViajes(gestorViajes, lectura);

        int opcion;
        do {
            System.out.println("\n===== CONTROL DE FLOTA - COOPERATIVA INTERPROVINCIAL =====");
            System.out.println("1. Conductores");
            System.out.println("2. Buses");
            System.out.println("3. Mantenimientos");
            System.out.println("4. Viajes");
            System.out.println("0. Salir");
            opcion = lectura.leerEntero("Opción: ");
            switch (opcion) {
                case 1:
                    menuConductores.mostrar();
                    break;
                case 2:
                    menuBuses.mostrar();
                    break;
                case 3:
                    menuMantenimientos.mostrar();
                    break;
                case 4:
                    menuViajes.mostrar();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
