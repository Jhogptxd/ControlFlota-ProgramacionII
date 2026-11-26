package interfaz;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// Lee datos por consola y vuelve a pedir si el dato esta mal escrito
public class Lectura {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private Scanner scanner;

    public Lectura() {
        scanner = new Scanner(System.in);
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("  Error: ingrese un número entero.");
            }
        }
    }

    public double leerDecimal(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje).replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("  Error: ingrese un número válido.");
            }
        }
    }

    public LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                return LocalDate.parse(leerTexto(mensaje + " (dd/mm/aaaa): "), FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println("  Error: use el formato dd/mm/aaaa, por ejemplo 25/12/2026.");
            }
        }
    }

    public LocalDateTime leerFechaHora(String mensaje) {
        while (true) {
            try {
                return LocalDateTime.parse(leerTexto(mensaje + " (dd/mm/aaaa hh:mm): "), FORMATO_FECHA_HORA);
            } catch (DateTimeParseException e) {
                System.out.println("  Error: use el formato dd/mm/aaaa hh:mm, por ejemplo 25/12/2026 08:30.");
            }
        }
    }
}
