
package Excepciones;

public class HorarioNoDefinidoException extends Exception {

    public HorarioNoDefinidoException() {
        super("El curso aún no tiene horarios definidos");
    }
    
}
