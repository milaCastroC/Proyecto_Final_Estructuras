
package Excepciones;

public class ReservaInexistenteException extends Exception{

    public ReservaInexistenteException() {
        super("La reserva no ha sido encontrada");
    }
    
    
}
