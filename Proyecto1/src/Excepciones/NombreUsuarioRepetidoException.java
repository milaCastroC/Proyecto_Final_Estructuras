
package Excepciones;

public class NombreUsuarioRepetidoException extends Exception {

    public NombreUsuarioRepetidoException() {
        super("El nombre de usuario ya existe, elija otro por favor");
    }
    
    
}
