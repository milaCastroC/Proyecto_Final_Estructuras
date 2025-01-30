
package Excepciones;

public class PrestamoExistenteException extends Exception{

    public PrestamoExistenteException() {
        super("El estudiante ya tiene un prestamo activo");
    }
    
    
}
