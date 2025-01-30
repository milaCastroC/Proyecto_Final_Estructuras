
package Modelo;

import Util.IPersona;
import java.time.LocalDate;
import java.time.Period;

public class Persona extends Usuario implements IPersona {
    
    private String nombre;
    private String id;
    private String telefono;
    LocalDate fechaNacimiento;

    public Persona(String Nombre, String id, String telefono, LocalDate fechaNacimiento, String nombreUsuario, String contrasena, String rol) {
        super(nombreUsuario, contrasena, rol);
        this.nombre = Nombre;
        this.id = id;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    @Override
    public int calcularEdad() {
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        return periodo.getYears();
    }
    
}
