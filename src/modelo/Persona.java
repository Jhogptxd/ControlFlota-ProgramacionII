package modelo;

// Clase padre de las personas del sistema
public abstract class Persona {

    private String cedula;
    private String nombres;
    private String telefono;

    public Persona(String cedula, String nombres, String telefono) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
