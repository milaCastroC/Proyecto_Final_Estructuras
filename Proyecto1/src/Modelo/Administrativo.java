
package Modelo;

import java.time.LocalDate;

public class Administrativo extends Persona {
    
    public Administrativo(String Nombre, String id, String telefono, LocalDate fechaNacimiento, String nombreUsuario, String contrasena, String rol) {
        super(Nombre, id, telefono, fechaNacimiento, nombreUsuario, contrasena, rol);
    }
    
}
