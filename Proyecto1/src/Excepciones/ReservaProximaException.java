
package Excepciones;

public class ReservaProximaException extends Exception{

    public ReservaProximaException() {
        super("Existe una reserva que comienza en la siguiente hora.");
    }
    
    
}
