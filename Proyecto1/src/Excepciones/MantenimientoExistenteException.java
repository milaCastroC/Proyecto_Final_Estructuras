
package Excepciones;

public class MantenimientoExistenteException extends Exception{

    public MantenimientoExistenteException() {
        super("Ya hay un matenimiento existente");
    }
    
}
