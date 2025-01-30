
package Excepciones;

public class ReservaExistenteException extends Exception{

    public ReservaExistenteException() {
        super("Ya hay una reserva");
    }
    
    
}
