package negocio;

import modelo.Conductor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

// Modulo 1: Conductores
public class GestorConductores {

    public static final int HORAS_DESCANSO = 8;

    private ArrayList<Conductor> conductores;

    public GestorConductores() {
        conductores = new ArrayList<>();
    }

    // RF-1.1 Registrar conductor
    public void registrarConductor(String cedula, String nombres, String telefono,
                                   String tipoLicencia, LocalDate fechaCaducidadLicencia) {
        if (!validarCedula(cedula)) {
            throw new IllegalArgumentException("La cédula no es válida.");
        }
        if (buscarConductor(cedula) != null) {
            throw new IllegalArgumentException("Ya existe un conductor con esa cédula.");
        }
        if (nombres == null || nombres.trim().isEmpty()) {
            throw new IllegalArgumentException("Los nombres son obligatorios.");
        }
        if (telefono == null || !telefono.matches("\\d{10}")) {
            throw new IllegalArgumentException("El teléfono debe tener 10 números.");
        }
        if (tipoLicencia == null || !tipoLicencia.matches("[A-Ga-g]")) {
            throw new IllegalArgumentException("El tipo de licencia debe ser una letra de la A a la G.");
        }
        if (fechaCaducidadLicencia == null) {
            throw new IllegalArgumentException("La fecha de caducidad de la licencia es obligatoria.");
        }
        Conductor conductor = new Conductor(cedula, nombres.trim(), telefono,
                tipoLicencia.toUpperCase(), fechaCaducidadLicencia);
        conductores.add(conductor);
    }

    // RF-1.2 Verificar si el conductor puede manejar
    public String verificarHabilitacion(Conductor conductor, LocalDateTime fechaHoraViaje) {
        if (!conductor.getTipoLicencia().equals("D")) {
            return "No habilitado: su licencia es tipo " + conductor.getTipoLicencia()
                    + " y se necesita tipo D.";
        }
        if (conductor.getFechaCaducidadLicencia().isBefore(fechaHoraViaje.toLocalDate())) {
            return "No habilitado: su licencia caducó el " + conductor.getFechaCaducidadLicencia() + ".";
        }
        if (conductor.getUltimaLlegada() != null) {
            long horas = Duration.between(conductor.getUltimaLlegada(), fechaHoraViaje).toHours();
            if (horas < HORAS_DESCANSO) {
                return "No habilitado: solo ha descansado " + horas + " horas (mínimo "
                        + HORAS_DESCANSO + ").";
            }
        }
        return "Habilitado";
    }

    public boolean estaHabilitado(Conductor conductor, LocalDateTime fechaHoraViaje) {
        return verificarHabilitacion(conductor, fechaHoraViaje).equals("Habilitado");
    }

    public Conductor buscarConductor(String cedula) {
        for (Conductor c : conductores) {
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Conductor> getConductores() {
        return conductores;
    }

    // Validacion de cedula ecuatoriana (digito verificador)
    private boolean validarCedula(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            return false;
        }
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }
        if (cedula.charAt(2) - '0' > 5) {
            return false;
        }
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = cedula.charAt(i) - '0';
            if (i % 2 == 0) {
                digito = digito * 2;
                if (digito > 9) {
                    digito = digito - 9;
                }
            }
            suma = suma + digito;
        }
        int verificador = (10 - suma % 10) % 10;
        return verificador == cedula.charAt(9) - '0';
    }
}
