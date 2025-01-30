
package Modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
    
    
    private String nombreUsuario;
    private String contrasena;
    private String rol;
    boolean primerInicioSesion;
    
    public static final String ADMINISTRATIVO = "Administrativo";
    public static final String DOCENTE = "Docente";
    public static final String ESTUDIANTE = "Estudiante";
    public static final String ADMINLAB = "Administrador de laboratorio";
    public static final String ADMINGENERAL = "Administrador general";

    public Usuario(String nombreUsuario, String contrasena, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.primerInicioSesion = true;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isPrimerInicioSesion() {
        return primerInicioSesion;
    }

    public void setPrimerInicioSesion(boolean primerInicioSesion) {
        this.primerInicioSesion = primerInicioSesion;
    }
    
}
