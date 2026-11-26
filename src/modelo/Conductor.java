package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Conductor de la cooperativa (hereda de Persona)
public class Conductor extends Persona {

    private String tipoLicencia;
    private LocalDate fechaCaducidadLicencia;
    private LocalDateTime ultimaLlegada; // fecha y hora en que termino su ultimo viaje

    public Conductor(String cedula, String nombres, String telefono,
                     String tipoLicencia, LocalDate fechaCaducidadLicencia) {
        super(cedula, nombres, telefono);
        this.tipoLicencia = tipoLicencia;
        this.fechaCaducidadLicencia = fechaCaducidadLicencia;
        this.ultimaLlegada = null;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public LocalDate getFechaCaducidadLicencia() {
        return fechaCaducidadLicencia;
    }

    public void setFechaCaducidadLicencia(LocalDate fechaCaducidadLicencia) {
        this.fechaCaducidadLicencia = fechaCaducidadLicencia;
    }

    public LocalDateTime getUltimaLlegada() {
        return ultimaLlegada;
    }

    public void setUltimaLlegada(LocalDateTime ultimaLlegada) {
        this.ultimaLlegada = ultimaLlegada;
    }

    @Override
    public String toString() {
        return getCedula() + " | " + getNombres() + " | Tel: " + getTelefono()
                + " | Licencia " + tipoLicencia + " (caduca: " + fechaCaducidadLicencia.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }
}
